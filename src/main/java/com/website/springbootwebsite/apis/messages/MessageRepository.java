package com.website.springbootwebsite.apis.messages;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    void save(Message message);

    List<Message> findAllBySenderIdAndReceiverId(Long senderId, Long receiverId);

    void deleteById(Long id);

    Message findById(Long id);

    Message findBySenderIdAndReceiverIdAndMessage(Long senderId, Long receiverId, String message);

}
