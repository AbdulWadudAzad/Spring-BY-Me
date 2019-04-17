package com.azad.templatequickjob.imagoeptimizer;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class ImageOptimizer {
    public void optimizeImage(String UPLOADED_FOLDER, MultipartFile file, float quality, int width, int height) throws IOException {
        MultipartFile multipartFile = null;
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        System.out.println("Path: "+path);
        Path pathOut = Paths.get(UPLOADED_FOLDER +"new-"+ file.getOriginalFilename());
        //create instance of File
        File optimizedImage=new File(pathOut.toString());
        File inputImage=new File(path.toString());
        //BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(Paths.get(basePath + imageSource)));
        BufferedImage bufferedImage=ImageIO.read(Files.newInputStream(path));
        BufferedImage resizedBufferedImage=resize(bufferedImage,height,width);
        ImageIO.write(resizedBufferedImage,"jpg",optimizedImage);
        //  multipartFile.transferTo(optimizedImage);
        //  return multipartFile;
    }
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        return resized;
    }
}
