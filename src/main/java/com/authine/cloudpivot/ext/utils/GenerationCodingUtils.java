package com.authine.cloudpivot.ext.utils;

import java.text.DecimalFormat;

public class GenerationCodingUtils {

    private static int totalCount = 0;



    private int customerID;
    public GenerationCodingUtils(){
        ++totalCount;
        customerID = totalCount;
        //System.out.println("增加一个");
    }

    public String getGenerationCoding() {
        DecimalFormat decimalFormat = new DecimalFormat("000");
        return decimalFormat.format(customerID);
    }

    public String getGenerationCoding1() {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        return decimalFormat.format(customerID);
    }
    public String getGenerationCoding2() {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return decimalFormat.format(customerID);
    }

    public static void main(String[] args) {
        GenerationCodingUtils c1 = new GenerationCodingUtils();
        System.out.println(c1.getGenerationCoding());
        GenerationCodingUtils c2 = new GenerationCodingUtils();
        System.out.println(c2.getGenerationCoding());

    }


}
