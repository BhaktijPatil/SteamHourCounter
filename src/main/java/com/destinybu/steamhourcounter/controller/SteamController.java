package com.destinybu.steamhourcounter.controller;

import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

@RestController
@RequestMapping("/steam")
public class SteamController {

    @Value("${steam.api.url}")
    private String steamApiUrl;

    @Value("${steam.api.apps.getAppList}")
    private String getAppList;

    @GetMapping("/info")
    public String info() {
        return new String("Steam controller status : UP.");
    }

    @GetMapping("/getAppCount")
    public int getAppCount() {
        RestTemplate restTemplate = new RestTemplate();
        String getSteamAppListUrl = steamApiUrl + getAppList;
        JSONObject appData = restTemplate.getForObject(getSteamAppListUrl, JSONObject.class);
        ArrayList apps = (ArrayList) ((LinkedHashMap) appData.get("applist")).get("apps");

        return apps.size();
    }
}
