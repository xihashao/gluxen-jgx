package com.gluxen.jgx.util;

/**
 * ValidateUtil
 * @author lishiqiang
 * @date 2017-3-15 modify history
 */
public class ValidateUtil {
	// 用户名密码 字符串 A-Z，a-z，0-9
	private static final String userStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
	private static final String numberStr = "0123456789";

	/**
	 * 判断是否是字母和数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean IsNumZM(String str) {
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')
					|| (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
					|| (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
				j = j + 1;
			}
		}
		if (j == str.length()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否不符合登陆字符串
	 * 
	 * @param str
	 * @return 有不符合
	 */
	public static boolean isUserErrStr(String str) {
		if (isNull(str))
			return false;
		str = str.trim();
		char[] tempChar = str.toCharArray();
		for (int kk = 0; kk < tempChar.length; kk++) {
			if (userStr.indexOf(tempChar[kk]) == -1)
				return true;
		}
		return false;
	}

	/**
	 * Null和空验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null)
			str = "";
		str = str.trim();
		if (str.equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 是否由数字组成
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isNull(str))
			return false;
		str = str.trim();
		char[] tempChar = str.toCharArray();
		for (int kk = 0; kk < tempChar.length; kk++) {
			if (numberStr.indexOf(tempChar[kk]) == -1)
				return false;
		}
		return true;
	}

	/**
	 * 长度判断
	 * 
	 * @param str
	 * @param stt
	 * @param end
	 * @return
	 */
	public static boolean isLength(String str, int stt, int end) {
		if (isNull(str))
			return false;
		str = str.trim();
		if (str.length() >= stt && str.length() <= end)
			return true;
		else
			return false;
	}

	/**
	 * 验证手机号码是否合法
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean validateMovePhone(String phoneNumber) {
		// 如果号码为空或是长度不为11，返回false;
		if (phoneNumber == null || phoneNumber.trim().length() != 11)
			return false;
		try {
			long l = Long.parseLong(phoneNumber) / 100000000;
			if (l >= 130 && l < 140)
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isPhone(String number) {
		return true;
	}

	/**
	 * 验证邮件地址合法性
	 * 
	 * @param emailAddr
	 * @return
	 */
	public static boolean validateEmail(String emailAddr) {
		if (emailAddr == null || emailAddr.trim().indexOf("@") == -1)
			return false;
		if (emailAddr.lastIndexOf(".") < emailAddr.indexOf("@"))
			return false;
		return true;
	}

	/**
	 * 验证身份证号码合法性
	 * 
	 * @param sfzNumber
	 * @return
	 */
	public static boolean validateSfz(String sfzNumber) {
		return SfzValidate.Verify(sfzNumber);
	}

	/**
	 * 取得身份证信息
	 * 
	 * @param s
	 * @return
	 */
	public static String[] getSfzInfo(String s) {
		return SfzValidate.getSfzInfo(s);
	}

	/**
	 * 身份证号15位升成18位
	 * 
	 * @param fifteencardid
	 * @return
	 */
	public static String UpTo18(String fifteencardid) {
		return SfzValidate.uptoeighteen(fifteencardid);
	}

	/**
	 * 测试
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
		String s = "51040319770907131X";
		System.out.println(validateSfz(s));
	}

	/**
	 * 身份证验证内隐类
	 */
	private static class SfzValidate {
		// wi =2(n-1)(mod 11)
		static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
				8, 4, 2, 1 };

		// verify digit
		static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };

		private static int[] ai = new int[18];

		// verify
		public static boolean Verify(String idcard) {
			if (idcard == null)
				return false;
			if (idcard.length() == 15) {
				idcard = uptoeighteen(idcard);
			}
			if (idcard.length() != 18) {
				return false;
			}
			String verify = idcard.substring(17, 18);
			if (verify.equals(getVerify(idcard))) {
				return true;
			}
			return false;
		}

		// get verify
		public static String getVerify(String eightcardid) {
			int remaining = 0;
			if (eightcardid.length() == 18) {
				eightcardid = eightcardid.substring(0, 17);
			}
			if (eightcardid.length() == 17) {
				int sum = 0;
				for (int i = 0; i < 17; i++) {
					String k = eightcardid.substring(i, i + 1);
					ai[i] = Integer.parseInt(k);
				}
				for (int i = 0; i < 17; i++) {
					sum = sum + wi[i] * ai[i];
				}
				remaining = sum % 11;
			}
			return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
		}

		/**
		 * 15 update to 18
		 */

		public static String uptoeighteen(String fifteencardid) {
			if (fifteencardid == null || fifteencardid.length() != 15)
				return fifteencardid;
			String eightcardid = fifteencardid.substring(0, 6);
			eightcardid = eightcardid + "19";
			eightcardid = eightcardid + fifteencardid.substring(6, 15);
			eightcardid = eightcardid + getVerify(eightcardid);
			return eightcardid;
		}

		/**
		 * 取得身份证信息
		 * 
		 * @param s
		 * @return
		 */
		public static String[] getSfzInfo(String s) {
			if (s.length() == 15)
				s = uptoeighteen(s);
			if (Verify(s)) {
				String[] sl = new String[3];
				sl[0] = s.substring(0, 6);// 发证单位号码
				sl[1] = s.substring(6, 10) + "年" + s.substring(10, 12) + "月"
						+ s.substring(12, 14) + "日";// 出身年月
				sl[2] = Integer.parseInt(s.substring(16, 17)) % 2 == 1 ? "男"
						: "女"; // 性别
				return sl;
			} else {
				return null;
			}
		}
	}
}
