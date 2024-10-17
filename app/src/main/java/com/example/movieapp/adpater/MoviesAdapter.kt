package com.example.movieapp.adpater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.models.MovieResult


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.Holder>() {

    var movieList :ArrayList<MovieResult>?=null
    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.inflate(android.view.LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return if (movieList==null) 0 else movieList!!.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(posts!!.get(position))
        // or
        movieList?.let {
            holder.bind(it.get(position))
        }
    }

inner class Holder(val binding: ItemMovieBinding)
    :RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
//                onItemClickListener?.onItemClick(movieList!!.get(adapterPosition).id!!)
                movieList?.get(layoutPosition)?.id?.let { it1 ->
                    onItemClickListener?.onItemClick(it1)
                }
            }
        }
    fun bind(movieResult: MovieResult) {
        binding.apply {
            movieTitle.text = movieResult.title
            movieRating.text ="⭐  "+ movieResult.vote_average.toString()+" / 10"
            movieDescription.text = movieResult.overview
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500" + movieResult.poster_path)
                .into(moviePoster)

        }
    }
    }

    interface OnItemClickListener {
        fun onItemClick(id:Int)
    }

}