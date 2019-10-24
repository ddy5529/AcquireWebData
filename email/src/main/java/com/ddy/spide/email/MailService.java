package com.ddy.spide.email;

public interface MailService {

    void sendSimpleMail(String to, String subject, String content);
}
