package com.springbootstart.service.email;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
}