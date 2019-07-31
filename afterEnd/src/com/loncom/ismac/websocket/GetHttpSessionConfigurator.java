package com.loncom.ismac.websocket;
/** * @author  作者 E-mail: youtao
* @date 创建时间：2017年8月4日 下午5:04:09 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
 
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator
{
    @Override
    public void modifyHandshake(ServerEndpointConfig config,
                                HandshakeRequest request,
                                HandshakeResponse response)
    {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        if(httpSession!=null){
        config.getUserProperties().put(HttpSession.class.getName(),httpSession);
        }
    }
}
