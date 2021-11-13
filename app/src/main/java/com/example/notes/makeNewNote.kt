package com.example.notes


import android.R.attr
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_new_note.*
import kotlinx.android.synthetic.main.activity_show.*
import android.R.attr.label

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context


class makeNewNote : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_new_note)
    }
    fun submit(v : View){
//        Toast.makeText(this,enternote.text.toString()+" from createnewnote",Toast.LENGTH_SHORT).show()

        val intent = intent


        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun activityback(){
        val i = intent
        intent.putExtra("key", enternote.text.toString())
        intent.putExtra("title", editTextTextPersonName.text.toString())

        setResult(Activity.RESULT_OK, i)
        finish()

    }
    override fun onBackPressed() {
        activityback()
        super.onBackPressed()


    }

//    override fun onSupportNavigateUp(): Boolean {
//        val i = intent
//        intent.putExtra("key", enternote.text.toString())
//        intent.putExtra("title", editTextTextPersonName.text.toString())
//        Toast.makeText(this,"intent chala",Toast.LENGTH_SHORT).show()
//        Toast.makeText(this,editTextTextPersonName.text.toString(),Toast.LENGTH_SHORT).show()
//
//        setResult(Activity.RESULT_OK, i)
//        Toast.makeText(this,"set result chala",Toast.LENGTH_SHORT).show()
//        finish()
//        return super.onSupportNavigateUp()
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_note){

            finish()

            return true
        }
        if (item.itemId == R.id.save_note){
            activityback()
            return true
        }
        if (item.itemId == android.R.id.home){
            activityback()
            return true
        }
        if(item.itemId== R.id.share){
            val title = editTextTextPersonName.text.toString()
            val text =enternote.text.toString()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, title+"\n"+text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
            return true
        }
        if(item.itemId==R.id.copy){
            val title = editTextTextPersonName.text.toString()
            val text =enternote.text.toString()
            val clipboard: ClipboardManager =

                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", title+"\n"+text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this,"Note Copied",Toast.LENGTH_SHORT).show()

            return true
        }

        activityback()
        return super.onOptionsItemSelected(item)

    }
}