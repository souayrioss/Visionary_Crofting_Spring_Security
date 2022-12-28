package org.roronoa.spring_security.service.Imp;


import org.roronoa.spring_security.entity.Order;
import org.roronoa.spring_security.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemServiceImp orderItemService;
    public Order save(@Valid  Order order){
        order.setUuid(UUID.randomUUID());
        order.setCreatedAt(LocalDateTime.now());
        order =  orderRepository.save(order);
        Order finalOrder = order;
        order.getOrderItems().forEach(orderItem -> {
             orderItem.setOrder(finalOrder);
            orderItemService.save(orderItem);
        });
        return order;

    }
}
