package jsoft.library;


import net.htmlparser.jericho.*;
import javax.servlet.*;

public class Utilities {

	public Utilities() {
		// TODO Auto-generated constructor stub
	}
	
	public static String encode(String strUNI) {
		return CharacterReference.encode(strUNI);
	}
	
	public static String decode(String strHTML) {
		return CharacterReference.decode(strHTML);
	}
	
	
	//---------------------------------------------
	public static byte getByteParam(ServletRequest request, String name) {
		byte value = -1;
		
		String strValue = request.getParameter(name);
		
		if (strValue!=null && !strValue.equalsIgnoreCase("")) {

			value = Byte.parseByte(strValue);
			
		}
		
		return value;
	}
	
	public static short getShortParam(ServletRequest request, String name) {
		Short value = -1;
		
		String strValue = request.getParameter(name);
		
		if (strValue!=null && !strValue.equalsIgnoreCase("")) {

			value = Short.parseShort(strValue);
			
		}
		
		return value;
	}
	
	public static int getIntParam(ServletRequest request, String name) {
		int value = -1;
		
		String strValue = request.getParameter(name);
		
		if (strValue!=null && !strValue.equalsIgnoreCase("")) {

			value = Integer.parseInt(strValue);
			
		}
		
		return value;
	}
	
	//---------------------------------------------------
	
	public static short convertStringToShort(String value) {
		if (value!=null&&!value.equalsIgnoreCase("")) {
			return Short.parseShort(value);
		}
		return -1;
	}
	
	public static byte convertStringToByte(String value) {
		if (value!=null&&!value.equalsIgnoreCase("")) {
			return Byte.parseByte(value);
		}
		return -1;
	}
	
	

}
