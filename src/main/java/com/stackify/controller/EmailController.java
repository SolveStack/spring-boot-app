package com.stackify.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackify.model.Subscriber;
import com.stackify.repository.SubscriberRepository;

@RestController
public class EmailController {
    private SubscriberRepository subscriberRepository;

    private void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("ana.tomboulian@gmail.com", "D>N3AP8yTBHRjj");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ana.tomboulian@gmail.com", false));


        List<Subscriber> subscribers = subscriberRepository.findAll();
        String[] addresses = new String[subscribers.size()];
        for ( int i = 0; i < subscribers.size(); i++) {
            addresses[i] = subscribers.get(i).getEmail();
        }
        String strAdresses = String.join(",", addresses);
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strAdresses));
        msg.setSubject("Thanks for joining us!");
        msg.setContent("You are now subscribed to Solvestack", "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }

    public EmailController(SubscriberRepository subscriberRepository){
        this.subscriberRepository = subscriberRepository;
    }


    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws AddressException, MessagingException, IOException {
        sendmail();
        return "Email sent successfully";
    }
}
