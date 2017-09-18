package design.shortnd.todolist

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import io.realm.Realm

class EditSelectedTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_edit_selected_todo)

        editCurrentTodo()
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
}
