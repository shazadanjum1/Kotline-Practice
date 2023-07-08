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

class ItemAdapter(private val mList: ArrayList<ItemsModel>, private val context: Activity) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val item = mList[position];

        holder.name.text = item.name;
        holder.details.text = item.details;

        holder.editBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(context, AddItemActivity::class.java);
                intent.putExtra("id", item.id);
                intent.putExtra("name", item.name);
                intent.putExtra("detail", item.details);
                intent.putExtra("mode", "update");

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                context.finish();
            }
        });

        holder.deleteBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val dbHelper = SQLiteDB(context, null);
                dbHelper.deleteRow(item.id);
                try {
                    mList.removeAt(position);
                    notifyDataSetChanged();

                }catch (e : Exception){
                    e.stackTrace
                }

            }
        });


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val name: TextView = itemView.findViewById(R.id.nameTV);
        val details: TextView = itemView.findViewById(R.id.detailsTV);
        val cardview: CardView = itemView.findViewById(R.id.cardview);

        val editBtn: ImageView = itemView.findViewById(R.id.editBtn);
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn);


    }
}
