package br.com.mypersonalfinances.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.mypersonalfinances.model.HomeCardModel
import br.com.mypersonalfinances.databinding.CardLayoutBinding

class HomeAdapter(var homeCardList: List<HomeCardModel>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = CardLayoutBinding
                .inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun getItemCount() = homeCardList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeCardList[position])
    }

    class HomeViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(homeCardModel: HomeCardModel) = with(itemView) {
            binding.itemTitle.text = homeCardModel.title
            binding.itemAmount.text = homeCardModel.amount
            binding.itemImage.setImageDrawable(homeCardModel.imagem)
            binding.root.setCardBackgroundColor(ContextCompat.getColor(context,homeCardModel.backgroundColor))

        }
    }
}
