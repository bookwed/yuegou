package com.wei.controller.commons;

;import com.wei.service.commons.CommonFileService;
import com.wei.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description
 * Author ed
 * Created 2017-08-03 17:35
 */
@RequestMapping(value = "/files/")
public class CommonFileController {

    @Autowired
    private CommonFileService commonFileService;

    @RequestMapping(value = "findPath/{id}")
    @ResponseBody
    public Response findPath(@PathVariable("id") String id){
        String path = commonFileService.findPath(id);
        return new Response().success(path);
    }
}
