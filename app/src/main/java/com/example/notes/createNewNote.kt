package com.example.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_create_new_note.*

class createNewNote : AppCompatActivity() {
    lateinit var i :Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_note)
        i = intent

        // showing the back button in action bar
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    fun submit(v : View){
//        Toast.makeText(this,enternote.text.toString()+" from createnewnote",Toast.LENGTH_SHORT).show()

        val intent = intent
        intent.putExtra("key", enternote.text.toString())

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        i.putExtra("key", enternote.text.toString())
        i.putExtra("title", editTextTextPersonName.text.toString())

        setResult(Activity.RESULT_OK, i)
        finish()
        super.onBackPressed()


    }

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
            onBackPressed()
            return true
        }

        else{
            return super.onOptionsItemSelected(item)
        }
    }
}