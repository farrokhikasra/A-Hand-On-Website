package com.website.springbootwebsite.apis.messages;

import com.website.springbootwebsite.apis.users.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class MessagesPackageTest {
    private static MessageService service;
    private static UserEntity sender;
    private static UserEntity receiver;

    @BeforeAll
    static void createObject(@Autowired MessageRepository repository) {
        service = new MessageService(repository);
        sender = new UserEntity("Kasra", "farrokhi.kasra@gmail.com", "123");
        receiver = new UserEntity("Kian", "farrokhikian@gmail.com", "321");
    }

    @Test
    void addMessageTest() {
        service.addMessageByObjectOnTime(sender, receiver, "Hi");
        List<Message> messages = service.findAllBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        assertEquals("Hi", messages.get(0).getMessage());
    }

    @Test
    void updateMessageTest() {
        service.addMessageByObjectOnTime(sender, receiver, "Hi");
        service.updateMessageOfMessage(sender.getId(), receiver.getId(), "Hi", "Hello");
        List<Message> messages = service.findAllBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        assertEquals("Hello", messages.get(0).getMessage());
    }

    @Test
    void getConversationTest() {
        List<String> convString = new ArrayList<>();
        convString.add("Hi");
        convString.add("Hello");
        convString.add("How are you?");
        convString.add("I'm fine!");
        service.addMessageByObjectOnTime(sender, receiver, convString.get(0));
        service.addMessageByObjectOnTime(receiver, sender, convString.get(1));
        service.addMessageByObjectOnTime(sender, receiver, convString.get(2));
        service.addMessageByObjectOnTime(receiver, sender, convString.get(3));
        System.out.println(sender.getId());
        List<Message> messages = service.getConversation(sender, receiver);
        for (int i = 0; i < messages.size(); i++) {
            assertEquals(convString.get(i), messages.get(i).getMessage());
        }


    }


}
