package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM notes_table where id = :id")
    suspend fun delete(id: Int)

    @Query("Select * from notes_table order by id ASC")
    fun getallNotes() :LiveData<List<Note>>

    @Query("Select text from notes_table where id= :id")
    fun getNote(id : Int) : String

    @Query("Select title from notes_table where id= :id")
    fun getNoteTitle(id : Int) : String

    @Query ("Update notes_table SET text = :text, title = :title where id = :id")
    fun updateonenote(text:String ,id :Int,title:String)


}