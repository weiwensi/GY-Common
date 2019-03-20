package com.gysoft.utils.test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 图片压缩工具测试
 *
 * @author 周宁
 * @Date 2018-12-06 14:57
 */
public class ThumbnailatorTest {

    public static void main(String[] args) throws Exception{
        //缩小图片为80像素×80像素
        Thumbnails.of("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\微信图片_20181012104010.jpg")
                .size(80, 80).toFile("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\Thumbnailator\\a.jpg");
        //在目录中创建图像文件的JPEG缩略图，全部调整为80像素×80像素的最大尺寸，同时保留原始图像的纵横比
        Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\Thumbnailator").listFiles())
                .size(80, 80)
                .outputFormat("jpg")
                .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
        //等比压缩图片，大小不变，图片质量变为原图的1/4
        Thumbnails.of("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\微信图片_20181012104010.jpg")
                .scale(1).outputQuality(0.25).toFile("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\Thumbnailator\\b.jpg");
        //创建80像素×80像素缩略图
        BufferedImage bufferedImage = Thumbnails.of("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\微信图片_20181012104010.jpg")
                .size(80,80).asBufferedImage();
        ImageIO.write(bufferedImage, "jpg", new File("C:\\Users\\Administrator\\Desktop\\工作\\我的图片\\Thumbnailator\\BufferedImage.jpg"));
    }

}
