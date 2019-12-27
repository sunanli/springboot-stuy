package com.tangwh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    /**
     * 单个文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) {

        System.out.println("Ajax请求进来了");
        //格式化当前日期
        String format = sdf.format(new Date());

        String realPath = request.getServletContext().getRealPath("/img") + format;

        // 需要保存的文件夹
        File folder = new File(realPath);

        //如果他不存在
        if (!folder.exists()){
            // 创建此抽象路径名指定的目录
            folder.mkdirs();
        }

        // 获取旧文件名
        String oldfilename = file.getOriginalFilename();

        // 设置新的文件名
        String newfileName = UUID.randomUUID().toString() + oldfilename.substring(oldfilename.lastIndexOf("."));


        try {
            //保存文件
            file.transferTo(new File(folder,newfileName));


            //文件的路径
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img" + format + newfileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * 多个文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/uploads")
    public String upload(MultipartFile[] files, HttpServletRequest request) {

        System.out.println("Ajax请求进来了");
        //格式化当前日期
        String format = sdf.format(new Date());

        String realPath = request.getServletContext().getRealPath("/img") + format;

        // 需要保存的文件夹
        File folder = new File(realPath);

        //如果他不存在
        if (!folder.exists()){
            // 创建此抽象路径名指定的目录
            folder.mkdirs();
        }

        for (MultipartFile file : files) {
            // 获取旧文件名
            String oldfilename = file.getOriginalFilename();

            // 设置新的文件名
            String newfileName = UUID.randomUUID().toString() + oldfilename.substring(oldfilename.lastIndexOf("."));


            try {
                //保存文件
                file.transferTo(new File(folder,newfileName));


                //文件的路径
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img" + format + newfileName;
                System.out.println(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return "success";
    }
}
