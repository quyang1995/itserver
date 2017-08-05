package com.longfor.itserver.common.util;

import jodd.datetime.JDateTime;
import net.mayee.encrypt.JudiEncrypt;
import org.apache.shiro.codec.Hex;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author chi.zhang
 * @create 2017/6/15 下午3:20
 *
 * @version v1.0
 **/
public class JJWTUtil {

    private static String key = "2b122b926f70812077c517e6955ecdf1";

    //it+/itplus
    //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpdCsiLCJpYXQiOjE1MDE4OTg0MTksInN1YiI6Iml0KyIsImlzcyI6Iml0cGx1cyJ9.2GyK2lt_V4rryS_CiitbqenXAd1GeWMYW4RnZ8NCMlQ

    public static void main(String[] args) throws IOException {
//        System.out.println(Hex.encodeToString(JudiEncrypt.generateAesKey()));
        String projectId = "it+";
        String token = CommonUtils.createJWT(
                projectId,
                "itplus",
                projectId,//getSsoAccessKey
                -1,
                key);
        System.out.println("Token: " + token);

    }

}
