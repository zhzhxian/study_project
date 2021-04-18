package com.zzx.rabbitmq.javarabbitmqproducer;

import com.zzx.rabbitmq.sevice.direct.DirectMessageSendSevice;
import com.zzx.rabbitmq.sevice.fanout.FanoutMessageSendSevice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootTest
class JavaRabbitmqProducerApplicationTests {


    @Autowired
    private FanoutMessageSendSevice fanoutMessageSendSevice;
    @Autowired
    private DirectMessageSendSevice directMessageSendSevice;

    @Test
    public void fanout() {
        fanoutMessageSendSevice.senMsg();
    }

    @Test
    public void direct() {
        directMessageSendSevice.senMsg("email");
    }

    @Test
    public void directTtl() {
        for (int i = 0; i < 11; i++) {
            directMessageSendSevice.senTtlMsg("email");
        }
    }

    @Test
    public void senTtlMessage() {
        directMessageSendSevice.senTtlMessage("ttlmessage");
    }

}
