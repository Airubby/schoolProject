package com.loncom.ismac.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * @author leijun
 * 
 */
public class DESTool {
	//DES算法要求有一个可信任的随机数源�Դ
	static SecureRandom sr;
	//创建一个密匙工厂，然后用它把DESKeySpec对象转换成
	static SecretKeyFactory keyFactory;
	//Cipher对象实际完成解密操作
	static Cipher cipher;
	static {
		try {
			sr = new SecureRandom();
			keyFactory = SecretKeyFactory.getInstance("DES");
			cipher = Cipher.getInstance("DES");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		//从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		//一个SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(dks);
                //用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
                // 现在，获取数据并解密
		// 正式执行解密操作�
		return cipher.doFinal(src);
	}

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		//从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		//一个SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(dks);
                //用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
                // 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param encode
	 *            密文
	 * @param pwd
	 *            密码
	 * @return
	 */
	public final static String decrypt(String encode, String pwd) {
		try {
			return new String(decrypt(hex2byte(encode.getBytes("UTF-8")), pwd
					.getBytes()),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param mw
	 *            明文
	 * @param pwd
	 *            密码
	 * @return
	 */
	public final static String encrypt(String mw, String pwd) {
		try {
			return byte2hex(encrypt(mw.getBytes(), pwd.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		StringBuffer stmp = new StringBuffer();
		for (int n = 0; n < b.length; n++) {
			stmp.setLength(0);
			stmp.append((java.lang.Integer.toHexString(b[n] & 0XFF)));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	/**
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(byte[] b) {
		String item = null;
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static String MD51(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
            String url="l1231111";
            String miwen=encrypt(url,"loncomip");
            System.out.println(miwen);
            System.out.println(decrypt(miwen,"loncomip"));
//		 System.out.println("go");
//		 String miwen=encrypt("select t.consignid as '单号',row_number()over(order by consignid )as '序号','委托公司' as '委托公司','发货人' as '发货人','收货人' as '收货人',t.chname as '品名','件数' as '件数','包装方式' as '包装方式',t.grossweight as '毛重','订舱号' as '订舱号','船名' as '船名','航次'  as '航次',sndbilldate as '截关期',boxtype as '柜型',boxid as '柜号'  from consigndtl t where t.consignid like ? and t.boxid like ? and operdate between cast(? as datetime) and cast(? as datetime)", "xdewefwefwefw");
//		 System.out.println(miwen);
//                 miwen="98ED33F169A4EC9A03EC03DC0CE0FBBF977421049C6DE04557485286CBC45DA6CABE64378120D80EC639B857BBF34493";
//                 //"48EC5FFF347F67662B405DAD6991FF8C35D92C5D0368B961593AFE953083B9F4"
//		 System.out.println(decrypt(miwen,"xdewefwefwefw"));

		// String x="123456789012345678901234567890123456789";
		// System.out.println(x.substring(0,x.length()-15));
		// System.out.println(x.substring(x.length()-15,x.length()));
//		System.out.println(DESTool.MD5("leijun.top1@163.com"));
	}
}
