package cn.itrip.search.service;

import cn.itrip.search.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.common.PropertiesUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchHotelService")
public class SearchHotelServiceImpl implements  SearchHotelService {

    public static String URL = PropertiesUtils.get("database.properties", "baseUrl");

    private BaseQuery<ItripHotelVO> itripHotelVOBaseQuery = new BaseQuery(URL);

    /***
     * 根据多个条件查询酒店分页
     */
    @Override
    public Page<ItripHotelVO> searchItripHotelPage(SearchHotelVO vo, Integer pageNo, Integer pageSize) throws Exception {
        //1.创建查询对象 并通过构造参数设置其查询条件
        SolrQuery query = new SolrQuery("*:*");
        StringBuffer tempQuery = new StringBuffer();
        int tempFlag = 0;
        if (EmptyUtils.isNotEmpty(vo)) {//判断搜索对象目的地不为null(必填属性)
            if (EmptyUtils.isNotEmpty(vo.getDestination())){
                tempQuery.append(" destination :" + vo.getDestination());
                tempFlag = 1;
            }
            //判断酒店级别不为空(1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
            if (EmptyUtils.isNotEmpty(vo.getHotelLevel())) {
                //addFilterQuery这是一个并且的方法
                //并且酒店级别为:1~5
                query.addFilterQuery("hotelLevel:" + vo.getHotelLevel() + "");
            }
            //判断关键词是否不为空(非必填)
            if (EmptyUtils.isNotEmpty(vo.getKeywords())) {
                if (tempFlag == 1) {//判断之前的是否有别的条件 比如目的地
                    tempQuery.append(" AND keyword :" + vo.getKeywords());
                } else {//不存在任何条件 则直接做为查询条件
                    tempQuery.append(" keyword :" + vo.getKeywords());
                }
            }
            //判断酒店特色是否不为空((每次只能选一个)，非必填,对应itrip_hotel_feature表的fatureId)
            if (EmptyUtils.isNotEmpty(vo.getFeatureIds())) {
                StringBuffer buffer = new StringBuffer("(");
                int flag = 0;
                //分割为数组,变成对应的酒店特色id数组
                String featureIdArray[] = vo.getFeatureIds().split(",");
                for (String featureId : featureIdArray) {
                    if (flag == 0) {
                        buffer.append(" featureIds:" + "*," + featureId + ",*");
                    } else {
                        buffer.append(" OR featureIds:" + "*," + featureId + ",*");
                    }
                    flag++;
                }
                buffer.append(")");
                //addFilterQuery这是一个并且的方法
                //并且酒店特色id为1个或多个
                query.addFilterQuery(buffer.toString());
            }
            //判断商圈id是否不为null(每次只能选填一个)
            if (EmptyUtils.isNotEmpty(vo.getTradeAreaIds())) {
                StringBuffer buffer = new StringBuffer("(");
                int flag = 0;
                String tradeAreaIdArray[] = vo.getTradeAreaIds().split(",");
                for (String tradeAreaId : tradeAreaIdArray) {
                    if (flag == 0) {
                        buffer.append(" tradingAreaIds:" + "*," + tradeAreaId + ",*");
                    } else {
                        buffer.append(" OR tradingAreaIds:" + "*," + tradeAreaId + ",*");
                    }
                    flag++;
                }
                buffer.append(")");
                //addFilterQuery这是一个并且的方法
                //并且酒店存在哪些商圈中
                query.addFilterQuery(buffer.toString());
            }
            //判断最高价是否不唯null
            if (EmptyUtils.isNotEmpty(vo.getMaxPrice())) {
                //并且酒店所有房间价格~最高价的范围
                query.addFilterQuery("minPrice:" + "[* TO " + vo.getMaxPrice() + "]");
            }
            //判断最低价
            if (EmptyUtils.isNotEmpty(vo.getMinPrice())) {
                //并且酒店所有房间价格~最低价的范围
                query.addFilterQuery("minPrice:" + "[" + vo.getMinPrice() + " TO *]");
            }

            //判断按照某个字段升序,取值为(isOkCount或avgScore或minPrice或hotelLevel)
            if (EmptyUtils.isNotEmpty(vo.getAscSort())) {
                //
                query.addSort(vo.getAscSort(), SolrQuery.ORDER.asc);
            }

            //[非必填] 按照某个字段降序,取值为(isOkCount或avgScore或minPrice或hotelLevel)
            if (EmptyUtils.isNotEmpty(vo.getDescSort())) {
                query.addSort(vo.getDescSort(), SolrQuery.ORDER.desc);
            }
        }
        //判断字符串工具对象是否不为空 不为空直接查询
        if (EmptyUtils.isNotEmpty(tempQuery.toString())) {
            query.setQuery(tempQuery.toString());
        }
        Page<ItripHotelVO> page = itripHotelVOBaseQuery.queryPage(query, pageNo, pageSize, ItripHotelVO.class);
        return page;
    }

    /***
     * 根据热门城市查询酒店
     * @param cityId
     * @param count
     * @return
     * @throws Exception
     */
    @Override
    public List<ItripHotelVO> searchItripHotelListByHotCity(Integer cityId, Integer count) throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        if (EmptyUtils.isNotEmpty(cityId)) {
            query.addFilterQuery("cityId:" + cityId);
        } else {
            return null;
        }
        List<ItripHotelVO> hotelVOList = itripHotelVOBaseQuery.queryList(query, count, ItripHotelVO.class);
        return hotelVOList;
    }
}
