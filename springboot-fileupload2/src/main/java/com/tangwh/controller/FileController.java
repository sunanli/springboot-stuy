package com.tangwh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);


    @RequestMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为:" + fileName);

        //获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("文件名的后缀名为:" + suffixName);

        // 设置文件的存储位置
        String filePath = "/Users/dalaoyang/Downloads/";
        String path = fileName + filePath;
        File dest = new File(path);
        // 检查路径是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs(); //新建文件
        }

        try {
            file.transferTo(dest);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传失败";
    }

    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {

            file = files.get(i);
            String filepath = "/Users/dalaoyang/Downloads/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filepath + file.getOriginalFilename()))); // 设置文件路径这以及名字
                    stream.write(bytes); // 写入
                    stream.close();
                } catch (IOException e) {
                    stream =null;
                    return "第"+i+"个文件上传失败==>"+e.getMessage();
                }
            }else {
                return "第"+i+"文件上传失败因为我呢见为空";
            }

        }

        return "上传成功";
    }


    @GetMapping("download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response){
        // 文件名
        String fileName = "dalaoyang.jpeg";
        if (fileName!=null){
            // 设置文件路径
            File file = new File("/Users/dalaoyang/Documents/dalaoyang.jpeg");
            //File file = new File(realPath , fileName);
            // 如果文件存在
            if (file.exists()){
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;

                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1){
                        os.write(buffer,0,i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (bis !=null){
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }if (fis !=null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }
        return "下载失败";
    }
}
