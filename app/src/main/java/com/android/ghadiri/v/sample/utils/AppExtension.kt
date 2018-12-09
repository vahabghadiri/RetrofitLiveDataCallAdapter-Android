package com.android.ghadiri.v.sample.utils

import android.util.Log

/**
 * @author vahabghadiri
 * @since 8/12/18
 */

inline fun <reified T : Any?> T.log(): T? {
    if (T::class is Iterable<*>) {
        (this as Iterable<*>).forEachIndexed { index, any ->
            Log.d("taggg", "index:$index  , $any")
        }
    } else {
        Log.d("taggg", this.toString())
    }
    return this
}