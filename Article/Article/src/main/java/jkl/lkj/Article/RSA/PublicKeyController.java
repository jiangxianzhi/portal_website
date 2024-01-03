package jkl.lkj.Article.RSA;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

//这个类我没写好，又是controller，又是service，不符合规范
@CrossOrigin
@RestController
@RequestMapping("/PublicKey")
public class PublicKeyController {

    //私钥
    PrivateKey sk;
//每次调用api都会实体化一个 PublicKeyController?


    @GetMapping("/key1")
    public ResponseEntity<String> getPublicKey() throws Exception {

//         存储密钥路径
        String fileName = "privateKey.pem";
        String resourceFolderPath = "src/main/resources";
        Path filePath = Paths.get(resourceFolderPath, fileName);

        // Generate new key pair

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

//写入文件，文件被偷了，那么这个就被破解了
        try {
            if (Files.exists(filePath)) {
                // 删除旧的
                Files.delete(filePath);

            }


            // Get private key and turn to  bytes
            PrivateKey privateKey = keyPair.getPrivate();
            byte[] privateKeyBytes = privateKey.getEncoded();
            // Write private key to file
            Files.write(filePath, privateKeyBytes);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String publicKeyString = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
//        用base64转过去就用base64转回来
        return ResponseEntity.ok(publicKeyString);

    }

    // 用私钥解密:
    public byte[] decrypt(byte[] input, PrivateKey sk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, sk);
        return cipher.doFinal(input);
    }

    @PostMapping("/key1")
    public String login(@RequestBody LoginRequest loginRequest) throws Exception {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

//把password解码
//读取 pem密钥
        try {
            // Read private key from file
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/privateKey.pem");
//            从根目录
            byte[] privateKeyBytes = fileInputStream.readAllBytes();
            fileInputStream.close();
            // Convert private key bytes to PrivateKey object
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            this.sk = privateKey;
            System.out.println("Private key read and written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
//password解码
        byte[] hadDecrypt = decrypt(Base64.getDecoder().decode(password), this.sk);

        System.out.println(new String(hadDecrypt));
        password = new String(hadDecrypt);
        System.out.println("username is " + username + " \n  password is " + password);
//        对比密码，账户写死
        if (username.equals("whois12345") && password.equals("helloWorld")) {
            //这里要继续编写加载成功逻辑， 给个fake jw token

            // Generate new key pair

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair1 = keyPairGenerator.generateKeyPair();
//            公钥加密
            // Get the public key from the key pair
            PublicKey publicKey = keyPair1.getPublic();

// Initialize the Cipher with the public key for encryption
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

// Encode the string "helloworld"
            byte[] encryptedBytes = cipher.doFinal("Ihavenotfigureoutyet".getBytes());

// Convert the encrypted bytes to a Base64 encoded string
            String encodedString = Base64.getEncoder().encodeToString(encryptedBytes);

            //         存储密钥
//            路径
            String fileName = "privateKey1.pem";
            String resourceFolderPath = "src/main/resources";
            Path filePath = Paths.get(resourceFolderPath, fileName);
            //写入文件
            try {
                if (Files.exists(filePath)) {
                    // Delete existing private key file
                    Files.delete(filePath);
                    System.out.println("Existing private key file deleted.");
                }


                // Get private key and turn to  bytes
                PrivateKey privateKey = keyPair1.getPrivate();
                byte[] privateKeyBytes = privateKey.getEncoded();
                // Write private key to file
                Files.write(filePath, privateKeyBytes);
                System.out.println("Private key file created and written successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            模仿   JWT格式，让别人误认。其实是 非对称加密
            String input = encodedString;
            int split = input.length()/3;
            return input.substring(0,split)+"."+input.substring(split,2*split)+"."+input.substring(2*split,input.length());
        }

        return "Login failed";
    }
}
