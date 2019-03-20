package com.gysoft.utils.util.file;

import com.gysoft.utils.exception.DataNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);
    /**
     * 1m内存
     */
    private static final int LENGTH = 1048576;

    /**
     * @param inFile
     * @param outFile
     * @return
     * @throws Exception
     * @Description: IO拷贝
     * @author DJZ-PJJ
     * @date 2018年4月16日 下午1:56:25
     */
    public static long copyFileByIO(String inFile, String outFile) throws Exception {
        long begin = System.currentTimeMillis();
        File in = new File(inFile);
        if (!in.exists()) {
            return 0;
        }
        File out = new File(outFile);
        FileInputStream fin = new FileInputStream(in);
        FileOutputStream fout = new FileOutputStream(out);
        byte[] buffer = new byte[LENGTH];

        while (true) {
            int ins = fin.read(buffer);
            if (ins == -1) {
                fin.close();
                fout.flush();
                fout.close();
                break;


            } else
                fout.write(buffer, 0, ins);
        }
        long end = System.currentTimeMillis();
        long runtime = 0;
        if (end > begin)
            runtime = end - begin;
        return runtime;

    }

    /**
     * @param inFile
     * @param outFile
     * @return
     * @throws Exception
     * @Description: NIO拷贝
     * @author DJZ-PJJ
     * @date 2018年4月16日 下午1:56:58
     */
    public static long copyFileByNIO(String inFile, String outFile) throws Exception {
        long begin = System.currentTimeMillis();
        File in = new File(inFile);
        if (!in.exists()) {
            throw new DataNotFoundException("输入文件不存在");
        }
        File out = new File(outFile);
        FileInputStream fin = new FileInputStream(in);
        FileOutputStream fout = new FileOutputStream(out);
        FileChannel inc = fin.getChannel();
        FileChannel outc = fout.getChannel();
        ByteBuffer bb = ByteBuffer.allocateDirect(LENGTH);
        while (true) {
            int ret = inc.read(bb);
            if (ret == -1) {
                fin.close();
                fout.flush();
                fout.close();
                break;
            }
            bb.flip();
            outc.write(bb);
            bb.clear();
        }
        long end = System.currentTimeMillis();
        long runtime = 0;
        if (end > begin)
            runtime = end - begin;
        return runtime;

    }

    /**
     * @param file
     * @return
     * @Description: 删除文件和文件夹
     * @author DJZ-PJJ
     * @date 2018年4月16日 下午1:57:17
     */
    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            try {
                boolean result = file.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc();
                    file.delete();
                    file.deleteOnExit();
                }
            } catch (Exception e) {
                logger.error("文件删除失败，" + file.getAbsolutePath(), e);
            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length >= 0) {
                for (File f : files) {
                    deleteFile(f);
                }
            }
            try {
                boolean result = file.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc();
                    file.delete();
                    file.deleteOnExit();
                }
            } catch (Exception e) {
                logger.error("文件夹删除失败，" + file.getAbsolutePath(), e);
            }
        }
        return true;
    }

    /**
     * @param file
     * @return
     * @Description: 判断目录是否为空
     * @author DJZ-PJJ
     * @date 2018年4月16日 下午1:57:32
     */
    public static boolean hasSubFiles(File file) {
        if (!file.isDirectory()) {
            return false;
        }
        String[] subFiles = file.list();
        return subFiles != null && subFiles.length > 0;
    }
}
