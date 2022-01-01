/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keygenqt.viewer.android.features.profile.ui.viewModels

import androidx.lifecycle.ViewModel
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.base.queryActions.QueryActions
import com.keygenqt.viewer.android.data.requests.UserUpdateRequest
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.features.profile.ui.screens.settings.SettingsScreen
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.UserModelDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

/**
 * [ViewModel] for [SettingsScreen]
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService
) : ViewModel() {

    /**
     * State actions
     */
    val query1 = QueryActions(this)

    /**
     * Listen user model
     */
    val user = dataService.getUserModel().distinctUntilChanged()

    /**
     * Update user profile
     *
     * @property name the new name of the user
     * @property blog the new blog URL of the user
     * @property twitterUsername the new Twitter username of the user
     * @property company the new company of the user
     * @property location the new location of the user
     * @property bio the new short biography of the user
     */
    fun userUpdate(
        name: String,
        blog: String,
        twitterUsername: String?,
        company: String,
        location: String,
        bio: String,
    ) {
        query1.queryLaunch {
            apiService.userUpdate(
                UserUpdateRequest(
                    name = name,
                    blog = blog,
                    twitter_username = twitterUsername,
                    company = company,
                    location = location,
                    bio = bio,
                )
            ).success {
                dataService.withTransaction<UserModelDataService> {
                    clearUserModel()
                    insertUserModel(it)
                }
            }
        }
    }
}
