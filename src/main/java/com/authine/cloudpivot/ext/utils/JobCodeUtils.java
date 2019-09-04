package com.authine.cloudpivot.ext.utils;

import java.text.DecimalFormat;

public class JobCodeUtils {

    private static int totalCount = 0;

    private int customerID;
    public JobCodeUtils(){
        ++totalCount;
        customerID = totalCount;
        System.out.println("增加一个");
    }

    public String getCustomerID() {
        DecimalFormat decimalFormat = new DecimalFormat("000000");
        return decimalFormat.format(customerID);
    }

    public static void main(String[] args) {
        JobCodeUtils c1 = new JobCodeUtils();
        System.out.println(c1.getCustomerID());
        JobCodeUtils c2 = new JobCodeUtils();
        System.out.println(c2.getCustomerID());

        String a = "ELC";
        String b = "MAC";
        int c = DateUtils.getYear();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a);
        stringBuffer.append(b);
        stringBuffer.append(c);

        String aaa = stringBuffer.toString();
        System.out.println(aaa);
    }


}
