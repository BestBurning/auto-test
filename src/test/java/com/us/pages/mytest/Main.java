package com.us.pages.mytest;

import com.us.util.PropertyLoader;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class Main{
    private static String defaultFilePath = "/file.jpg";

    private static String filePath = PropertyLoader.loadProperty("usms.service.publish.filepath");

    public static void main(String[] args) {
//        if(StringUtils.isBlank(filePath)){
//            filePath = defaultFilePath;
//        }
//        System.out.println(Main.class.getResource(filePath).getFile());
//        File realFile = new File(Main.class.getResource(filePath).getFile());
//        System.out.println(realFile.getAbsoluteFile().toPath());

        System.out.println(PropertyLoader.loadProperty("usms.service.publish.systemDescription1"));
    }


}
