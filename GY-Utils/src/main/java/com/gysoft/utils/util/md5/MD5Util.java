/**
 * 文件名：MD5Util.java
 *
 * 版本信息：
 * 日期：2012-2-17
 * Copyright 足下 Corporation 2012
 * 版权所有
 *
 */
package com.gysoft.utils.util.md5;

import com.gysoft.utils.jdbc.pojo.IdGenerator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 * @Description: 文件md5
 * @author DJZ-PJJ
 * @date 2018年4月3日 下午5:07:32
 */
public class MD5Util {
	/**
	 * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	protected static MessageDigest messagedigest = null;

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	/**
	 *
	 * @Description: 适用于上G大的文件(此接口有问题对于大文件取MD5)
	 * @author DJZ-PJJ
	 * @date 2018年4月3日 下午5:09:07
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		if(file.length()<=(3*(1L<<31))){
			return getBigFileMD5String(file);
		}
		long length = 1L << 22;
		long cur = 0L;
		String filePathString = file.getPath();
		File newfile = new File(filePathString);
		FileInputStream in = new FileInputStream(newfile);
		try {
			FileChannel ch = in.getChannel();
			while (cur < file.length() ){
				MappedByteBuffer byteBuffer;
				if(cur+length >= file.length()){
					byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, cur, file.length()-cur);
				}else{
					byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, cur, length);
				}
				messagedigest.update(byteBuffer);
				cur += length;
			}
			String string =bufferToHex(messagedigest.digest());
			return string;
		} catch (Exception e) {
			throw e;
		}finally {
			in.close();
		}

	}

	private static String getBigFileMD5String(File file) throws IOException {
		try (FileInputStream in = new FileInputStream(file)){
			byte[] buffer = new byte[8192];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				messagedigest.update(buffer, 0, len);
			}
			in.close();
			String string = bufferToHex(messagedigest.digest());
			return string;
		}catch (Exception e) {
			throw e;
		}
	}




	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	public static String getMD5String(ByteBuffer buff) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(buff.array(), 0, buff.position());
			return bufferToHex(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static void main(String[] args) throws IOException {
//		File big = new File("D:\\Test\\uploadtmp\\20181114.jpg");
//		File big = new File("C:\\Users\\Administrator\\Desktop\\q\\微信图片_20181229160817.jpg");
//		File bigFile = new File("F:\\workTest\\HangZhou_osgb.rar");
//		System.out.println("fileSize:" + big.length());
//		System.out.println("fileName:" + big.getName());
//		String bigMd5 = getBigFileMD5String(bigFile);
//		String md5 = getFileMD5String(bigFile);
//		long end = System.currentTimeMillis();
//		System.out.println("md5:" + md5);
//		System.out.println("bigMd5:" + bigMd5);
		String pwd = "GY*&%mqtt";
		System.out.println(getMD5String(pwd));
//		System.out.println(getMD5String("111111cb4f6cdc"));
		String salt = IdGenerator.createSalt();
		System.out.println(salt);
		System.out.println(getMD5String("50e50bc849f2047c9b753944458dbe7c"+"264c2adf"));
	}
}