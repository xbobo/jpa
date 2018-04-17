package com.xiaobo.util;

import java.awt.Color;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 工具类
 */
public abstract class UtilsHelper {

	/**
	 * 将对象转为为字符串，如果为null则返回"",如果为字符串则进行.trim()返回
	 * 
	 * @param obj
	 * @return
	 */
	public static String EmptyNull(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}

	/**
	 * 当obj为空时，返回rpc;当obj不为空则返回obj.trim()
	 * 
	 * @param obj
	 * @param rpc
	 * @return
	 */
	public static String NullReplace(Object obj, String rpc) {
		String temp_obj = EmptyNull(obj);
		if (temp_obj.equals("")) {
			return rpc;
		}
		return temp_obj;
	}

	/**
	 * 去除结尾字符
	 * 
	 * @param instr
	 * @param c
	 * @return
	 */
	public static String TrimEnd(String instr, String c) {
		if (instr == null) {
			return null;
		}
		if (c == null || c.equals("")) {
			return instr;
		}
		while (!instr.equals("") && instr.endsWith(c)) {
			instr = instr.substring(0, instr.length() - c.length()).trim();
		}
		return instr;
	}

	/**
	 * 去除开始字符
	 * 
	 * @param instr
	 * @param c
	 * @return
	 */
	public static String TrimStart(String instr, String c) {
		if (instr == null) {
			return null;
		}
		if (c == null || c.equals("")) {
			return instr;
		}
		while (!instr.equals("") && instr.startsWith(c)) {
			instr = instr.substring(c.length()).trim();
		}
		return instr;
	}

	/**
	 * 去除开始结尾字符
	 * 
	 * @param instr
	 * @param c
	 * @return
	 */
	public static String Trim(String instr, String c) {
		if (instr == null) {
			return null;
		}
		if (c == null || c.equals("")) {
			return instr;
		}
		return TrimEnd(TrimStart(instr, c), c);
	}

	/**
	 * 字节码转换成16进制字符串
	 * 
	 * @param byte[] b 输入要转换的字节码
	 * @return String 返回转换后的16进制字符串
	 */
	public static String Byte2Hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";

		}
		return hs.toUpperCase();

	}

	/**
	 * 字符串转成字节数组.
	 * 
	 * @param hex
	 *            要转化的字符串.
	 * @return byte[] 返回转化后的字符串.
	 */
	public static byte[] HexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			byte b = (byte) "0123456789ABCDEF".indexOf(achar[pos]);
			byte b2 = (byte) "0123456789ABCDEF".indexOf(achar[pos + 1]);
			result[i] = (byte) (b << 4 | b2);
		}
		return result;
	}

	public static byte[] IntToByteArray(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);

		}
		return bLocalArr;
	}

	// 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位
	public static int ByteArrayToInt(byte[] bRefArr) {
		int iOutcome = 0;
		byte bLoop;
		for (int i = 0; i < bRefArr.length; i++) {
			bLoop = bRefArr[i];
			iOutcome += (bLoop & 0xFF) << (8 * i);
		}
		return iOutcome;
	}

	/***
	 * 四舍五入
	 * 
	 * @param ft
	 * @param scale
	 *            保留的小数位
	 * @return
	 */
	public static Double RoundDouble(Double ft, int scale) {
		BigDecimal bd = new BigDecimal(ft);
		bd = bd.setScale(scale, 4);// 4标识四舍五入
		return bd.doubleValue();
	}

	/***
	 * 拆分到数组
	 * 
	 * @param in
	 *            需要解析的字符
	 * @param sp
	 *            解析分隔符
	 * @return
	 */
	public static List<String> SplitToList(String in, String sp) {
		List<String> temp = new ArrayList<String>();
		in = EmptyNull(in);
		if (!in.equals("")) {
			for (String s : in.split(sp)) {
				temp.add(s);
			}
		}
		return temp;
	}

	/***
	 * 拆分到数组
	 * 
	 * @param in
	 *            需要解析的字符
	 * @param sp
	 *            解析分隔符
	 * @param removeSame
	 *            去除相同的
	 * @return
	 */
	public static List<String> SplitToList(String in, String sp, boolean removeSame) {
		List<String> temp = new ArrayList<String>();
		in = EmptyNull(in);
		if (!in.equals("")) {
			for (String s : in.split(sp)) {
				if (removeSame) {
					if (temp.contains(s)) {
						continue;
					}
				}
				temp.add(s);
			}
		}
		return temp;
	}
	
	
	
	/***
	 * 拆分到数组
	 * 
	 * @param in
	 *            需要解析的字符
	 * @param sp
	 *            解析分隔符
	 * @return
	 */
	public static List<Long> SplitToLongList(String in, String sp) {
		List<Long> temp = new ArrayList<Long>();
		in = EmptyNull(in);
		if (!in.equals("")) {
			for (String s : in.split(sp)) {
				try{
				temp.add(Long.parseLong(s));
				}catch (Exception e) {
				}
			}
		}
		return temp;
	}

	/***
	 * 拆分到数组
	 * 
	 * @param in
	 *            需要解析的字符
	 * @param sp
	 *            解析分隔符
	 * @param removeSame
	 *            去除相同的
	 * @return
	 */
	public static List<Long> SplitToLongList(String in, String sp, boolean removeSame) {
		List<Long> temp = new ArrayList<Long>();
		in = EmptyNull(in);
		if (!in.equals("")) {
			for (String s : in.split(sp)) {
				try{
				if (removeSame) {
					if (temp.contains(Long.parseLong(s))) {
						continue;
					}
				}
				temp.add(Long.parseLong(s));
				}catch(Exception ex){
					
				}
			}
		}
		return temp;
	}

	public static String JoinListToStr(List<String> list, String sp) {
		if (list == null) {
			return "";
		}
		String temp = "";
		for (String s : list) {
			if (!temp.equals("")) {

				temp += ",";
			}
			temp += s;
		}
		return temp;
	}

	public static Color StringToColor(String str) {
		int i = Integer.parseInt(str.substring(1), 16);
		return new Color(i);
	}

	public static String ColorToString(Color color) {
		String R = Integer.toHexString(color.getRed());
		R = R.length() < 2 ? ('0' + R) : R;
		String B = Integer.toHexString(color.getBlue());
		B = B.length() < 2 ? ('0' + B) : B;
		String G = Integer.toHexString(color.getGreen());
		G = G.length() < 2 ? ('0' + G) : G;
		return '#' + R + B + G;
	}

	public static String SubString(String in, int length, String end) {
		if (in == null) {
			return null;
		}
		if (in.length() > length) {
			in = in.substring(0, length) + end;
		}
		return in;
	}

	public static String GetClientIp(HttpServletRequest request) {
		try {
			String ss = request.getHeader("x-forwarded-for");
			if (ss == null || ss.length() == 0 || "unknown".equalsIgnoreCase(ss)) {
				ss = request.getHeader("Proxy-Client-IP");
			}
			if (ss == null || ss.length() == 0 || "unknown".equalsIgnoreCase(ss)) {
				ss = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ss == null || ss.length() == 0 || "unknown".equalsIgnoreCase(ss)) {
				ss = request.getRemoteAddr();
			}
			return ss;
		} catch (Exception ex) {
			return "";
		}
	}
	
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static boolean isEmpty(Collection<?> coll){
		return coll == null || coll.isEmpty();
	}
	
	public static boolean isEmpty(Map<?,?> m){
		return m == null || m.isEmpty();
	}
	
	public static boolean isEmpty(String str){
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * 判断集合coll元素中的fieldName属性值是否包含 obj
	 * @param coll
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static boolean isContain(Collection<?> coll, String fieldName, Object obj) {
		if(isEmpty(coll) || isNull(obj))
			return false;
		for(Object o : coll) {
			if(o == null) 
				continue;
			try {
				Field field = o.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				if(obj.equals(field.get(o))) 
					return true;
			} catch (Exception e) {
				throw new RuntimeException(o.getClass().getName() + " does not have the field: " + fieldName);
			}
		}
		return false;
	}
	
	public static boolean isContain(Object[] objs, Object obj) {
		if (objs == null)
			return false;
		
		for (Object o : objs) {
			if (obj == null) {
				if (o == null)
					return true;
			} else {
				if (obj.equals(o))
					return true;
			}
		}
		return false;
	}
	
}
