package jkl.lkj.Article.entity;

import lombok.Data;

@Data
public class ImageResponse {
    private int errno;
    private ImageData data;
    public ImageResponse(int errno, ImageData imageData) {
     this.errno = errno;
     this.data  = imageData;
    }
    // constructor, getters and setters
}