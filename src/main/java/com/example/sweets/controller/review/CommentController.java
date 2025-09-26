package com.example.sweets.controller.review;

import com.example.sweets.dto.request.review.CommentRequestDto;
import com.example.sweets.dto.response.review.CommentResponseDto;
import com.example.sweets.service.review.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;



    @GetMapping("/product/{productId}")
    public List<CommentResponseDto> getComments(@PathVariable UUID productId){
        return commentService.getCommentByProductId(productId);
    }


    @PostMapping()
    public CommentResponseDto addComment(
            Authentication authentication,
            @Valid @ModelAttribute CommentRequestDto commentRequestDto
            ){
        String username = authentication.getName();
        return commentService.addComment(username,commentRequestDto);

    }

    @PutMapping("/{commentId}")
    public CommentResponseDto  updateComment(
            Authentication authentication,
            @PathVariable UUID commentId,
            @RequestParam String text
    ){
        String username = authentication.getName();
        return commentService.updateComment(username,commentId,text);
    }

    @DeleteMapping("/{commentId}")
    public boolean deleteComment(Authentication authentication,@PathVariable UUID commentId){
        String username = authentication.getName();
        return commentService.deleteComment(username, commentId);
    }
}
