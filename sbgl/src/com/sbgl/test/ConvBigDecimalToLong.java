package com.sbgl.test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ConvBigDecimalToLong {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String filePath = "D:/github/sbgl/sbgl/src/com/sbgl/app/entity";
		File f = new File(filePath);
		File[] files = f.listFiles();
		// List<File> fileList =
		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			List<String> strList = FileUtils.readLines(file);
			List<String> newStrList = new ArrayList<String>();
			System.out.println(file.getName());
			if (file.getName().split("\\.")[1].equals("java")) {
				newStrList = convJava(strList);
			}
			if (file.getName().split("\\.")[1].equals("hbm")) {
				newStrList = convXml(strList);
			}

			FileUtils.writeLines(new File(file.getPath()), newStrList);

		}

	}

	public static List<String> convJava(List<String> strList) {
		List<String> newStrList = new ArrayList<String>();
		String temp = "";
		for (int i = 0; i < strList.size(); i++) {

			if (strList.get(i).equals("import java.sql.Timestamp;")) {
				temp = "import java.util.Date;";
			} else if (strList.get(i).contains("Timestamp")) {
				temp = strList.get(i).replace("Timestamp", "Date");

			} else {
				temp = strList.get(i);
			}
			newStrList.add(temp);
		}

		return newStrList;
	}

	public static List<String> convXml(List<String> strList) {
		List<String> newStrList = new ArrayList<String>();
		String temp = "";
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
			if (strList.get(i).contains("java.sql.Timestamp")) {
				temp = strList.get(i).replace("java.sql.Timestamp", "java.util.Date");
			}else {
				temp = strList.get(i);
			}

			newStrList.add(temp);
		}
		return newStrList;
	}

}
