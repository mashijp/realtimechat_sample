package controllers.api.chat;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.chat.ChatSpread;
import models.chat.ChatSpreadContainer;
import models.chat.protocol.ChatOutbound;
import models.chat.protocol.CometOutbound;

/**
 * Servlet implementation class Comet
 */
@WebServlet(urlPatterns = "/api/chat/comet", asyncSupported = true)
public class Comet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final ChatSpread chatSpread = ChatSpreadContainer.getDefault();
		
		AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(10 * 1000);
		
		ChatOutbound outbound = new CometOutbound(asyncContext);
		
		chatSpread.addOutbound(outbound);
	}


}
