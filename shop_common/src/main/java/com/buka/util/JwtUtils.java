package com.buka.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Map;

public class JwtUtils {
    public static Map get(String token){
        Claims itbuka = Jwts.parser().setSigningKey("itbuka").parseClaimsJws(token).getBody();
        return itbuka;
    }
}
