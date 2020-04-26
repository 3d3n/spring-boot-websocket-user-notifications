package com.example.springbootwebsocketusernotifications.service;

import com.example.springbootwebsocketusernotifications.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Service class for sending notification messages.
 */
@Configuration
public class NotificationService {
    // The SimpMessagingTemplate is used to send Stomp over WebSocket messages.
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * Send notification to users subscribed on channel "/user/queue/notify".
     * <p>
     * The message will be sent only to the user with the given username.
     *
     * @param notification The notification message.
     * @param username     The username for the user to send notification.
     */

    public void notify(Notification notification, String username) {
        template.convertAndSendToUser(username, "/queue/notify", notification);
    }
}
