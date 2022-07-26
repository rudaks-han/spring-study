package com.example.configrefresh.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Value("${version}")
    public String version;

    @RequestMapping("version")
    public String getVersion() {
        return version;
    }
}
