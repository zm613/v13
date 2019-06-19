package com.zm.v13centerweb.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zm.v13.common.pojo.ResultBean;
import com.zm.v13centerweb.pojo.WangeditorResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileController {
    @Autowired
    private FastFileStorageClient client;

    @Value("${image.server}")
    private String imageServer;

    @PostMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename(); //**.**
        String extName = originalFilename.substring(originalFilename.indexOf(".") + 1);//后缀名
        try {
            StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), extName, null);
            String fullPath = storePath.getFullPath();//返回图片路径
            String path = new StringBuilder(imageServer).append(fullPath).toString();
            return new ResultBean("200", path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultBean("400", "上传失败");
    }

    @PostMapping("multiUpload")
    @ResponseBody
    public WangeditorResultBean multiUpload(MultipartFile[] files) {
        WangeditorResultBean wangeditorResultBean = new WangeditorResultBean();
        String[] data = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String originalFilename = files[i].getOriginalFilename(); //**.**
            String extName = originalFilename.substring(originalFilename.indexOf(".") + 1);//后缀名
            try {
                StorePath storePath = client.uploadFile(files[i].getInputStream(), files[i].getSize(), extName, null);
                String fullPath = storePath.getFullPath();//返回图片路径
                String path = new StringBuilder(imageServer).append(fullPath).toString();
                data[i] = path;
            } catch (IOException e) {
                e.printStackTrace();
                return new WangeditorResultBean("1", data);
            }
        }
        return new WangeditorResultBean("0", data);
    }
}
