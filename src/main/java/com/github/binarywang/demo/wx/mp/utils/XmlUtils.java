package com.github.binarywang.demo.wx.mp.utils;

import com.github.binarywang.demo.wx.mp.controller.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



    public class XmlUtils {
        /**
         * 解析微信发来的请求（XML）
         *
         * @param request
         * @return Map<String, String>
         * @throws Exception
         */
        @SuppressWarnings("unchecked")
        public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
            // 将解析结果存储在HashMap中
            Map<String, String> map = new HashMap<String, String>();

            // 从request中取得输入流
            InputStream inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList)
                map.put(e.getName(), e.getText());

            // 释放资源
            inputStream.close();
            inputStream = null;

            return map;
        }

        /**
         * 文本消息对象转换成xml
         *
         * @param textMessage
         *            文本消息对象
         * @return xml
         */
        public static String messageToXml(TextMessage textMessage) {
            xstream.alias("xml", textMessage.getClass());
            return xstream.toXML(textMessage);
        }

        /**
         * 扩展xstream使其支持CDATA
         */
        private static XStream xstream = new XStream();
    }
