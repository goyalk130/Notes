package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name="text") val text:String,@ColumnInfo(name="title" )val title:String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}