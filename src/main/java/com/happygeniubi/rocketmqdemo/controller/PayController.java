package com.happygeniubi.rocketmqdemo.controller;

import com.happygeniubi.rocketmqdemo.jms.JmsConfig;
import com.happygeniubi.rocketmqdemo.jms.PayProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    @Autowired
    private PayProducer payProducer;

    @RequestMapping("/api/v1/pay_cb")
    public Object callBack(String text) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        System.out.println("开始发送消息...");
        Message message = new Message(JmsConfig.TOPIC, "tagA",("Hello,happyge de RocketMq: " + text).getBytes());
        SendResult result = payProducer.getProducer().send(message);
        System.out.println("消息发送结果: " + result);

        return "Successs";
    }
}
