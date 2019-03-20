package com.gysoft.utils.util.token;

import com.gysoft.utils.exception.ParamInvalidException;
import strman.Strman;

/**
 * @author 周宁
 * @Date 2019-01-15 16:09
 */
public class IosTokenUtils {

    /**
     * 校验并且格式化token
     * @param token
     * @return String
     * @throws ParamInvalidException
     */
    public static String formatToken(String token) throws ParamInvalidException {
        try {
            token = Strman.removeSpaces(Strman.between(token, "<", ">")[0]);
        } catch (Exception e) {
            throw new ParamInvalidException("token不合法");
        }
        if(token.length()!=64){
            throw new ParamInvalidException("token不合法");
        }
        return token;
    }
}
