package com.hjl.comman.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringReader;

/**
 * @author ：hjl
 * @date ：2019/9/27 15:24
 * @description： xml文件操作demo
 * @modified By：
 */
public class XmlDemo {

    private static final Logger LOG = LoggerFactory.getLogger(XmlDemo.class);
    public static void main(String[] args){
        readXml();
    }

    /**
     * 解析xml
     */
    public static void readXml(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader stringReader = new StringReader("<root>\n" +
                    "\t<first t=\"test\">\n" +
                    "\t</first>\n" +
                    "</root>");
            // 解析文件
            Document document = builder.parse(new InputSource(stringReader));
            // 获取文件的根节点
            Element element = document.getDocumentElement();
            String tag = element.getTagName();
            LOG.info("tag = {}" , tag);
            // 根据名字获取子节点集合
            NodeList nodeList = element.getElementsByTagName("first");
            LOG.info("size ={}" , nodeList.getLength());
            for (int i=0; i<nodeList.getLength();i++){
                // 获取指定子节点
                Node node = nodeList.item(i);
                // 获取节点中所有属性
                NamedNodeMap namedNodeMap =  node.getAttributes();
                // 获取指定属性值
                String content = namedNodeMap.getNamedItem("t").getTextContent();
                LOG.info("content = {}" , content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建xml文件
     */
    public static void createXml(){
        try {
            File file = new File("new.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 创建文档
            Document doc = builder.newDocument();
            // 创建注释
            Comment comment = doc.createComment("注释");
            // 添加注释到文档
            doc.appendChild(comment);
            // 创建节点
            Element root = doc.createElement("message");
            // 添加第一个组件
            doc.appendChild(root);
            // 创建并添加子组件
            Element child1 = doc.createElement("child1");
            // 设置组件文本内容
            child1.setTextContent("test");
            // 设置组件属性
            child1.setAttribute("att","a");
            // 设置成子组件
            root.appendChild(child1);
            // 作为文档对象模型（DOM）树形式的变换源树的持有者。
            DOMSource src = new DOMSource(doc);
            // 作为转换结果的持有人，可以是XML，纯文本，HTML或其他形式的标记。
            StreamResult sr = new StreamResult(file);
            // Transformer的工厂
            TransformerFactory tf = TransformerFactory.newInstance();
            /**
             * 这个抽象类的一个实例可以将一个源代码树转换成一个结果树。
             * 该类的一个实例可以使用TransformerFactory.newTransformer方法获得。 然后，该实例可用于从各种源处理XML，并将转换输出写入各种汇。
             */
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.VERSION, "1.0");
            t.setOutputProperty(OutputKeys.STANDALONE, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // 将xml source转换成result
            t.transform(src, sr);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
