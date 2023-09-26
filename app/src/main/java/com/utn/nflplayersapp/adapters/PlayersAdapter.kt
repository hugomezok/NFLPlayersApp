package com.utn.nflplayersapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.models.Player

class PlayersAdapter(
    private val list: List<Player>,
    private val onItemClick:(Player) -> Unit
) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = list[position]
        holder.bind(player)
        holder.setOnClickListener {
            onItemClick(player)
        }
    }



    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){
        fun bind(player: Player){
            val txtName: TextView = v.findViewById(R.id.txtName)
            txtName.text = player.name

            val imgPhoto: ImageView = v.findViewById(R.id.imgPhoto)
            Glide.with(imgPhoto).load(player.photo).centerCrop().circleCrop().into(imgPhoto)

           }

        fun setOnClickListener(onClick: () -> Unit){
            v.setOnClickListener {
                onClick()
            }

        }

    }

}