package com.itheima.reggie_takeout.controller;

import com.itheima.reggie_takeout.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件的上传与下载
 * @author purpoas
 * @date 2022/4/19 14:57
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Value(value = "${reggie.path}")
    private String basePath;

    @PostMapping(value = "/upload")
    public R<String> upload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;

        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(fileName);
    }

    @GetMapping(value = "/download")
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(basePath + name);

            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            fileInputStream.close();
            outputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
