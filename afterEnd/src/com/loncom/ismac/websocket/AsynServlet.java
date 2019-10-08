package com.loncom.ismac.websocket;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.loncom.ismac.bean.websocket.WebSoketData;
import com.loncom.ismac.logs.Logs;
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class AsynServlet extends HttpServlet {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	private HttpSession httpSession;
	private long time = 0;
	private long times = 0;
	private int yzcount = 0;
	WebSoketData data = null;
	private Map<String, Object> subption = new HashMap<String, Object>();
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	public static CopyOnWriteArraySet<AsynServlet> webSocketSet = new CopyOnWriteArraySet<AsynServlet>();
	int i = 0;
	Timer timer;
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		this.session = session;
		System.out.println(session.getId());
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		System.out.println(config);
		webSocketSet.add(this); // 加入set中
		addOnlineCount();

		// 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {

		/*JSONObject js = null;
 		if (Common.isNotNull(message)) {
			data = (WebSoketData) JSONObject.toBean(JSONObject.fromObject(message), WebSoketData.class);
			if ("getdata".equals(data.getCmd())) {

				if (data.getData().size() > 0) {
					subption.put("getdata", data.getData());
				}
			} else if ("getaddrstate".equals(data.getCmd())) {
				if (data.getData().size() > 0) {
					subption.put("getaddrstate", data.getData());
				}
			} else if ("getalarm".equals(data.getCmd())) {
				if (data.getData().size() > 0) {
					String str=AppContext.getRoleadd().get(data.getData().get(0));
					subption.put("getalarm",Arrays.asList(str.split(",")));
				}
			} else if ("getheart".equals(data.getCmd())) {
				this.time = Long.parseLong(data.getData().get(0));
			}else if("getconfigure".equals(data.getCmd())){
				if (data.getData().size() > 0) {
					subption.put("getconfigure", data.getData());
				}
			}
		}*/
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("已关闭了一连接");
		System.out.println(session.getId());
		// AppContext.getSID().remove(httpSession.getId());
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		i++;
		synchronized(session) {
		if (this.session.isOpen()) {

			try {
				this.session.getBasicRemote().sendText(message);;
			} catch (Exception e) {
				Logs.log(e);
			}

		}
		}
		// this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		AsynServlet.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		AsynServlet.onlineCount--;
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}

	public int getYzcount() {
		return yzcount;
	}

	public void setYzcount(int yzcount) {
		this.yzcount = yzcount;
	}

	public static CopyOnWriteArraySet<AsynServlet> getWebSocketSet() {
		return webSocketSet;
	}

	public static void setWebSocketSet(CopyOnWriteArraySet<AsynServlet> webSocketSet) {
		AsynServlet.webSocketSet = webSocketSet;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static void setOnlineCount(int onlineCount) {
		AsynServlet.onlineCount = onlineCount;
	}

	public Map<String, Object> getSubption() {
		return subption;
	}

	public void setSubption(Map<String, Object> subption) {
		this.subption = subption;
	}

}