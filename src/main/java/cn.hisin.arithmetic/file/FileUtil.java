package cn.hisin.arithmetic.file;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author hisin
 * 文件相关的工具类
 */
public class FileUtil {


    public static boolean createFile(byte[] bytes,String path) throws IllegalAccessException, IOException {
        FileOutputStream fileOutputStream = null;
        try {
            //创建一个文件
            File file = new File(path);
            if (file.exists()){
                throw new IllegalAccessException("目标文件已经存在");

            }else if (file.isDirectory()){
                throw new IllegalAccessException("目标文件不能是一个目录");
            }
            if (!file.getParentFile().exists()){
                if (!file.getParentFile().mkdir()){
                    throw new IOException("文件目录创建失败");
                }
            }
            if (file.createNewFile()){
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                return true;
            }
            throw new IOException("创建写入文件失败");
        }finally {
            if (fileOutputStream!=null){
                fileOutputStream.close();
            }
        }

    }

    public static void main(String[] args) {
        try {
            BufferedImage read = ImageIO.read(new File("C:\\Users\\qiqiang\\Pictures\\1.jpg"));
            createFile(read.toString().getBytes(StandardCharsets.UTF_8),"E:\\Work\\JavaTest\\JAVA-TEST\\src\\main\\resources\\a.jpg");
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }
}
