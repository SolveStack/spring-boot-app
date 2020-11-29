package com.stackify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackify.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
