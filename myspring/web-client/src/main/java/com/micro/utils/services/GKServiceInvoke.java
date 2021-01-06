package com.micro.utils.services;

import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import sun.misc.BASE64Decoder;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;


public class GKServiceInvoke {



    /**
     * 传递OMElement构建的参数，返回OMElement
     *
     * @param epr
     *            服务地址
     * @param ome
     *            传入的参数(构建完毕的OMElement)
     * @param timeoutseconds
     *            超时时间(毫秒为单位)
     * @param properties
     *            构建属性
     * @return OMElement
     * @throws Exception
     */
    public static OMElement invokeAndReturnOmeFor(OMElement ome, String epr, HashMap<String, Object> properties,
                                                    long timeoutseconds) throws Exception {
        RPCServiceClient serviceClient = null;
        try {
            serviceClient = new RPCServiceClient();
        } catch (AxisFault e) {
            throw new Exception(e);
        }
        Options options = serviceClient.getOptions();
        options.setTimeOutInMilliSeconds(timeoutseconds);

        EndpointReference targetEPR = new EndpointReference(epr);
        options.setTo(targetEPR);

        if (properties != null) {
            options.setProperties(properties);
        }
        OMElement result;
        try {

            //serviceClient.addStringHeader(new QName("http://soa.csg.cn", "username", "soa"), "fm");
            result = serviceClient.sendReceive(ome);
           // gkRsp = response.toString().replaceAll("\\<\\w+\\:", "<").replaceAll("\\</\\w+\\:", "</");
           // byte [] dataBytes = new BASE64Decoder().decodeBuffer( gkRsp);
           // gkRsp = new String(dataBytes, "utf-8");
            return result;
        } catch (AxisFault e) {
            throw new Exception(e);
        }
    }


}
