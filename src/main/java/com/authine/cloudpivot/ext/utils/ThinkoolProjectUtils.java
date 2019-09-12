package com.authine.cloudpivot.ext.utils;

public class ThinkoolProjectUtils {
    private static String  THINKOOL_IP="http://47.103.123.171";

    /**
     * 获取代办URL
     * @param workitemId
     * @param workflowInstanceId
     * @return
     */
    public static String getWoritemUrl(String workitemId,String workflowInstanceId){
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append(THINKOOL_IP);
        fromUrl.append("/form/detail?");
        fromUrl.append("workitemId=").append(workitemId);
        fromUrl.append("&workflowInstanceId=").append(workflowInstanceId);
        fromUrl.append("&return=/workflow-center/my-unfinished-workitem");
        return fromUrl.toString();
    }

    public static String getFormUrl(String schemaCode,String objectId,String sheetCode,String parentId,String applicationName){
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append(THINKOOL_IP);
        fromUrl.append("/form/detail?");
        fromUrl.append("sheetCode=").append(sheetCode);
        fromUrl.append("&objectId=").append(objectId);
        fromUrl.append("&schemaCode=").append(schemaCode);
        fromUrl.append("&return=/application/").append(applicationName);
        fromUrl.append("/application-list/project_summary?parentId=");
        fromUrl.append(parentId);
        fromUrl.append("&code=project_summary");
        fromUrl.append("&openMode");
        fromUrl.append("&pcUrl");
        return fromUrl.toString();
    }
}
