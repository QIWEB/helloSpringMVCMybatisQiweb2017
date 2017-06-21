package com.ning.demo;


import javax.mail.Message;

import org.springframework.batch.item.ItemProcessor;

public class MessageProcessor implements ItemProcessor<User, Message> {
    @Override
    public Message process(User item) throws Exception {
        Message message = null;
        if (item.getAge() > 16) {
//            message = new Message();
//            message.setContent(item.getName() + ",Please come to police station!");
        }
        return message;
    }
}