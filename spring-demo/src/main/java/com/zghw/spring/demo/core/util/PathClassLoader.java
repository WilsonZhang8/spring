package com.zghw.spring.demo.core.util;

import java.io.*;


public class PathClassLoader extends ClassLoader {
	private String classPath;

	/**
	 * 设置类路径
	 * 
	 * @param classPath
	 */
	public PathClassLoader(String classPath) {
		this.classPath = classPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = findLoadedClass(name);
		if (clazz != null) {
			return clazz;
		}
		byte[] b = readClass(name);
		if (b == null) {
			throw new ClassNotFoundException();
		} else {
			clazz = defineClass(name, b, 0, b.length);
			return clazz;
		}

	}

	private byte[] readClass(String name) {
		String path = this.classPath + File.separatorChar
				+ name.replace('.', File.separatorChar) + ".class";
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			is = new FileInputStream(new File(path));
			baos = new ByteArrayOutputStream();
			byte[] bs = new byte[1024];
			int length;
			while ((length = is.read(bs)) > -1) {
				baos.write(bs, 0, length);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (baos != null)
					baos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String args[]) {
		PathClassLoader pcl = new PathClassLoader("/tmp");
		try {
			pcl.loadClass("test.TestPath");
			System.out.println(pcl);
			//java.lang.NoClassDefFoundError出现的原因是，
			//输入查找的是test.ThisX 而查找的ThisX.class文件对应的包是com.zghw.base.ThisX
			pcl.loadClass("test.ThisX");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
