package com.zghw.spring.demo.core.util;

import java.util.*;
import java.util.Properties;
import java.util.Random;

import org.springframework.util.StringUtils;

/**
 * String工具类示例
 * 
 * @author zghw
 *
 */
public class StringUtilsTest {
	public static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		// 判断字符串是否为空
		// 字符串trim以后来判断是否为空
		String str = null;
		f("String str=null时调用为空：StringUtils.isEmpty(str)=true，是否为空："
				+ StringUtils.isEmpty(str));
		f("String str=null时调用没有长度：StringUtils.hasLength(str)=false是否有长度："
				+ StringUtils.hasLength(str));
		f("String str=null时调用为空：StringUtils.hasText(str)=true，是否为空："
				+ StringUtils.hasText(str));
		str = "";
		f("String str=\"\"时调用为空：StringUtils.isEmpty(str)=true，是否为空："
				+ StringUtils.isEmpty(str));
		f("String str=\"\"时调用没有长度：StringUtils.hasLength(str)=false，是否有长度："
				+ StringUtils.hasLength(str));
		f("String str=\"\"时调用为空：StringUtils.hasText(str)=false，是否有长度："
				+ StringUtils.hasText(str));
		str = " ";
		f("String str=\" \"（中间有空格）时调用不为空：StringUtils.isEmpty(str)=false，是否为空："
				+ StringUtils.isEmpty(str));
		f("String str=\" \"（中间有空格）时调用有长度：StringUtils.hasLength(str)=true，是否有长度："
				+ StringUtils.hasLength(str));
		f("String str=\" \"时调用为空：StringUtils.hasText(str)=false，是否有长度："
				+ StringUtils.hasText(str));

		str = "  sdf_sd sd A_	b S_DF _DS    ";
		f("正常字符串  :" + str + "=");
		f("去除前后空格：" + StringUtils.trimWhitespace(str) + "=");
		f("去除全部空格：" + StringUtils.trimAllWhitespace(str) + "=");
		f("去除头部空格：" + StringUtils.trimLeadingWhitespace(str) + "=");
		f("去除尾部空格：" + StringUtils.trimTrailingWhitespace(str) + "=");
		str = " aBstrs";
		String prefix = "Abs";
		// 注意空格也算
		f("忽略前缀字符串大小写判断是否出现在在str首位："
				+ StringUtils.startsWithIgnoreCase(str, prefix));
		str = "aBstrs";
		f("忽略前缀字符串大小写判断是否出现在在str首位："
				+ StringUtils.startsWithIgnoreCase(str, prefix));
		String suffix = "Trs";
		f("忽略后缀字符串大小写判断是否出现在在str首位："
				+ StringUtils.endsWithIgnoreCase(str, suffix));
		String sub = "zg";
		str = "asdfasdzghwsdfasdzg sdfsdfagzgwgawefsdfGszaagSAzgsdfsdfsd";
		f("统计sub在str中出现的次数：" + StringUtils.countOccurrencesOf(str, sub));
		f("把str中的zg替换为ZG：" + StringUtils.replace(str, "zg", "ZG"));
		f("把str中的sd   删除：" + StringUtils.delete(str, "sd"));
		f("把str中的a f z删除：" + StringUtils.deleteAny(str, "afz"));

		// 美化str
		str = "str";
		f("加上单引号:" + StringUtils.quote(str));
		f("去掉全限定名：" + StringUtils.unqualify("com.zghw.StringUtilTest"));
		f("去掉文件名：" + StringUtils.unqualify("/home/zghw/a.txt", '/'));
		f("第一个单词转化为大写：" + StringUtils.capitalize("zghw"));
		f("第一个单词转化为小写：" + StringUtils.uncapitalize("Gghw"));
		f("去掉路径名得到文件名：" + StringUtils.getFilename("/home/zghw/a.txt"));
		f("得到文件名扩展名：" + StringUtils.getFilenameExtension("/home/zghw/a.txt"));
		f("去掉文件名扩展名：" + StringUtils.stripFilenameExtension("/home/zghw/a.txt"));
		f("路径+文件合并成文件路径："
				+ StringUtils
						.applyRelativePath("/home/zghw/a.txt", "test.java"));
		f("把\\分隔符替换成/ 并且去掉 ..文件符号 ："
				+ StringUtils
						.cleanPath("file:core\\..\\core\\io\\Resource.class"));
		f("比较两个地址是否相等： 会先把\\分隔符替换成/ 并且去掉 ..文件符号 ： 然后比较："
				+ StringUtils.pathEquals("file:core\\..\\io\\Resource.class",
						"file:core/../io/Resource.class"));
		// 字符串数组
		String[] strs = new String[5];
		strs[0] = str;
		strs[1] = "str1";
		strs[2] = "str2";
		strs = StringUtils.addStringToArray(strs, "str3");

		String[] strsA = new String[5];
		strsA[0] = "sdf";
		strsA[1] = "BB";
		strsA[2] = "AA";
		strsA[3] = "str2";
		strsA[4] = "str3";
		// 从一个数组添加到另一个数组中，不会覆盖以存在的字符串
		 strs=StringUtils.concatenateStringArrays(strs,strsA);
		//去除空值
		 strs = StringUtils.trimArrayElements(strs);
		 for (String s : strs) {
				System.out.print(s+",");
			}
		 f("去除重复值");
		 //去除重复值
		 strs =StringUtils.removeDuplicateStrings(strs);
		 for (String s : strs) {
				System.out.print(s+",");
			}
		// 这个会合并数组字符串
		//strs = StringUtils.mergeStringArrays(strs, strsA);
		
		// 排序数组不能为空
		 strs=StringUtils.sortStringArray(strsA);
		 f("排序");
		 for (String s : strs) {
				System.out.print(s+",");
			}
		//集合和字符串数组互相转换
		strs = StringUtils.toStringArray(Arrays.asList(strs));
		strs =StringUtils.removeDuplicateStrings(strs);
		f("添加字符串到字符串数组中" + strs);
		for (String s : strs) {
			f(s);
		}
		//分隔
		f("\n 分隔一次第一次，分开key vlaue 用于属性文件的分隔");
		for (String s : StringUtils.split("key:value", ":")) {
			System.out.print(s+",");
		}
		//Properties prop =StringUtils.splitArrayElementsIntoProperties(new String[]{"name:zghw","age:19","job:it"},":");
		//分割后的字符串要进行删除调-字符串
		Properties prop =StringUtils.splitArrayElementsIntoProperties(new String[]{"na-me:zg-hw","age:19","job:it"},":","-");
		System.out.println("分隔成属性文件格式:");
		for (Object s : prop.keySet()) {
			f(s+"="+prop.get(s));
		}
		str = "  sdf_sd sd A_	b S_DF _DS    ";
		//使用_分割，并对每个分开的字符进行去除空格及空白
		strs =StringUtils.tokenizeToStringArray(str,"_");
		System.out.println("分隔成属性文件格式:");
		for (String s : strs) {
			System.out.print(s+",");
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		String sss=StringUtils.collectionToDelimitedString(list," ; ","{ "," }");
		f("\n"+"循环集合中的每个元素并在元素前添加前缀{和后缀}，用；分割\n"+sss);
		String obj=StringUtils.arrayToDelimitedString(new Object[]{new Thread(),new ArrayList(),new Random()},",");
		f("\n"+obj);
	}

}
