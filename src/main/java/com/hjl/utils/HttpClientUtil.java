package com.hjl.utils;

import com.hjl.constant.Constants;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Author hjl
 * @Description HttpClient工具类
 * @Date 2019/8/13 11:51
 */
public class  HttpClientUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * get 请求
     * @param path
     * @param parametersBody
     * @return
     * @throws Exception
     */
    public static String getRequest(String path, List<NameValuePair> parametersBody) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(path);
        uriBuilder.setParameters(parametersBody);
        HttpGet get = new HttpGet(uriBuilder.build());
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= Constants.CODE_400){
                throw new RuntimeException((new StringBuilder()).append("Could not access protected resource. Server returned http code: ").append(code).toString());
            }
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
        return "error";
    }



    /**
     * 发送POST请求（普通表单形式）
     * @param path
     * @param parametersBody
     * @return
     * @throws Exception
     */
    public static String postForm(String path, List<NameValuePair> parametersBody) throws Exception {
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(path, "application/x-www-form-urlencoded", entity);
    }

    /**
     * 发送POST请求（JSON形式）
     * @param path
     * @param json
     * @return
     * @throws Exception
     */
    public static String postJSON(String path, String json) throws Exception {
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        return postRequest(path, "application/json", entity);
    }

    /**
     *  无参的post请求
     * @param path
     * @return
     * @throws Exception
     */
    public static String post(String path) throws Exception {
        return postRequest(path, "application/json", null);
    }

    /**
     * 发送POST请求
     * @param path
     * @param mediaType
     * @param entity
     * @return
     * @throws
     */
    public static String postRequest(String path, String mediaType, HttpEntity entity) throws Exception {
        LOG.debug("[postRequest] resourceUrl: {}", path);
        HttpPost post = new HttpPost(path);
        post.addHeader("Content-Type", mediaType);
        post.addHeader("Accept", "application/json");
        if(Objects.nonNull(entity)){
            post.setEntity(entity);
        }
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= Constants.CODE_400){
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            }

            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new RuntimeException("postRequest -- Client protocol exception!", e);
        } catch (IOException e) {
            throw new RuntimeException("postRequest -- IO error!", e);
        } finally {
            post.releaseConnection();
        }

    }
}
