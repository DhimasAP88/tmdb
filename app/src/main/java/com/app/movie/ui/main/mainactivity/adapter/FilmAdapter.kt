package com.app.movie.ui.main.mainactivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.BuildConfig
import com.app.movie.R
import com.app.movie.data.model.Film
import com.bumptech.glide.Glide

class FilmAdapter(private var movieList: List<Film>, private val context: Context) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var onClick : OnClick? = null
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_list_film)
    }

    fun setListener(onClick: OnClick) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = movieList[position]
        Glide.with(context).load(BuildConfig.URL_IMAGE.plus(film.backdrop_path))
            .into(holder.image)
        holder.image.setOnClickListener {
            onClick?.onClickFilm(film)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface OnClick{
        fun onClickFilm(film: Film)
    }
}

