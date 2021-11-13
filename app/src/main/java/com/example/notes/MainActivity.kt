package com.example.notes

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.app.Activity

import android.R.attr.data
import android.R.attr.navigationBarColor
import android.os.Build
import kotlinx.android.synthetic.main.activity_create_new_note.*
import java.lang.Exception


class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel : NoteViewModel
    val RequestCODE = 1 //for creating new note
    val forupdatingCODE = 2 //for creating new note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        recyclerView.layoutManager = GridLayoutManager(this,2)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {

                adapter.updateList(it)
            }

        })

    }

    override fun onitemclicked(note: Note) {

    }

    override fun getOneNoteItem(note: Note)  {
        viewModel.getnote(this,note)
    }


    fun callingIntent(s:String){
        val i  = Intent(this,show::class.java)
        i.putExtra("mynote",s)
        startActivity(i)
    }
//    suspend fun gettingdata(note: Note):String{
//        Toast.makeText(this,"gettingdata chal to gaya",Toast.LENGTH_SHORT).show()
//        return withContext(Dispatchers.IO){
//
//            viewModel.getnote(note)
//        }
//
//    }

    fun SubmitData(s:String?,title:String?){
//        Toast.makeText(this,"call to ho ra h",Toast.LENGTH_SHORT).show()
            var notetext :String
            var t :String
        if (title==null){
            t=""
        }else{
            t=title.toString()
        }
        if (s==null){
            notetext=""
        }else{
            notetext=s.toString()
        }
            if(notetext!="" || t!=""){
                viewModel.insertNote(Note(notetext,t))

                Toast.makeText(this,"New Note is Inserted",Toast.LENGTH_SHORT).show()

        }


    }
    fun onFabClick(v : View){
        val o = Intent(this,makeNewNote::class.java)
        startActivityForResult(o,RequestCODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Toast.makeText(this,"onActivityResult",Toast.LENGTH_SHORT).show()



        try {
//            Toast.makeText(this,"try blobk of onActivityResult",Toast.LENGTH_SHORT).show()
            if (requestCode == RequestCODE || resultCode == Activity.RESULT_OK) {
                val requiredValue: String? = data?.getStringExtra("key")
                val requiredTitleValue: String? = data?.getStringExtra("title")
//                Toast.makeText(this,requiredValue+" after try block",Toast.LENGTH_SHORT).show()
//                Toast.makeText(this,requiredTitleValue+" after block",Toast.LENGTH_SHORT).show()
                if (requiredValue != null || requiredTitleValue!=null) {
//                    Toast.makeText(this,"requiredValue is not null",Toast.LENGTH_SHORT).show()

                    SubmitData(requiredValue,requiredTitleValue)
                }
            }

//            else{
//                Toast.makeText(this,"ye chal ra h",Toast.LENGTH_SHORT).show()
//            }
        } catch (ex: Exception) {
            Toast.makeText(
                this, ex.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}