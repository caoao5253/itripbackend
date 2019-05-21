package cn.itrip.search.service;


import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;


public class BaseQuery<T>  {
    private HttpSolrClient httpSolrClient;

    static Logger logger = Logger.getLogger(BaseQuery.class);

    /***
     * 使用URL 初始化 httpSolrClient
     * @param url
     */
    public BaseQuery(String url) {
        httpSolrClient = new HttpSolrClient(url);
        httpSolrClient.setParser(new XMLResponseParser()); // 设置响应解析器
        httpSolrClient.setConnectionTimeout(500); // 建立连接的最长时间
    }

    /***
     * 使用SolrQuery 查询分页数据
     */
    public Page<T> queryPage(SolrQuery query, Integer pageNo, Integer pageSize, Class clazz) throws Exception {
        //设置起始页数
        Integer rows= EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Integer currPage=(EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO - 1 : pageNo - 1);
        Integer start=currPage*rows;
        query.setStart(start);
        //一页显示多少条
        query.setRows(rows);
        QueryResponse queryResponse = httpSolrClient.query(query);
        SolrDocumentList docs = queryResponse.getResults();
        Page<T> page = new Page(currPage+1, query.getRows(), new Long(docs.getNumFound()).intValue());
        List<T> list = queryResponse.getBeans(clazz);
        page.setRows(list);
        return page;
    }


    /**
     * 使用SolrQuery 查询列表数据
     * @param query      slor查询对象
     * @param pageSize   传递过来的总数据量
     * @param clazz
     * @return
     * @throws Exception
     */
    public List<T> queryList(SolrQuery query, Integer pageSize, Class clazz) throws Exception {

        List<T> list = null;
        try {
            //设置起始页数
            query.setStart(0);
            //一页显示多少条   (判断是否存在数据量) 存在查
            query.setRows(EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize);
            QueryResponse queryResponse = httpSolrClient.query(query);
            list = queryResponse.getBeans(clazz);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
