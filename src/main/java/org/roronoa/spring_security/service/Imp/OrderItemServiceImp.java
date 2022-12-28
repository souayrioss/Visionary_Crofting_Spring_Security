package org.roronoa.spring_security.service.Imp;


import org.roronoa.spring_security.entity.OrderItem;
import org.roronoa.spring_security.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp {

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
