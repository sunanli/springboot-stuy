package com.tangwh.springbootfileuploadfastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@SpringBootTest
class SpringbootFileuploadFastdfsApplicationTests {

    //文件上传
    @Test
    void contextLoads() {

        /**
         * 连接超时 记得防火墙
         */
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            NameValuePair nvp[] = null;
            //上传到文件系统
            String fileId = client.upload_file1("C:\\Users\\86177\\Pictures\\Saved Pictures\\Howe.jpg", "png", nvp);

            System.err.println(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //文件下载
    @Test
    void download() {

        /**
         * 连接超时 记得防火墙
         */
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);

            byte[] bytes = client1.download_file1("group1/M00/00/00/wKhbgl5wuOeALkk1AAH5YPuLlEg760.png");

            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\86177\\Pictures\\Saved Pictures\\Howes2.jpg"));

            fos.write(bytes);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取令牌 才能访问
     */
    @Test
    void testToken() throws UnsupportedEncodingException, NoSuchAlgorithmException, MyException {

        int ts = (int) Instant.now().getEpochSecond();// 时间戳

        // 获取token
        String token = ProtoCommon.getToken("M00/00/00/wKhbgl5wu-SARHSmAAH5YPuLlEg969.png", ts, "FastDFS1234567890");


        StringBuffer sb = new StringBuffer();
        sb.append("http://192.168.91.130")
                .append("/group1/M00/00/00/wKhbgl5wu-SARHSmAAH5YPuLlEg969.png")
                .append("?token=")
                .append(token)
                .append("&ts=")
                .append(ts);

        System.out.println(sb.toString());

    }
}
