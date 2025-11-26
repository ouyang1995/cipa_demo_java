package com.szbf;

import com.alibaba.fastjson.JSONObject;
import com.szbf.constant.CommandEnum;
import com.szbf.constant.GetValue;
import com.szbf.constant.MethodName;
import com.szbf.pojo.LabelParam;
import com.szbf.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
@EnableAsync
public class SpringbootApplication {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("C:\\Program Files\\Java\\jdk1.8.0_151\\bin\\server.controller.cer"));
        TxtReadline.SampleManagement(new File(Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg"));
        SpringApplication.run(SpringbootApplication.class,args);
    }
}