package br.com.mypersonalfinances.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.mypersonalfinances.databinding.CardExtractBinding
import br.com.mypersonalfinances.presenter.ExtractCardModel

class ExtractAdapter(var extractCardList: List<ExtractCardModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ExtractAdapter.ExtractViewHolder>() {

    interface OnItemClickListener {
        fun delete(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtractViewHolder {
        val binding = CardExtractBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ExtractViewHolder(binding)
    }

    override fun getItemCount() = extractCardList.size

    override fun onBindViewHolder(holder: ExtractViewHolder, position: Int) {

        holder.binding.ivDelete.setOnClickListener {
            listener.delete(position)
        }

        holder.bind(extractCardList[position])
    }

    class ExtractViewHolder(val binding: CardExtractBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(extractCardModel: ExtractCardModel) = with(itemView) {

            binding.tvDescription.text = extractCardModel.description
            binding.tvAmount.text = formatCurrency(extractCardModel.amount)
            binding.tvCategory.text = extractCardModel.category
            binding.tvDate.text = extractCardModel.date

            extractCardModel.backgroundColor?.let {
                binding.root.setCardBackgroundColor(ContextCompat.getColor(context, it))
            }
        }

        private fun formatCurrency(amount: String): String {
            return "R$ $amount"
        }
    }
}



