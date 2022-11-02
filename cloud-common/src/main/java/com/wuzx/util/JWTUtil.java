package com.wuzx.util;

import com.wuzx.model.LoginUser;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT(json web token)
 *
 * @author: wuzhixuan
 * @date 2022/10/20 00:43
 * @Version 1.0
 */
@Slf4j
public class JWTUtil {
    /**
     * 主题
     */
    private static final String SUBJECT = "wuzhixuan";

    /**
     * 加密密钥
     */
    private static final String SECRET = "wuzx.cool";

    /**
     * 令牌前缀
     */
    private static final String TOKNE_PREFIX = "cloud-link";


    /**
     * token过期时间，7天
     */
    private static final long EXPIRED = 1000 * 60 * 60 * 24 * 7;


    /**
     * 根据用户信息，生成令牌 *
     * @param loginUser
     * @return
     */
    public static String geneJsonWebToken(LoginUser loginUser) {

        if (loginUser == null) {
            throw new NullPointerException("对象为空");
        }


        String token = Jwts.builder().setSubject(SUBJECT) // 设置主题
                //配置payload
                .claim("head_img", loginUser.getHeadImg())
                .claim("account_no", loginUser.getAccountNo())
                .claim("username", loginUser.getUsername())
                .claim("mail", loginUser.getMail())
                .claim("phone", loginUser.getPhone())
                .claim("auth", loginUser.getAuth())
                .setIssuedAt(new Date()) // 签发日期
                .setExpiration(new Date(CommonUtil.getCurrentTimestamp() + EXPIRED)) // 过期的日期
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();

        token = TOKNE_PREFIX + token;

        return token;
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        final Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKNE_PREFIX, ""))
                    .getBody();
            return claims;
        } catch (Exception e) {

            log.error("jwt 解密失败 {}",e.getMessage());
            return null;
        }
    }
}
