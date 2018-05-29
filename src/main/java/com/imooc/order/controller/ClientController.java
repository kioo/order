package com.imooc.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    /* @Autowired // 第二种方法
     private LoadBalancerClient loadBalancerClient;*/
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 1. 第一种方式（直接使用restTemplate，url写死）
       /* RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/msg",String.class);
        log.info("response={}",response);*/
        // 2. 第二中方式(通过应用名字获取host信息，再次使用restTemplate）
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(),serviceInstance.getPort()+"/msg");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("response={}", response);*/

        // 3. 第三种方式（利用@LoadBalanced，可在restTemplate里使用应用名字）
        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);
        log.info("response={}", response);
        return response;
    }
}
