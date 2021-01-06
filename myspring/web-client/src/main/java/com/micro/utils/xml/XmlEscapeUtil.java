package com.micro.utils.xml;

import org.apache.velocity.app.event.implement.EscapeReference;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class XmlEscapeUtil extends EscapeReference {

    private static final Map<Integer, String> arrayMap = new HashMap<Integer, String>();

    static {
        // "
        arrayMap.put(34, "quot");
        // &
        arrayMap.put(38, "amp");
        // <
        arrayMap.put(60, "lt");
        // >
        arrayMap.put(62, "gt");
    }

    /**
     * 检索输入的字符串中特定字符，将其进行转义。
     * 可以转换的字符有：",&,<,>
     * @param in 需要处理的字符串
     * @return String 转义后的字符串
     */
    public static String doEscapeXml(String in) {
        StringWriter stringWriter = createStringWriter(in);

        int len = in.length();
        for (int i = 0; i < len; ++i) {
            char c = in.charAt(i);
            String entityName = arrayMap.get((int) c);
            if (entityName == null) {
                stringWriter.write(c);
            } else {
                //转义后&+entityName+; 38是&
                stringWriter.write(38);
                stringWriter.write(entityName);
                //59是;
                stringWriter.write(59);
            }
        }

        return stringWriter.toString();
    }

    private static StringWriter createStringWriter(String str) {
        return new StringWriter((int) (str.length() + str.length() * 0.1D));
    }

    @Override
    protected String escape(Object text) {
        return doEscapeXml(text.toString());
    }

    @Override
    protected String getMatchAttribute() {
        return "eventhandler.escape.xml.match";
    }
}
