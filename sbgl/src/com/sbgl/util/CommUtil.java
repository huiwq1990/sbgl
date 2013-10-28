package com.sbgl.util;

public class CommUtil {
	public static String getWherePart(String fieldName, String controlValue,
			String strOperate, String fieldType) {
		String strWherePart = "";
		String value = "";

		if (fieldName == null || fieldName.equals("")) {
			return strWherePart;
		}
		if (controlValue == null || controlValue.equals("")) {
			return strWherePart;
		}

		value = controlValue.trim();
		if (value == null || value.equals("")) {
			return strWherePart;
		}

		if (fieldType == null || fieldType == "") {
			fieldType = "0";
		}
		if (strOperate == null || strOperate.equals("")) {
			strOperate = "=";
		}

		if (fieldType == "0") { // 0:字符型
			if (strOperate.equals("like")) {
				strWherePart = " and " + fieldName.trim() + " like '%"
						+ value.trim() + "%' ";
			} else { 
				// 如果条件中包含有@@，则视为通配符
				if (value.indexOf("@@") >= 0) {
					value = value.replaceAll("@@", "%");
					strWherePart = " and " + fieldName.trim() + " like '"
							+ value.trim() + "' ";
				} else {
					strWherePart = " and " + fieldName.trim()
							+ strOperate.trim() + "'" + value.trim() + "' ";
				}
			}
		}

		if (fieldType == "1") { // 1:数字型
			strWherePart = " and " + fieldName.trim() + strOperate.trim()
					+ value.trim() + " ";
		}

		return strWherePart;
	}
}
