package com.authine.cloudpivot.ext.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class CnToSpell {

    // 将汉字转换为全拼
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += java.lang.Character.toString(t1[i]);
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    // 只返回中文的首字母大写
    public static String getPinYinHeadChar(String str) {
        String temp = "";
        String demo = "";
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else{
            convert += word;
            }
        }
        for (int i = 0; i < convert.length(); i++) {
            // convert目前为小写首字母,下面是将小写首字母转化为大写
            if (convert.charAt(i) >= 'a' && convert.charAt(i) <= 'z') {
                temp = convert.substring(i, i + 1).toUpperCase();
                demo += temp;
            }
        }
        return demo;
    }


    // 返回所有字段的大写的首字母(包括符号)
    public static String getPinYinHeadCharAll(String str) {

        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            //提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null){
                convert += pinyinArray[0].charAt(0);
            }else{
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    // 将字符串转移为ASCII码
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            //System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {

        String name = "圣罗兰YSLaaa";
        System.out.println(getPingYin(name));
        System.out.println(getPinYinHeadChar(name));
        System.out.println(getPinYinHeadCharAll(name));
        System.out.println(getCnASCII(name));


        System.out.println(CnToSpell.getPinYinHeadChar(name));

    }




}


