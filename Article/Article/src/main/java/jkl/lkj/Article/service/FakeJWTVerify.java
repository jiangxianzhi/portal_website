package jkl.lkj.Article.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class FakeJWTVerify {
    PrivateKey sk;

    // 用私钥解密:
    public static byte[] decrypt(byte[] input, PrivateKey sk) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, sk);
        return cipher.doFinal(input);
    }


    public boolean verifyValue(String fakeJWTValue) throws Exception {
//        获取密钥，解密，比对，是不是“Ihavenotfigureoutyet
//去除两个点
        String modifiedString = fakeJWTValue.replace(".", "");
        modifiedString = modifiedString.substring(7, modifiedString.length());

        //读取 pem密钥
        try {
            // Read private key from file
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/privateKey1.pem");
//            从根目录
            byte[] privateKeyBytes = fileInputStream.readAllBytes();
            fileInputStream.close();
            // Convert private key bytes to PrivateKey object
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            sk = privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
//password解码
        byte[] hadDecrypt = null;
        try {
            hadDecrypt = decrypt(Base64.getDecoder().decode(modifiedString), sk);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String password = new String(hadDecrypt);
        if (password.equals("Ihavenotfigureoutyet")) {
            return true;
        } else {
            return false;
        }

    }
}
