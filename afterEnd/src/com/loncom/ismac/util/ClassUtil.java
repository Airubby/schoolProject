package com.loncom.ismac.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPackagePropertiesMarshaller;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ClassUtil {

	public static List<String> getClassLocation(List<Class> listClass){
		List<String> list=new ArrayList<String>();
		for (Class class1 : listClass) {
			list.add(""+getClassLocation(class1));
		}
		return list;
	}
	public static URL getClassLocation(final Class cls) {
		if (cls == null)
			throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		// java.lang.Class contract does not specify if 'pd' can ever be null;
		// it is not the case for Sun's implementations, but guard against null
		// just in case:
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			// 'cs' can be null depending on the classloader behavior:
			if (cs != null)
				result = cs.getLocation();
			if (result != null) {
				// Convert a code source location into a full class file
				// location
				// for some common cases:
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar")|| result.toExternalForm().endsWith(".zip"))
							result = new URL("jar:".concat(result.toExternalForm())
									.concat("!/").concat(clsAsResource));
						else if (new File(result.getFile()).isDirectory())
							result = new URL(result, clsAsResource);
					} catch (MalformedURLException ignore) {
						ignore.printStackTrace();
					}
				}
			}
		}
		if (result == null) {
			// Try to find 'cls' definition as a resource; this is not
			// document．d to be legal, but Sun's implementations seem to //allow
			// this:
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource)
					: ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}
	
	public static void main(String[] args) {
		String  classLocation =  ""+getClassLocation(ZipPackagePropertiesMarshaller.class);
		System.out.println(classLocation);
		
		List<Class> l=new ArrayList<Class>();
		l.add(ZipPackagePropertiesMarshaller.class);
		l.add(SXSSFWorkbook.class);
		System.out.println(getClassLocation(l));
	}
}
