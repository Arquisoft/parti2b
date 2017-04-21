package uo.asw.dashboard.pojos;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.dashboard.pojos.Notification;
import es.uniovi.asw.dbmanagement.types.NotificationType;

public class NotificationTest {

	@Test
	public void notificationTest() {
		Notification notification1 = new Notification();
		Notification notification2 = new Notification(NotificationType.CREATION, (long) 3);
		
		notification1.setType(NotificationType.VOTING);
		assertEquals(NotificationType.VOTING, notification1.getType());
		assertEquals(NotificationType.CREATION, notification2.getType());
		
		notification1.setSuggestionId((long) 5);
		assertEquals(5, (long)notification1.getSuggestionId());
		assertEquals(3, (long)notification2.getSuggestionId());
		
	}
	
}
