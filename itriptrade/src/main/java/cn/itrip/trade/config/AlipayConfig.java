package cn.itrip.trade.config;

import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.*;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092700608293";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCpMkLwS2QpqEIMG/aBQI7AxKluhdjytcMgOySBHQS48Iy59jFQJ7vLc4edykvCdPkMShlwWmP1gqcVrGfbogQviIQ2USamBgIyRatQ/tn+R+++8R1l05wh74vPNMFomwaA2WEsASjpZKfYKjvavqMNzlRkRyPhx4x7GmeWJJ1j9TcxJx2fJjDdI7PSPYVQPxpZcz0vR0lOyaM6lbI50LMEYsDDDq8KLTFkDV20/od9SIStLFBd9x2AoSd4oTTTzJwt+7y63a8dNMkqybCzCU5RbstD17NVXlmysf0lICpfAU+9X2beubfjWVQNR9Jo+vAlsx+QI+P01K85guHlO0BbAgMBAAECggEAU6mMu/EriAWxEvfIvkTD0cHs3b/FdM43u/pCpK1KpoAbteBspevfykxIJNfg+jiI5XDGf23Su5VtNSRY/ptwYmiop4sdBz81WtJkUdqSxE+h1tK27KDkNUuT4FN/x0/QuWnDyNSR/EZPUG4hxpo5WhxKUoNy1LOtXTnDOtTEFqZbK9uQjhUfxOSE18YAs0gMJyD0XULBEldq5EQ5P7Yxu0yBTaemoc2P1b+UBD/3t1WxduiFGamRA3IBKtaN3xXaYFrNpQw5q6WwflA5IhZXjt4CKKEtglMSKeG7ao/LE/hGdP4oDH0gHF+wvPSZU5O7ys91Q7K/VlVGuxfaUWQUIQKBgQD4OCeJMGxHHA5G/nq9zk6PHshud2nTt7Buia7p7TNLyzzTL6qEDDChBPID2zZgZwmZGnkWpImA4XI30poXRURdpTAOjesRWYZmix3CPZmTxiawNUOmmzdqI+4mJ7BDRDEKnUiBx7zokXEE3LCIuGbHs1BDA4INnY5WBwjrOCg50wKBgQCuf/vdGO/u02ShW72oEnfK3EWhl9CUgFPUBB/q2nexsMO+sn44GCIfUL/rvruv5pFC2FAhdG0h6iDamM5HZZT7oy/1x8CvHQBF7oV+3TZEv7lJF36XxF6sNmYjZO4s8Mq4lBGsPOm4IVNPlGbkQVt9ca5d8D+KRfw5b+qOR1aCWQKBgQDW/uSXun5+1Tk0muU7k/+Nja8zp3DddgR2qgD8E31T0BS+I1JBj8FrJrptRqmBtJ2mpDxvtVJ4dE2XNm5La668ogDPCnOqEm4MUcET5oEocwA9rdUL8PN9luOG3ueYS+9ibztHVfc1Rgyrlm+97GLx5EnH0bkLDAYmU2+RNZH6CwKBgE5taTaq8ZNmYTEAeZIjksGTFJyXDgDgWEzO+cVlISy0pJV9xarSdeubMFb1J4fcWecwL3uJx07Z2qBE01lVd4tNgc22VIyfvTKxDEDDjZOCk/Uc4SG2+VHvNSKtRzrdkk85BWRjECxYlggLS99E3KjZwiOoSfaMk9sAyOvfdispAoGBAMyLsv8bMhO+HcvxRhRvGuG7MCmvmValeOXmU4OAeqUv2jikq3vHaNiGmaP0xJWn2d4N9zZxlA/sQNPFL/nMsK782txHdHwYCuandDdAyEUVH2qSPdtrVCwUI1hYoDBSoymszXiU9tpNeZs6MH420DgZKkhC7iB84LLHsuDg5TSl";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2SxUqQ4R9vJtHIyfQa7RFtPnL3DM7R0prBOwoFm5slwR/honGf8lYKy7+HW65FMeb/k3agcUBfxoCemyrTCAQ57zqlCwUbOc8MhG7sRjq/d7MSnv66YxdFkC0w1yH3IyY76SUhmBMKtJ9SjiE1VWRBU0u36QsyPSATExOJI/e4jRlNRYRRC88ciOPOHuQN+hW1Riuq55tZq1nxs51QsRjmfiLyLO2FxTD2wvAn0S4Ril6iPs9MRBZoGj66hJAMqIBnvw/erFiBBjzRMx7eSHgLfiMKYtxuX61t1HGA0jffD6hy3JSsH30QYJVbMhFKVjnVynMQJxB+X9xUELdQkhWwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://itripDebug.vipgz1.idcfengye.com/trade/api/alipay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://itripDebug.vipgz1.idcfengye.com/trade/api/alipay/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "UTF-8";

    // 支付宝网关  如果是沙箱环境在applipay后加上dev
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志地址
    public static String log_path = "logs/";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

