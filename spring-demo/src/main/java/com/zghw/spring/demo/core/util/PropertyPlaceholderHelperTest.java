package com.zghw.spring.demo.core.util;

import java.util.Properties;

import org.springframework.util.PropertyPlaceholderHelper;
/**
 * 用处：解析文件中占位符并替换时。
 * @author zghw
 *
 */
public class PropertyPlaceholderHelperTest {

	public static void main(String[] args) {
		//占位符基本的 {} () []
		//创建一个占位符对象
		PropertyPlaceholderHelper pph = new PropertyPlaceholderHelper("==","}");
		String ph ="==name}and==age}";
		Properties prop=new Properties();
		prop.setProperty("name", "zghw");
		prop.setProperty("age", "27");
		String result =pph.replacePlaceholders(ph, prop);
		System.out.println(result);
		//自定义的== } 以-分隔两个占位符不显示 忽略不正确的
		PropertyPlaceholderHelper pph1 = new PropertyPlaceholderHelper("==","}","-",true);
		String ph1 ="==name-job} and ==age}==-}==job} ";
		Properties prop1=new Properties();
		prop1.setProperty("name", "zghw");
		prop1.setProperty("age", "27");
		prop1.setProperty("job", "it");
		String result1 =pph1.replacePlaceholders(ph1, prop1);
		System.out.println(result1);
		
	}

}
