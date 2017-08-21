package com.littletree.thtplay.util

import android.content.Context


/**
 * Created by frank.zhang on 2017/8/14.
 */

object SharedPreferencesUtil {
    private val spFileName = "welcomePage"
    val FIRST_OPEN = "first_open"

    fun getBoolean(context: Context,
                   strKey: String,
                   strDefault: Boolean): Boolean {//strDefault boolean: Value to return if this preference does not exist.
        val setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE)
        val result = setPreferences.getBoolean(strKey, strDefault)
        return result
    }

    fun putBoolean(context: Context,
                   strKey: String,
                   strData: Boolean?) {
        val activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE)
        val editor = activityPreferences.edit()
        editor.putBoolean(strKey, strData!!)
        editor.commit()
    }
}