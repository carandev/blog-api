package com.carandev.blogapi.service;

import com.carandev.blogapi.dto.PostDTO;
import com.carandev.blogapi.entity.Post;
import com.carandev.blogapi.exception.ResourceNotFoundException;
import com.carandev.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
  
  @Autowired
  private PostRepository postRepository;
  
  public PostDTO createPost(PostDTO postDTO){
    Post post = mapToEntity(postDTO);
    
    Post newPost = postRepository.save(post);
    
    PostDTO postResponse = mapToDTO(newPost);
    
    return postResponse;
  }
  
  public List<PostDTO> getAllPosts(){
    List<Post> posts = postRepository.findAll();
    
    return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
  }
  
  public PostDTO getById(long id){
    Post post = postRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
    
    return mapToDTO(post);
  }
  
  public PostDTO updatePost(PostDTO postDTO, long id){
    Post post = postRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
  
    post.setTitle(postDTO.getTitle());
    post.setDescription(postDTO.getDescription());
    post.setContent(postDTO.getContent());
    
    Post postUpdated = postRepository.save(post);
    
    return mapToDTO(postUpdated);
  }
  
  public void deletePost(long id){
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    
    postRepository.delete(post);
  }
  
  private PostDTO mapToDTO(Post post){
    
    PostDTO postDTO = new PostDTO();
    postDTO.setId(post.getId());
    postDTO.setTitle(post.getTitle());
    postDTO.setDescription(post.getDescription());
    postDTO.setContent(post.getContent());
    
    return postDTO;
  }
  
  private Post mapToEntity(PostDTO postDTO){
  
    Post post = new Post();
    post.setTitle(postDTO.getTitle());
    post.setDescription(postDTO.getDescription());
    post.setContent(postDTO.getContent());
    
    return post;
  }
}
