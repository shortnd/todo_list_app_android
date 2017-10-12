package design.shortnd.todolist

import android.R.layout
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.main_list_item.view.*

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

class ToDoAdapter(context: Context?, resource: Int, objects: MutableList<ToDoItem>?) :
        ArrayAdapter<ToDoItem>(context, resource, objects) {
    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowMain: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(parent!!.context)
            rowMain = layoutInflater.inflate(R.layout.main_list_item, parent, false)
            rowMain.tag = ViewHolder(rowMain.todo_item_name)
        } else {
            rowMain = convertView
        }

        val toDoItem = getItem(position)
        rowMain.todo_item_name.text = toDoItem.name
        if (toDoItem.important) {
            rowMain.todo_item_name.typeface = Typeface.DEFAULT_BOLD
        }
        return rowMain
    }

    private inner class ViewHolder(val todoItemName: TextView)
}