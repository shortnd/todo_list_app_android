package design.shortnd.todolist

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
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
@Suppress("NAME_SHADOWING")
class SelectedTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_todo)
        selectedCurrentTodoId()
    }

    private fun selectedCurrentTodoId() {
        val selectedTodoItemName = findViewById<TextView>(R.id.selected_todo_name)
        val selectedTodoItemImportantCheckbox =
                findViewById<CheckBox>(R.id.selected_todo_completed_checkbox)
        val completeSelectedTodoItemButton =
                findViewById<Button>(R.id.selected_todo_completed_button)

        // Gets the default instance of realm database
        val realm = Realm.getDefaultInstance()
        realm.use { realm ->
            // Query the database with the id from the intent and
            // saves it as a variable to call its methods for the selected
            // todoItem
            val selectedTodoItem = realm.where(ToDoItem::class.java)
                    .equalTo("id", getSelectedTodoItemId())
                    .findFirst()
            if (selectedTodoItem != null) selectedTodoItemName.text = selectedTodoItem.name
            if (selectedTodoItem?.important!!) {
                selectedTodoItemName.typeface = Typeface.DEFAULT_BOLD
                selectedTodoItemImportantCheckbox.isChecked = selectedTodoItem.important
            }
            // Click listener on the Completed Button to delete the current TodoItem
            // from the realm database
            completeSelectedTodoItemButton.setOnClickListener {
                realm.beginTransaction()
                selectedTodoItem.deleteFromRealm()
                realm.commitTransaction()
                finish()
            }
        }
    }

    // Gets the current Id of the selected TodoItem and returns it
    private fun getSelectedTodoItemId(): String {
        return intent.getStringExtra("selectedTodoId")
    }
}
