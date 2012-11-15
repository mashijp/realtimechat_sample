package models.chat.protocol;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

import models.chat.ChatMessage;

public class CometOutbound implements ChatOutbound {

	private AsyncContext asyncContext;
	
	public CometOutbound(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
	}
	
	@Override
	public void out(ChatMessage chatMessage) throws IOException {
		PrintWriter out = asyncContext.getResponse().getWriter();
		out.println(chatMessage.getMessage());
		out.close();
		asyncContext.complete();
	}

	@Override
	public boolean isContinuous() {
		return false;
	}

}
