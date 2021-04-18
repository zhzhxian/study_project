package com.zzx.rabbitmq.controller;

import com.zzx.rabbitmq.sevice.direct.DirectMessageSendSevice;
import com.zzx.rabbitmq.sevice.fanout.FanoutMessageSendSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebParam;

@RestController
public class MessageTestController {

    @Autowired
    private FanoutMessageSendSevice fanoutMessageSendSevice;
    @Autowired
    private DirectMessageSendSevice directMessageSendSevice;

    @GetMapping("/fanout")
    public void fanout() {
        fanoutMessageSendSevice.senMsg();
    }

    @GetMapping("/direct")
    public Object direct(@RequestParam(value = "routeKey") String routeKey) {
        return directMessageSendSevice.senMsg(routeKey);
    }
    @GetMapping("/direct/ttl")
    public Object direct() {
        return directMessageSendSevice.senTtlMsg("email");
    }
}
