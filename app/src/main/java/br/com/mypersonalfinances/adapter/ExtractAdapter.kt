package br.com.mypersonalfinances.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.databinding.CardExtractBinding
import br.com.mypersonalfinances.model.Transaction

class ExtractAdapter(var transactionsList: List<Transaction>) : RecyclerView.Adapter<ExtractAdapter.ExtractViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtractViewHolder {
        val binding = CardExtractBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ExtractViewHolder(binding)
    }

    override fun getItemCount() = transactionsList.size

    override fun onBindViewHolder(holder: ExtractViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }

    class ExtractViewHolder(val binding: CardExtractBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) = with(itemView) {
            binding.tvDescription.text = transaction.description
            binding.tvCategory.text = "TROCAR"
            binding.tvDate.text = transaction.date

            if (transaction.savedMoney) {
                binding.tvAmount.text = String.format("R$ %.2f", transaction.amount)
                binding.tvAmount.setTextColor(ContextCompat.getColor(context, R.color.saved_money))
                binding.containerCardExtract.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
            } else {
                binding.tvAmount.text = String.format("R$ -%.2f", transaction.amount)
                binding.tvAmount.setTextColor(ContextCompat.getColor(context, R.color.red))
                binding.containerCardExtract.setCardBackgroundColor(ContextCompat.getColor(context, R.color.spent_money))
            }
        }
    }
}