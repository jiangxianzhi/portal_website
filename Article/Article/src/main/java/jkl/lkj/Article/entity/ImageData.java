package jkl.lkj.Article.entity;

import lombok.Data;

@Data
public class ImageData {
    private String url;
    private String alt;
    private String href;

    public ImageData(String url, String alt, String href) {
        this.url = url != null ? url : "defaultUrl";
        this.alt = alt != null ? alt : "defaultAlt";
        this.href = href != null ? href : "defaultHref";
    }

    // getters and setters
}