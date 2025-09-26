package com.example.sweets.service.order;


import com.example.sweets.dto.response.order.OrderResponseDto;
import com.example.sweets.entity.cart.Cart;
import com.example.sweets.entity.cart.CartItem;
import com.example.sweets.entity.order.Order;
import com.example.sweets.entity.order.OrderItem;
import com.example.sweets.entity.order.OrderStatus;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.order.OrderMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.repository.cart.CartRepository;
import com.example.sweets.repository.order.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDto placeOrder(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order=new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());

            order.getItems().add(orderItem);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }

        order.setTotalPrice(total);

        cart.getItems().clear();
        cartRepository.save(cart);

        orderRepository.save(order);

        return orderMapper.toDto(order);
    }


    public List<OrderResponseDto> getUserOrders(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toDto)
                .toList();
    }



    public OrderResponseDto changeStatus(UUID orderId, OrderStatus status){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return orderMapper.toDto(order);
    }
}
