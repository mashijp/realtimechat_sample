package controllers.api.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import models.chat.ChatMessage;
import models.chat.ChatSpread;
import models.chat.ChatSpreadContainer;
import models.chat.protocol.ChatOutbound;
import models.chat.protocol.WebSocketOutbound;

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
		
		final ChatSpread chatSpread = ChatSpreadContainer.getDefault();
		
		return new StreamInbound() {
			
			@Override
			protected void onOpen(final WsOutbound outbound){
				log("Open!");
				ChatOutbound chatOutbound = new WebSocketOutbound(outbound);
				chatSpread.addOutbound(chatOutbound);
			}
			
			@Override
			protected void onTextData(Reader reader) throws IOException {
				log("TextData Received.");
				BufferedReader br = new BufferedReader(reader);
				StringBuffer sb = new StringBuffer();
				String line;
				while((line = br.readLine()) != null){
					ChatMessage chatMessage = new ChatMessage(line);
					chatSpread.spread(chatMessage);
				}
			}
			
			@Override
			protected void onBinaryData(InputStream arg0) throws IOException {
			}
			
			@Override
			protected void onClose(int status) {
				log("WebSocket closed.");
				
			}
		};
	}

}
