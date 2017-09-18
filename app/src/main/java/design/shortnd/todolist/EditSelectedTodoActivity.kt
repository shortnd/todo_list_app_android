package design.shortnd.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import io.realm.Realm

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

class EditSelectedTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_selected_todo)
        editCurrentTodo()
        mobileAdsInitAndRequestBanner()
    }

    private fun editSelectedTodoItemId(): String {
        return intent.getStringExtra("id")
    }

    private fun editCurrentTodo() {
        val editSelectedTodoName = findViewById<TextView>(R.id.edit_todo_edit_text)
        val editSelectedTodoCheckbox = findViewById<CheckBox>(R.id.edit_todo_important_checkbox)
        val finishEditButton = findViewById<Button>(R.id.edit_todo_button)

        val realm = Realm.getDefaultInstance()
        realm.use {
            val selectedTodoItem = realm.where(ToDoItem::class.java)
                    .equalTo("id", editSelectedTodoItemId())
                    .findFirst()

            editSelectedTodoName.text = selectedTodoItem?.name
            editSelectedTodoCheckbox.isChecked = selectedTodoItem?.important!!

            finishEditButton.setOnClickListener {
                realm.executeTransaction {
                    val editedTodo = realm.where(ToDoItem::class.java)
                            .equalTo("id", editSelectedTodoItemId())
                            .findFirst()
                    editedTodo?.name = editSelectedTodoName.text.toString()
                    editedTodo?.important = editSelectedTodoCheckbox.isChecked
                    realm.copyToRealmOrUpdate(editedTodo)
                    finish()
                }
            }
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
