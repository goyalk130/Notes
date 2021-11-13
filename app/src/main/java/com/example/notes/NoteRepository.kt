package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val notedao :NoteDao) {

    val allnotes :LiveData<List<Note>> = notedao.getallNotes()

    suspend fun insert(note:Note){
        notedao.insert(note)
    }

    suspend fun delete(id:Int){
        notedao.delete(id)
    }

    fun getNote(note: Int):String{
        return notedao.getNote(note)
    }
    fun getNoteTitle(note: Int):String{
        return notedao.getNoteTitle(note)
    }

    fun updatenote(text:String , id:Int,title:String){
        notedao.updateonenote(text,id,title)
    }
}