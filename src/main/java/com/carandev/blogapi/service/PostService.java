package com.carandev.blogapi.service;

import com.carandev.blogapi.dto.PostDTO;
import com.carandev.blogapi.dto.PostResponse;
import com.carandev.blogapi.entity.Post;
import com.carandev.blogapi.exception.ResourceNotFoundException;
import com.carandev.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  
  public PostResponse getAllPosts(int numberPage, int pageSize, String sortBy, String sortDirection){
    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
      ? Sort.by(sortBy).ascending()
      : Sort.by(sortBy).descending();
    
    Pageable pageable = PageRequest.of(numberPage, pageSize, sort);
  
    Page<Post> posts = postRepository.findAll(pageable);
    
    List<Post> postsList = posts.getContent();
    List<PostDTO> content =  postsList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(content);
    postResponse.setNumberPage(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalItems(posts.getTotalElements());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setLast(posts.isLast());
    
    return postResponse;
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
