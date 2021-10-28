package com.dingtalk.service;


import com.aliyun.dingtalkim_1_0.Client;
import com.aliyun.teaopenapi.models.Config;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallCalluserRequest;
import com.dingtalk.api.request.OapiCallGetuserlistRequest;
import com.dingtalk.api.request.OapiCallSetuserlistRequest;
import com.dingtalk.api.request.OapiSmartworkHrmEmployeeQueryonjobRequest;
import com.dingtalk.api.response.OapiCallCalluserResponse;
import com.dingtalk.api.response.OapiCallGetuserlistResponse;
import com.dingtalk.api.response.OapiCallSetuserlistResponse;
import com.dingtalk.api.response.OapiSmartworkHrmEmployeeQueryonjobResponse;
import com.dingtalk.constant.UrlConstant;
import com.dingtalk.util.AccessTokenUtil;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 主业务功能
 */
@Service
@Slf4j
public class BizManager {

    /**
     * 获取在职员工列表
     *
     * @param corpId
     * @return Result
     */
    public OapiSmartworkHrmEmployeeQueryonjobResponse.PageResult queryOnJobList(String corpId) throws ApiException {
        // 1. 获取access_token
        String accessToken = AccessTokenUtil.getCorpAccessToken(corpId);

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.QUERY_ON_JOB_LIST);
        OapiSmartworkHrmEmployeeQueryonjobRequest req = new OapiSmartworkHrmEmployeeQueryonjobRequest();
        req.setStatusList("3");
        req.setOffset(0L);
        req.setSize(50L);
        OapiSmartworkHrmEmployeeQueryonjobResponse rsp = client.execute(req, accessToken);
        System.out.println(rsp.getBody());
        log.info("queryOnJobList rsp body:{}", rsp.getBody());
        if (rsp.isSuccess()) {
            return rsp.getResult();
        }
        return null;
    }

    /**
     * 查询可发起智能办公电话的员工
     *
     * @param corpId
     * @return Result
     */
    public OapiCallGetuserlistResponse.Result getCallList(String corpId) throws ApiException {
        // 1. 获取access_token
        String accessToken = AccessTokenUtil.getCorpAccessToken(corpId);

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.CALL_GET_USER_LIST);
        OapiCallGetuserlistRequest req = new OapiCallGetuserlistRequest();
        req.setOffset(0L);
        req.setSize(100L);
        OapiCallGetuserlistResponse rsp = client.execute(req, accessToken);
        log.info("getCallList rsp body:{}", rsp.getBody());
        if (rsp.isSuccess()) {
            return rsp.getResult();
        }
        return null;
    }

    /**
     * 设置可发起智能办公电话的员工
     *
     * @param corpId
     * @return Result
     */
    public boolean setCallList(String corpId, String ids) throws ApiException {
        // 1. 获取access_token
        String accessToken = AccessTokenUtil.getCorpAccessToken(corpId);

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.CALL_SET_USER_LIST);
        OapiCallSetuserlistRequest req = new OapiCallSetuserlistRequest();
        req.setStaffIdList(ids);
        OapiCallSetuserlistResponse rsp = client.execute(req, accessToken);
        System.out.println(rsp.getBody());
        log.info("setCallList rsp body:{}", rsp.getBody());
        if (rsp.getErrcode() == 0) {
            return true;
        }
        return false;
    }

}
