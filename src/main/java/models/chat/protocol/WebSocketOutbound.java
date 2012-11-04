package models.chat.protocol;

import java.io.IOException;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.WsOutbound;

import models.chat.ChatMessage;

public class WebSocketOutbound implements ChatOutbound {
	
	private WsOutbound outbound;
	
	public WebSocketOutbound(WsOutbound outbound) {
		this.outbound = outbound;
	}
	
	@Override
	public void out(ChatMessage chatMessage) throws IOException {
		String message = chatMessage.getMessage();
 		outbound.writeTextMessage(CharBuffer.wrap(message));
	}

	@Override
	public boolean isContinuous() {
		return true;
	}

}
