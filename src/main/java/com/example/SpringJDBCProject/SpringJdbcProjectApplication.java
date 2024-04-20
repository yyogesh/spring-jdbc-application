package com.example.SpringJDBCProject;

import com.example.SpringJDBCProject.model.Notification;
import com.example.SpringJDBCProject.service.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(SpringJdbcProjectApplication.class, args);

		Notification notification = context.getBean(Notification.class);
		notification.setId(5);
		notification.setType("Error");
		notification.setMessage("Error Message");

		System.out.println(notification.getMessage());

		// Get notification service object
		NotificationService notificationService = context.getBean(NotificationService.class);
		notificationService.saveNotification(notification);

		List<Notification> notifications = notificationService.getNotifications();
		notifications.forEach(obj -> System.out.println("Id:: " + obj.getId() + ", Type:: " + obj.getType() + ", Message:: " + obj.getMessage() ));
	}

}
