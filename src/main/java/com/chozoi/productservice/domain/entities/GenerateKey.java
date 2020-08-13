package com.chozoi.productservice.domain.entities;

import lombok.Getter;

@Getter
public class GenerateKey {
    public final static String PREFIX_KEY = "cz_";

    public static String genUserKey(String token){
        return PREFIX_KEY + "user_" + token;
    }

    public static String genProductKey(String token, long id){
        return PREFIX_KEY + "product_" + id + "_" + token;
    }

    public static String genCategoryKey(String token){
        return PREFIX_KEY + "category_" + token;
    }

}
