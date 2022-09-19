package com.carandev.blogapi.controller;

import com.carandev.blogapi.dto.CommentDTO;
import com.carandev.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
  @Autowired
  private CommentService commentService;
  
  @PostMapping("/posts/{post_id}/comments")
  public ResponseEntity<CommentDTO> saveComment(
    @PathVariable(value = "post_id") long postId,
    @RequestBody CommentDTO commentDTO){
    
    return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
  }
}
