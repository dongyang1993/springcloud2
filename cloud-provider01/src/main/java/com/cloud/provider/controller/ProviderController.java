package com.cloud.provider.controller;

import com.cloud.common.api.Rs;
import com.cloud.common.api.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @RequestMapping("listInfo")
    public Rs<List<UserInfo>> listInfo(@RequestParam("msg") String msg) {
        log.info("msg:{}", msg);
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("Henry", "123456"));
        list.add(new UserInfo("Dell", "xps7590"));

        return Rs.ok(list, "provider01");
    }
}
