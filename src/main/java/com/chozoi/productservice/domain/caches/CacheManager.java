package com.chozoi.productservice.domain.caches;

import com.chozoi.productservice.domain.entities.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class CacheManager {

    @Autowired
    private RedisTemplate<String, TokenInfo> redisTemplate;

    private static final long EXPIRES_TIME = 15 * 60 * 1000;

    public void set(String key, String value) {
        TokenInfo tokenInfo = new TokenInfo(value);
        log.info(tokenInfo + "");
        redisTemplate.
                opsForValue().
                set(key, tokenInfo, Duration.ofMillis(EXPIRES_TIME));

    }

    public TokenInfo get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

}
