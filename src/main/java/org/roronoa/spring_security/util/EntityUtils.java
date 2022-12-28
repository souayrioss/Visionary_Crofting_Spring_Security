package org.roronoa.spring_security.util;


import org.modelmapper.ModelMapper;
import org.roronoa.spring_security.dto.OrderDTO;
import org.roronoa.spring_security.dto.ProductDTO;
import org.roronoa.spring_security.dto.UserDTO;
import org.roronoa.spring_security.entity.Order;
import org.roronoa.spring_security.entity.Product;
import org.roronoa.spring_security.entity.UserApp;

import java.security.SecureRandom;

import static org.roronoa.spring_security.util.IConstantes.CAPS;
import static org.roronoa.spring_security.util.IConstantes.LENGTH;


public class EntityUtils {

    private static final SecureRandom random = new SecureRandom();



    public static String generateReference() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CAPS.length());
            result.append(CAPS.charAt(index));
        }
        return result.toString();
    }



    public static OrderDTO orderToOrderDTO(Order order) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(order, OrderDTO.class);
    }
    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderDTO, Order.class);
    }
    public static UserDTO userToUserDTO(UserApp user) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }
    public static UserApp userDTOToUser(UserDTO userDTO) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(userDTO, UserApp.class);
    }
    public static ProductDTO productToProductDTO(Product product) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(product, ProductDTO.class);
    }
    public static Product productDTOToProduct(ProductDTO productDTO) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(productDTO, Product.class);
    }

}
