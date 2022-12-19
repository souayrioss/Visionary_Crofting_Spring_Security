package org.roronoa.spring_security.rest;


import org.roronoa.spring_security.entity.Order;
import org.roronoa.spring_security.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class CommandeResource {

    @Autowired
    OrderService orderService;

    @PostMapping("/") @ResponseBody
    public Order save(@RequestBody @Valid Order order) {
        return orderService.save(order);
    }


}
