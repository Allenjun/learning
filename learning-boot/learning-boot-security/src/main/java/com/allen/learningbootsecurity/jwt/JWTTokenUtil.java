package com.allen.learningbootsecurity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

/**
 * @author JUN @Description TODO
 * @createTime 15:36
 */
@Component
@EnableConfigurationProperties(JWTProperties.class)
public final class JWTTokenUtil {

    private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static byte[] DEFAULT_SECRET =
            "123456789@ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(DEFAULT_CHARSET);
    private static int DEFAULT_EXPIRE = 3600;
    private static byte[] secret;
    private static int expire;
    private static Key key;

    public JWTTokenUtil(ObjectProvider<JWTProperties> jwtPropertiesProvider) throws BeansException {
        JWTProperties jwtProperties = jwtPropertiesProvider.getIfAvailable();
        if (Objects.nonNull(jwtProperties)) {
            JWTTokenUtil.secret = jwtProperties.getSecret().getBytes(DEFAULT_CHARSET);
            JWTTokenUtil.expire = jwtProperties.getExpire();
        } else {
            JWTTokenUtil.secret = JWTTokenUtil.DEFAULT_SECRET;
            JWTTokenUtil.expire = JWTTokenUtil.DEFAULT_EXPIRE;
        }
        key = Keys.hmacShaKeyFor(JWTTokenUtil.secret);
    }

    public static void main(String[] args) {
        String jws =
                Jwts.builder()
                        .setSubject("Joe")
                        .setExpiration(DateUtils.addSeconds(new Date(), expire))
                        .signWith(key)
                        .compact();
        System.out.println(jws);

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jws);
        System.out.println(claimsJws.getSignature());
        System.out.println(claimsJws.getBody().getSubject());
    }

    public static String generateToken(Payload payload) {
        return Jwts.builder()
                .setSubject(payload.getSub())
                .setExpiration(DateUtils.addSeconds(new Date(), expire))
                .signWith(key)
                .compact();
    }

    public static Payload parseToken(String jws) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody();
        return Payload.builder().expire(claims.getExpiration()).sub(claims.getSubject()).build();
    }

    @Builder
    @Data
    public static class Payload {

        private String sub;
        private Date expire;
    }
}
