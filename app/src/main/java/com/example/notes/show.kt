package com.example.notes

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_new_note.*
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.android.synthetic.main.item_layout.*

class show : AppCompatActivity() {
     var currentNoteId : String? = null
    var currentNote : String? = null
    var currentNoteTitle : String=""
    lateinit var viewModel : NoteViewModel
    var i =intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        currentNote = intent.getStringExtra("mynote")
        currentNoteId = intent.getStringExtra("idOfNote")
        currentNoteTitle = intent.getStringExtra("titleOfNote").toString()
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        try{
            textView2.setText(currentNote)
            thisistitleofanote.setText(currentNoteTitle)
        }catch (exception:Exception){
            Toast.makeText(this,"ERROR aa gaya bhaiiiii",Toast.LENGTH_SHORT).show()
        }


    }
    fun onactionclicked(v: View){
        updatingnote()
    }
    fun updatingnote(){
        val title = thisistitleofanote.text.toString()
//        if (currentNoteTitle.isEmpty()){
//            title=" "
//        }else{
//            title=currentNoteTitle.toString()
//        }
        val text =textView2.text.toString()
        text.let {
            currentNoteId?.let { it1 ->
                viewModel.updatenote(it, it1.toInt(),title)
    //                    Toast.makeText(this,"save chala",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_note) {
            currentNoteId?.let {
                viewModel.deleteNote(it.toInt())
//                Toast.makeText(this,"delete chala",Toast.LENGTH_SHORT).show()
                finish()
            }
            return true
        }
        if (item.itemId == R.id.save_note) {
            updatingnote()
            return true
        }
        if (item.itemId == R.id.share) {
            val title = thisistitleofanote.text.toString()
            val text = textView2.text.toString()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, title + "\n" + text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
            return true
        }
        if (item.itemId == R.id.copy) {
            val title = thisistitleofanote.text.toString()
            val text = textView2.text.toString()
            val clipboard: ClipboardManager =

                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", title + "\n" + text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this,"Note Copied",Toast.LENGTH_SHORT).show()
            return true
        }
        else {
            return super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        updatingnote()
        return super.onSupportNavigateUp()
    }


    override fun onBackPressed() {
        updatingnote()
        super.onBackPressed()

    }

}