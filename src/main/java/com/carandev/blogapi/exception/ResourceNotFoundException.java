package com.carandev.blogapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
  private String resourceName;
  private String fieldName;
  private long fieldContent;
  
  public ResourceNotFoundException(String resourceName, String fieldName, long fieldContent) {
    super(String.format("%s not found with: %s : '%s'", resourceName, fieldName, fieldContent));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldContent = fieldContent;
  }
}
