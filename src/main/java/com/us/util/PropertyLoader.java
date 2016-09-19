package com.us.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

/*
 * Class that extracts properties from the prop file.
 * 
 * @author Sebastiano Armeli-Battana
 */
public class PropertyLoader {

	private static Properties props = new Properties();

	private static final String PROP_FILE = "/application.properties";

	static {
		try {
			BufferedReader br = null;
			Properties datas = new Properties();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PropertyLoader.class.getResource(PROP_FILE).getFile())), "UTF-8"));
			props.load(br);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private PropertyLoader() {}

	public static String loadProperty(String name) {

		String value = "";

		if (name != null) {
			value = props.getProperty(name);
		}
		return value;
	}
	public static String loadProperty(String name,String defaultValue) {
		String value = "";
		if (name != null) {
			value = props.getProperty(name);
		}
		if (StringUtils.isBlank(value)){
			value = defaultValue;
		}
		return value;
	}

	public static Properties getProperties(){
		return props;
	}

}