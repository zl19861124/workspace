package com.micro.utils.ftl;

import com.micro.bean.wsForAttr.WsForAttrServicesInVo;
import com.micro.utils.log.LoggerUtils;
import freemarker.template.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FtlBeanToXml {

    private static final Logger log = LoggerUtils.getLog(FtlBeanToXml.class);

    public String beanToXml(String dirName,String fileName) throws Exception {
        String ftlPath = this.getClass().getResource("/").getPath();
        ftlPath = ftlPath.replace("test-", "") + dirName;
        log.info(ftlPath);
        Map<String, List<WsForAttrServicesInVo>> map = new HashMap<String, List<WsForAttrServicesInVo>>();
        List<WsForAttrServicesInVo> list = new ArrayList<WsForAttrServicesInVo>();
        WsForAttrServicesInVo item = new WsForAttrServicesInVo();
        item.setUserId("abcd");
        list.add(item);
        map.put("list", list);
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
       // cfg.setObjectWrapper(new DefaultObjectWrapper());
      //  cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        Template template = cfg.getTemplate(fileName);
        try {
            StringWriter out = new StringWriter();
            //  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:/micro/out.txt")));
            template.process(map, out);
         return out.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }
}
