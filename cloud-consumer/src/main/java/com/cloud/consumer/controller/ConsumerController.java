package com.cloud.consumer.controller;

import com.cloud.common.api.Rs;
import com.cloud.common.api.entity.UserInfo;
import com.cloud.consumer.feign.ProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class ConsumerController {
    @Autowired
    private ProviderFeignClient providerFeignClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/listInfo")
    Rs<List<UserInfo>> listInfo(@RequestParam("msg") String msg) {
        return providerFeignClient.listInfo(msg);
    }

}
