package com.example.friendsinfo.repository

import com.example.friendsinfo.api.ImageService
import javax.inject.Inject

class ImageRepository
@Inject
constructor(private val api: ImageService) {

    suspend fun getAllImages() = api.getFriendsInfo()
}