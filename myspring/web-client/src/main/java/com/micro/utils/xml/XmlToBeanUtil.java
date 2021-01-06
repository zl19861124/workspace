package com.micro.utils.xml;
import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.binder.DigesterLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlToBeanUtil {

    @SuppressWarnings("rawtypes")
    protected List returnList = new ArrayList();
    public static XmlToBeanUtil getInstance() {
        return new XmlToBeanUtil();
    }
    /**
     * 将XML转换成特定Java Bean类
     *
     * @param beanClass 转换后的Java Bean类
     * @param xml       需要转换的XML
     * @param encode    XML的编码
     * @return List<T>对象列表
     * @throws  Exception
     */
    public <T> List<T> parseXml(final Class<T> beanClass, final String xml, final String encode)
            throws Exception {
        DigesterLoader loader = newLoader(new FromAnnotationsRuleModule() {
            @Override
            protected void configureRules() {
                bindRulesFrom(beanClass);
            }
        });
        Digester digester = loader.newDigester();
        digester.push(this);

        ObjectCreate objectCreate = (ObjectCreate) beanClass.getAnnotation(ObjectCreate.class);
        digester.addSetNext(objectCreate.pattern(), "addToList");
        try {
            InputStream in = new ByteArrayInputStream(xml.getBytes(encode));
            digester.parse(in);
        } catch (Exception ex) {
            throw new Exception("从XML转换成Bean发生错误" + ex.getMessage(), ex);
        }

        return this.returnList;
    }

    /**
     * 通过vm方式构建XML.
     *
     * @param pojo
     *            目标对象
     * @param fileName
     *            vm文件名
     * @param argName
     *            变量名
     * @param type
     *            vm下的文件夹名称，如果是vm填空.
     * @param encode
     *            编码
     * @return str
     * @throws IOException
     *             e
     * @throws Exception
     *             e
     */
    public static String buildXmlDataByVm(final Object pojo, final String fileName, final String argName,
                                          final String type, final String encode) throws IOException, Exception {
        /*
         * String filePath ; if(CommonUtil.isEmpty(type)){ filePath =
         * Constants.VM_FILE_PATH_PREFIX + Constants.SLASH + fileName; }else{
         * filePath = Constants.VM_FILE_PATH_PREFIX + Constants.SLASH + type +
         * Constants.SLASH + fileName; }
         *
         * LOG.debug("开始加载VM文件【" + filePath + "】");
         *
         * // 读取vm配置文件信息 final String template =
         * SoawareIoUtil.loadFromFileAsString(filePath, encode); final
         * BeanToXmlParser bp = BeanToXmlParser.getInstance();
         * bp.putArgs(argName, pojo); final String reqXml =
         * bp.parseBeanFromStringTemplate(fileName, template, encode);
         * LOG.debug("加载VM文件【" + filePath + "】并转换完成！"); return reqXml;
         */
        final Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(argName, pojo);
        return buildXmlDataByVm(fileName, dataMap, type, encode);
    }
    /**
     * 通过vm方式构建XML.
     *
     * @param fileName
     *            vm文件名
     * @param dataMap
     *            存放vm参数和值
     * @param type
     *            vm下的文件夹名称，如果是vm填空.
     * @param encode
     *            编码
     * @return str
     * @throws IOException
     *             e
     * @throws Exception
     *             e
     */
    public static String buildXmlDataByVm(final String fileName, final Map<String, Object> dataMap, final String type,
                                          final String encode) throws IOException, Exception {
        String filePath = null;
            filePath = "Constants.VM_FILE_PATH_PREFIX"  + fileName;

        //LOG.debug("开始加载VM文件【" + filePath + "】");

        // 读取vm配置文件信息
        final String template ="";// SoawareIoUtil.loadFromFileAsString(filePath, encode);
        Map<String,Object> map=new HashMap<String,Object>();
        // put参数
        for (final Map.Entry<String, Object> entry : dataMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        final String reqXml = BeanToXmlParser.getInstance().parseBeanFromStringTemplate(fileName, template,map, encode);
        //LOG.debug("加载VM文件【" + filePath + "】并转换完成！");
        return reqXml;
    }

    public void addToList(Object o) {
        returnList.add(o);
    }

    public void addToList(){

    }
}
