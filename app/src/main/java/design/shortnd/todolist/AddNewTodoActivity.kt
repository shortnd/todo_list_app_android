package design.shortnd.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AddNewTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_todo)
        title = "Add New Todo"
    }
}
