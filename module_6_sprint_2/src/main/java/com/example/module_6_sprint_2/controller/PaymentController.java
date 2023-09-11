package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.configMomo.ConfigMomo;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {
    @GetMapping("/momo")
    public ResponseEntity<?> paymentMomo() {
        String orderInfo = "Pay With MoMo";
        long amount = 50000;
        String partnerName = "test MoMo";
        String requestType= "payWithATM";
        String returnURL = "https://google.com.vn";
        String notifyURL = "https://google.com.vn";
        String storeId = "test store ID";
        String extraData = "";
        String signature = "e2acf133a1168d62d1f5893083a576af820b6d8f948d8bb7f811cc927e450187";
        String partnerCode = "MOMOLRJZ20181206";
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        String lang = "vi";
        int startTime = 169383;

        ConfigMomo configMomo = new ConfigMomo(orderInfo,amount,partnerName,requestType,returnURL,notifyURL,storeId,
                extraData,signature,partnerCode,requestId,orderId,lang,startTime);


        RestTemplate restTemplate = new RestTemplate();

        // Tạo đối tượng HttpHeaders với các thông tin cần thiết (ví dụ: tiêu đề, kiểu nội dung)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo đối tượng HttpEntity để đại diện cho yêu cầu POST
//        String requestBody = "Dữ liệu yêu cầu POST";
        HttpEntity<?> requestEntity = new HttpEntity<>(configMomo, headers);

        // Gửi yêu cầu POST và nhận phản hồi từ API
        String apiUrl = "https://test-payment.momo.vn/v2/gateway/api"; // URL của API đích
        ResponseEntity<ConfigMomo> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ConfigMomo.class);

        // Xử lý phản hồi từ API (ví dụ: trả về mã phản hồi và nội dung phản hồi)
        int statusCode = responseEntity.getStatusCodeValue();
        String responseBody = String.valueOf(responseEntity.getBody());

        // Trả về phản hồi từ API
        return ResponseEntity.status(statusCode).body(responseBody);
    }


}
