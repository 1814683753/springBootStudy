package com.hjl.utils;

import com.hjl.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;
import java.util.Objects;

/**
 * @author ：hjl
 * @date ：2019/9/29 9:50
 * @description：xml工具类
 * @modified By：
 */
public class XmlUtils {

    private static final Logger LOG = LoggerFactory.getLogger(XmlUtils.class);

    /**
     * 创建根标签
     * @param rootTagName
     * @return 返回创建的子标签，参数为空或者异常则返回null
     */
    public static Element createRootTag(String rootTagName){
        /**
         * 参数非空校验
         */
        if (StringUtils.isEmpty(rootTagName)){
            String.format(Constants.PARAMETER_NOT_NULL,"rootTagName");
            LOG.info("参数不能为空...........rootTagName:{}",rootTagName);
            return null;
        }
        try{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 创建文档
            Document doc = builder.newDocument();
            // 创建根标签
            Element rootElement = doc.createElement(rootTagName);
            doc.appendChild(rootElement);
            return rootElement;
        }catch (Exception e){
            LOG.error("create xml rootTag happened a error : ",e);
        }
        // 默认返回空
        return null;
    }

    /**
     * 创建普通标签
     * @param parentElement 父标签
     * @param tagName 标签名
     * @param params 标签的参数,可以不传
     * @param value 标签的值，可以不传
     * @return 返回创建成功的标签，否则返回null
     */
    public static Element createTag(Element parentElement,String tagName, Map<String,String> params,String value){
        if (StringUtils.isEmpty(tagName)){
            LOG.info("参数不能为空...........tagName:{}",tagName);
            return null;
        }
        try {
            // 获取所属的文档
            Document document = parentElement.getOwnerDocument();
            // 创建标签
            Element tag = document.createElement(tagName);
            // 设置文本值
            if (StringUtils.isNotEmpty(value)){
                tag.setTextContent(value);
            }
            if (!CollectionUtils.isEmpty(params)){
                params.forEach((k,v) -> {
                    tag.setAttribute(k,v);
                });
            }
            parentElement.appendChild(tag);
            return tag;
        }catch (Exception e){
            LOG.error("create xml tag happened a error : ",e);
        }
        return null;

    }

    /**
     * 创建xml文件
     * @param rootElement 根标签
     * @param path 文件路径包含xml文件名
     * @return
     */
    public static boolean createFile(Element rootElement,String path){
        /**
         * 参数校验
         */
        if (Objects.isNull(rootElement) || StringUtils.isEmpty(path)){
            LOG.info("参数不能为空...........rootElement:{},path:{}",rootElement,path);
            return Boolean.FALSE;
        }
        if (!path.endsWith(Constants.XML_SUFFIX)){
            LOG.info("路径不合法，必须以.xml结尾,path:{}",path);
            return Boolean.FALSE;
        }
        try {
            File file = new File(path);
            Document document = rootElement.getOwnerDocument();
            // 作为文档对象模型（DOM）树形式的变换源树的持有者。
            DOMSource src = new DOMSource(document);
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
            return Boolean.TRUE;
        }catch (Exception e){
            LOG.error("create xml happened a error : ",e);
        }
        return Boolean.FALSE;
    }

    /**
     * 解析指定xml文件获取对应的根标签
     * @param path 文件路径
     * @return 解析成功返回对应的对象，否则返回null
     */
    public static Element parseXmlFile(String path){
        /**
         * 参数校验
         */
        if (StringUtils.isEmpty(path)){
            LOG.info("参数不能为空...........path:{}",path);
            return null;
        }
        if (!path.endsWith(Constants.XML_SUFFIX)){
            LOG.info("路径不合法，必须以.xml结尾,path:{}",path);
            return null;
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 解析文件
            Document document = builder.parse(new File(path));
            Element rootElement = document.getDocumentElement();
            return rootElement;
        }catch (Exception e){
            LOG.error("parse xml happened a error : ",e);
        }
        return null;
    }

    /**
     * 获取根标签名
     * @param rootElement
     * @return 成功则返回标签名，否则返回null
     */
    public static String getRootTagName(Element rootElement){
        if (Objects.isNull(rootElement)){
            return null;
        }
        return rootElement.getTagName();
    }

    /**
     * 获取标签内容或者参数内容
     * @param rootElement 根标签
     * @param tagName 要查找的子标签名字
     * @param type  Constants.PARAM_TYPE：查询参数值  Constants.TEXT_TYPE：查询内容
     * @param paramName 参数名：当类型为Constants.PARAM_TYPE时需要填写
     * @return 返回获取的数据 ，异常或者没获取到返回null
     */
    public static String getTagText(Element rootElement,String tagName,String type,String paramName){
        if (Objects.isNull(rootElement) || StringUtils.isEmpty(tagName) || StringUtils.isEmpty(type)){
            LOG.info("参数不能为空......rootElement:{},tagName:{},type:{}",rootElement,tagName,type);
            return null;
        }
        if (!Constants.PARAM_TYPE.equals(type)&&!Constants.TEXT_TYPE.equals(type)){
            LOG.info("类型不符合规范 type:{}",type);
            return null;
        }
        /**
         * 获取参数值时传递的参数名不能为空
         */
        if (Constants.PARAM_TYPE.equals(type) && StringUtils.isEmpty(paramName)){
            LOG.info("参数不能为空......type:{},param:{}",type,paramName);
            return null;
        }
        String result = "";
        try {
            NodeList nodeList = rootElement.getElementsByTagName(tagName);
            for (int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (Objects.nonNull(node)){
                    switch (type){
                        case Constants.TEXT_TYPE :
                            result =  node.getTextContent();
                            break;
                        case Constants.PARAM_TYPE:
                            NamedNodeMap namedNodeMap = node.getAttributes();
                            Node paramNode = namedNodeMap.getNamedItem(paramName);
                            if (Objects.nonNull(paramNode)){
                                result = paramNode.getTextContent();
                            }
                            break;
                        default:
                            break;
                    }
                }
                if (StringUtils.isNotEmpty(result)){
                    return result;
                }
            }
        }catch (Exception e){
            LOG.error("获取对应标签的内容失败：",e);
        }
        return null;
    }
    /**
     * 根据xml标签转换成xml字符串
     * @param element 根标签
     * @return xml字符串
     */
    public static String toXmlString(Element element){
        try {
            // 获取文档
            Document document = element.getOwnerDocument();
            // 作为文档对象模型（DOM）树形式的变换源树的持有者。
            DOMSource source = new DOMSource(document);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            StreamResult streamResult = new StreamResult(outputStream);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(source, streamResult);
            return outputStream.toString("UTF-8");
        }catch (Exception e){
            LOG.error("create xml tag happened a error : ",e);
        }
        return "";
    }
}
