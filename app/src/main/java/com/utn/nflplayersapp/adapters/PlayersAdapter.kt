package com.utn.nflplayersapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.nflplayersapp.R
import com.utn.nflplayersapp.models.Player

class PlayersAdapter(
    private var list: List<Player>,
    private var onClick:(Int) -> Unit
) : RecyclerView.Adapter<PlayersAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return PlayerHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val player = list[position]
        holder.bind(player)
        holder.getCard().setOnClickListener {
            onClick(position)
        }

        /*
        holder.setOnClickListener {
            onItemClick(player)
        }
        */
    }

    inner class PlayerHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view : View
        init {
            this.view = v
        }

        fun bind(player: Player){
            val txtName: TextView = view.findViewById(R.id.txtName)
            txtName.text = player.name

            val imgPhoto: ImageView = view.findViewById(R.id.imgPhoto)
            Glide.with(imgPhoto).load(player.photo).centerCrop().circleCrop().into(imgPhoto)

        }

        fun getCard() : CardView{
            return view.findViewById(R.id.cvPlayer)
        }

        /*
        fun setOnClickListener(onClick: () -> Unit){
            view.setOnClickListener {
                onClick()
            }
        }
        */

    }

}