package weixin.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {

	protected final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static long code;

	public static synchronized String nextCode() {
		code++;
		String str = sdf.format(new Date());
		long m = Long.parseLong((str)) * 10000;
		m += code;
		return m+"";
	}
}
