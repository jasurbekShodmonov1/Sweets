package com.example.sweets.controller.cart;



import com.example.sweets.dto.response.cart.CartResponseDto;
import com.example.sweets.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;




    @GetMapping()
    public ResponseEntity<CartResponseDto> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(cartService.getUserCart(username));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<CartResponseDto> addToCart( @AuthenticationPrincipal UserDetails userDetails,
                                                      @PathVariable UUID productId){
        String username = userDetails.getUsername();
        return ResponseEntity.ok(cartService.addCart(username, productId));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<CartResponseDto> removeFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable UUID productId) {

        String username = userDetails.getUsername();
        CartResponseDto cart = cartService.removeFromCart(username, productId);
        return ResponseEntity.ok(cart);
    }
}
