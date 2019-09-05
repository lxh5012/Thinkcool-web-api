package com.authine.cloudpivot.ext.utils;

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
        System.out.println("生成设备编号：" + equipmentNo);

        String a = "123";
        String b = "456";
        String c = a + b;
        System.out.println(c);
    }
}
