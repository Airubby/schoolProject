package com.loncom.ismac.jdbc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class PropertyReader {
	public PropertyReader() {
	}

	public static Properties getProperties(String propertyFile) {
		try {
			URL url = getPropertiesURL(propertyFile);
			return getProperties(url);
		} catch (Exception ex) {
			return null;
		}
	}

	public static URL getPropertiesURL(String fileName) {
		try {
			URL url = null;
			url = PropertyReader.class.getResource("/" + fileName);
			System.out.println("load config file:"+url.toString());
			String s = url.toString();
			if (s.indexOf("file://") != -1) {
				int indexOf = s.indexOf("file://") + 6;
				String temp = s.substring(0, indexOf);
				url = new URL(temp + "//" + s.substring(indexOf));
			}
			return url;
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Properties getProperties(URL url) {
		try {
			Properties props = new Properties();
			props.load(url.openStream());
			return props;
		} catch (IOException ex) {
			return null;
		}
	}

	public String getWebClassesPath() {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;

	}

	public static void main(String[] args) {
		String filename = getPropertiesURL("db.properties").toString();
		System.out.println(filename);
	}
}
