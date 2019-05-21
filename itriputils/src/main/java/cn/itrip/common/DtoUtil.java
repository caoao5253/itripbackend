package cn.itrip.common;

import cn.itrip.beans.dto.Dto;

/**
 * 用于返回Dto的工具类
 * Created by XX on 17-5-8.
 */
public class DtoUtil {

    public static String success="true";//判断系统是否出错做出相应的true或者false的返回，与业务无关，出现的各种异常

    public static String fail="false";

    public static String errorCode="0";//该错误码为自定义，一般0表示无错
    /***
     * 统一返回成功的DTO
     */
    public static Dto returnSuccess(){
        Dto dto=new Dto();
        dto.setSuccess(success);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 带数据
     */
    public static Dto returnSuccess(String message,Object data){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 不带数据
     */
    public static Dto returnSuccess(String message){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 带数据 没有消息
     */
    public static Dto returnDataSuccess(Object data){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return  dto;
    }

    public static Dto returnFail(String message,String errorCode){
        Dto dto=new Dto();
        dto.setSuccess(fail);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return  dto;
    }
}
