package cn.leadeon.utils.nicknamegGeneration;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bjcathay on 2017/11/2.
 */
public class ConfigNicktype {

    //0为昵称生成时不带8位字符，昵称为央视网友  1为生成生成时带8位字符
    static public final Integer type = 1;

    //    输出注册时默认昵称央视网友是否带8位后缀 0代表输出默认昵称时去掉8位后缀。
    static public final Integer ctype = 1;

    //    第三方昵称是否先发后审 1是 0否
    static public final Integer thirdname = 1;
    //    第三方头像是否先发后审 1是 0否
    static public final Integer thirdhead = 1;

    /**
     * @var array  第三方登录类型  用于在审核日志上注明是哪种类型的审核，以便于用不同的方式处理审核结果，如果有新第三方类型，请添加上
     *
     * ## 注意 头像为奇称为偶数
     */
    static public Map thirdType =new HashMap();


    /**
     * @var array 第三方登录类型，如有新第三方类型，请添加上
     *
     * ## 注意 头像为奇数  昵称为偶数
     *
     */
    static public Map thirdTypeR =new HashMap();


    static {
        Map m= new HashMap<>();
        m.put("face",51);
        m.put("name",52);
        thirdType.put("weixin",m);
        Map m1= new HashMap<>();
        m.put("face",53);
        m.put("name",54);
        thirdType.put("qzone",m1);
        Map m2= new HashMap<>();
        m.put("face",55);
        m.put("name",56);
        thirdType.put("sina",m2);

        Map m3= new HashMap<>();
        m.put("face",57);
        m.put("name",58);
        thirdType.put("renren",m3);

        Map m4= new HashMap<>();
        m.put("face",59);
        m.put("name",60);
        thirdType.put("kaixin",m4);
        Map m5= new HashMap<>();
        m.put("face",61);
        m.put("name",62);
        thirdType.put("kaixin",m5);




    }



}
