package com.jwhh.notekeeper

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteRecyclerViewAdapter(private val context: Context,
            private val notes: List<NoteInfo>):
    RecyclerView.Adapter<NoteRecyclerViewAdapter
    .ViewHolder>() {



    private val layourinflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layourinflater.inflate(R.layout.item_note_list,
        parent, false)
            return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.textCourse?.text = note.course?.title
        holder.textTitle?.text = note.title
        holder.notePosition = position
    }
    override fun getItemCount() = notes.size


  inner class ViewHolder(itemView : View?) :
        RecyclerView.ViewHolder(itemView!!){
            val textCourse = itemView?.findViewById<TextView>(R.id.textCourse)
            val textTitle = itemView?.findViewById<TextView>(R.id.TextTitle)
            var notePosition =  0
        init {
            itemView?.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }
        }



        }


}