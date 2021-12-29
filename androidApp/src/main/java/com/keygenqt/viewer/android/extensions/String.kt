package com.keygenqt.viewer.android.extensions

import com.keygenqt.viewer.android.data.models.RepoVisibility
import java.util.*

/**
 * Returns a copy of this string having its first letter titlecased using the rules of the default
 * locale, or the original string if it's empty or already starts with a title case letter.
 */
fun String.capitalize(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

/**
 * Get RepoVisibility from string
 */
fun String?.toRepoVisibility(): RepoVisibility {
    return this?.let { RepoVisibility.valueOf(this.uppercase()) } ?: RepoVisibility.PUBLIC
}