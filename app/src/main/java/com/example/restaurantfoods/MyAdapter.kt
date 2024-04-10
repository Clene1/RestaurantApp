package com.example.restaurantfoods


import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class MyAdapter: ListAdapter<User, MyAdapter.CustomAdapter>(UserViewHolder()) {

    class CustomAdapter(view : View): RecyclerView.ViewHolder(view)
    {

    }
    override fun onCreateViewHolder(parent : ViewGroup,viewType:Int):CustomAdapter
    {
        val inflater = LayoutInflater.from(parent.context)
        return CustomAdapter(inflater.inflate(
            R.layout.userlayout,parent,false
        ))
    }

    override fun onBindViewHolder(holder: CustomAdapter,position: Int)
    {
        val user = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.UName).text = user.Name
        holder.itemView.findViewById<TextView>(R.id.UFood).text = user.food
        //Declaring executor to parse the url
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {

            try {
                Log.d("AddednewUser","Image in text ")
                handler.post {
                    Log.d("AddedNewUser","Image Added")
                }
            }
            catch (e:java.lang.Exception)
            {
                Log.d("AddNewUser","Error happened ..."+e.toString())
                e.printStackTrace()
            }
        }
    }
}
class UserViewHolder : DiffUtil.ItemCallback<User>()
{
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.Name == newItem.Name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }
}
