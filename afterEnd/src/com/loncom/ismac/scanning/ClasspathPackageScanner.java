package com.loncom.ismac.scanning;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loncom.ismac.util.BaseUtil;

/**
 * 扫描实体类
 * @author youtao
 *
 */
@SuppressWarnings({"resource"})

public class ClasspathPackageScanner implements PackageScanner{
    private Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);
    private String basePackage;
    private ClassLoader cl;
  //  private List<PackageClass> list;

    /**
     * 初始化
     * @param basePackage
     */
    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }
    public ClasspathPackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }
    public ClasspathPackageScanner(){};
    /**
     *获取指定包下的所有字节码文件的全类名
     */
    public List<Object> getFullyQualifiedClassNameList(Object obj) throws IOException {
        logger.info("开始扫描包{}下的所有类", basePackage);
        return doScan(basePackage, new ArrayList<Object>(),obj);
    }

    /**
     *doScan函数
     * @param basePackage
     * @param nameList
     * @return
     * @throws IOException
     */
    private List<Object> doScan(String basePackage, List<Object> nameList,Object obj) throws IOException {
        String splashPath = StringUtil.dotToSplash(basePackage);
        URL url = cl.getResource(splashPath);   //file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
        String filePath = StringUtil.getRootPath(url);
        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
            if (logger.isDebugEnabled()) {
                //System.out.println("{} 是一个JAR包");
            }
            names = readFromJarFile(filePath, splashPath);
        } else {
            if (logger.isDebugEnabled()) {
                //System.out.println("{} 是一个目录");
            }
            names = readFromDirectory(filePath);
        }
        if(names!=null){
        for (String name : names) {
            if (isClassFile(name)) {
             toFullyQualifiedName(name, basePackage,nameList,obj);
            } else {
                doScan(basePackage + "." + name, nameList,obj);
            }
        }
        if (logger.isDebugEnabled()) {
          /*  for (PackageClass n : nameList) {
             //   System.out.println(n.getClassurl());
            }*/
        }
        return nameList;
        }
        return null;
    }

    private void toFullyQualifiedName(String shortName, String basePackage,List<Object> nameList,Object obj) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtil.trimExtension(shortName));
        if(obj instanceof PackageClass){
           BaseUtil.setAllComponentsName(sb.toString(),nameList);
        }else if(obj instanceof PackageClassTable){
        	 BaseUtil.setAllTableName(sb.toString(),nameList);
        }
    	  

    	   
       
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        if (logger.isDebugEnabled()) {
            System.out.println("从JAR包中读取类: {}");
        }
		JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();
        List<String> nameList = new ArrayList<String>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }

    private List<String> readFromDirectory(String path) throws UnsupportedEncodingException {
    	
        File file = new File(java.net.URLDecoder.decode(path,"UTF-8"));
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    /**
     * For test purpose.
     */
	public static void main(String[] args) throws Exception {
        PackageScanner scan = new ClasspathPackageScanner("com.loncom.samc.bean");
       List<Object> list=scan.getFullyQualifiedClassNameList(new PackageClassTable());
      for (Object object : list) {
		System.out.println(object);
	}
    }
	
}