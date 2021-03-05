package com.cloud.provider.controller;

import com.cloud.common.api.Rs;
import com.cloud.common.api.entity.UserInfo;
import com.cloud.provider.config.CloudEnv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RefreshScope
@Slf4j
@RestController
@RequestMapping("/userInfo")
public class ProviderController {

    @Value("${spring.application.name}")
    private String name;
    @Autowired
    private CloudEnv cloudEnv;

    @RequestMapping("listInfo")
    public Rs<List<UserInfo>> listInfo(@RequestParam("msg") String msg) {
        log.info("msg:{}", msg);
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("Henry", "123456"));
        list.add(new UserInfo("Dell", "xps7590"));
        log.info("env:{}", name);
        log.info("cloudEnv:{}", cloudEnv.getName());
        return Rs.ok(list, "provider01");
    }
}
