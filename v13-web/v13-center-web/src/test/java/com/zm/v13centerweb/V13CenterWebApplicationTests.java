package com.zm.v13centerweb;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zm.v13.api.IProductService;
import com.zm.v13.entity.TProduct;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13CenterWebApplicationTests {

    @Autowired
    private FastFileStorageClient client;

    @Test
    public void contextLoads() {
        File file = new File("F:\\ideaWorks\\v13\\v13-web\\v13-center-web\\src\\main\\resources\\templates\\product\\list.html");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StorePath html = client.uploadFile(inputStream, file.length(), "html", null);
            System.out.println(html.getFullPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void del(){
            client.deleteFile("group1/M00/00/00/wKj-gF0DhOKAOkuUAAAo7cqb_jE13.html");
            System.out.println("删除成功");

    }

}
