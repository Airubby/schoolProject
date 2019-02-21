package com.loncom.ismac.scanning;

import java.io.IOException;
import java.util.List;



public interface PackageScanner {
	public List<Object> getFullyQualifiedClassNameList(Object obj) throws IOException;
	
	
}
