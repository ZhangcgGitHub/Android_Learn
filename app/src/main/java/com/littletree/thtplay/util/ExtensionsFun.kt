package com.littletree.thtplay.util

import android.app.Activity
import android.app.Fragment
import android.view.View

/**
 * Created by frank.zhang on 2017/8/14.
 */

inline fun <reified T : View> View.find(id: Int): T = findViewById(id) as T

inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id) as T

inline fun <reified T : View> Fragment.find(id: Int): T = view?.findViewById(id) as T

