package com.authine.cloudpivot.ext.utils;

import java.text.DecimalFormat;

public class Test {

    /**
     * 生成规则编号:jobcode+四位编号（从1开始，不够前补0）
     * @param jobcode
     * @param equipmentNo 最新编号
     * @return
     */
    public static String getGenerationCoding(String jobcode, String equipmentNo){
        String newEquipmentNo = "001";

        if(equipmentNo != null && !equipmentNo.isEmpty()){
            int newEquipment = Integer.parseInt(equipmentNo) + 1;
            newEquipmentNo = String.format(jobcode + "%05d", newEquipment);
        }
        return newEquipmentNo;
    }


    public static void main(String[] args) {
        String equipmentNo = Test.getGenerationCoding("SYXH19930604", "");
        //System.out.println("生成设备编号：" + equipmentNo);

        String a = "1";
        String b = "456";
        String c = a + b;


        Integer integer1 = 64;
        DecimalFormat decimalFormat1 = new DecimalFormat("00000");
        String str1 = decimalFormat1.format(integer1);
        System.out.println("str1的值为:::::::"+str1);
        String string1 = String.valueOf(str1);
        System.out.println("string1的值为:::::::"+string1);
        int i1 = string1.length();
        String str11 = string1.substring(i1-1,i1);

        Integer integer2 = 64;
        DecimalFormat decimalFormat2 = new DecimalFormat("00000");
        String str2 = decimalFormat2.format(integer2);
        System.out.println("str2的值为:::::::"+str2);
        String string2 = String.valueOf(str2);
        System.out.println("string2的值为:::::::"+string2);
        int i2 = string2.length();
        String str22 = string2.substring(i2-2,i2);

        Integer integer3 = 64;
        DecimalFormat decimalFormat3 = new DecimalFormat("00000");
        String str3 = decimalFormat3.format(integer3);
        System.out.println("str3的值为:::::::"+str3);
        String string3 = String.valueOf(str3);
        System.out.println("string3的值为:::::::"+string3);
        int i3 = string3.length();
        String str33 = string3.substring(i3-3,i3);

        System.out.println(str11);
        System.out.println(str22);
        System.out.println(str33);
    }
}
