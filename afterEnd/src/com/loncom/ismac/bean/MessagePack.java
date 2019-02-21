package com.loncom.ismac.bean;

import java.nio.ByteBuffer;

import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;

import net.sf.json.JSONObject;
@SuppressWarnings({  "unused"})

public class MessagePack {

	 /// <summary>
    /// 开始标志
    /// </summary>
    public long StartFlag;

    /// <summary>
    /// 数据长度
    /// </summary>
    public int DataLen ;

    /// <summary>
    /// 版本号
    /// </summary>
    public int Version;

    /// <summary>
    /// 保留值
    /// </summary>
    public int Extend ;

    /// <summary>
    /// 数据类型
    /// 0x01-实时数据包
    /// 0x02-通讯数据包
    /// 0x03-事件数据包
    /// </summary>
    public int DataType ;

    /// <summary>
    /// 数据个数
    /// </summary>
    public int DataCount;

    /// <summary>
    /// 数据包体为List<DataPack>、List<EventPack>或者List<CommPack>Json序列化后，采用UTF-8编码
    /// </summary>
    public byte[] DataBody ;

    /// <summary>
    /// 校验位,采用CRC16，只校验数据DataType、DataCount、DataLen、DataBody
    /// </summary>
    public short CheckFlag ;

    /// <summary>
    /// 结束标志
    /// </summary>
    public long EndFlag;

	public long getStartFlag() {
		return StartFlag;
	}

	public void setStartFlag(long startFlag) {
		StartFlag = startFlag;
	}

	public int getDataLen() {
		return DataLen;
	}

	public void setDataLen(int dataLen) {
		DataLen = dataLen;
	}

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
	}

	public int getExtend() {
		return Extend;
	}

	public void setExtend(int extend) {
		Extend = extend;
	}

	public int getDataType() {
		return DataType;
	}

	public void setDataType(int dataType) {
		DataType = dataType;
	}

	public int getDataCount() {
		return DataCount;
	}

	public void setDataCount(int dataCount) {
		DataCount = dataCount;
	}

	public byte[] getDataBody() {
		return DataBody;
	}

	public void setDataBody(byte[] dataBody) {
		DataBody = dataBody;
	}

	public short getCheckFlag() {
		return CheckFlag;
	}

	public void setCheckFlag(short checkFlag) {
		CheckFlag = checkFlag;
	}

	public long getEndFlag() {
		return EndFlag;
	}

	public void setEndFlag(long endFlag) {
		EndFlag = endFlag;
	}
    
	
	/**
	 * 数据解码
	 * @param data
	 */
    public void Decoding(byte[] data)
    {
    	try {
    		   this.StartFlag = BaseUtil.getIntByte(data,0);//0

    	        this.DataLen = BaseUtil.getIntByte(data, 4);

    	        this.Version = BaseUtil.getIntByte(data, 8);

    	        this.Extend = BaseUtil.getIntByte(data, 12);

    	        this.DataType = BaseUtil.getIntByte(data, 16);

    	        this.DataCount =BaseUtil.getIntByte(data, 20);

    	        this.CheckFlag = BaseUtil.getShortByte(data, data.length - 6);

    	        this.EndFlag = BaseUtil.getIntByte(data,data.length-4);

    	       this.DataBody=BaseUtil.subBytes(data, 24, data.length - 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    }
    
    /**
     * 输出
     * @return
     */
    public byte[] Out(){
    	ByteBuffer buffer=null;
    	String out="";
    	try {
    		
    		 buffer=ByteBuffer.allocate(this.getDataLen());
    		buffer.put(BaseUtil.intToByteArray((int)this.getStartFlag()));
    		buffer.put(BaseUtil.intToByteArray(this.getDataLen()));
    		buffer.put(BaseUtil.intToByteArray(this.getVersion()));
    		buffer.put(BaseUtil.intToByteArray(this.getExtend()));
    		buffer.put(BaseUtil.intToByteArray(this.getDataType()));
    		buffer.put(BaseUtil.intToByteArray(this.getDataCount()));
    		buffer.put(this.getDataBody());
    		buffer.put(BaseUtil.shortToByte(this.getCheckFlag()));
    		buffer.put(BaseUtil.intToByteArray((int)this.getEndFlag()));
    		//out=BaseUtil.bytesToString(buffer.array()).toString();
    		//StringUtils.join(BaseUtil.intToByteArray((int) this.getEndFlag())," ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return buffer.array();
    }
    
    
    
    public String toString(){
    	
    	return this.StartFlag+" "+this.CheckFlag+" "+this.DataCount+" "+this.DataType+" "+this.getDataBody();
    }
    
    
	
}
