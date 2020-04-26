package com.example.springbootwebsocketusernotifications.controller;

import com.example.springbootwebsocketusernotifications.model.Notification;
import com.example.springbootwebsocketusernotifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private NotificationService notificationService;

    /**
     * GET  /  -> show the index page.
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }


    /**
     * GET  /notifications  -> show the notifications page.
     */
    @RequestMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    /**
     * POST  /some-action  -> do an action.
     *
     * After the action is performed will be notified UserA.
     */
    @RequestMapping(value = "/some-action", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> someAction(HttpServletRequest request) {
        //set user to send message to
        String username = "UserB"; /**request.getRemoteUser(); // logged in User**/
        // Do an action here
        // ...

        // Send the notification to "UserA" (by username)
        notificationService.notify(
                new Notification("hello " + username), // notification object
                username                    // username
        );

        // Return an http 200 status code
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
