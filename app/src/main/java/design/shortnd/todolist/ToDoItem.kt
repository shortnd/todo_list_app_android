package design.shortnd.todolist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

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

open class ToDoItem: RealmObject() {
    @PrimaryKey
    private var id = UUID.randomUUID().toString()
    var name = ""
    var important = false

    override fun toString(): String {
        return name
    }

    fun getId(): String {
        return id
    }
}