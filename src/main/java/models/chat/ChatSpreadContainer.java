package models.chat;

public class ChatSpreadContainer {
	private static ChatSpread defaultSpread = new ChatSpread();
	
	public static ChatSpread getDefault() {
		return defaultSpread;
	}
}
