package com.wei.controller.commons;

;import com.wei.model.commons.CommonFile;
import com.wei.service.commons.CommonFileService;
import com.wei.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description
 * Author ed
 * Created 2017-08-03 17:35
 */
@Controller
@RequestMapping(value = "/files/")
public class CommonFileController {

    @Autowired
    private CommonFileService commonFileService;

    /**
     * demo
     * @param id
     * @return
     */
    @RequestMapping(value = "findPath/{id}")
    @ResponseBody
    public Response findPath(@PathVariable("id") String id){
        String path = commonFileService.findPath(id);
        return new Response().success(path);
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public Response add(CommonFile commonFile){
        try {
            Integer id = commonFileService.insert(commonFile);
            return new Response().success(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response().failure();
    }



}
