package jkl.lkj.Article.RSA;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String ky;
    // Getters and setters
}