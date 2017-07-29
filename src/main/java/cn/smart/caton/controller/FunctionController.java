/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */


package cn.smart.caton.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smart.caton.model.Function;
import cn.smart.caton.service.FunctionService;
import cn.smart.caton.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author wanglei email:840996551@qq.com
 * @version 1.0
 * @since 1.0
 * */
@RestController
@RequestMapping("/function")
public class FunctionController extends BaseController{
	
	private FunctionService functionService;

	@RequestMapping(value = "/functions.do",method = RequestMethod.POST)
    public List<Function> list(HttpServletRequest request){
        return functionService.findList(RequestUtil.getParamMap(request));
    }
    @RequestMapping(value ="/delete.do",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",functionService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequestMapping(value ="/function.do",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody Function function){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",functionService.insertOrUpdate(function)==1?true:false);
        return returnMap;
    }
	
}

