package com.example.sweets.service.review;


import com.example.sweets.dto.request.review.CommentRequestDto;
import com.example.sweets.dto.response.review.CommentResponseDto;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.review.Comment;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.review.CommentMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.UserRepository;
import com.example.sweets.repository.review.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentResponseDto addComment(String username, CommentRequestDto commentRequestDto){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));

        Product product = productRepository.findById(commentRequestDto.productId())
                .orElseThrow(()->new RuntimeException("Product not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setProduct(product);
        comment.setText(commentRequestDto.text());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Transactional
    public List<CommentResponseDto> getCommentByProductId(UUID productId){
        List<Comment> comments = commentRepository.findByProductId(productId);
        return comments.stream()
                .map(commentMapper::toDto)
                .toList();

    }

    @Transactional
    public CommentResponseDto updateComment(String username, UUID id, String text){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Comment not found"));

        comment.setUser(user);
        comment.setText(text);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return  commentMapper.toDto(comment);
    }

    @Transactional
    public boolean deleteComment(String username, UUID id){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("user not found"));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Comment not found"));

        Product product = comment.getProduct();
        product.getComments().remove(comment);

        commentRepository.delete(comment);
        return  true;
    }
}


