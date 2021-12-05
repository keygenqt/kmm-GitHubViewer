package com.keygenqt.viewer.android.utils

import com.keygenqt.viewer.android.BuildConfig
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object AppHelper {

    /**
     * Check string is not json
     */
    fun isValidJson(test: String?): Boolean {
        if (test == null) {
            return false
        }
        try {
            JSONObject(test)
        } catch (ex: JSONException) {
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }
        }
        return true
    }

    /**
     * Generate dynamic links
     */
    fun getDynamicLinks(path: String): String {
        return "https://${BuildConfig.dynamicLinksHost}$path"
    }
}