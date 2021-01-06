package com.micro;

import static org.junit.Assert.assertTrue;

import com.micro.bean.wsForAttr.WsForAttrServicesOutVo;
import com.micro.utils.io.MicroIoUtil;
import com.micro.utils.log.LoggerUtils;
import com.micro.utils.services.GKServiceInvoke;
import com.micro.utils.xml.XmlToBeanUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.apache.log4j.*", "javax.xml.*", "org.apache.commons.digester3", "org.xml.*", "org.w3c.*",
        "javax.management.*" })
@PrepareForTest({ GKServiceInvoke.class})
public class WebClientAppTest {
    /**
     * 日志对象.
     */
    private static final Logger log = LoggerUtils.getLog(WebClientAppTest.class);
    private static String epr="http://127.0.0.1:8080/ws/WsForAttrServices.wsdl";

    /**
     * Rigorous Test :-)
     */
    @Test
    public void callWsForAttrServices() throws Exception {
        mockGKServiceInvokeTest();
        List<WsForAttrServicesOutVo> lst = new ArrayList<WsForAttrServicesOutVo>();
        WsForAttrServicesOutVo response = new WsForAttrServicesOutVo();
        try {
            String invo =  MicroIoUtil.loadFromFileAsString("micro/wsForAttrServicesinvo.xml");
            log.info(invo);
            OMElement xmlValue = new StAXOMBuilder(new ByteArrayInputStream(
                    invo.getBytes("UTF-8"))).getDocumentElement();
            log.info("epr"+epr);
            OMElement readme = GKServiceInvoke.invokeAndReturnOmeFor(xmlValue, epr, new HashMap(),
                    1000);
            String gkRsp = readme.toString().replaceAll("\\<\\w+\\:", "<").replaceAll("\\</\\w+\\:", "</");
            log.info("返回xml" + gkRsp);
            lst = XmlToBeanUtil.getInstance().parseXml(WsForAttrServicesOutVo.class, gkRsp, "UTF-8");
            log.info(lst.size());
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        if (lst.size() > 0) {
            log.info(lst.get(0));

        }
    }

    public void   mockGKServiceInvokeTest() throws Exception {
      //  1、PowerMock.replayAll() 方法是使得PowerMock创建和维护的Mock对象和类进入回放模式。 也就是说让mock生效了。
      //  2、PowerMock.verifyAll()方法是验证PowerMock创建和维护的Mock对象和类是有效的
      //  3、PowerMock.resetAll() 方法是使得PowerMock创建和维护的Mock对象和类恢复初始状态，这是mock出来的对象也就是无效状态。

        //  PowerMock.expectLastCall().andAnswer(new IAnswer<Object>() {
        PowerMock.mockStaticPartial(GKServiceInvoke.class,"invokeAndReturnOmeFor");
        IExpectationSetters except = EasyMock.expect(GKServiceInvoke.invokeAndReturnOmeFor(
                EasyMock.anyObject(OMElement.class),
                        EasyMock.anyObject(String.class),
                        EasyMock.anyObject(HashMap.class),
                        EasyMock.anyLong())).
                andReturn(
                        new StAXOMBuilder(new ByteArrayInputStream(
                                MicroIoUtil.loadFromFileAsString("micro/wsForAttrServicesoutvo.xml").
                                        getBytes("UTF-8"))).getDocumentElement()
                ).anyTimes();
        PowerMock.replayAll();
      //  PowerMock.verifyAll();
       /* PowerMock.expectLastCall().andAnswer(new IAnswer<Object>() {
            public String answer() throws Throwable {
                final List<GrisFundPayAndInPlaceModel> datas = (List<GrisFundPayAndInPlaceModel>) EasyMock
                        .getCurrentArguments()[0];
                // final int dir = Integer
                // .parseInt(EasyMock.getCurrentArguments()[2].toString());
                // 把第三方传递过来的发票标识(invoiceId)映射成管控方值
                int i=41016220;
                for (GrisFundPayAndInPlaceModel model : datas) {
                    model.setGrisProjectId(String.valueOf(i++));

                }
                return "";
            }
        }).anyTimes();*/
    }

}