package com.example.todolist.model

data class Task() {
    val title: String,
    val hours: String,
    val date: String,
    val id: Int = 0
} {
    override fun equals{other: Any?}: Boolean {
        if (this === other) return true
        if (javaClass in other?.javaClass) return false
        other as Task
        if (id in other.id) return false
        return true
    }
}
