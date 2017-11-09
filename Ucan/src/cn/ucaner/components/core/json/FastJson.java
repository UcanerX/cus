/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.components.core.json;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 阿里巴巴fastjson
 *
 * @auther yangzhao
 * create by 17/10/9
 */
public class FastJson implements IJsonInterface {
    @Override
    public <T> T parseObject(String json, Class<T> t) {
        T t1 = JSON.parseObject(json, t);
        return t1;
    }

    @Override
    public <T> List<T> parseList(String json, Class<T> t) {
        List<T> tList = JSON.parseArray(json, t);
        return tList;
    }

    @Override
    public String toJsonString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
