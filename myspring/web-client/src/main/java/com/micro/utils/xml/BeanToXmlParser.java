package com.micro.utils.xml;

import com.micro.utils.io.MicroIoUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanToXmlParser {

    public static BeanToXmlParser getInstance() {
        BeanToXmlParser beanToXmlPares = new BeanToXmlParser();
        return beanToXmlPares;
    }

    private BeanToXmlParser() {

    }

    /**
     * 模板通过传入内容，而不是路径。用于模板文件是从数据库配置表中获取时使用，默认为gbk编码
     * <p>
     *
     * @param templateName
     *            ：模板名称(ID),有多个模板时区分唯一性
     * @return XML
     * @throws Exception
     */
    public   String beanToXml(String templateName,Map<String,Object> argMap,String encode)
            throws Exception {
        String tempContent = MicroIoUtil.loadFromFileAsString(templateName);
        VelocityContext context = new VelocityContext();
        VelocityEngine ve = getStringSourceEngine(true);
        StringResourceRepository repo = StringResourceLoader.getRepository();
        repo.putStringResource(templateName, tempContent);

        Set<Map.Entry<String, Object>> argSet = argMap.entrySet();
        StringWriter s = new StringWriter();

        for (Map.Entry<String, Object> entry : argSet)
            context.put(entry.getKey(), entry.getValue());
        try {
            Template t = ve.getTemplate(templateName);
            t.setEncoding("GBK");
            t.merge(context, s);

        } catch (Exception ex) {
            throw new Exception("使用vm生成xml出现异常", ex);
        }
        return s.toString();
    }
    public   String beanToXml(String templateName,String xmlContent,String encode)
            throws Exception {
        String tempContent = MicroIoUtil.loadFromFileAsString(templateName);
        VelocityContext context = new VelocityContext();
        VelocityEngine ve = getStringSourceEngine(true);
        StringResourceRepository repo = StringResourceLoader.getRepository();
        repo.putStringResource(templateName, tempContent);

        Set<Map.Entry<String, Object>> argSet = null;//argMap.entrySet();
        StringWriter s = new StringWriter();

        for (Map.Entry<String, Object> entry : argSet)
            context.put(entry.getKey(), entry.getValue());
        try {
            Template t = ve.getTemplate(templateName);
            t.setEncoding("GBK");
            t.merge(context, s);

        } catch (Exception ex) {
            throw new Exception("使用vm生成xml出现异常", ex);
        }
        return s.toString();
    }
    /**
     * 模板通过传入内容，而不是路径。用于模板文件是从数据库配置表中获取时使用。
     * <p>
     *
     * @param templateName
     *            ：模板名称(ID),有多个模板时区分唯一性
     * @param template
     *            ：模板的内容
     * @return XML
     * @throws Exception
     */
    public String parseBeanFromStringTemplate(String templateName, String template,Map<String,Object> argMap, String encode)
            throws Exception {
        VelocityContext context = new VelocityContext();
        VelocityEngine ve = getStringSourceEngine(true,encode);

        StringResourceRepository repo = StringResourceLoader.getRepository();
        repo.putStringResource(templateName, template);

        Set<Map.Entry<String, Object>> argSet = argMap.entrySet();
        StringWriter s = new StringWriter();

        for (Map.Entry<String, Object> entry : argSet)
            context.put(entry.getKey(), entry.getValue());
        try {
            Template t = ve.getTemplate(templateName);
            t.setEncoding(encode);
            t.merge(context, s);

        } catch (Exception ex) {
            throw new Exception("使用vm生成xml出现异常", ex);
        }
        return s.toString();
    }

    /**
     * 初始化用String为模板解释的引擎
     *
     * @return VelocityEngine
     */
    private static VelocityEngine getStringSourceEngine(boolean escapeXml) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(VelocityEngine.RESOURCE_LOADER, "string");
        ve.setProperty("string.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.StringResourceLoader");

        ve.setProperty(Velocity.ENCODING_DEFAULT, "GBK");
        ve.setProperty(Velocity.INPUT_ENCODING, "GBK");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");

        if (escapeXml) {
            ve.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION,
                    "com.micro.utils.xml.XmlEscapeUtil");
        }

        ve.init();

        return ve;
    }


    /**
     * 初始化用String为模板解释的引擎
     *
     * @return VelocityEngine
     */
    private static VelocityEngine getStringSourceEngine(boolean escapeXml, String encode) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(VelocityEngine.RESOURCE_LOADER, "string");
        ve.setProperty("string.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.StringResourceLoader");

        ve.setProperty(Velocity.ENCODING_DEFAULT, encode);
        ve.setProperty(Velocity.INPUT_ENCODING, encode);
        ve.setProperty(Velocity.OUTPUT_ENCODING, encode);

        if (escapeXml) {
            ve.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION,
                    "com.ygsoft.soaware.util.YgXmlEscapeUtil");
        }

        ve.init();

        return ve;
    }

}
