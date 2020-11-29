package com.stackify.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.stackify.model.Subscriber;
import com.stackify.repository.SubscriberRepository;

@RestController
public class SubscriberController {

    private SubscriberRepository subscriberRepository;

    public SubscriberController(SubscriberRepository subscriberRepository){
        this.subscriberRepository = subscriberRepository;
    }

    @PostMapping("subscribers")
    @ResponseStatus(HttpStatus.CREATED)
    public Subscriber addSubscriber(@RequestBody Subscriber subscriber){
        subscriberRepository.save(subscriber);
        return subscriber;
    }

    @GetMapping("/subscribers")
    public List<Subscriber> getSubscribers() {
        return this.subscriberRepository.findAll();
    }
}
