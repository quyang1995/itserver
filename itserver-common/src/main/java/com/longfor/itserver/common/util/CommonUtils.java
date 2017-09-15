package com.longfor.itserver.common.util;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DatatablesHelper;
import com.longfor.itserver.common.helper.JoddHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jodd.props.Props;
import net.mayee.commons.TimeUtils;
import net.mayee.commons.entity.api.APIObject;
import net.mayee.commons.entity.api.Property;
import net.mayee.commons.helper.APIHelper;
import net.mayee.encrypt.JudiEncrypt;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.*;

import static com.longfor.itserver.common.constant.ConfigConsts.PLACE_HOLDER;

/**
 * 系统的通用类,封装了常用方法
 *
 * @author mayee
 * Created on 2017/3/29 16:26
 *
 * @version v1.0
 */
public class CommonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);

    private static BASE64Encoder encoder = new BASE64Encoder();
    private static List<String> RESERVED_NAME_LIST = new ArrayList<>();
    public static List<String> TOKEN_LIST_UAT = new ArrayList<>();
    public static List<String> TOKEN_LIST_PROD = new ArrayList<>();

    static {
        RESERVED_NAME_LIST.add("serialVersionUID");
        TOKEN_LIST_UAT.add("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJwY3MiLCJpYXQiOjE0OTg1MzU3NDksInN1YiI6InBjcyIsImlzcyI6ImFnYXRlIn0.tmHpnupQsmzR1LuDjttZcRm_AQi77PJbesMAA1hRQU4");
        TOKEN_LIST_PROD.add("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpdHBsdXNwcm9kIiwiaWF0IjoxNTA0NzU2NTU4LCJzdWIiOiJpdHBsdXNwcm9kIiwiaXNzIjoiaXRwbHVzcHJvZCJ9.Bv3Z4NPX8VPnBt1WiNJMUm0RQPJBpbZyPmHT8m8UZpM");
    }

    public static boolean isDevelopment() {
        return "development".equals(JoddHelper.getInstance().getJoddProps().getActiveProfiles()[0]);
    }

    public static boolean isProduction() {
        return "production".equals(JoddHelper.getInstance().getJoddProps().getActiveProfiles()[0]);
    }

    public static boolean isUat() {
        return "uat".equals(JoddHelper.getInstance().getJoddProps().getActiveProfiles()[0]);
    }

    public static String getSSOLoginURL(){
        return JoddHelper.getInstance().getJoddProps().getValue("web.sso.url");
    }

    /**
     * 获取post请求中的数据
     *
     * @param is
     * @return
     */
    public static String getContent(InputStream is) {
        String pageString = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            isr = new InputStreamReader(is, ConfigConsts.UTF8);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            pageString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb = null;
        }
        return pageString;
    }

    public static String getJSONString(JSONArray array, long totalDisplayRecords) {
        JSONObject result = new JSONObject();
        String totalRecordsKey = DatatablesHelper.getInstance().getGlobalValue("totalRecordsKey");
        String totalDisplayRecordsKey = DatatablesHelper.getInstance().getGlobalValue("totalDisplayRecordsKey");
        String dataKey = DatatablesHelper.getInstance().getGlobalValue("dataKey");
        try {
            result.put(totalRecordsKey, totalDisplayRecords);
            result.put(totalDisplayRecordsKey, totalDisplayRecords);
            result.put(dataKey, array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    /**
     * SHA-256加密方法
     *
     * @param input
     * @param salt
     * @return
     */
    public static byte[] sha256(byte[] input, byte[] salt) {
        return JudiEncrypt.sha256(input, salt);
    }

    /**
     * AES加密
     *
     * @param input
     * @param key
     * @return
     */
    public static String aesEncrypt(String input, String key) {
        return JudiEncrypt.aesEncrypt(input, key);
    }

    /**
     * AES解密
     *
     * @param input
     * @param key
     * @return
     */
    public static String aesDecrypt(String input, String key) {
        return JudiEncrypt.aesDecrypt(input, key);
    }

    /**
     * 获取SHA-256加密时使用的盐的长度,在配置文件中定义的
     *
     * @return
     */
    public static int getSaltLength() {
        String saltLength = JoddHelper.getInstance().getJoddProps().getValue("encrypt.sha.salt.size");
        return Integer.parseInt(saltLength) * 2;
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= (cs[0] > 96 && cs[0] < 123) ? 32 : 0;
        return String.valueOf(cs);

    }

    /**
     * 不考虑编码的情况下截取字符串长度
     *
     * @param str
     * @param length
     * @return
     */
    public static String subStringByLength(String str, int length) {
        if (str != null && str.length() > length) {
            return StringUtils.substring(str, 0, length) + "...";
        } else {
            return str;
        }
    }

    /**
     * BGK编码的情况下截取字符串长度
     *
     * @param str
     * @param subSLength
     * @return
     */
    public static String subStringByViewLength(String str, int subSLength)
            throws UnsupportedEncodingException {
        if (str == null)
            return "";
        else {
            int tempSubLength = subSLength;
            String subStr = str.substring(0, str.length() < subSLength ? str.length() : subSLength);
            int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度
            while (subStrByetsL > tempSubLength) {
                int subSLengthTemp = --subSLength;
                subStr = str.substring(0, subSLengthTemp > str.length() ? str.length() : subSLengthTemp);
                subStrByetsL = subStr.getBytes("GBK").length;
            }
            return !str.equals(subStr) ? subStr + "..." : subStr;
        }
    }


    public static int millis2Minute(long millis) {
        if (millis < 60000)
            return 1;
        else
            return (int) millis / 60000;
    }

    public static double millis2Second(long millis) {
        return (double) millis / 1000;
    }

    /**
     * 对于自定义headers的处理
     * @param resp
     */
    private static void setResponseCross(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", ConfigConsts.TOKEN_NAME);
        resp.setHeader("Access-Control-Max-Age", "1728000");
    }

    public static void printWriteWithEnum(HttpServletResponse resp, BizEnum bizEnum) throws IOException {
        setResponseCross(resp);
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Access-Control-Allow-Headers", ConfigConsts.TOKEN_NAME + ",content-type");
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(getResultMapByBizEnum(bizEnum)));
    }

    public static void printWriteWithMap(HttpServletResponse resp, Map<String, String> map) throws IOException {
        setResponseCross(resp);
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "no-store");
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(map));
    }

    /**
     * 获取API ID
     *
     * @param servletPath
     * @return
     */
    public static String getAPIId(String servletPath) {
        String apiId = servletPath.replaceAll("/", "_");
        if (apiId.startsWith("_")) {
            apiId = apiId.substring(1, apiId.length());
        }
        return apiId;
    }

    /**
     * 根据异常编码返回Json格式字符串
     * 第一个参数为BizEnum
     * 后面可变参数为国际化中的变量
     *
     * @param bizEnum
     * @param val
     * @return
     */
    public static Map<String, Object> getResultMapByBizEnum(BizEnum bizEnum, String... val) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(APIHelper.CODE, bizEnum.getCode());

        if (val != null) {
            String msg = bizEnum.getText();
            for (String aVal : val) {
                msg = msg.replaceFirst(PLACE_HOLDER, aVal);
            }
            resultMap.put(APIHelper.MSG, msg);
        } else {
            resultMap.put(APIHelper.MSG, bizEnum.getText());
        }

        return resultMap;
    }

    /**
     * 根据根据自定义参数，返回Json格式字符串
     *
     * @param code
     * @param msg
     * @return
     */
    public static Map<String, Object> getResultMap(String code, String msg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(APIHelper.CODE, code);
        resultMap.put(APIHelper.MSG, msg);
        return resultMap;
    }


    /**
     * 用于访问https接口
     */
    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    /**
     * 生成n位随机数字字符串
     *
     * @return
     */
    public static String generateRandomData(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static String getImageBinary(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, file.getName().substring(file.getName().lastIndexOf(".") + 1), baos);
        byte[] bytes = baos.toByteArray();
        return encoder.encodeBuffer(bytes).trim();
    }

    public static boolean isTokenExpired(Claims claims) {
        return Calendar.getInstance().getTime().after(claims.getExpiration());
    }

    public static String createToken() {
        return Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, MacProvider.generateKey())
                .compact();
    }

    public static String createJWT(String id, String issuer, String subject, long ttlMillis, String key) throws UnsupportedEncodingException {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = key.getBytes(ConfigConsts.UTF8);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 生成JWT规范的token,JJWT实现
     *
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) throws UnsupportedEncodingException {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        String keys = "bad57711ec31f2148faa4a9d75ae1800";
        //We will sign our JWT with our ApiKey secret
        //String key = JoddHelper.getInstance().getJoddProps().getValue("token.key");
        String key = JoddHelper.getInstance().getJoddProps().getValue(keys);
        byte[] apiKeySecretBytes = key.getBytes(ConfigConsts.UTF8);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        String key = "bad57711ec31f2148faa4a9d75ae1800";
//        String key = JoddHelper.getInstance().getJoddProps().getValue("token.key");
        return Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(jwt).getBody();
    }

    public static HashMap<Long, Object> list2Map(List list) {
        HashMap<Long, Object> map = new HashMap<Long, Object>();
        for (Object obj : list) {
            Class clazz = obj.getClass();
            try {
                Method getId = clazz.getDeclaredMethod("getId");
                Long id = (Long) getId.invoke(obj);
                map.put(id, obj);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 将map转成对象
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T map2Object(Map<String, String> map, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * 将对象转成map
     *
     * @param map
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Map<String, Object> object2Map(Map<String, String> map, Object obj) throws IllegalAccessException, InstantiationException {
        Map<String, Object> resultMap = getResultMapByBizEnum(BizEnum.SSSS);
        if(obj == null){
            return getResultMapByBizEnum(BizEnum.E9994);
        }
        String apiId = map.get(APIHelper.API_ID);
        if (StringUtils.isNotBlank(apiId)) {
            APIObject apiObject = APIHelper.getInstance().getAPIObject(apiId);

            //遍历一次,数据预处理
            List<Property> propertyList = apiObject.getResponsePropertyList();
            List<String> outputList = new ArrayList<>();
            List<String> exceptList = new ArrayList<>();
            for (Property property : propertyList) {
                if (!property.isExcept()) {
                    outputList.add(property.getName());
                } else {
                    exceptList.add(property.getName());
                }
            }

            //定义的全是例外字段,那么把要输出的字段放到outputList中
            if (outputList.size() == 0 && exceptList.size() > 0) {
                Field[] fs = obj.getClass().getDeclaredFields();
                for (Field f : fs) {
                    if (!exceptList.contains(f.getName()) && !RESERVED_NAME_LIST.contains(f.getName())) {
                        outputList.add(f.getName());
                    }
                }
            }

            //输出get方法的值到map中
            outputList.forEach(item -> {
                try {
                    Method get = obj.getClass().getMethod("get" + captureName(item));
                    Object val = get.invoke(obj);
                    if (val instanceof String) {
                        resultMap.put(item, (String) val);
                    } else if (val instanceof Date) {
                        Date date = (Date) val;
                        resultMap.put(item, TimeUtils.getTime(date.getTime(), TimeUtils.JDATE_FORMAT_DEFAULT));
                    } else if (val instanceof Integer) {
                        resultMap.put(item, (Integer) val);
                    } else if (val instanceof Long) {
                        resultMap.put(item, (Long) val);
                    } else if (val instanceof Double) {
                        resultMap.put(item, (Double) val);
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return resultMap;
    }

    /**
     * 登录时验证码检查
     *
     * @param viCode
     * @param request
     * @return
     */
    private static boolean checkValidateImg(String viCode, HttpServletRequest request) {
        if ("false".equals(JoddHelper.getInstance().getJoddProps().getValue("test.login.validateimg.check")))
            return true;

        if (StringUtils.isBlank(viCode) || viCode.trim().length() != 4)
            return false;

        String vi_code_session = (String) request.getSession().getAttribute(
                ConfigConsts.ADMIN.SESSIONKEY_LOGIN_VALIDATEIMG);
        return viCode.toUpperCase().equals(vi_code_session);
    }

    public static void removeValidateImg(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute(ConfigConsts.ADMIN.SESSIONKEY_LOGIN_VALIDATEIMG) != null) {
            session.removeAttribute(ConfigConsts.ADMIN.SESSIONKEY_LOGIN_VALIDATEIMG);
            session.removeAttribute(ConfigConsts.ADMIN.SESSIONKEY_LOGIN_VALIDATEIMG_VALUE);
        }
    }

    /**
     * 获取最新编码
     * 格式: 三位字母 + 二位数字
     *
     * @param letter
     * @param number
     * @return
     */
    public static String getNewCode(String letter, String number) {
        char c_first = letter.charAt(0);
        char c_mid = letter.charAt(1);
        char c_last = letter.charAt(2);
        int number_int = Integer.parseInt(number);
        if (number_int > 98) {
            if (c_last == 90) {
                c_last = 65;
                if (c_mid == 90) {
                    c_mid = 65;
                    if (c_first == 90) {
                        //game over
                        return null;
                    } else {
                        c_first++;
                    }
                } else {
                    c_mid++;
                }
            } else {
                c_last++;
            }
            String reLetter = String.valueOf(c_first) + String.valueOf(c_mid) + String.valueOf(c_last);
            return reLetter + "01";
        } else {
            String newNumber = ++number_int + "";
            if (newNumber.length() < 2) {
                newNumber = "0" + newNumber;
            }
            return letter + newNumber;
        }
    }



}