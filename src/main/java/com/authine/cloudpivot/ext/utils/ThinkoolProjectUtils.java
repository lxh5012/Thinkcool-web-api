package com.authine.cloudpivot.ext.utils;

public class ThinkoolProjectUtils {
    private static String  THINKOOL_IP="http://47.103.123.171";

    public static String getWoritemUrl(String workitemId,String workflowInstanceId){
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append(THINKOOL_IP);
        fromUrl.append("/form/detail?");
        fromUrl.append("workitemId=").append(workitemId);
        fromUrl.append("&workflowInstanceId=").append(workflowInstanceId);
        fromUrl.append("&return=/workflow-center/my-unfinished-workitem");
        return fromUrl.toString();
    }
}
