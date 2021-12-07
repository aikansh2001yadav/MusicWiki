package com.example.musicwiki.ui.genre

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.model.genreItems.Tag
import com.example.musicwiki.databinding.GenreItemBinding
import com.example.musicwiki.ui.genreInfo.GenreInfoActivity

class GenreAdapter(
    private val context:Context,
    private val genreList: List<Tag>
) : RecyclerView.Adapter<GenreAdapter.IntroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.genre_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.genreItemBinding.textGenre.text = genreList[position].name
        holder.genreItemBinding.root.setOnClickListener {
            val intent = Intent(context, GenreInfoActivity::class.java)
            intent.putExtra("GENRE_NAME", genreList[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    inner class IntroViewHolder(val genreItemBinding: GenreItemBinding) :
        RecyclerView.ViewHolder(genreItemBinding.root)
}