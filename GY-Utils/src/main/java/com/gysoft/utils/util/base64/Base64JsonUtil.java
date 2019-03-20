package com.gysoft.utils.util.base64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: Base64相关操作
 * @author DJZ-PJJ
 * @date 2018年3月30日 下午5:28:18
 */
public class Base64JsonUtil {

	/**
	 * 
	 * @Description: 将对象转换为json进行base64编码
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 下午5:49:08
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String parse2Json(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		// 转换成json
		String json = JSONArray.fromObject(obj).toString();
		String jsonBase64 = Base64Util.encode(json.getBytes());
		return jsonBase64;
	}

	/**
	 * 
	 * @Description: 将base64编码的json转换为对象
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 下午5:49:30
	 * @param str
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parse2Object(String str, T t) throws Exception {
		String json = new String(Base64Util.decode(str));
		JSONArray array = JSONArray.fromObject(json);// 先读取串数组
		Object[] o = array.toArray(); // 转成对像数组
		JSONObject obj = JSONObject.fromObject(o[0]);// 再使用JsonObject遍历一个个的对像
		t = (T) JSONObject.toBean(obj, t.getClass());// 指定转换的类型，但仍需要强制转化-成功
		return t;
	}

}
