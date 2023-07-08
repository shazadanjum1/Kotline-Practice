package com.kentae.kotlinepractice.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kentae.kotlinepractice.models.ItemsModel
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.database.SQLiteDB
import com.kentae.kotlinepractice.activities.AddItemActivity
import com.kentae.kotlinepractice.activities.UpdateNoteActivity
import com.kentae.kotlinepractice.models.NoteEntity
import com.kentae.kotlinepractice.utils.Constants

class NoteAdapter(private val mList: MutableList<NoteEntity>, private val context: Activity) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val item = mList[position];

        holder.name.text = item.noteTitle;
        holder.details.text = item.noteDesc;

        holder.cardView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(context, UpdateNoteActivity::class.java);
                intent.putExtra(Constants.BUNDLE_NOTE_ID, item.noteId);
                intent.putExtra("name", item.noteTitle);
                intent.putExtra("detail", item.noteDesc);
                intent.putExtra("mode", "update");

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                context.finish();
            }
        });

//        holder.deleteBtn.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                val dbHelper = SQLiteDB(context, null);
//                dbHelper.deleteRow(item.id);
//                try {
//                    mList.removeAt(position);
//                    notifyDataSetChanged();
//
//                }catch (e : Exception){
//                    e.stackTrace
//                }
//
//            }
//        });


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val name: TextView = itemView.findViewById(R.id.tvTitle);
        val details: TextView = itemView.findViewById(R.id.tvDesc);
        val cardView: CardView = itemView.findViewById(R.id.cardView);

//        val editBtn: ImageView = itemView.findViewById(R.id.editBtn);
//        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn);


    }
}
