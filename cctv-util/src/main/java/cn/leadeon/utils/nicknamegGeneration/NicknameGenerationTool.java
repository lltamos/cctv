package cn.leadeon.utils.nicknamegGeneration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用戶昵稱生成算法工具
 *
 * @author wl
 * @version [1.0, 2017年11月2日]
 */
public class NicknameGenerationTool {
    //最大值
    private static final long MAXUID = 9223372036854775807L;
    //基礎數據
    static final private char[] arrChar = {'c', 'd', 'a', 'l', 'm', 'n', 'j', 'k', 'p', 'v', 'w', 'x', 'q', 'r', 's', 't', 'u', 'y', 'i', 'z', 'o', 'b', 'e', 'f', 'g', 'h'};
    static final private int[] arrNumber = {8, 6, 7, 0, 1, 4, 5, 2, 3, 9};
    static final private int[] arrBoundary = {456976000, 17576000, 1757600, 67600, 6760, 260, 26, 1};
    //前綴
    static private String preNickName = "央视网友";

    static private String isThirdLogin = null;

    // array('央视新闻网友'   ,4              ,1                 )
    // array('前缀'       ,    '随机字符个数','是否显示第三方昵称'
    static public Map preNameArr = new HashMap();

    {

        preNameArr.put("cctv_news_mobile", new Object[]{"央视新闻网友", 4, 0});
        preNameArr.put("cctvnews_app", new Object[]{"央视新闻网友", 4, 1});
        preNameArr.put("cbox_mobile", new Object[]{"央视网友", 8, 1});
        preNameArr.put("new_media", new Object[]{"央视新闻网友", 4, 1});
        preNameArr.put("norman", new Object[]{"央视网友", 8, 0});
    }


    /**
     * @param userId
     * @return bool|string 正常返回央视网友xxxxxxxx uid超出范围返回false
     */
    public String genUserNickName(Long userId) {
        if(userId >= this.MAXUID || userId < 1){
            return null;
        }
//        if((config_nicktype::$type==1)){
//            $uid = (int)$uid;
//            $prelen = 0;
//            switch($uid){
//                case ($uid >= self::$arrBoundary[0] && $uid < self::$MAXUID): {
//                    $prelen = 0;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[1] && $uid < self::$arrBoundary[0]): {
//                    $prelen = 1;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[2] && $uid < self::$arrBoundary[1]): {
//                    $prelen = 2;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[3] && $uid < self::$arrBoundary[2]): {
//                    $prelen = 3;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[4] && $uid < self::$arrBoundary[3]): {
//                    $prelen = 4;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[5] && $uid < self::$arrBoundary[4]): {
//                    $prelen = 5;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[6] && $uid < self::$arrBoundary[5]): {
//                    $prelen = 6;
//                    break;
//                }
//                case ($uid >= self::$arrBoundary[7] && $uid < self::$arrBoundary[6]): {
//                    $prelen = 7;
//                    break;
//                }
//            }
//
//            $nickname = '';
//            if($prelen > 0){
//                $i = 0;
//                while($i < $prelen){
//                    if($i%2 == 0){
//                        $nickname .= self::$arrNumber[0];
//                    }else{
//                        $nickname .= self::$arrChar[0];
//                    }
//                    $i++;
//                }
//            }
//            $len = count(self::$arrBoundary); //nickname:8c8c8c8a  len:8 prelen:7  //3975478  nickname :8l7
//            while($prelen < $len){
//                $mod = floor($uid/self::$arrBoundary[$prelen]);//2
//                if($prelen%2 == 0){
//                    $nickname .= self::$arrNumber[$mod];
//                }else{
//                    $nickname .= self::$arrChar[$mod];
//                }
//                $uid = $uid%self::$arrBoundary[$prelen];
//                $prelen++;
//            }
//
//            return self::$preNickName.$nickname;
//        }else{
//            return self::$preNickName;
//        }



        return null;
    }


}
