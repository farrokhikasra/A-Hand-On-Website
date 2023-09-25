package com.website.springbootwebsite.apis.messages;

import com.website.springbootwebsite.apis.users.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void addMessage(Long senderId, Long receiverId, String message, LocalDateTime time) {
        Message messageClass = new Message(senderId, receiverId, message, time);
        repository.save(messageClass);
    }

    public void addMessageOnTime(Long senderId, Long receiverId, String message) {
        addMessage(senderId, receiverId, message, LocalDateTime.now());
    }

    public void addMessageByObject(UserEntity sender, UserEntity receiver, String message, LocalDateTime time) {
        addMessage(sender.getId(), receiver.getId(), message, time);
    }

    public void addMessageByObjectOnTime(UserEntity sender, UserEntity receiver, String message) {
        addMessageByObject(sender, receiver, message, LocalDateTime.now());
    }

    public List<Message> findAllBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        return repository.findAllBySenderIdAndReceiverId(senderId, receiverId);
    }

    public List<Message> findAllBySenderAndReceiverObj(UserEntity sender, UserEntity receiver) {
        return findAllBySenderIdAndReceiverId(sender.getId(), receiver.getId());
    }

    public List<Message> getConversation(UserEntity user1, UserEntity user2) {
        List<Message> conversation = findAllBySenderAndReceiverObj(user1, user2);
        conversation = conversation.stream().sorted(Comparator.comparing(Message::getTime)).collect(Collectors.toList());
        return conversation;
    }

    public void deleteMessage(Long id) {
        repository.deleteById(id);
    }

    public Message updateMessageOfMessage(Long id, String message) {
        Message messageClass = repository.findById(id);
        deleteMessage(id);
        messageClass.setMessage(message);
        repository.save(messageClass);
        return messageClass;
    }

    public Message updateMessageOfMessage(Long senderId, Long receiverId, String prevMessage, String newMessage) {
        Message message = repository.findBySenderIdAndReceiverIdAndMessage(senderId, receiverId, prevMessage);
        return updateMessageOfMessage(message.getId(), newMessage);
    }


}
