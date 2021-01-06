package com.micro.utils.xml;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class XmlVoToPo {
    /**
     * 将vo转化为po.
     *
     * @param vos
     *            vo对象
     * @param config
     *            配置文件
     * @return po对象
     * @throws Exception
     *             if has error
     */
    public List<GldxPo> transform(final List<GldxXmlVo> vos, final Config config) throws Exception {
        final List<GldxPo> pos = new ArrayList<GldxPo>();
        final Map<String, BasicDynaClass> subPropertyMap = getSubPropertyMap(config);
        final DynaProperty[] propertys = getProperty(config, subPropertyMap.keySet());


        for (GldxXmlVo vo : vos) {
            try {
                final GldxPo po =new GldxPo();
                final Map<String, String> mainTable = vo.getMainTabel();
                po.setGldxlxid(config.getGldxlx());
                // 设置第三方惟一id
                this.setSeqid(config, mainTable, po);
                // 设置主表字段
                this.setMain(po, config, mainTable);
                // 设置子表字段
                this.setSub(po, vo, config, subPropertyMap);
                pos.add(po);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        return pos;
    }
    /**
     * 得到所有子表对应的动态class.
     *
     * @param config
     * @return 子表属性
     */
    private Map<String, BasicDynaClass> getSubPropertyMap(final Config config) {
        final Map<String, BasicDynaClass> subPropertyMap = new HashMap<String, BasicDynaClass>();
        final List<SubTable> subTables = config.getSubTables();
        for (SubTable subTable : subTables) {
            final List<DynaProperty> dynaPropertys = new ArrayList<DynaProperty>();
            for (SubProperty subProperty : subTable.getProperties()) {
                dynaPropertys.add(new DynaProperty(subProperty.getName(), String.class));
            }
            final DynaProperty[] dynaPropertyArrays = new DynaProperty[subTable.getProperties().size()];
            final BasicDynaClass dynaClass = new BasicDynaClass(subTable.getName(), GldxSub.class,
                    dynaPropertys.toArray(dynaPropertyArrays));
            subPropertyMap.put(subTable.getName(), dynaClass);
        }
        return subPropertyMap;
    }
    /**
     * 得到配置文件中的seqid.
     *
     * @param config
     *            配置
     * @param mainTable
     *            主表
     * @param po
     *            实体对象
     * @throws Exception
     *             if has error
     */
    private void setSeqid(final Config config, final Map<String, String> mainTable, final GldxPo po)
            throws Exception {
        final SeqId seqId = config.getSeqid();
        // 设置seqId
        final List<String> idList = new ArrayList<String>();
        for (Id id : config.getSeqid().getIds()) {
            final String idPart = mainTable.get(id.getZdPath());
            if (StringUtils.isEmpty(idPart) || "%NULL%".equals(idPart)) {
                throw new Exception(id.getZdPath() + "在报文中不存在或值为空");
            }
            idList.add(idPart);
        }

        po.setSeqId(StringUtils.join(idList.toArray(), seqId.getJoin()));
    }
    /**
     * 设置主表字段.
     *
     * @param po
     *            po对象
     * @param config
     *            配置
     * @param mainTable
     *            主表
     * @throws Exception
     *             if has error
     */
    private void setMain(final GldxPo po, final Config config, final Map<String, String> mainTable)
            throws Exception {
        this.setProperty(config.getProperties(), po, mainTable);
    }

    /**
     * 设置子表.
     *
     * @param po
     *            po对象
     * @param vo
     *            vo对象
     * @param config
     *            配置
     * @param subPropertyMap
     *            子表属性
     * @throws Exception
     *             if has error
     */
    private void setSub(final GldxPo po, final GldxXmlVo vo, final Config config,
                        final Map<String, BasicDynaClass> subPropertyMap) throws Exception {
        // 设置子表
        for (SubTable subTable : config.getSubTables()) {
            // 子表类型
            final BasicDynaClass subClass = subPropertyMap.get(subTable.getName());
            // 子表有多个
            final List<DynaBean> subBeans = new ArrayList<DynaBean>();
            // 得到vo中的子表
            final List<Map<String, String>> subList = vo.getSubTables().get(subTable.getSubPath());

            if (subList != null) {
                for (Map<String, String> propertyMap : subList) {
                    final GldxSub subBean = (GldxSub) subClass.newInstance();
                    this.setProperty(subTable.getProperties(), subBean, propertyMap);
                    // 加入子表列中
                    subBeans.add(subBean);
                }
            }
            // 加入po对应的子表类型中
            PropertyUtils.setProperty(po, subTable.getName(), subBeans);
        }
    }

    /**
     * 设置属性.
     *
     * @param properties
     *            属性
     * @param bean
     *            对象
     * @param propertyMap
     *            属性Map
     * @throws Exception
     *             if has error
     */
    private void setProperty(final List< ? extends IProperty> properties, final Object bean,
                             final Map<String, String> propertyMap) throws Exception {
        // 设置主表字段
        for (IProperty property : properties) {
            final String zdName = property.getName();
            String zdValue = propertyMap.get(property.getZdPath());
            if (property.getConverter() != null) {
                zdValue = property.getConverter().get(zdValue);
            }
            PropertyUtils.setProperty(bean, zdName, zdValue);
        }
    }

    /**
     * 获取属性.
     *
     * @param config
     *            配置
     * @param subTableNames
     *            子表
     * @return 属性数组
     */
    private DynaProperty[] getProperty(final Config config, final Set<String> subTableNames) {
        final List<DynaProperty> dynaPropertys = new ArrayList<DynaProperty>();

        final List<Property> properties = config.getProperties();

        for (Property property : properties) {
            dynaPropertys.add(new DynaProperty(property.getName(), String.class));
        }

        dynaPropertys.add(new DynaProperty("xtdw", String.class));

        for (String tableName : subTableNames) {
            dynaPropertys.add(new DynaProperty(tableName, List.class));
        }

        final DynaProperty[] dynaPropertyArrays = new DynaProperty[properties.size()];
        return dynaPropertys.toArray(dynaPropertyArrays);
    }

}
