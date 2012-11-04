package models.chat;

import static org.junit.Assert.*;

import java.io.IOException;

import models.chat.protocol.ChatOutbound;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class ChatSpreadTest {

	@Test
	public void testAddOutbound() {
		ChatSpread cs = new ChatSpread();
		ChatOutbound outbound1 = mock(ChatOutbound.class);
		ChatOutbound outbound2 = mock(ChatOutbound.class);
		
		cs.addOutbound(outbound1);
		assertEquals(1, cs.size());
		
		cs.addOutbound(outbound2);
		assertEquals(2, cs.size());	
	}

	@Test
	public void testSpread() throws IOException {
		ChatSpread cs = new ChatSpread();
		ChatOutbound outbound1 = mock(ChatOutbound.class);
		when(outbound1.isContinuous()).thenReturn(true);
		
		ChatOutbound outbound2 = mock(ChatOutbound.class);
		when(outbound2.isContinuous()).thenReturn(false);
		
		cs.addOutbound(outbound1);
		cs.addOutbound(outbound2);
		
		ChatMessage mockChatMessage1 = new ChatMessage("hogefuga");
		ChatMessage mockChatMessage2 = new ChatMessage("hogefuga");
		cs.spread(mockChatMessage1);
		verify(outbound1).out(mockChatMessage1);
		verify(outbound2).out(mockChatMessage1);
		
		cs.spread(mockChatMessage2);
		verify(outbound1).out(mockChatMessage2);
		verify(outbound2, times(0)).out(mockChatMessage2);
	}

}
