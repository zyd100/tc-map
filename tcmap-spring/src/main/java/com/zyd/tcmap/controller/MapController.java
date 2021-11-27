package com.zyd.tcmap.controller;

import com.zyd.tcmap.dto.Result;
import com.zyd.tcmap.util.TcMapUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zyd
 */
@CrossOrigin("*")
@RestController
@RequestMapping("map")
public class MapController {

    /**
     * 建议使用时改为配置文件配置
     */
    private final String KEY = "你的key";
    private final String SECRET_KEY = "你的Secret key";


    /**
     * 关键词输入提示
     *
     * @param keyword 关键词
     * @param region  城市
     * @return
     */
    @GetMapping("suggestion")
    public Result suggestion(@RequestParam(name = "keyword") String keyword, @RequestParam(name = "region", required = false) String region) {

        TcMapUtil.ParamBuilder builder = TcMapUtil.newParamBuilder()
                .param("keyword", keyword);
        if (StringUtils.hasText(region)) {
            builder.param("region", region);
        }
        return Result.response(TcMapUtil.suggestion(builder.build(), KEY, SECRET_KEY), 0, null);
    }

    /**
     * 坐标解析
     *
     * @param location 坐标
     * @return
     */
    @GetMapping("geocoder")
    public Result geocoder(@RequestParam(name = "location") String location) {
        Map param = TcMapUtil.newParamBuilder()
                .param("location", location)
                .param("get_poi", "0")
                .build();
        return Result.response(TcMapUtil.geocode(param, KEY, SECRET_KEY), 0, null);
    }
}
