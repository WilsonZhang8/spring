package com.zghw.spring.demo.core.util;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
/**
 * 数据流之间的拷贝
 * @author zghw
 *
 */
public class FileCopyUtilsTest {

	public static void main(String[] args) throws IOException {
		File f1 = new File("/home/zghw/b.txt");
		File f2 = new File("/home/zghw/test/b.txt");
		File f3 = new File("/home/zghw/hs_err_pid2467.log");
		//int count= FileCopyUtils.copy(f1, f2);
		//复制文件1中的文件到2中
		int count= FileCopyUtils.copy(f3, f2);
		byte[]  b =new byte[10];
		b[0]=1;
		b[1]=23;
		b[1]=54;
		//复制字节数组到文件中
		FileCopyUtils.copy(b, f1);
		System.out.println(count);
		//输出文件的byte字节数组
		for(byte ba :FileCopyUtils.copyToByteArray(f1)){
			System.out.println(ba);
		}
		
	}

}
