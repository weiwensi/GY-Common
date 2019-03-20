package com.gysoft.utils.util.token;

import com.gysoft.bean.login.UserBasicInfo;
import com.gysoft.utils.jdbc.pojo.IdGenerator;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.gysoft.utils.util.date.MillUnit.getWebsiteTimeMills;

/**
 * @author 周宁
 * @Date 2018-09-19 8:43
 */
public class JwtTokenUtils {
    /**
     * 秘钥,其实是gytokeninfo的md5加密字符串哈哈
     */
    private static final byte[] SECRET = "f6e4c8851e5e78d6be296c0bd94346b3".getBytes();
    /**
     * 初始化head部分的数据为
     * {
     * "alg":"HS256",
     * "type":"JWT"
     * }
     */
    private static final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);


    /**
     * 生成token方法，token默认有效时长为30s
     *
     * @param userBasicInfo
     * @return String
     * @throws Exception
     */
    public static String createToken(UserBasicInfo userBasicInfo) throws JOSEException {
        Long currentMills = getWebsiteTimeMills();
        Map<String, Object> payload = new HashMap();
        payload.put("epid", userBasicInfo.getEpid());
        payload.put("userName", userBasicInfo.getUserName());
        payload.put("productNum", userBasicInfo.getProductNum());
        payload.put("iat", currentMills);
        //10秒过期
        payload.put("ext", currentMills + 10000);
        //添加uuid防止重复
        payload.put("uid", IdGenerator.newShortId());
        String tokenString = null;
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        jwsObject.sign(new MACSigner(SECRET));
        tokenString = jwsObject.serialize();
        return tokenString;
    }

}
