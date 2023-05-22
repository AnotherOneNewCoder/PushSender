package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message

import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Ivan",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageNewPost = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "userId": 1,
          "userName": "Ivan",
          "postId": 2,
          "postContent": "Apply NotificationCompat.InboxStyle to a notification if you want to add multiple short summary lines, such as snippets from incoming emails. This allows you to add multiple pieces of content text that are each truncated to one line, instead of one continuous line of text provided by NotificationCompat.BigTextStyle."
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageError = Message.builder()
        .putData("action", "NEW_POSTTTT")
        .putData("content", """{
          "userId": 1,
          "userName": "Ivan",
          "postId": 2,
          "postContent": "Apply NotificationCompat.InboxStyle to a notification if you want to add multiple short summary lines, such as snippets from incoming emails. This allows you to add multiple pieces of content text that are each truncated to one line, instead of one continuous line of text provided by NotificationCompat.BigTextStyle."
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messageNewPost)
    FirebaseMessaging.getInstance().send(message)
    FirebaseMessaging.getInstance().send(messageError)
}
