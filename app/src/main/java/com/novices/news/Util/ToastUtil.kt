package com.novices.news.Util

import android.widget.Toast
import com.novices.news.MyApplication

fun String.showToast(){
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}