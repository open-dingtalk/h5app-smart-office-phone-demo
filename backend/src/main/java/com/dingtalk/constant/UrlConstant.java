package com.dingtalk.constant;

/**
 * 钉钉开放接口网关常量
 */
public class UrlConstant {

    /**
     *获取用户在企业内userId的接口URL
     */
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";
    /**
     * 获取授权码的接口 url
     */
    public static final String GET_PERMANENT_CODE_URL = "https://oapi.dingtalk.com/service/get_permanent_code?suite_access_token=SUITE_ACCESS_TOKEN";
    /**
     * 激活企业授权的应用 url
     */
    public static final String ACTIVATE_SUITE_URL = "https://oapi.dingtalk.com/service/activate_suite?suite_access_token=SUITE_ACCESS_TOKEN";

    /**
     * 根据用户id获取用户详情 url
     */
    public static final String USER_GET_URL = "https://oapi.dingtalk.com/topapi/v2/user/get";
    /**
     * 获取可访问企业相关信息的accessToken的URL
     */
    public static final String URL_GET_CORP_TOKEN = "https://oapi.dingtalk.com/service/get_corp_token";
    /**
     * 获取可访问企业相关信息的accessToken的URL
     */
    public static final String SUITE_ACCESS_TOKEN_URL = "https://oapi.dingtalk.com/service/get_suite_token";
    /**
     * 查询可发起智能办公电话的员工
     */
    public static final String CALL_GET_USER_LIST= "https://oapi.dingtalk.com/topapi/call/getuserlist";
    /**
     * 设置可发起智能办公电话的员工
     */
    public static final String CALL_SET_USER_LIST= "https://oapi.dingtalk.com/topapi/call/setuserlist";
    /**
     * 获取在职员工列表
     */
    public static final String QUERY_ON_JOB_LIST= "https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/queryonjob";

}
