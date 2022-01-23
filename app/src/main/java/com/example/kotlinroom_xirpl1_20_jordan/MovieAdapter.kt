package com.example.kotlinroom_xirpl1_20_jordan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroom_xirpl1_20_jordan.room.Movie
import kotlinx.android.synthetic.main.list_movie.view.*

class MovieAdapter(private val movies: ArrayList<Movie>, private val Listener: OnAdapterListener)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie  = movies[position]
        holder.view.event_title.text = movie.title
        holder.view.event_description.text = movie.desc
        holder.view.event_title.setOnClickListener{
            Listener.onClick( movie )
        }
        holder.view.icon_edit.setOnClickListener {
            Listener.onUpdate(movie)
        }
        holder.view.icon_delete.setOnClickListener {
            Listener.onDelete(movie)
        }
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Movie>){
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(movies: Movie)
        fun onUpdate(movies: Movie)
        fun onDelete(movies: Movie)
    }
}