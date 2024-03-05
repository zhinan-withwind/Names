package run.zhinan.names.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 海报工具类
 */
public class PosterUtils {
    private static final Font font = loadFont("src/main/resources/font/QiGong-Traditional.ttf");
    public static BufferedImage openImage(String imageFile) {
        try {
            File file = new File(imageFile);
            return ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void saveImage(BufferedImage image, String imageFile) {
        try {
            File file = new File(imageFile);
            ImageIO.write(image, "png", file);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Font loadFont(String fontFile) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontFile));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new Font("宋体", Font.PLAIN, 30);
    }

    /**
     * 在图片上写文字
     * @param image 图片
     * @param text 要写的文字
     * @param x x坐标
     * @param y y坐标
     * @param font 字体
     * @param color 文字颜色
     * @param direction 方向 0-竖直 1-水平
     */
    @SneakyThrows
    private static void writeTextToImage(BufferedImage image, String text, int x, int y, Font font, int style, int size, Color color, int direction) {
            // 获取图形上下文
            Graphics2D g = image.createGraphics();

            // 设置字体和颜色
            g.setFont(font.deriveFont(style, size));
            g.setColor(color);

            // 在指定位置绘制文字
            if (direction == 0) {
                for (int i = 0; i < text.length(); i++) {
                    String s = text.substring(i, i + 1);
                    g.drawString(s, x, y + g.getFontMetrics().getHeight() * i);
                }
            } else {
                g.drawString(text, x, y);
            }
            g.dispose();
    }

    private static void printWaterMark(BufferedImage image, String waterMark) {
        writeTextToImage(image, waterMark.substring(0, 2), 165, 1140, font, Font.BOLD, 30, Color.WHITE, 1);
        writeTextToImage(image, waterMark.substring(2, 4), 165, 1180, font, Font.BOLD, 30, Color.WHITE, 1);
    }

    public static void generatePoster(String name, String sentence1, String sentence2, String author) {
        BufferedImage image = openImage("src/main/resources/image/background.png");
        printWaterMark(image, "悦清品名");
        if (image != null) {
            writeTextToImage(image, name     , 300, 600, font, Font.PLAIN, 120, Color.BLACK, 0);
            writeTextToImage(image, sentence1, 600, 600, font, Font.PLAIN,  50, Color.BLACK, 0);
            writeTextToImage(image, sentence2, 700, 600, font, Font.PLAIN,  50, Color.BLACK, 0);
            if (author != null) {
                writeTextToImage(image, author, 800, 800, font, Font.PLAIN, 30, Color.BLACK, 0);
            }
            saveImage(image, "/Users/withwind/Downloads/" + name + ".png");
        }

    }

    @SneakyThrows
    public static void main(String[] args) {
        String jsonFilePath = "src/main/resources/data/names.json";
        JSONArray data = JSON.parseArray(new String(Files.readAllBytes(Paths.get(jsonFilePath))));
        for (Object o : data) {
            JSONObject d = (JSONObject) o;
            generatePoster(
                    d.getString("name"),
                    d.getString("sentence1"),
                    d.getString("sentence2"),
                    d.getString("author") + " " + d.getString("poem"));
        }
    }
}
