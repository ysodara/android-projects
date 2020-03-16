package com.sodaray.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
class TvShowAdapter (
    private var tvShow:MutableList<TvShow>,
    private val onTvShowClick: (tvShow: TvShow) -> Unit
    ): RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tv_show, parent, false)

        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShow[position])
    }
    fun appendTvShows(tvShow: List<TvShow>) {
        this.tvShow.addAll(tvShow)
        notifyItemRangeInserted(
            this.tvShow.size,
            tvShow.size -1)
    }
    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_tv_show_poster)
        fun bind(tvshow: TvShow) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tvshow.posterPath}") .transform(CenterCrop())
                .into(poster)
            itemView.setOnClickListener { onTvShowClick.invoke(tvshow)
            }
        }
    }

}