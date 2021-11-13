package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter (private val context: Context,private val listner : INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>(){

    val allNotes = ArrayList<Note>()

    inner class NotesViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        val textview = itemView.findViewById<TextView>(R.id.textView)
        val titlee = itemView.findViewById<TextView>(R.id.titleofnote)
//        val deletebutton = itemView.findViewById<ImageButton>(R.id.imageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewholder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false))

//        viewholder.deletebutton.setOnClickListener {
//            listner.onitemclicked(allNotes[viewholder.adapterPosition])
//        }
        viewholder.textview.setOnClickListener{
            listner.getOneNoteItem(allNotes[viewholder.adapterPosition])
        }
        return viewholder
    }



    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes = allNotes[position]
        holder.textview.text = currentNotes.text
        holder.titlee.text = currentNotes.title

//        holder.textview.setOnClickListener{
//            val i =Intent(context,show::class.java)
//            i.putExtra("myNote",holder.textview.text)
//            context.startActivity(i)
//        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

}
interface INotesRVAdapter{
    fun onitemclicked(note:Note)

    fun getOneNoteItem(note: Note)

}