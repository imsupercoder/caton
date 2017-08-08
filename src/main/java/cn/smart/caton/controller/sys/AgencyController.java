/*
 * Powered By [wang lei]
 * Web Site: https://github.com/imsupercoder/generator
 * email:840996551@qq.com
 * Since 2017
 */


package cn.smart.caton.controller.sys;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smart.caton.controller.BaseController;
import cn.smart.caton.model.sys.Agency;
import cn.smart.caton.service.sys.AgencyService;
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
@RequestMapping("/agency")
public class AgencyController extends BaseController {

    @Autowired
	private AgencyService agencyService;

	@RequestMapping(value = "/agencys",method = RequestMethod.GET)
    public List<Agency> list(HttpServletRequest request){
        return agencyService.findList(RequestUtil.getParamMap(request));
    }
    @RequestMapping(value ="/delete",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("id")String id){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",agencyService.delete(id)==1?true:false);
        return returnMap;
    }
    @RequestMapping(value ="/agency",method = RequestMethod.POST)
    public Map<String,Object> insertOrUpdate(@RequestBody Agency agency){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("flag",agencyService.insertOrUpdate(agency)==1?true:false);
        return returnMap;
    }
	
}

