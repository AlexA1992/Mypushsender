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
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(Token.token)
        .build()

    val newPost = Message.builder()
        .putData("action", "NEWPOST")
        .putData(
            "content", """{
          "postOwner": "Alex",
          "postContent": "This post is very useful and will enable you to go fast in Android development."
        }""".trimIndent()
        )
        .setToken(Token.token)
        .build()

    val wrongMessage = Message.builder()
        .putData("action", "WRONGMES")
        .putData(
            "content", """{
           "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Vasiliy",
          "postContent": "I have created a post, and think you must look it through"
        }""".trimIndent()
        )
        .setToken(Token.token)
        .build()
    //FirebaseMessaging.getInstance().send(message)
    //FirebaseMessaging.getInstance().send(newPost)
    FirebaseMessaging.getInstance().send(wrongMessage)
}

