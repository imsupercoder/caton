package cn.smart.caton.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/7/8.
 */
public class RequestUtil {
    public static Map<String,String> getParamMap(HttpServletRequest request){
        Map<String,String[]> orgMap = request.getParameterMap();
        Map<String,String> returnMap = new HashMap<>();
        if(orgMap==null)
            return  returnMap;
        for(Map.Entry<String,String[]> entry : orgMap.entrySet()){
            returnMap.put(entry.getKey(),entry.getValue()==null?"":entry.getValue()[0]);
        }
        return returnMap;
    }
}
