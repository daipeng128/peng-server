package com.peng.itrat.core.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ImageUtil {
    public static final String THUMB_DEFAULT_PREVFIX = "thumb_";
    public static final String SMALL_DEFAULT_PREVFIX = "small_";
    private static final int DEFAULT_WIDTH = 260;
    private static final int DEFAULT_HEIGHT = 160;
    private static final int THUMB_DEFAULT_WIDTH = 160;
    private static final int THUMB_DEFAULT_HEIGHT = 160;
    private File targetFile;

    public ImageUtil() {
    }

    public String dealImage(File imgFile) {
        this.thumbnailImage(imgFile);
        return this.cutImage(this.targetFile);
    }

    private String thumbnailImage(File imgFile, String prevfix) {
        String thumbPath = "";
        String fileName = "";
        if(imgFile.exists()) {
            try {
                String e = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }

                if(suffix == null || e.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    return thumbPath;
                }

                BufferedImage img = ImageIO.read(imgFile);
                int width = img.getWidth((ImageObserver)null);
                int height = img.getHeight((ImageObserver)null);
                int w;
                int h;
                if(width > height) {
                    h = 160;
                    w = (int)((double)(width * h) * 1.0D / ((double)height * 1.0D));
                } else {
                    w = 260;
                    h = (int)((double)(height * w) * 1.0D / ((double)width * 1.0D));
                }

                BufferedImage bi = new BufferedImage(w, h, 1);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, (ImageObserver)null);
                g.dispose();
                fileName = prevfix + imgFile.getName();
                String p = imgFile.getPath();
                thumbPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + fileName;
                this.targetFile = new File(thumbPath);
                ImageIO.write(bi, suffix, this.targetFile);
            } catch (Exception var15) {
                var15.printStackTrace();
            }
        }

        return fileName;
    }

    private String thumbnailImage(File imgFile) {
        return this.thumbnailImage(imgFile, "small_");
    }

    private String cutImage(File imgFile) {
        String fileName = "";
        String thumbPath = "";

        try {
            try {
                String e = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }

                if(suffix == null || e.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    return thumbPath;
                }

                BufferedImage img = ImageIO.read(imgFile);
                int width = img.getWidth((ImageObserver)null);
                int height = img.getHeight((ImageObserver)null);
                int w;
                int x;
                if(width > 160) {
                    x = (width - 160) / 2;
                    w = 160 + x;
                } else {
                    w = width;
                    x = 0;
                }

                int h;
                int y;
                if(height > 160) {
                    y = (height - 160) / 2;
                    h = 160 + y;
                } else {
                    h = height;
                    y = 0;
                }

                BufferedImage bi = new BufferedImage(160, 160, 1);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, x, y, x + w, y + h, (ImageObserver)null);
                g.dispose();
                String name = imgFile.getName();
                name = name.replaceAll("small_", "");
                fileName = "thumb_" + name;
                String p = imgFile.getPath();
                thumbPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + fileName;
                this.targetFile = new File(thumbPath);
                ImageIO.write(bi, suffix, this.targetFile);
            } catch (Exception var20) {
                var20.printStackTrace();
            }

            return fileName;
        } finally {
            ;
        }
    }
}
