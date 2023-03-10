package br.com.mypersonalfinances.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.mypersonalfinances.presenter.HomeCardModel
import br.com.mypersonalfinances.databinding.HomeCardLayoutBinding

class HomeAdapter(var homeCardList: List<HomeCardModel>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeCardLayoutBinding
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

    class HomeViewHolder(private val binding: HomeCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(homeCardModel: HomeCardModel) = with(itemView) {

            binding.itemTitle.text = homeCardModel.title
            binding.itemAmount.text = formatCurrency(homeCardModel.amount)
            binding.itemImage.setImageDrawable(
                ContextCompat.getDrawable(context, homeCardModel.imagem))

            homeCardModel.backgroundColor?.let {
                binding.root.setCardBackgroundColor(ContextCompat.getColor(context, it))
            }
        }

        private fun formatCurrency(amount: String): String {
            return "R$ $amount"
        }
    }
}
