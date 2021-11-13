package com.example.notes

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository :NoteRepository
    val allNotes: LiveData<List<Note>>

    init{
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allnotes
    }

    fun deleteNote(id: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(id)
    }

    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO ){
        repository.insert(note)
    }

    fun getnote(context: Context,note: Note) = viewModelScope.launch(Dispatchers.IO) {
        val i  = Intent(context,show::class.java)
        i.putExtra("mynote",repository.getNote(note.id))
        i.putExtra("idOfNote",""+note.id)
        i.putExtra("titleOfNote",""+repository.getNoteTitle(note.id))

        context.startActivity(i)
    }
    fun updatenote(text:String,id:Int,title:String) = viewModelScope.launch(Dispatchers.IO ){
        repository.updatenote(text,id,title)
    }


}
