package com.carandev.blogapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posts", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"title"})
})
public class Post {
  
  @Id
  @GeneratedValue
  private Long id;
  
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "content", nullable = false)
  private String content;
}
