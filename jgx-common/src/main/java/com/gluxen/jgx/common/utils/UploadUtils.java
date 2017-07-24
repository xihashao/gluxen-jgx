package com.gluxen.jgx.common.utils;

import net.mikesu.fastdfs.FastdfsClient;
import net.mikesu.fastdfs.FastdfsClientFactory;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/24.
 */
public class UploadUtils {
    /***
     * 文件上传接口
     * @return 返回文件Id
     */
    private final static String configFile = "FastdfsClient.properties";
    private static String file_read_url = null;
    public static String localFilePath = null;

    static {
        try {
            Configuration config = new PropertiesConfiguration(configFile);
            file_read_url = config.getString("file_read_url");
            localFilePath = config.getString("local.file.path");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static String uploadFile(MultipartFile file, Map meta) throws Exception {
        FastdfsClient fastdfsClient = FastdfsClientFactory.getFastdfsClient();//获取FastdsfClient类
        String fileId = null;
        if (MapUtils.isEmpty(meta)) {
            Map meta2 = new HashMap<String, Object>();
            fileId = fastdfsClient.upload(file, meta2);
        } else {
           fileId = fastdfsClient.upload(file, meta);
        }
        return fileId;
    }

    /***
     * 获取文件url地址
     * @param fileId
     * @return
     */
    public static String getFileUrl(String fileId) {
        try {
            //String url=fastdfsClient.getUrl(fileId);
            String url = file_read_url + fileId;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrlByUploadFile(MultipartFile file, Map meta) {
        FastdfsClient fastdfsClient = FastdfsClientFactory.getFastdfsClient();//获取FastdsfClient类
        String fileId = null;
        String url = null;
        if (MapUtils.isEmpty(meta)) {
            Map meta2 = new HashMap<String, Object>();
            try {
                fileId = fastdfsClient.upload(file, meta2);
                url = file_read_url + fileId;
                //url=fastdfsClient.getUrl(fileId);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        } else {
            try {
                fileId = fastdfsClient.upload(file, meta);
                url = file_read_url + fileId;
                //url = fastdfsClient.getUrl(fileId);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        return url;
    }

    public static String uploadFile(File file){
        FastdfsClient fastdfsClient = FastdfsClientFactory.getFastdfsClient();//获取FastdsfClient类
        String  fileId= null;
        try {
            fileId = fastdfsClient.upload(file);
            //url = file_read_url + fileId;
            //url = fastdfsClient.getUrl(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileId;
    }
    public static boolean removeFileById(String fileId){
        if(StringUtils.isBlank(fileId)){
            return false;
        }
        //http://172.16.1.24:8080/group
        String suffix=null;
        /*if(url.startsWith("http://")){
            suffix=url.substring(url.indexOf('/')+2);
            suffix=suffix.substring(suffix.indexOf('/')+1);
        }else{
            suffix=url.substring(url.indexOf('/',1)+1);
        }
        if(suffix==null){
            suffix=url.substring(url.indexOf("group"));
        }*/
        FastdfsClient fastdfsClient = FastdfsClientFactory.getFastdfsClient();
        try {
            return fastdfsClient.delete(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void  main(String args[]){
        System.out.println(removeFileById("group/jfdslf/abc.png"));
    }
}
