package org.roronoa.spring_security.util;


import org.roronoa.spring_security.dto.OrderDTO;
import org.roronoa.spring_security.dto.UserDTO;
import org.roronoa.spring_security.entity.Order;
import org.modelmapper.ModelMapper;
import org.roronoa.spring_security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityUtils {

 /*   public static OrderItemDTO orderItemDTOToOrderItem(OrderItem orderItem){
        ModelMapper modelMapper =new ModelMapper();
        OrderItemDTO oidto=modelMapper.map(orderItem,OrderItemDTO.class);
        return oidto;
    }*/

    public static OrderDTO orderToOrderDTO(Order order) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(order, OrderDTO.class);
    }
    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderDTO, Order.class);
    }
    public static UserDTO userToUserDTO(User user) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }
    public static User userDTOToUser(UserDTO userDTO) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

}
