package cn.smart.caton.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: UniqueKeyGenerator
 * @Description: TODO 生成时间格式+3个随机数的唯一值
 * @author: wanglei
 * @date: 2016年8月23日 下午2:39:24
 */

public class UniqueKeyGenerator {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String getUniqueKeyId()
    {
        String dateStr = sdf.format(new Date());
        int randomInt = (int)(Math.random()*900+100);
        String randomStr = String.valueOf(randomInt);
        return dateStr+randomStr;
    }
}
