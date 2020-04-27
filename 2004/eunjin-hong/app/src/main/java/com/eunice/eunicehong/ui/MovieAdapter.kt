package com.eunice.eunicehong.ui

import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.Movie

class MovieAdapter(private val presenter: MoviePresenter) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false),
        presenter
    )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(movieList[position])
        }
    }

    private fun addAllMovies(movies: Collection<Movie>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun setMovieList(movies: Collection<Movie>) {
        movieList.clear()
        addAllMovies(movies)
    }

    class ViewHolder(itemView: View, private val presenter: MoviePresenter) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var movie: Movie

        private val card = itemView.findViewById<CardView>(R.id.movie_card).apply {
            setOnClickListener {
                presenter.showDetail(movie.link)
            }
        }
        private val title = itemView.findViewById<TextView>(R.id.movie_title)
        private val poster = itemView.findViewById<ImageView>(R.id.movie_poster)
        private val subtitle = itemView.findViewById<TextView>(R.id.movie_subtitle)
        private val actors = itemView.findViewById<TextView>(R.id.movie_actors)
        private val directors = itemView.findViewById<TextView>(R.id.movie_directors)
        private val pubDate = itemView.findViewById<TextView>(R.id.movie_date_text)
        private val rating = itemView.findViewById<TextView>(R.id.movie_user_rating_value)

        fun bind(item: Movie) {
            movie = item

            title.text = movie.title.parseHTML()

            subtitle.text = movie.subtitle.parseHTML()

            actors.text = movie.actors.formatStaffList().parseHTML()

            directors.text = movie.directors.formatStaffList().parseHTML()

            pubDate.text = movie.pubDate
            rating.text =
                rating.context.getString(R.string.movie_item_user_rating_key, movie.userRating)

            Glide.with(poster.context).load(movie.imageUrl).into(poster)

        }

        private fun String.parseHTML(): Spanned =
            HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)


        private fun String.formatStaffList(): String = this.split("|")
            .map { it.trim() }
            .filter { it != "" }
            .joinToString(", ")
    }

}