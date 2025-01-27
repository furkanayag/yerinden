package com.yerinden.yerinden.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.DestroyFailedException;
import java.nio.charset.StandardCharsets;

@Configuration
public class SecretKey implements javax.crypto.SecretKey {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public String getAlgorithm() {
        return "HmacSHA512";
    }

    @Override
    public String getFormat() {
        return "RAW";
    }

    @Override
    public byte[] getEncoded() {
        return secretKey.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void destroy() throws DestroyFailedException {
        javax.crypto.SecretKey.super.destroy();
    }

    @Override
    public boolean isDestroyed() {
        return javax.crypto.SecretKey.super.isDestroyed();
    }
}
