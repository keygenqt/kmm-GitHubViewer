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
package com.keygenqt.viewer.android.data.mock

import com.keygenqt.viewer.android.data.models.UserModel

fun mockUserModel() = UserModel(
    id = "id",
    avatarUrl = "https://get.wallhere.com/photo/women-brunette-face-wind-portrait-hair-in-face-depth-of-field-women-outdoors-sea-cliff-motion-blur-blue-eyes-1379479.jpg",
    name = "Vitaliy Zarubin",
    location = "Moscow",
)
