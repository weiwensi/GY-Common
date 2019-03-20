package com.gysoft.utils.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;


public class FileCodeChangeUtil {
	private static final String UTF8 = "UTF-8";
	private static Logger logger = Logger.getLogger(FileCodeChangeUtil.class);
	public static String lineseparator = System.getProperty("line.separator");
	/**
	 * 
	 * @Description: 判断文本文件编码
	 * @author DJZ-PJJ
	 * @date 2018年7月10日 下午5:23:17
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String  getFileCode(File file) throws Exception{
		if(file==null || !file.exists() ||  !file.isFile()){
			throw new Exception("获取文本文件编码错误，文件不存在");
		}
		return CodingDecoder.getFileCode(file);
	}
	
	/**
	 * 
	 * @Description:转换文本文件编码到UTF-8
	 * @author DJZ-PJJ
	 * @date 2018年7月10日 下午5:23:29
	 * @param file
	 * @param newFileName
	 * @return
	 * @throws Exception
	 */
	public static File convertFileToUTF8(File file,String newFileName) throws Exception{
		String oldCode = getFileCode(file);
		File newFile = new File(newFileName);
		if(oldCode.equals(UTF8)){
			FileUtil.copyFileByIO(file.getAbsolutePath(), newFileName);
			return newFile;
		}
		BufferedReader bin = null;
        OutputStreamWriter fos = null;
        try {
			bin = new BufferedReader(new InputStreamReader(new FileInputStream(file), oldCode));
			fos = new OutputStreamWriter(new FileOutputStream(newFile), UTF8);
			String line = null;
			while ((line = bin.readLine()) != null) {
				fos.write(line);
				fos.write(lineseparator);
			}
			fos.flush();
			fos.close();
            bin.close();
            fos = null;
            bin = null;
            return newFile;
        } catch (Exception e) {
        	logger.error("转换文本文件到UTF-8编译异常",e);
		    throw new Exception("转换文本文件到UTF-8编译异常",e);
        }finally{
        	if(bin != null){
        		bin.close();
        	}
        	if(fos != null){
        		fos.close();
        	}
        }
    }
}