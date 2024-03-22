package com.project.resto.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by zyx on 2019/6/26.
 */
public class VersionUtil {

    //342>341
    public static boolean isMixVersionCanDo(String version,String minVersion){
        if (StringUtils.isEmpty(version)||StringUtils.isEmpty(minVersion)) {
            return false;
        }
        String[] versions = version.split("\\.");
        String[] minVersions = minVersion.split("\\.");
        String v0 = versions[0];
        String v1 = versions[1];
        String v2 = versions[2];

        String mv0 = minVersions[0];
        String mv1 = minVersions[1];
        String mv2 = minVersions[2];

        if (Integer.parseInt(v0)>Integer.parseInt(mv0)) return true;
        if (Integer.parseInt(v0)<Integer.parseInt(mv0)) return false;
        if (Integer.parseInt(v0)==Integer.parseInt(mv0)) {
            if (Integer.parseInt(v1)>Integer.parseInt(mv1)) return true;
            if (Integer.parseInt(v1)<Integer.parseInt(mv1)) return false;
            if (Integer.parseInt(v1)==Integer.parseInt(mv1)) {
                if (Integer.parseInt(v2)>Integer.parseInt(mv2)) return true;
                if (Integer.parseInt(v2)<Integer.parseInt(mv2)) return false;
                if (Integer.parseInt(v2)==Integer.parseInt(mv2)) return false;
            }
        }
        return true;
    }

    //342>=342
    public static boolean isMixVersionAndEqCanDo(String version,String minVersion){
        if (StringUtils.isEmpty(version)||StringUtils.isEmpty(minVersion)) {
            return false;
        }
        String[] versions = version.split("\\.");
        String[] minVersions = minVersion.split("\\.");
        String v0 = versions[0];
        String v1 = versions[1];
        String v2 = versions[2];

        String mv0 = minVersions[0];
        String mv1 = minVersions[1];
        String mv2 = minVersions[2];

        if (Integer.parseInt(v0)>Integer.parseInt(mv0)) return true;
        if (Integer.parseInt(v0)<Integer.parseInt(mv0)) return false;
        if (Integer.parseInt(v0)==Integer.parseInt(mv0)) {
            if (Integer.parseInt(v1)>Integer.parseInt(mv1)) return true;
            if (Integer.parseInt(v1)<Integer.parseInt(mv1)) return false;
            if (Integer.parseInt(v1)==Integer.parseInt(mv1)) {
                if (Integer.parseInt(v2)>Integer.parseInt(mv2)) return true;
                if (Integer.parseInt(v2)<Integer.parseInt(mv2)) return false;
                if (Integer.parseInt(v2)==Integer.parseInt(mv2)) return true;
            }
        }
        return true;
    }

    //342<343
    public static boolean isMaxVersionCanDo(String version,String maxVersion){
        if (StringUtils.isEmpty(version)||StringUtils.isEmpty(maxVersion)) {
            return false;
        }
        String[] versions = version.split("\\.");
        String[] minVersions = maxVersion.split("\\.");
        String v0 = versions[0];
        String v1 = versions[1];
        String v2 = versions[2];

        String mv0 = minVersions[0];
        String mv1 = minVersions[1];
        String mv2 = minVersions[2];

        if (Integer.parseInt(v0)>Integer.parseInt(mv0)) return false;
        if (Integer.parseInt(v0)<Integer.parseInt(mv0)) return true;
        if (Integer.parseInt(v0)==Integer.parseInt(mv0)) {
            if (Integer.parseInt(v1)>Integer.parseInt(mv1)) return false;
            if (Integer.parseInt(v1)<Integer.parseInt(mv1)) return true;
            if (Integer.parseInt(v1)==Integer.parseInt(mv1)) {
                if (Integer.parseInt(v2)>Integer.parseInt(mv2)) return false;
                if (Integer.parseInt(v2)<Integer.parseInt(mv2)) return true;
                if (Integer.parseInt(v2)==Integer.parseInt(mv2)) return false;
            }
        }
        return true;
    }

    //342<=342
    public static boolean isMaxVersionAndEqCanDo(String version,String maxVersion){
        if (StringUtils.isEmpty(version)||StringUtils.isEmpty(maxVersion)) {
            return false;
        }
        String[] versions = version.split("\\.");
        String[] minVersions = maxVersion.split("\\.");
        String v0 = versions[0];
        String v1 = versions[1];
        String v2 = versions[2];

        String mv0 = minVersions[0];
        String mv1 = minVersions[1];
        String mv2 = minVersions[2];

        if (Integer.parseInt(v0)>Integer.parseInt(mv0)) return false;
        if (Integer.parseInt(v0)<Integer.parseInt(mv0)) return true;
        if (Integer.parseInt(v0)==Integer.parseInt(mv0)) {
            if (Integer.parseInt(v1)>Integer.parseInt(mv1)) return false;
            if (Integer.parseInt(v1)<Integer.parseInt(mv1)) return true;
            if (Integer.parseInt(v1)==Integer.parseInt(mv1)) {
                if (Integer.parseInt(v2)>Integer.parseInt(mv2)) return false;
                if (Integer.parseInt(v2)<Integer.parseInt(mv2)) return true;
                if (Integer.parseInt(v2)==Integer.parseInt(mv2)) return true;
            }
        }
        return true;
    }
}
