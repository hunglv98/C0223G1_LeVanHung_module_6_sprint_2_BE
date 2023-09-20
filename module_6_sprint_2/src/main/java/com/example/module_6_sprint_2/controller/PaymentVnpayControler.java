package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.config_vnpay.PaymentConfig;
import com.example.module_6_sprint_2.model.Customer;
import com.example.module_6_sprint_2.model.Seat;
import com.example.module_6_sprint_2.model.Ticket;
import com.example.module_6_sprint_2.repository.ITicketRepo;
import com.example.module_6_sprint_2.service.ICustomerService;
import com.example.module_6_sprint_2.service.ISeatService;
import com.example.module_6_sprint_2.service.ITicketService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/vnpay")
public class PaymentVnpayControler {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private ISeatService seatService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam long total)
            throws UnsupportedEncodingException {

        String orderType = "170000";
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//        String bankCode = req.getParameter("bankCode");


        String amount = String.valueOf(total * 100);
//        String amount = "10000000";
        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
//        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.vnp_Version);
        vnp_Params.put("vnp_Command", PaymentConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", "0:0:0:0:0:0:0:1");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
        return new ResponseEntity<>(paymentUrl, HttpStatus.OK);
    }

    @PutMapping("/return/{username}/{listIdSeat}")
    public ResponseEntity<?> showReturn(@PathVariable String username,@PathVariable List<Integer> listIdSeat ) {
        Customer customer = customerService.getCustomerByAccount_Username(username);
        String date = String.valueOf(LocalDateTime.now());
        List<Ticket> codeTickets = new ArrayList<>();
        for (Integer i: listIdSeat) {
            String codeTicket;
            do {
                 codeTicket = String.valueOf((int)Math.floor(Math.random()*1000000));
            }while (ticketService.getTicketByCodeTicket(codeTicket)!=null);
            Seat seat = seatService.getSeatByIdSeat(i);
            seat.setFlagPayment(true);
            seatService.save(seat);
            Ticket ticket = new Ticket();
            ticket.setDateBooking(date);
            ticket.setCustomer(customer);
            ticket.setSeat(seat);
            ticket.setCodeTicket(codeTicket);
            ticketService.save(ticket);
            codeTickets.add(ticket);
        }

        return new ResponseEntity<>(codeTickets,HttpStatus.OK);
    }
//
//    @PostMapping("/returnMoney")
//    public ResponseEntity<?> returnMoney(@RequestParam long total) throws IOException {
//        String vnp_RequestId = PaymentConfig.getRandomNumber(8);
//        String vnp_Version = "2.1.0";
//        String vnp_Command = "refund";
//        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
//        String vnp_TransactionType = "02";
//        // TxnRef chưa hiểu
////        String vnp_TxnRef = req.getParameter("order_id");
//        String vnp_TxnRef = "28109379";
////        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//        String vnp_Amount = String.valueOf(1500000*100);
//        String vnp_OrderInfo = "Hoan tien GD OrderId:" + vnp_TxnRef;
//        String vnp_TransactionNo = ""; //Assuming value of the parameter "vnp_TransactionNo" does not exist on your system.
////        String vnp_TransactionDate = req.getParameter("trans_date");
//        String vnp_TransactionDate = "20231509155209";
//
//        String vnp_CreateBy = "hung123";
//
//        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String vnp_CreateDate = formatter.format(cld.getTime());
//
//        String vnp_IpAddr = "0:0:0:0:0:0:0:1";
//
//        JsonObject  vnp_Params = new JsonObject();
//
//        vnp_Params.addProperty("vnp_RequestId", vnp_RequestId);
//        vnp_Params.addProperty("vnp_Version", vnp_Version);
//        vnp_Params.addProperty("vnp_Command", vnp_Command);
//        vnp_Params.addProperty("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.addProperty("vnp_TransactionType", vnp_TransactionType);
//        vnp_Params.addProperty("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.addProperty("vnp_Amount", vnp_Amount);
//        vnp_Params.addProperty("vnp_OrderInfo", vnp_OrderInfo);
//
//        if(vnp_TransactionNo != null && !vnp_TransactionNo.isEmpty())
//        {
//            vnp_Params.addProperty("vnp_TransactionNo", "{get value of vnp_TransactionNo}");
//        }
//
//        vnp_Params.addProperty("vnp_TransactionDate", vnp_TransactionDate);
//        vnp_Params.addProperty("vnp_CreateBy", vnp_CreateBy);
//        vnp_Params.addProperty("vnp_CreateDate", vnp_CreateDate);
//        vnp_Params.addProperty("vnp_IpAddr", vnp_IpAddr);
//
//
//
//        String hash_Data = vnp_RequestId + "|" + vnp_Version + "|" + vnp_Command + "|" + vnp_TmnCode + "|" +
//                vnp_TransactionType + "|" + vnp_TxnRef + "|" + vnp_Amount + "|" + vnp_TransactionNo + "|"
//                + vnp_TransactionDate + "|" + vnp_CreateBy + "|" + vnp_CreateDate + "|" + vnp_IpAddr + "|" + vnp_OrderInfo;
//
//        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.vnp_HashSecret, hash_Data.toString());
//
//        vnp_Params.addProperty("vnp_SecureHash", vnp_SecureHash);
//
//        URL url = new URL (PaymentConfig.vnp_apiUrl);
//        HttpURLConnection con = (HttpURLConnection)url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(vnp_Params.toString());
//        wr.flush();
//        wr.close();
//        int responseCode = con.getResponseCode();
//        System.out.println("nSending 'POST' request to URL : " + url);
//        System.out.println("Post Data : " + vnp_Params);
//        System.out.println("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String output;
//        StringBuffer response = new StringBuffer();
//        while ((output = in.readLine()) != null) {
//            response.append(output);
//        }
//        in.close();
//        System.out.println(response.toString());
//
//
//        return new ResponseEntity<>( HttpStatus.OK);
//    }
//
//    @PostMapping("/createRefund")
//    public ResponseEntity<?> createRefund(@RequestParam long total) throws UnsupportedEncodingException {
//        String orderType = "170000";
////        long amount = Integer.parseInt(req.getParameter("amount"))*100;
////        String bankCode = req.getParameter("bankCode");
//
//
//        String amount = String.valueOf(total * 100);
////        String amount = "10000000";
//        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
////        String vnp_IpAddr = Config.getIpAddress(req);
//        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
//        Map<String, String> vnp_Params = new HashMap<>();
//        vnp_Params.put("vnp_Version", PaymentConfig.vnp_Version);
//        vnp_Params.put("vnp_Command", "refund");
//        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.put("vnp_Amount", String.valueOf(amount));
//        vnp_Params.put("vnp_CurrCode", "VND");
//        vnp_Params.put("vnp_BankCode", "NCB");
//        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
//        vnp_Params.put("vnp_OrderType", orderType);
//        vnp_Params.put("vnp_Locale", "vn");
////        String locate = req.getParameter("language");
////        if (locate != null && !locate.isEmpty()) {
////            vnp_Params.put("vnp_Locale", locate);
////        } else {
////            vnp_Params.put("vnp_Locale", "vn");
////        }
//        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_Returnurl);
//        vnp_Params.put("vnp_IpAddr", "0:0:0:0:0:0:0:1");
//
//        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String vnp_CreateDate = formatter.format(cld.getTime());
//        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
//
//        cld.add(Calendar.MINUTE, 15);
//        String vnp_ExpireDate = formatter.format(cld.getTime());
//        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
//
//        List fieldNames = new ArrayList(vnp_Params.keySet());
//        Collections.sort(fieldNames);
//        StringBuilder hashData = new StringBuilder();
//        StringBuilder query = new StringBuilder();
//        Iterator itr = fieldNames.iterator();
//        while (itr.hasNext()) {
//            String fieldName = (String) itr.next();
//            String fieldValue = (String) vnp_Params.get(fieldName);
//            if ((fieldValue != null) && (fieldValue.length() > 0)) {
//                //Build hash data
//                hashData.append(fieldName);
//                hashData.append('=');
//                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//                //Build query
//                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
//                query.append('=');
//                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//                if (itr.hasNext()) {
//                    query.append('&');
//                    hashData.append('&');
//                }
//            }
//        }
//        String queryUrl = query.toString();
//        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.vnp_HashSecret, hashData.toString());
//        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
//        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
//        return new ResponseEntity<>(paymentUrl, HttpStatus.OK);
//    }
}
