package demo.com.demolibraries

import android.util.Base64
import android.util.Log

object Utility {
    fun hashFromSHA1(sha1: String) {
        val arr = sha1.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val byteArr = ByteArray(arr.size)

        for (i in arr.indices) {
            byteArr[i] = Integer.decode("0x" + arr[i])!!.toByte()
        }

        Log.e("hash : ", Base64.encodeToString(byteArr, Base64.NO_WRAP))
    }
}
