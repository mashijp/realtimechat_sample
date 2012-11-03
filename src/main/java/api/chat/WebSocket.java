package api.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

/**
 * Servlet implementation class WebSocket
 */
@WebServlet("/api/chat/web_socket")
public class WebSocket extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see WebSocketServlet#WebSocketServlet()
     */
    public WebSocket() {
        super();
    }

	@Override
	protected StreamInbound createWebSocketInbound(String protocol, HttpServletRequest request) {
		
		return new StreamInbound() {
			
			@Override
			protected void onOpen(WsOutbound outbound){
				log("Open!");
			}
			
			@Override
			protected void onTextData(Reader reader) throws IOException {
				BufferedReader br = new BufferedReader(reader);
				String line;
				while((line = br.readLine()) != null){
					log("Message: "+line);
				}
			}
			
			@Override
			protected void onBinaryData(InputStream arg0) throws IOException {
			}
		};
	}

}
