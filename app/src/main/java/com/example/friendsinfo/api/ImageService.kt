package com.example.friendsinfo.api

import com.example.friendsinfo.model.FriendData
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {
    @GET("/api?results=20")
    suspend fun getFriendsInfo(): Response<FriendData>
}