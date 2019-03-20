/** 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author DJZ-PJJ
 * @date 2018年4月8日 下午3:15:49 
 */
package com.gysoft.utils.util.fileblock;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.gysoft.utils.util.md5.MD5Util;

/**
 * @Description: 文件分块信息工具类
 * @author DJZ-PJJ
 * @date 2018年4月8日 下午3:15:49
 */
public class FileBlockUtil {

	/**
	 * 
	 * @Description: 计算文件的分块信息
	 * @author DJZ-PJJ
	 * @date 2018年4月8日 下午4:22:12
	 * @param file
	 * @param blockSize
	 * @param fileMD5 文件的MD5编码，可以传null或空，交给程序计算
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static StringBuilder getFileBlockInfo(File file, Integer blockSize, String fileMD5) throws Exception {
		if (fileMD5 == null || fileMD5.equals("")) {
			fileMD5 = MD5Util.getFileMD5String(file);
		}

		StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<FileBlockInfo Ver=\"1.0\" FileName=\"").append(file.getName()).append("\" FileMD5=\"")
				.append(fileMD5).append("\" FileSize=\"").append(file.length()).append("\" BlockSize=\"")
				.append(blockSize).append("\">");
		FileChannel channel = null;
		FileInputStream in = null;
		try {
			in =  new FileInputStream(file);
			channel = in.getChannel();
			ByteBuffer buff = ByteBuffer.allocate(blockSize);
			int idx = 1;
			while (true) {
				int flag = channel.read(buff);
				if (flag == -1) {
					break;
				}
				String md5 = MD5Util.getMD5String(buff);
				result.append("<BlockItem>");
				result.append("<BlockId>").append(idx).append("</BlockId >");
				result.append("<BlockMD5>").append(md5).append("</BlockMD5>");
				result.append("</BlockItem>");
				idx++;
				buff.flip();
			}
		} finally {
			if (channel != null) {
				channel.close();
			}
			if (in !=null) {
				in.close();
			}
		}
		result.append("</FileBlockInfo>");
		return result;
	}

	/**
	 * 
	 * @Description: 计算文件的分块信息(指定文件名，而不用计算中的文件的文件名)
	 * @author DJZ-PJJ
	 * @date 2018年4月8日 下午4:22:43
	 * @param file
	 * @param fileName
	 * @param blockSize
	 * @param fileMD5 文件的MD5编码，可以传null或空，交给程序计算
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static StringBuilder getFileBlockInfo(File file, String fileName, Integer blockSize, String fileMD5)
			throws Exception {
		if (fileMD5 == null || fileMD5.equals("")) {
			fileMD5 = MD5Util.getFileMD5String(file);
		}
		StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<FileBlockInfo Ver=\"1.0\" FileName=\"").append(fileName).append("\" FileMD5=\"")
				.append(fileMD5).append("\" FileSize=\"").append(file.length()).append("\" BlockSize=\"")
				.append(blockSize).append("\">");
		FileChannel channel = null;
		try {
			channel = new FileInputStream(file).getChannel();
			ByteBuffer buff = ByteBuffer.allocate(blockSize);
			int idx = 1;
			while (true) {
				int flag = channel.read(buff);
				if (flag == -1) {
					break;
				}
				String md5 = MD5Util.getMD5String(buff);
				result.append("<BlockItem>");
				result.append("<BlockId>").append(idx).append("</BlockId >");
				result.append("<BlockMD5>").append(md5).append("</BlockMD5>");
				result.append("</BlockItem>");
				idx++;
				buff.flip();
			}
		} finally {
			if (channel != null) {
				channel.close();
			}
		}
		result.append("</FileBlockInfo>");
		return result;
	}

	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\fap数据库设计-周.docx");
			StringBuilder sb = getFileBlockInfo(file, 123, null);
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
