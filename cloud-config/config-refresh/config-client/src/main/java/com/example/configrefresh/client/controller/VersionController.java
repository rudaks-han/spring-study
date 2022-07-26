package com.example.configrefresh.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class VersionController {

    @Value("${app.version}")
    public String version;

    @RequestMapping("version")
    public String getVersion() {
        return version;
    }
}
