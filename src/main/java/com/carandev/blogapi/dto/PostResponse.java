package com.carandev.blogapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
  private List<PostDTO> content;
  private int numberPage;
  private int pageSize;
  private long totalItems;
  private int totalPages;
  private boolean last;
}
