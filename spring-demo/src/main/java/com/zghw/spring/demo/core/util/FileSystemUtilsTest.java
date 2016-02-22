package com.zghw.spring.demo.core.util;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileSystemUtils;

public class FileSystemUtilsTest {
	public static void main(String args[]) throws IOException{
		File f1 = new File("/home/zghw/test");
		File f2 = new File("/home/zghw/sourceFile");
		//删除该文件及该文件下的所有
		FileSystemUtils.deleteRecursively(f1);
		//第归拷贝f2中的文件到f1中
		//FileSystemUtils.copyRecursively(f2, f1);
	}
}
