package com.szbf.util;

import com.szbf.SpringbootApplication;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName ImageUtil.java
 * @Description TODO
 * @createTime 2023年10月10日 10:55:00
 */
public class ImageUtil {

    public static String imagePngToBase64String(String imageName) throws IOException {
     /*   ClassLoader classLoader = SpringbootApplication.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(imageName);
        resourceAsStream.read();*/
        BufferedImage read = ImageIO.read(Objects.requireNonNull(ImageUtil.class.getClassLoader().getResource(imageName)));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        /*byte[] buffer = new byte[4096];*/
        ImageIO.write(read,"png",outputStream);
        byte[] bytes = outputStream.toByteArray();
/*        int bytesRead;
        while ((bytesRead = resourceAsStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] bytes = outputStream.toByteArray();*/
        String base64Image = Base64.getEncoder().encodeToString(bytes);
        return replaceEnter(base64Image);
    }
    public static String replaceEnter(String str){
        String reg="[\n-\r]";
        Pattern p=Pattern.compile(reg);
        Matcher m=p.matcher(str);
        return m.replaceAll("");
    }


}
