package com.keygenqt.viewer.android.data.requests

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * @Body request update user data
 *
 * @property name The new name of the user.
 * @property blog The new blog URL of the user.
 * @property twitter_username The new Twitter username of the user.
 * @property company The new company of the user.
 * @property location The new location of the user.
 * @property bio The new short biography of the user.
 */
@Immutable
@Serializable
data class UserUpdateRequest(
    val name: String,
    val blog: String,
    val twitter_username: String?,
    val company: String,
    val location: String,
    val bio: String,
)
