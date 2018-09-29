/**
 * 
 */
package cn.pair.arithmetic.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * function:处理文件输入输出
 * 
 * @author liangjing yuanzhijie
 *
 */
public class FileUtil {

	/**
	 * function:将生成的题目存入执行程序的当前目录下的Exercises.txt文件
	 * 
	 * @param questions
	 * @throws IOException
	 */
	public static void generate(HashSet<String> questions) throws IOException {

		long startTime = System.currentTimeMillis();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
				"D:\\Exercises.txt"));
		int i = 1;
		// 遍历集合
		for (String question : questions) {
			// 写入数据
			bufferedWriter.write(i + "、" + question);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			i++;
		}
		long endTime = System.currentTimeMillis();
		bufferedWriter.write("總耗時：" + (endTime - startTime) + "ms");
		bufferedWriter.flush();
		// 释放资源
		bufferedWriter.close();
	}

	/**
	 * function:将生成的题目的答案存入执行程序的当前目录下的Answers.txt文件
	 * 
	 * @param questions
	 * @throws IOException
	 */
	public static void answer(List<String> answers) throws IOException {

		long startTime = System.currentTimeMillis();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
				"D:\\Answers.txt"));
		int i = 1;
		// 遍历集合
		for (String answer : answers) {
			// 写入数据
			bufferedWriter.write(i + "、" + answer);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			i++;
		}
		long endTime = System.currentTimeMillis();
		bufferedWriter.write("總耗時：" + (endTime - startTime) + "ms");
		bufferedWriter.flush();
		// 释放资源
		bufferedWriter.close();
	}

}
