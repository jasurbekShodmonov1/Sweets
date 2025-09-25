package com.example.sweets.service.cart;



import com.example.sweets.dto.response.cart.CartResponseDto;
import com.example.sweets.entity.cart.Cart;
import com.example.sweets.entity.cart.CartItem;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.cart.CartMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    public CartResponseDto getUserCart(String username){
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(currentUser)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(currentUser);
                    return cartRepository.save(newCart);
                });

        if (!cart.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Access denied: not your cart");
        }

        return cartMapper.toDto(cart);
    }

    public CartResponseDto addCart(String username, UUID productId){
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(currentUser)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(currentUser);
                    return cartRepository.save(newCart);
                });

        if (!cart.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Access denied");
        }



        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));





        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cart.getItems().add(newItem);
        }

        product.setCount(product.getCount() -1);
        productRepository.save(product);

        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartResponseDto removeFromCart(String username, UUID productId) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(currentUser)
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        Iterator<CartItem> iterator = cart.getItems().iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId().equals(productId)) {
                Product product = item.getProduct();


                product.setCount(product.getCount() + 1);
                productRepository.save(product);


                int newQuantity = item.getQuantity() - 1;
                if (newQuantity > 0) {
                    item.setQuantity(newQuantity);
                } else {
                    iterator.remove();
                }

                break;
            }
        }

        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }
}
