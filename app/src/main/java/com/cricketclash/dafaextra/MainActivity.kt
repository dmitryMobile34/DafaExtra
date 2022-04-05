package com.cricketclash.dafaextra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cricketclash.dafaextra.model.News
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://newsapi.org/"
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RVAdapter
    lateinit var q: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        spinnerAction()

        categorySpinner.setSelection(0)

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position == 0) {
                    q = ""
                    startNews(q)
                }
                if (position == 1 ) {
                    q = "cricket"
                    startNews(q)
                }
                if (position == 2 ) {
                    q = "football"
                    startNews(q)
                }
                if (position == 3 ) {
                    q = "Motor"
                    startNews(q)
                }

            }

        }

        loginWidget.setOnClickListener {
            Toast.makeText(this, "Work in progress.", Toast.LENGTH_LONG).show()
        }

    }

    fun startNews(q: String) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitServices::class.java)

        api.getNews(q).enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.body() != null) {
                    adapter = RVAdapter(this@MainActivity, response.body()!!.articles)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast
                    .makeText(applicationContext, "Something went wrong. Try to check your internet connection.", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun spinnerAction() {
        val spinner: Spinner = findViewById(R.id.categorySpinner)
        ArrayAdapter
            .createFromResource (this, R.array.spinner_menu, R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
    }


}