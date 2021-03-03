package com.cloud.consumer.feign;

import com.cloud.common.api.Rs;
import com.cloud.common.api.entity.UserInfo;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cloud-provider", path = "/provider")
public interface ProviderFeignClient {

    @RequestMapping("/listInfo")
    Rs<List<UserInfo>> listInfo(@RequestParam("msg") String msg);
}
