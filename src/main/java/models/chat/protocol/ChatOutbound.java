package models.chat.protocol;

import models.chat.ChatMessage;

public interface ChatOutbound {
	
	public void out(ChatMessage chatMessage);
	
	/**
	 * 連続的な通信かどうかを返す
	 * 
	 * 単純なTCP/IPによるソケット通信や、WebSocketなど接続の切れない通信であればtrue。
	 * Comet通信など、メッセージの送信を終えた際に切断する必要があればfalse。
	 * 
	 * @return 連続的な通信ならtrue。
	 */
	public boolean isContinuous();
}
