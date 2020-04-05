package com.eunice.eunicehong.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.remote.Movie

class MovieAdapter(private val items: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.get(position).let { movie ->
            with(holder) {
                title.text = HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_LEGACY)

                subtitle.text = HtmlCompat.fromHtml(
                    movie.subtitle,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                actors.text = movie.actors
                    .split("|")
                    .map { it.trim() }
                    .filter { it != "" }
                    .joinToString(", ")

                directors.text = movie.directors
                    .split("|")
                    .map { it.trim() }
                    .filter { it != "" }
                    .joinToString(", ")

                pubDate.text = movie.pubDate
                rating.text = movie.userRating

                Glide.with(poster.context).load(movie.imageUrl).into(poster)
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.movie_title)
        val poster = itemView.findViewById<ImageView>(R.id.movie_poster)
        val subtitle = itemView.findViewById<TextView>(R.id.movie_subtitle)
        val actors = itemView.findViewById<TextView>(R.id.movie_actors)
        val directors = itemView.findViewById<TextView>(R.id.movie_directors)
        val pubDate = itemView.findViewById<TextView>(R.id.movie_date_text)
        val rating = itemView.findViewById<TextView>(R.id.movie_user_rating_value)
    }

}