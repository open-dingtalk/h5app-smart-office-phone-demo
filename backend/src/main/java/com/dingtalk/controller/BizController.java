package com.dingtalk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiSmartworkHrmEmployeeQueryonjobResponse;
import com.dingtalk.model.RpcServiceResult;
import com.dingtalk.service.BizManager;
import com.dingtalk.service.UserManager;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/biz")
public class BizController {

    @Resource
    private UserManager userManager;

    @Autowired
    private BizManager bizManager;

    @GetMapping("/queryOnJobList")
    public RpcServiceResult queryOnJobList(@RequestParam String corpId) throws ApiException {
        log.info("queryOnJobList: corpId:{}", corpId);
        OapiSmartworkHrmEmployeeQueryonjobResponse.PageResult pageResult = bizManager.queryOnJobList(corpId);
        if(pageResult == null){
            return RpcServiceResult.getFailureResult("-1", "查询出错");
        }
        log.info("queryOnJobList: pageResult:{}", JSON.toJSONString(pageResult));
        List<String> dataList = pageResult.getDataList();
        List<Map<String, Object>> userList = new ArrayList<>(dataList.size());
        for(String userId : dataList){
            String userName = userManager.getUserName(userId, corpId);
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("userName", userName);
            userList.add(map);
        }
        return RpcServiceResult.getSuccessResult(userList);
    }

    @PostMapping("/setCallList")
    public RpcServiceResult setCallList(@RequestBody String param) throws ApiException {
        log.info("setCallList: param:{}", param);
        JSONObject jsonObject = JSONObject.parseObject(param);
        String corpId = jsonObject.getString("corpId");
        String ids = jsonObject.getString("ids");
        boolean b = bizManager.setCallList(corpId, ids);
        if(!b){
            return RpcServiceResult.getFailureResult("-1", "设置出错");
        }
        return RpcServiceResult.getSuccessResult(null);
    }


}
