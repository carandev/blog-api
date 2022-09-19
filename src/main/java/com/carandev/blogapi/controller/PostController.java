package com.carandev.blogapi.controller;

import com.carandev.blogapi.dto.PostDTO;
import com.carandev.blogapi.dto.PostResponse;
import com.carandev.blogapi.service.PostService;
import com.carandev.blogapi.utility.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  @Autowired
  private PostService postService;
  
  @GetMapping
  public ResponseEntity<PostResponse> getPosts(
    @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_NUMBER_PAGE, required = false) int pageNumber,
    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_SIZE_PAGE, required = false) int pageSize,
    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
    @RequestParam(value = "sortDirection", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDirection){
    
    return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getById(@PathVariable(name = "id") long id){
    return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
  }
  
  @PostMapping
  public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
    return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<PostDTO> updateById(@PathVariable(name = "id") long id, @RequestBody PostDTO postDTO){
    return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteById(@PathVariable(name = "id") long id){
    postService.deletePost(id);
    return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
  }
}
