package design.shortnd.todolist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        add_new_todo_fab.setOnClickListener {
            startActivity(Intent(this, AddNewTodoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getRealmDatabase()
        mobileAdsInitAndRequestBanner()
    }

    private fun getRealmDatabase() {
        // Gets a default instance of Realm Database
        val realm = Realm.getDefaultInstance()
        // Queries the whole database
        val query = realm.where(ToDoItem::class.java)
        // Gets the results for all items in the database
        val results = query.findAll()
        // Gets the todoListView
        val todoListView = findViewById<ListView>(R.id.todo_list_view)
        // makes an array adapter for the list view
        val adapter = ToDoAdapter(this, android.R.layout.simple_list_item_1, results)
        // Connects the adapter to the list view
        todoListView.adapter = adapter

        // Setting an onItemClickListener for the ListView
        todoListView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedTodo = results[i]
            startActivity(Intent(this, SelectedTodoActivity::class.java)
                    .putExtra("selectedTodoId", selectedTodo.getId()))
        }
    }

    private fun mobileAdsInitAndRequestBanner() {
        // Initialize ads
        MobileAds.initialize(applicationContext, "ca-app-pub-1335542357641525/2271632014")
        // Finds the adView
        val adView = findViewById<AdView>(R.id.main_activity_ad_view)
        // Makes an adRequest with the builder
        val adRequest = AdRequest.Builder()
            // This IS FOR TESTING ONLY
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        // Loads the ad into the adView
        adView.loadAd(adRequest)
    }
}
