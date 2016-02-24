package com.zghw.spring.demo.core.util;

import java.io.*;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

/**
 * 输入输出流的操作
 * @author zghw
 *
 */
public class StreamUtilsTest {

	public static void main(String[] args) throws IOException {
		InputStream in =new FileInputStream("/home/zghw/hs_err_pid2308.log");
		//输入流转换为字节数组
		byte[] b=StreamUtils.copyToByteArray(in);
		/*for(byte bb :b){
			System.out.println(bb);
		}*/
		Charset charset=Charset.forName("UTF-8");
		charset=Charset.defaultCharset();
		//输入流转换为字符串
		String s=StreamUtils.copyToString(in, charset);
		System.out.println(s);
	}

}
