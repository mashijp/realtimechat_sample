package models.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.chat.protocol.ChatOutbound;

public class ChatSpread {
	
	List<ChatOutbound> outbounds = new ArrayList<>();
	
	public void addOutbound(ChatOutbound outbound) {
		outbounds.add(outbound);
	}
	
	/**
	 * 
	 * @param outbound
	 * @return 削除できたらtrue。できなかったらfalse。
	 */
	public boolean removeOutbound(ChatOutbound outbound) {
		synchronized (outbounds) {
			return outbounds.remove(outbounds);
		}
	}
	
	public void spread(ChatMessage chatMessage){
		synchronized (outbounds) {
			Iterator<ChatOutbound> it = outbounds.iterator();
			while (it.hasNext()) {
				ChatOutbound outbound = it.next();
				outbound.out(chatMessage);
				if (outbound.isContinuous()) {
					it.remove();
				}
			}
		}
	}
	
	
}
