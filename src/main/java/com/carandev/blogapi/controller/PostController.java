package com.carandev.blogapi.controller;

import com.carandev.blogapi.dto.PostDTO;
import com.carandev.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  @Autowired
  private PostService postService;
  
  @PostMapping
  public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
    return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
  }
  
  @GetMapping
  public ResponseEntity<List<PostDTO>> getPosts(){
    return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getById(@PathVariable(name = "id") long id){
    return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<PostDTO> updateById(@PathVariable(name = "id") long id, @RequestBody PostDTO postDTO){
    return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity deleteById(@PathVariable(name = "id") long id){
    return new ResponseEntity(postService.deletePost(id));
  }
}
