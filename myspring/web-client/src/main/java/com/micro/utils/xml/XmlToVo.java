package com.micro.utils.xml;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class XmlToVo {
    /**
     * 解析报文.
     *
     * @param xml
     *            报文
     * @param config
     *            配置文件
     * @return vo对象
     * @throws Exception
     *             if has error
     */
    public List<GldxXmlVo> parse(final String xml, final Config config) throws Exception {
        final List<GldxXmlVo> vos = new ArrayList<GldxXmlVo>();
        // 主表路径
        final String itemPath = config.getItemPath();

        // 子表路径
        final List<String> subPaths = new ArrayList<String>();
        for (SubTable subTable : config.getSubTables()) {
            subPaths.add(subTable.getSubPath());
        }

        try {
            final Document dom = DocumentHelper.parseText(xml);
            final Element root = dom.getRootElement();
            final List<Node> nodes = root.selectNodes(itemPath);

            // 如果路径不存在，解析不了，报错
            if (nodes == null || nodes.size() == 0) {
                throw new Exception("主表路径:" + itemPath + "为空");
            }

            for (Node node : nodes) {
                final GldxXmlVo vo = new GldxXmlVo();

                // 解析主表
                this.parseMain(vo, node);

                // 解析子表
                this.parseSub(subPaths, node, vo);
                vos.add(vo);
            }
        } catch (DocumentException e) {
            throw new Exception("目标xml解析出错", e);
        }
        return vos;
    }

    /**
     * 解析主表.
     *
     * @param vo
     *            vo对象
     * @param node
     *            节点
     */
    private void parseMain(final GldxXmlVo vo, final Node node) {
        final Iterator<Element> mainIterator = ((Element) node).elementIterator();
        // 解析主表
        while (mainIterator.hasNext()) {
            final Element e = mainIterator.next();
            // 如果是复杂结构，说明是子表
            if (e.hasMixedContent()) {
                continue;
            }

            //南网要求 ，如果传"-"代表此属性不同步
            String value = e.getText();

            if ("-".equals(value)) {
                value = "%NULL%";
            }

            // 南网要求，如果待映射数据为空，则可能会出现ID和名称拼接时只有!@-@!的情况
            if("!@-@!".equals(value)){
                value = "";
            }

            if(StringUtils.isNotEmpty(value) && value.startsWith("!@-@!")){
                value = "";
            }
            // 加入到主表中
            vo.addToMainTable(e.getName(), value);
        }
    }

    /**
     * 解析子表.
     *
     * @param subPaths
     *            子表路径
     * @param node
     *            节点
     * @param vo
     *            vo对象
     */
    private void parseSub(final List<String> subPaths, final Node node, final GldxXmlVo vo) {
        for (String subPath : subPaths) {
            // 子表有多条
            final List<Element> subTables = (ArrayList) ((Element) node).selectNodes(subPath);
            for (Element subTable : subTables) {
                final Map<String, String> subTablePropertys = new HashMap<String, String>();
                // 子表里的属性也有多条
                final List<Element> subTablePropertyElements = subTable.elements();
                for (Element propertyElement : subTablePropertyElements) {

                    String value = propertyElement.getText();

                    //南网要求 ，如果传"-"代表此属性不同步
                    if ("-".equals(value)) {
                        value = "%NULL%";
                    }

                    if("!@-@!".equals(value)){
                        value = "";
                    }

                    if(StringUtils.isNotEmpty(value) && value.startsWith("!@-@!")){
                        value = "";
                    }
                    // 加入到主表中
                    subTablePropertys.put(propertyElement.getName(), value);
                }
                // subTable.getParent()得到子表的名称
                vo.addToSubTable(subPath, subTablePropertys);
            }
        }
    }
}
