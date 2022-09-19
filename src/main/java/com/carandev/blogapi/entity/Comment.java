package com.carandev.blogapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String name;
  private String email;
  private String body;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;
}
