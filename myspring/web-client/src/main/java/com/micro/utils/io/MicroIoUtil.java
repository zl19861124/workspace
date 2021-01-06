package com.micro.utils.io;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MicroIoUtil {

    /**
     *   需要支持字符编码，还要从数据库读取
     * @param filePath
     * @return
     */
    public static String loadFromFileAsString(String filePath) {
        StringBuffer sb = new StringBuffer();
        try {
            Resource resource = new ClassPathResource(filePath);
            File file = resource.getFile();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line=null;
            while ((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
