package com.wei.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClasspathPropertiesUtil {

	private static Properties properties;
	private static ClasspathPropertiesUtil self;
	private ClasspathPropertiesUtil() throws Exception {
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("can not get server port");
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static synchronized ClasspathPropertiesUtil getInstance() {
		if(self == null) {
			try {
				self = new ClasspathPropertiesUtil();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return self;
	}
	
	public <T> T get(String key, Class<T> clazzType) {
		Object object = properties.get(key);
		return to(object, clazzType);
	}
	
	
	private static <T> T to(Object value1, Class<T> clazzType) {
		if (clazzType.isAssignableFrom(String.class)) {
			return (T) value1.toString();
		} else if (clazzType.isAssignableFrom(Long.class)) {
			return (T) new Long(value1.toString());
		} else if (clazzType.isAssignableFrom(Byte.class)) {
			return (T) new Byte(value1.toString());
		} else if (clazzType.isAssignableFrom(Integer.class)) {
			return (T) new Integer(value1.toString());
		} else if (clazzType.isAssignableFrom(Float.class)) {
			return (T) new Float(value1.toString());
		} else if (clazzType.isAssignableFrom(Float.class)) {
			return (T) new Double(value1.toString());
		} else if (clazzType.isAssignableFrom(Boolean.class)) {
			return (T) new Boolean(value1.toString());
		} else {
			return (T) value1.toString();
		}
	}
}
