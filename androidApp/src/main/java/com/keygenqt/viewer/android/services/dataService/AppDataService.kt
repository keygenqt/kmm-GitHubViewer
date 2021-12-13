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
package com.keygenqt.viewer.android.services.dataService

import com.keygenqt.viewer.android.data.AppDatabase
import com.keygenqt.viewer.android.data.AppSecurityDatabase
import com.keygenqt.viewer.android.services.dataService.impl.FollowerModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.RepoModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.SecurityModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.UserModelDataService

/**
 * Base service module for work with db room
 *
 * @author Vitaliy Zarubin
 */
class AppDataService(
    override val db: AppDatabase,
    override val dbSecurity: AppSecurityDatabase,
) : SecurityModelDataService,
    UserModelDataService,
    RepoModelDataService,
    FollowerModelDataService {

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        super<SecurityModelDataService>.clearCacheAfterLogout()
        super<UserModelDataService>.clearCacheAfterLogout()
        super<RepoModelDataService>.clearCacheAfterLogout()
        super<FollowerModelDataService>.clearCacheAfterLogout()
    }
}
