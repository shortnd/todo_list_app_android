package design.shortnd.todolist

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
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm

class AddNewTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_todo)
        // Changes the title to "Add New _todo"
        title = "Add New Todo"

        // Finds the EditText, CheckBox and Button
        val addNewTodoEditText =
                findViewById<EditText>(R.id.add_new_todo_edit_text)
        val addNewTodoImportantCheckBox =
                findViewById<CheckBox>(R.id.add_new_todo_important_checkbox)
        val addNewTodoButton =
                findViewById<Button>(R.id.add_new_todo_button)

        // Add a click listener to the {@addNewTodoButton} to add a new
        // todoObject to realm
        addNewTodoButton.setOnClickListener {
            val todo = ToDoItem()
            // Must call .toString() when getting .text from {@addNewTodoEditText}
            todo.name = addNewTodoEditText.text.toString()
            todo.important = addNewTodoImportantCheckBox.isChecked

            // Gets an instance of realm
            val realm = Realm.getDefaultInstance()
            if (todo.name != "") {
                realm.beginTransaction()
                realm.copyToRealm(todo)
                realm.commitTransaction()
                finish()
            } else {
                Toast.makeText(this, "Please enter a new Todo", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}
