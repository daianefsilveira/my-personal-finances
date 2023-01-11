package br.com.mypersonalfinances.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mypersonalfinances.adapter.ExtractAdapter
import br.com.mypersonalfinances.databinding.FragmentExtractBinding
import br.com.mypersonalfinances.model.Category
import br.com.mypersonalfinances.model.Transaction

class ExtractFragment : Fragment() {

    private var _binding: FragmentExtractBinding? = null
    private val binding get() = _binding!!

    lateinit var extractAdapter: ExtractAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExtractBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mockTransactionList = listOf<Transaction>(
            Transaction(
                id = 1,
                amount = 56.3,
                description = "compra de um gago",
                date = "10/01/2000",
                savedMoney = false,
                category = Category.LAZER
            )
        )
        setupRecyclerView(mockTransactionList)
    }

    private fun setupRecyclerView(transactionList: List<Transaction>) {

        extractAdapter = ExtractAdapter(transactionList)
        binding.recyclerViewExtract.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewExtract.setHasFixedSize(true)
        binding.recyclerViewExtract.adapter = extractAdapter
    }
}