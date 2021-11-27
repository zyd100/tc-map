package com.zyd.tcmap.util;

import com.zyd.tcmap.consts.TcMapConstant;
import org.apache.http.client.utils.URIBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.zyd.tcmap.util.Md5Util.stringToMD5;

/**
 * @author zyd
 * 腾讯位置服务封装
 */
public class TcMapUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(TcMapUtil.class);


    public static JsonNode suggestion(Map<String, String> params, String key, String nonStr) {
        Map<String, String> resultMap = addSign(TcMapConstant.SUGGESTION_URI, params, key, nonStr);
        String finalUrl = buildUrl(TcMapConstant.BASE_URL + TcMapConstant.SUGGESTION_URI, resultMap);
        LOGGER.debug("finalUrl:{}",finalUrl);
        return RestTemplateUtils.get(finalUrl, JsonNode.class).getBody();
    }

    public static JsonNode geocode(Map<String, String> params, String key, String nonStr) {
        Map<String, String> resultMap = addSign(TcMapConstant.GEOCODER_URI, params, key, nonStr);
        String finalUrl = buildUrl(TcMapConstant.BASE_URL + TcMapConstant.GEOCODER_URI, resultMap);
        LOGGER.debug("finalUrl:{}",finalUrl);
        try {
            //encoder之后返回坐标格式错误，所以decoder一次
            return RestTemplateUtils.get(URLDecoder.decode(finalUrl,"UTF-8"), JsonNode.class,resultMap).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> addSign(String uri, Map<String, String> params, String key, String nonStr) {
        Map<String, String> resultMap;
        params.put("key", key);
        resultMap = new TreeMap<>(Comparator.naturalOrder());
        resultMap.putAll(params);
        LOGGER.debug("params:{}",params);

        String needSign = null;
        try {
            needSign = URLDecoder.decode(getSign(uri, nonStr, resultMap),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LOGGER.debug("needSign:{}",needSign);
        String sig = stringToMD5(needSign);
        resultMap.put("sig", sig);
        LOGGER.debug("最终参数:{}",resultMap);
        return resultMap;
    }

    private static String getSign(String uri, String nonStr, Map<String, String> resultMap) {
        return buildUrl(uri, resultMap) + nonStr;
    }

    public static String buildUrl(String url, Map<String, String> param) {
        try {
            URIBuilder builder = new URIBuilder(url);
            param.forEach(builder::addParameter);
            return builder.build().toString();
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    public static ParamBuilder newParamBuilder() {
        return new ParamBuilder();
    }

    public static class ParamBuilder {
        private Map<String,String>map;

        ParamBuilder() {
            map=new HashMap<>();
        }


        public ParamBuilder param(String key, String value) {
            map.put(key, value);
            return this;
        }

        public Map build() {
            return map;
        }

        public String toString() {
            return map.toString();
        }
    }
}
