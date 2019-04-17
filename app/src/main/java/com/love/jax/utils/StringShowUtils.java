package com.love.jax.utils;

import android.text.TextUtils;

import com.love.jax.bean.LettersEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * tailife
 * Created by jax_wog on 2018/10/18.
 * Todo:字符串处理
 */

public class StringShowUtils {

    private static final String HEAD_AFTER = "<font color='#517DF7'>";
    private static final String End_AFTER = "</font>";
    private static final String HEAD_BEFORE = "<em>";
    private static final String END_BEFORE = "</em>";

    /**
     * 截取固定长度字符串
     *
     * @param word   原始字符串
     * @param length 需要截取的字符串长度
     * @param add    字符串后面追加内容
     * @return 截取后的字符串
     */
    public static StringBuffer interceptString(String word, int length, String add) {
        StringBuffer sBuffer = new StringBuffer(word);
        if (word.isEmpty() || length <= 0 || length >= word.length()) {
            return sBuffer;
        }
        sBuffer.setLength(length);
        sBuffer.append(add);
        return sBuffer;
    }

    /**
     * 判断字符串是否全部为数字,消除中间空格影响
     *
     * @param word 需要判断的字符串
     * @return
     */
    public static boolean isNumberStr(String word) {
        if (word.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]");
        char c[] = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (String.valueOf(c[i]).equals(" ")) {
                continue;
            }
            Matcher isNum = pattern.matcher(String.valueOf(c[i]));
            if (!isNum.matches()) {
                return false;
            }
        }
        return true;//全数字
    }

    /**
     * 判断字符串是否全部为数字
     *
     * @param word 需要判断的字符串
     * @return
     */
    public static boolean isNumberStr2(String word) {
        if (word.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(word);
        if (isNum.matches()) {
            return true;//全数字
        }
        return false;
    }

    public static boolean getWordNumOrLett(String word){
        if (word.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9A-Za-z]*");
        Matcher isNum = pattern.matcher(word);
        if(isNum.matches() ){
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为拼音
     * @param word 需要判断的字符串
     * @return
     */
    public static boolean isletters(String word){
        Pattern pattern = Pattern.compile("[a-z]*");
        Matcher isNum = pattern.matcher(word);
        if(isNum.matches() ){
            return true;
        }
        return false;
    }


    /**
     * 查询相关的数据内容
     *
     * @param list 传入原始任务数据
     * @param word 需要高亮的字符串
     * @return 返回高亮后的列表
     */
    public static List<LettersEntity> hitTargetLet(List<LettersEntity> list, String word) {
        List<LettersEntity> tempList = new ArrayList<>();
        LettersEntity entity = new LettersEntity();
        for (int i = 0; i < list.size(); i++) {

            //如果为英文
            if (isletters(word)){
                //如果包含字符串，添加到list中返回
                if (isInclu(list.get(i).getTitleLetter(), word)) {
//需要判断对应的位置转换为具体的文字进行变色
//                    list.get(i).setTitle(getTaget(list.get(i).getTitle(), word));
                    list.get(i).setTitle(getAryIndex(list.get(i).getTitle(),word))  ;
                    tempList.add(list.get(i));
                }
            }else {
                //如果包含字符串，添加到list中返回
                if (isInclu(list.get(i).getTitle(), word)) {
                    list.get(i).setTitle(getTaget(list.get(i).getTitle(), word));
                    tempList.add(list.get(i));
                }
            }

        }

        //如果匹配为空进行，显示全部数据内容或者不显示内容
//        if (ListUtils.isEmpty(tempList)) {
//            return list;
//        }
        return tempList;

    }


    public static String getTagetLet(String s1, String s2) {
        StringBuffer sBuffer = new StringBuffer(HEAD_AFTER);
        sBuffer.append(s2);
        sBuffer.append(End_AFTER);
        s1 = s1.replace(s2, sBuffer);
        return s1;
    }



    /**
     * 判断字符串是否全部为汉字（不包含标点特殊字符等）
     *
     * @param word 需要判断的字符串
     * @return
     */
    public static boolean isChineseStr(String word) {
        if (word.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        char c[] = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            //消除中间空格影响
            if (String.valueOf(c[i]).equals(" ")) {
                continue;
            }
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;//全中文
    }

    /**
     * 替换查询高亮标签
     */
    public static String highLight(String word) {
        if (word.isEmpty()) {
            return null;
        }
        word = word.replaceAll(HEAD_BEFORE, HEAD_AFTER);
        word = word.replaceAll(END_BEFORE, End_AFTER);

        return word;

    }

    /**
     * 删除标签<em></em>
     *
     * @param word
     * @return
     */
    public static String delTag(String word) {
        if (word.isEmpty()) {
            return null;
        }
        word = word.replaceAll(HEAD_AFTER, "");
        word = word.replaceAll(End_AFTER, "");

        return word;

    }



    /**
     * 查询相关的数据内容
     *
     * @param list 传入原始任务数据
     * @param word 需要高亮的字符串
     * @return 返回高亮后的列表
     */
    public static List<String> hitTargets(List<String> list, String word) {
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //如果包含字符串，添加到list中返回
            if (isInclu(list.get(i), word)) {
                tempList.add(getTaget(list.get(i), word));
            }
        }
        if (ListUtils.isEmpty(tempList)) {
            return list;
        }
        return tempList;

    }

    /**
     * @param s1
     * @param str
     * @return
     */
    public static boolean isInclu(String s1, String str) {
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (s1.indexOf(str) != -1) {
            return true;
        }
        return false;
    }

    public static String getTaget(String s1, String s2) {
        StringBuffer sBuffer = new StringBuffer(HEAD_AFTER);
        sBuffer.append(s2);
        sBuffer.append(End_AFTER);
        s1 = s1.replace(s2, sBuffer);
        return s1;
    }




    /**
     * 根据位置获取数组下标,需要进行算法优化（二分法查找）
     * @param title 数据的title(汉字)
     * @param content 搜索框中输入的内容
     * @return 标记后的title
     */
    public static String getAryIndex(String title,String content){
      int k =  CharacterParser.getInstance().getSelling(title).indexOf(content);

//        int k = titleLet.indexOf(content);
        if (k==-1){
            return title;
        }
        int [] arr = CharacterParser.getInstance().getArrLeg();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>k){
              return  getStrTag(title,i);
            }
        }
        return title;
    }



    /**
     * 将下标位置进行替换
     * @param title  数据的title（汉字）
     * @param loc 标记的汉字所在的位置
     * @return 标记后的title
     */
    public static String getStrTag(String title,int loc){
        if (loc>title.length()){
            return title;
        }
     return getTaget(title,String.valueOf(title.charAt(loc)));


    }

}
