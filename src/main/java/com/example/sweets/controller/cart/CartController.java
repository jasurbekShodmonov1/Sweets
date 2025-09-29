package com.example.sweets.controller.cart;



import com.example.sweets.dto.response.cart.CartResponseDto;
import com.example.sweets.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<CartResponseDto> getCart(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(cartService.getUserCart(username));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<CartResponseDto> addToCart(  Authentication authentication,
                                                      @PathVariable UUID productId){
        String username = authentication.getName();
        return ResponseEntity.ok(cartService.addCart(username, productId));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<CartResponseDto> removeFromCart(
            Authentication  authentication,
            @PathVariable UUID productId) {

        String username = authentication.getName();
        CartResponseDto cart = cartService.removeFromCart(username, productId);
        return ResponseEntity.ok(cart);
    }
}
