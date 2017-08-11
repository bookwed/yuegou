package com.wei.controller.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

/**
 * Description
 * Author ed
 * Created 2017-08-11 14:15
 */
@Controller
@RequestMapping(value = "/menu/")
public class MenuController {


    /**
     * 根据权限，生成菜单
     * @return
     */
    @RequestMapping(value = "getMenu",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMenu(){
        try {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("id","code");
            item.put("text","首页代码");
            item.put("href","main/code.html");

            Map<String,Object> item2 = new HashMap<String,Object>();
            item2.put("id","main-menu");
            item2.put("text","顶部导航");
            item2.put("href","main/menu.html");

            List<Map<String,Object>> items = new ArrayList<Map<String, Object>>();
            items.add(item);
            items.add(item2);

            Map<String,Object> menu = new HashMap<String, Object>();
            menu.put("text","前台管理");
            menu.put("items",items);

            List<Map<String,Object>> menus = new ArrayList<Map<String, Object>>();
            menus.add(menu);

            Map<String,Object> config = new HashMap<String, Object>();
            config.put("id","menu");
            config.put("collapsed",true);
            config.put("homePage","code");
            config.put("menu",menus);

            List<Map<String,Object>> configs = new ArrayList<Map<String, Object>>();
            configs.add(config);

            ObjectMapper mapper = new ObjectMapper(); //转换器
            String json = mapper.writeValueAsString(configs);
            System.out.println(json);
            return json;
        }catch (Exception e){

        }
        return null;
    }

}
