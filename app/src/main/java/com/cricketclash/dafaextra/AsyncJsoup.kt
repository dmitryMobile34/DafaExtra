package com.cricketclash.dafaextra

import android.content.Context
import com.cricketclash.dafaextra.Constants.C1
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class AsyncJsoup (val context: Context) {

    private var jsoup: String? = "null"
    private val hawk : String? = Hawk.get(C1,"null")
    private var forJsoupSet: String = Constants.lru + Constants.odone + hawk

    suspend fun getDocSecretKey(): String?{
        withContext(Dispatchers.IO){
            val doc = Jsoup.connect(forJsoupSet).get()
            jsoup = doc.text().toString()
        }
        return jsoup
    }
}