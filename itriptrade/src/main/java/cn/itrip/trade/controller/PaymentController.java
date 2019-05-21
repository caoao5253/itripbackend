package cn.itrip.trade.controller;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.common.EmptyUtils;
import cn.itrip.trade.config.AlipayConfig;
import cn.itrip.trade.service.hotelorder.ItripHotelOrderService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/api/alipay")
public class PaymentController {
    private Logger logger = Logger.getLogger(PaymentController.class);

    @Resource(name = "itripHotelOrderService")
    private ItripHotelOrderService orderService;

    /**
     * @return
     * @确认订单进入支付页面，
     * @使用Rest风格
     * @如/pay/affirm/数据
     * @通过PathVariable去接收传递的Rest风格传递的数据
     * @ModelMap可以存储很多的数据，如多个list列表集合，然后在页面中直接进行key值获取
     * @然后进行遍历list集合
     */
    @RequestMapping(value = "/pay/affirm/{orderNo}")
    public String affirmPay(@PathVariable String orderNo, ModelMap modelMap,HttpServletRequest request) {
        ItripHotelOrder itripHotelOrder = orderService.selectCondition(orderNo);
        try {
            if (EmptyUtils.isNotEmpty(itripHotelOrder)) {
                //酒店名称
                request.setAttribute("hotelName", itripHotelOrder.getHotelname());
                logger.info("酒店名称:"+itripHotelOrder.getHotelname());
                //房间id
                request.setAttribute("roomid", itripHotelOrder.getRoomid());
                logger.info("房间id:"+itripHotelOrder.getRoomid());
                //消耗数量
                request.setAttribute("count", itripHotelOrder.getCount());
                logger.info("消耗数量:"+itripHotelOrder.getCount());
                //订单编号
                request.setAttribute("WIDout_trade_no", itripHotelOrder.getOrderno());
                logger.info("订单编号:"+itripHotelOrder.getOrderno());
                //订单名称 =酒店名称+房间id
                request.setAttribute("WIDsubject", itripHotelOrder.getHotelname() + "-" + itripHotelOrder.getRoomid());
                logger.info("订单名称:"+itripHotelOrder.getHotelname() + "-" + itripHotelOrder.getRoomid());
                //付款金额
                request.setAttribute("WIDtotal_amount", itripHotelOrder.getPayamount());
                logger.info("付款金额:"+itripHotelOrder.getPayamount());
                //商品描述
                request.setAttribute("WIDbody",itripHotelOrder.getHotelname()+"-"+itripHotelOrder.getRoomid()+"房间-"+itripHotelOrder.getCount()+"间房");
                logger.info("商品描述:"+itripHotelOrder.getHotelname()+"-"+itripHotelOrder.getRoomid()+"房间-"+itripHotelOrder.getCount()+"间房");
                return "alipay.trade.page.pay";//支付页面
            } else {
                return "notfound";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "alipay.trade.page.pay";
        }
    }

    /**
     * 异步通知，跟踪支付状态变更
     * 【代码参考alipay.trade.wap.pay-java-utf-8\WebContent\notify_url.jsp页面中小脚本的java代码】
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void trackPaymentStatus(HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            // 获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }
            /**
             *  获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
             *  1.商户订单号
             */
            String out_trade_no = new String(request.getParameter("out_trade_no")
                    .getBytes("ISO-8859-1"), "UTF-8");
            /**
             * 2.支付宝交易号，交易流水号
             */
            String trade_no = new String(request.getParameter("trade_no").getBytes(
                    "ISO-8859-1"), "UTF-8");

            /**
             * WAIT_BUYER_PAY   交易创建，等待买家付款
             * TRADE_CLOSED	    未付款交易超时关闭，或支付完成后全额退款
             * TRADE_SUCCESS	交易支付成功
             * TRADE_FINISHED	交易结束，不可退款
             * 3.交易状态
             */
            String trade_status = new String(request.getParameter("trade_status")
                    .getBytes("ISO-8859-1"), "UTF-8");


            // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
            // 计算得出通知验证结果
             /**
              * boolean类型: AlipaySignature.rsaCheckV1(Map<String, String> params, String
              *publicKey, String charset, String sign_type)
              * publicKey:  支付宝公钥
              * charset:    编码
              * sign_type： 签名方式,签名类型
              * */
            boolean verify_result = AlipaySignature.rsaCheckV1(params,
                    AlipayConfig.alipay_public_key, AlipayConfig.charset, "RSA2");
            // 验证成功
            if (verify_result) {
                /**
                 * 请在这里加上商户的业务逻辑程序代码
                 * 即时到账普通版，那么这时的交易状态值为：TRADE_FINISHED；
                 * 如果是即时到账高级版，此时的交易状态值就为：TRADE_SUCCESS
                 * 收到TRADE_FINISHED请求后,这笔订单就结束了,支付宝不会再主动请求商户网站了;(交易结束，不可退款)
                 * 收到TRADE_SUCCESS请求后,后续一定还有至少一条通知记录,即TRADE_FINISHED.(交易支付成功)
                  */
                //TRADE_FINISHED:(交易结束，不可退款) 高级版
                if (trade_status.equals("TRADE_FINISHED")) {
                    /*
                    判断该笔订单是否在商户网站中已经做过处理,即已支付
                    1.根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    2.请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    如果有做过处理，不执行商户的业务程序
                    */
                    if (!orderService.processed(out_trade_no)) {
                        orderService.paySuccess(out_trade_no, 1, trade_no);
                    }
                    logger.info("订单：" + out_trade_no + " 交易完成");
                    // 注意：
                    // 如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    // 如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                    //(交易支付成功) 普通版
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    // 如果有做过处理，不执行商户的业务程序
                    if (!orderService.processed(out_trade_no)) {
                        orderService.paySuccess(out_trade_no, 1, trade_no);
                    }
                    logger.info("订单：" + out_trade_no + " 交易成功");

                    /**
                     * 注意：
                     * 如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                     * */
                }
                response.getWriter().println("success"); // 请不要修改或删除
                // ////////////////////////////////////////////////////////////////////////////////////////
            } else {// 验证失败,即支付失败

                /**
                 * 支付失败
                 * @param orderNo 订单编号
                 * @param payType 支付方式:1:支付宝 2:微信 3:到店付
                 * @param tradeNo 支付平台返回的交易码
                 * @throws Exception
                 */
                //传递商品订单号,支付方式：1.支付宝 ,支付宝交易号，支付宝返回的交易码
                orderService.payFailed(out_trade_no, 1, trade_no);
                //直接在页面上输出内容
                response.getWriter().println("fail");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * 支付宝页面跳转同步通知页面
     * 【代码参考alipay.trade.wap.pay-java-utf-8\WebContent\return_url.jsp页面中小脚本的java代码】
     prepay     */
    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public void callback(HttpServletRequest request,
                         HttpServletResponse response) {
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            //通过迭代器循环遍历上述Map集合对象中支付宝返回的12个信息
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, "RSA2");
            if (verify_result) {//验证成功
                //提示支付成功，跳转到支付成功页面
                response.sendRedirect("http://itripDebug.vipgz1.idcfengye.com");
            } else {
                //提示支付失败，跳转到支付失败页面
                response.sendRedirect("Failure");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());

        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    /**
     * @return
     * @跳转支付二维码页面
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String pay(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "alipay.trade.page.pay";
    }

    //支付成功跳转页面
    @RequestMapping("/paymentSucc")
    public String paymentSucc() {
        return "success";
    }

    //支付失败跳转页面
    @RequestMapping("/paymentFail")
    public String paymentFail() {
        return "fail";
    }


    /**
     * 查询订单
     *
     * @return
     */
    @RequestMapping("/query")
    public String bb() {
        return "alipay.trade.query";
    }

    @RequestMapping("/error")
    public String ss() {
        return "notify_url";
    }

    @RequestMapping("/retu_url")
    public String dda() {
        return "return_url";
    }
}
