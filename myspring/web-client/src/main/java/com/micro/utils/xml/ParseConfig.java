package com.micro.utils.xml;

import java.util.List;

public class ParseConfig {
    private static Config config;

    /**
     * 初始化config
     *
     * @return
     */
    public static Config getConfig(String configXml) {

        try {
           // config = BeanToXmlParser.getInstance().
           //         beanToXml(Config.class.getName(), configXml, "GBK");
        } catch (Exception e) {
            try {
               // config = parse.parseXml(Config.class, configXml, "UTF-8").get(0);
            } catch (Exception ex) {
                throw new RuntimeException("解析configXml出错", ex);
            }
        }



        // 如果在行项目中配置了dxmc手工删除
        List<Property> properties = config.getProperties();
        for (Property property : config.getProperties()) {
            if (property.getName().equals("dxmc")) {
                properties.remove(property);
                break;
            }
        }
        return config;
    }
}
