package com.carandev.blogapi.service;

import com.carandev.blogapi.dto.CommentDTO;
import com.carandev.blogapi.entity.Comment;
import com.carandev.blogapi.entity.Post;
import com.carandev.blogapi.exception.ResourceNotFoundException;
import com.carandev.blogapi.repository.CommentRepository;
import com.carandev.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  
  @Autowired
  private CommentRepository commentRepository;
  
  @Autowired
  private PostRepository postRepository;
  
  public CommentDTO createComment(long postId, CommentDTO commentDTO){
    Comment comment = mapToEntity(commentDTO);
  
    System.out.println(comment);
    
    Post post = postRepository.findById(postId)
      .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
    
    comment.setPost(post);
  
    System.out.println(comment);
    
    Comment newComment = commentRepository.save(comment);
    
    return mapToDTO(newComment);
  }
  
  private CommentDTO mapToDTO(Comment comment){
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(comment.getId());
    commentDTO.setName(comment.getName());
    commentDTO.setEmail(comment.getEmail());
    commentDTO.setBody(comment.getBody());
    
    return commentDTO;
  }
  
  private Comment mapToEntity(CommentDTO commentDTO){
    Comment comment = new Comment();
    comment.setName(commentDTO.getName());
    comment.setEmail(commentDTO.getEmail());
    comment.setBody(commentDTO.getBody());
    
    return comment;
  }
}
