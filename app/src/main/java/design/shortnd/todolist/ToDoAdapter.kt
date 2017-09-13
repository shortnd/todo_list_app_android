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

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val toDoTextView = inflater.inflate(layout.simple_list_item_1, parent, false) as TextView
        val toDoItem = getItem(position)
        toDoTextView.text = toDoItem.name
        if (toDoItem.important) {
            toDoTextView.typeface = Typeface.DEFAULT_BOLD
        }
        return toDoTextView
    }
}