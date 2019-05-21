package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.hotel.ItripHotelService;
import cn.itrip.service.image.ItripImageService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
/**
 * 协议集描述
 */
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotel")
public class HotelController {
    private Logger logger = Logger.getLogger(HotelController.class);
    @Resource(name = "itripAreaDicService")
    private ItripAreaDicService itripAreaDicService;   //区域字典表接口
    @Resource(name = "itripHotelService")
    private ItripHotelService itripHotelService;       //酒店表接口
    @Resource
    private ItripLabelDicService itripLabelDicService; //通用字典表接口
    @Resource
    private ItripImageService itripImageService;//图片接口

    /****
     * 查询热门城市的接口
     * @return返回热门城市id与热门城市名称，我们将这两个值封装到了ItripAreaDicVO类中。
     */
    @ApiOperation(value = "查询热门城市", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotcity/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryHotCity(@PathVariable Integer type) {
        List<ItripAreaDic> itripAreaDics = null;//区域字典表
        List<ItripAreaDicVO> itripAreaDicVOS = null;
        if (EmptyUtils.isNotEmpty(type)) {
            Map param = new HashMap();
            param.put("ishot", 1);//热门城市 1.是 0.否(第一次点击酒店默认查询热门城市)
            param.put("ischina", type);//1.国内 2.国外
            itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(param);
            if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                itripAreaDicVOS = new ArrayList<>();
                for (ItripAreaDic dics : itripAreaDics) {
                    ItripAreaDicVO vo = new ItripAreaDicVO();
                    /**
                     * 将ItripAreaDic中的城市id与城市名称复制到ItripAreaDicVO对象中。
                     * 第一个参数(ItripAreaDic)比为：a  第二个(ItripAreaDicVO)比为:b
                     * a替换b
                     * 通过反射将一个对象的值赋值个另外一个对象（前提是对象中属性的名字相同）
                     * 发现两个类中的属性相同但是其中一个只有另一个一部分属性
                     * 这个时候只需要获取少量属性的类钟进行返回
                     * 通过这个方法去替换  相同属性得进行补充，A补给B
                     * 就是B中存在与A相同得属性且名字相同，这个时候A将属性值传给B，B就有A得属性值了
                     * */
                    BeanUtils.copyProperties(dics, vo);
                    itripAreaDicVOS.add(vo);
                }
            }
        } else {
            DtoUtil.returnFail("type不能为空", "10201");
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOS);
    }


    /***
     * 查询商圈的接口
     * @param cityId 根据城市编号查询商圈
     */
    @ApiOperation(value = "查询商圈", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据城市查询商圈" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10203 : cityId不能为空 </p>" +
            "<p>10204 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/querytradearea/{cityId}" +
            "", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryTradeArea(@PathVariable Long cityId) {
        List<ItripAreaDic> itripAreaDics = null;  //区域字典表
        List<ItripAreaDicVO> itripAreaDicVOs = null;//冗余区域类 只有id 和区域名
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                Map param = new HashMap();
                //isTradingArea 是否是商圈，1表示是。
                param.put("isTradingArea", 1);
                param.put("parent", cityId);//父级区域ID
                //cityId市区id   商圈id对应区域字典表的parentid
                //查询出这个市区的商圈位置信息，也就是热门酒店存在的地区
                itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(param);
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = new ArrayList();
                    for (ItripAreaDic dic : itripAreaDics) {
                        ItripAreaDicVO vo = new ItripAreaDicVO();
                        //A补B
                        BeanUtils.copyProperties(dic, vo);
                        itripAreaDicVOs.add(vo);
                    }
                }
            } else {
                DtoUtil.returnFail("cityId不能为空", "10203");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10204");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOs);
    }


    @ApiOperation(value = "根据酒店id查询酒店特色、商圈(所在地段)、酒店名称", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100214 : 获取酒店视频文字描述失败 </p>" +
            "<p>100215 : 酒店id不能为空</p>")
    @RequestMapping(value = "/getvideodesc/{hotelId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Dto<Object> getVideoDescByHotelId(@ApiParam(required = true, name = "hotelId", value = "酒店ID")
                                             @PathVariable String hotelId) {
        Dto<Object> dto = new Dto<Object>();
        logger.debug("getVideoDescByHotelId hotelId : " + hotelId);
        if (null != hotelId && !"".equals(hotelId)) {
            HotelVideoDescVO hotelVideoDescVO = null;
            try {
                //酒店详情类 名称,酒店商圈，酒店特色
                hotelVideoDescVO = itripHotelService.getVideoDescByHotelId(Long.valueOf(hotelId));
                dto = DtoUtil.returnSuccess("获取酒店视频文字描述成功", hotelVideoDescVO);
            } catch (Exception e) {
                e.printStackTrace();
                dto = DtoUtil.returnFail("获取酒店视频文字描述失败", "100214");
            }
        } else {
            dto = DtoUtil.returnFail("酒店id不能为空", "100215");
        }
        return dto;
    }


    /***
     * 查询酒店特色列表
     */
    @ApiOperation(value = "查询酒店特色列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" + "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfeature", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryHotelFeature() {
        List<ItripLabelDic> itripLabelDics = null; //通用字典表
        List<ItripLabelDicVO> itripAreaDicVOs = null; //简写的通用字典表（key值，描述）
        try {
            Map param = new HashMap();
            param.put("parentId", 16);//父级id对应酒店表的主键ID
            //查询返回指定酒店的特色
            itripLabelDics = itripLabelDicService.getItripLabelDicListByMap(param);
            if (EmptyUtils.isNotEmpty(itripLabelDics)) {
                itripAreaDicVOs = new ArrayList();
                for (ItripLabelDic dic : itripLabelDics) {
                    ItripLabelDicVO vo = new ItripLabelDicVO();
                    //前付后 补充
                    BeanUtils.copyProperties(dic, vo);
                    itripAreaDicVOs.add(vo);
                }
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOs);
    }


    /***
     * 根据酒店id查询酒店设施 -add by donghai
     */
    @ApiOperation(value = "根据酒店id查询酒店设施", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据酒店id查询酒店设施" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" + "<p>10206: 酒店id不能为空</p>" +
            "<p>10207: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfacilities/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryHotelFacilities(
            @ApiParam(required = true, name = "id", value = "酒店ID")
            @PathVariable Long id) {
        ItripSearchFacilitiesHotelVO itripSearchFacilitiesHotelVO = null;
        try {
            if (EmptyUtils.isNotEmpty(id)) {
                //通过酒店id查询酒店的设施
                itripSearchFacilitiesHotelVO = itripHotelService.getHotelFacilitiesById(id);
                return DtoUtil.returnDataSuccess(itripSearchFacilitiesHotelVO.getFacilities());
            } else {
                return DtoUtil.returnFail("酒店id不能为空", "10206");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常,获取失败", "10207");
        }
    }

    /***
     * 根据酒店id查询酒店政策 -add by donghai
     * itrip_hotel表
     */
    @ApiOperation(value = "根据酒店id查询酒店政策", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据酒店id查询酒店政策" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>10208: 酒店id不能为空</p>" +
            "<p>10209: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelpolicy/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryHotelPolicy(
            @ApiParam(required = true, name = "id", value = "酒店ID")
            @PathVariable Long id) {
        //酒店政策VO类
        ItripSearchPolicyHotelVO itripSearchPolicyHotelVO = null;
       /* if(EmptyUtils.isNotEmpty(id)){

        }*/
        try {
            if (EmptyUtils.isNotEmpty(id)) {
                //通过酒店id查找酒店指定政策
                itripSearchPolicyHotelVO = itripHotelService.getHotelhotelPolicyById(id);
                return DtoUtil.returnDataSuccess(itripSearchPolicyHotelVO.getHotelPolicy());
            } else {
                return DtoUtil.returnFail("酒店id不能为空", "10208");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常,获取失败", "10209");
        }
    }

    /***
     * 根据酒店id查询酒店特色和介绍 -add by donghai
     */
    @ApiOperation(value = "根据酒店id查询酒店特色和介绍", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据酒店id查询酒店特色和介绍" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>10210: 酒店id不能为空</p>" +
            "<p>10211: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhoteldetails/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryHotelDetails(
            @ApiParam(required = true, name = "id", value = "酒店ID")
            @PathVariable Long id) {
        //酒店特色和介绍
        List<ItripSearchDetailsHotelVO> itripSearchDetailsHotelVOList = null;
        try {
            if (EmptyUtils.isNotEmpty(id)) {
                //通过酒店id查询酒店特色和介绍
                itripSearchDetailsHotelVOList = itripHotelService.queryHotelDetailsByhotelId(id);
                return DtoUtil.returnDataSuccess(itripSearchDetailsHotelVOList);
            } else {
                return DtoUtil.returnFail("酒店id不能为空", "10210");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常,获取失败", "10211");
        }
    }


    @ApiOperation(
            value = "根据关联id targetId查询酒店图片(type=0)", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据酒店ID查询酒店图片" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100212 : 获取酒店图片失败 </p>" +
            "<p>100213 : 酒店id不能为空</p>")
    @RequestMapping(value = "/getimg/{targetId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Dto<Object> getImgByTargetId(@ApiParam(required = true, name = "targetId", value = "酒店ID")
                                        @PathVariable String targetId) {
        Dto<Object> dto = new Dto<Object>();
        logger.debug("getImgBytargetId targetId : " + targetId);
        if (null != targetId && !"".equals(targetId)) {
            //图片表的简短类
            List<ItripImageVO> itripImageVOList = null;
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("type", "0"); //图片类型(0:酒店图片1:房间图片2:评论图片)
            param.put("targetId", targetId);  //targetId为酒店id。
            try {
                itripImageVOList = itripImageService.getItripImageListByMap(param);
                dto = DtoUtil.returnSuccess("获取酒店图片成功", itripImageVOList);
            } catch (Exception e) {
                e.printStackTrace();
                dto = DtoUtil.returnFail("获取酒店图片失败", "100212");
            }

        } else {
            dto = DtoUtil.returnFail("酒店id不能为空", "100213");
        }
        return dto;
    }

}