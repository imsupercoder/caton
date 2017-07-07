package cn.smart.caton.util;

/**
 * Created by wl on 2017/7/7.
 */
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 继承自  {@link StringUtils}
 * 并且添加了常用的 String 、 String数组的操作
 * @author houzhaowei
 *
 */
public class StringUtil extends StringUtils{

    /**
     * 检测String是否为空（去除两侧空格）
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str == null){
            return true;
        }
        if(str.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 数组中是否包含target
     * @param array
     * @param target
     * @return 包含返回true，不包含返回false
     */
    public static boolean contains(String[] array,String target){
        boolean contains = false;
        for(String one : array){
            if(one.equals(target)){
                contains = true;
                break;
            }
        }
        return contains;
    }

    /**
     * 数组中是否有某个元素包含 target
     * @param array
     * @param target
     * @return 数组中某元素包含target则返回true，否则返回false
     */
    public static boolean containsItemContains(String[] array,String target){
        boolean contains = false;
        for(String one : array){
            if(target.indexOf(one) != -1){
                contains = true;
            }
        }
        return contains;
    }

    public static boolean isDate(String value,String format){

        SimpleDateFormat sdf = null;
        ParsePosition pos = new ParsePosition(0);//指定从所传字符串的首位开始解析

        if(value == null || isEmpty(format)){
            return false;
        }
        try {
            sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            Date date = sdf.parse(value,pos);
            if(date == null){
                return false;
            }else{
                if(pos.getIndex() > format.trim().length()){
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将指定位置的字符进行替换
     * @param source		原字符串
     * @param start	开始位置	 最小为1
     * @param end		结束位置         最大为字符串长度
     * @param regix			替换后的字符串
     * 例：将  123456789 转换成 123***789  则 start=4,end=6
     * @return
     */
    public static String replaceChar(String source,int start,int end,char regix){
        StringBuffer str = new StringBuffer(source);
        for(int i =start-1;i<end ;i++){
            str.setCharAt(i, regix);
        }
        return str.toString();
    }

    /**
     * 根据指定长度格式化给定字符串，位数不够时左补0。
     *
     * @param str
     *            指定字符串
     * @param len
     *            指定长度
     * @return
     */
    public static String fillBlank(String str, int len) {
        if (str == null)
            str = "0";

        if (len <= 0)
            len = 1;

        return String.format("%0" + len + "d", Integer.valueOf(str)).toString();
    }


    public static void main(String[] args) {
        System.out.println(replaceChar("6225550188010695", 7, 12,'*'));
        System.out.println(fillBlank("1", 8));
    }

}
