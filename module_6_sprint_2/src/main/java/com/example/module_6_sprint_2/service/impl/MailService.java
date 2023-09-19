package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, List<Ticket> list) throws MessagingException, UnsupportedEncodingException {
        List<String> list1 = list.stream().map(i->i.getCodeTicket()).collect(Collectors.toList());
        String fromAddress = "hungle06041998@gmail.com";
        String senderName = "Công Ty TNHH Dịch vụ và Thương mại HLE";
        String subject = "Mã đặt vé của email "+to;
        String content = "<div style=\"font-family: Arial, sans-serif;\n" +
                "            background-color: #f5f5f5;\n" +
                "            margin: 0;\n" +
                "            padding: 20px;\">\n" +
                "    <div style=\"max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 4px;\n" +
                "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\">\n" +
                "        <h2 style=\"color: #333333;\n" +
                "            font-size: 24px;\n" +
                "            margin-bottom: 20px;\">Mã đặt vé của bạn là:</h2>\n" +list1.toString() +
                "        <p style=\"color: #666666;\n" +
                "            margin-bottom: 10px;\">Cảm ơn bạn đã đặt vé! Vui lòng sử dụng mã đặt vé trên để hoàn tất quá trình hoàn vé\n" +
                "            </p>\n" +
                "        <a href=\"http://localhost:3000/ticketReturn\"  style=\"color: #333333;\n" +
                "            font-size: 18px;\n" +
                "            margin-top: 30px;\">" +"Nhấp vào đây để quay lại trang hoàn vé"+   "</a>\n" +
                "        <p style=\" color: #666666;\n" +
                "            margin-bottom: 10px;\"> Đây là một email tự động, vui lòng không trả lời.</p>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <p>Cảm ơn bạn đã chọn HLE YACHT!</p>\n" +
                "    </div>\n" +
                "</div>" +
                "\n" + "\n" + "\n" + "\n" +
                "Công Ty TNHH Dịch vụ và Thương mại HLE"+ "\n"+"<br/>"+
                "MST: 0400429984" + "\n"+"<br/>"+
                "Email : https://www.fb.com/hleYatch" + "\n"+"<br/>"+
                "Địa chỉ : Tầng 10, Tòa Nhà Hành chính, Số 10 Trần Phú, Phường Phước Ninh, Quận Hải Châu, Thành Phố Đà Nẵng";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}