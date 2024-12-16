package com.example.hotal.controller;

import com.example.hotal.component.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/webSocket")
public class WebSocketController {

    @Autowired
    WebSocketServer webSocketServer;

    @GetMapping(value = {"/index"})
    public String webSocket() {
        return "webscoket";
    }

    @GetMapping(value = {"/chat"})
    public String chat() {
        return "chat";
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/sendMsg/{sid}/{msg}")
    public Map pushToWeb(@PathVariable String sid, @PathVariable String msg) {
        System.err.println("请求到这里2");
        Map<String, Object> result = new HashMap<>();
        webSocketServer.sendInfo(sid, msg);
        result.put("code", sid);
        result.put("msg", msg);
        return result;
    }
}