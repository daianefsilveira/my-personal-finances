package br.com.mypersonalfinances.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mypersonalfinances.adapter.HomeAdapter
import br.com.mypersonalfinances.model.HomeCardModel
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(createList())
        setupButtons()
    }

    private fun createList(): List<HomeCardModel> {
        return listOf(
            HomeCardModel(
                    title = "Entradas",
            amount = "R$ 0,00",
            imagem = ContextCompat.getDrawable(requireContext(), R.drawable.income)!!,
            backgroundColor = R.color.saved_money
        ),
            HomeCardModel(
                title = "Saídas",
                amount = "R$ 0,00",
                imagem = ContextCompat.getDrawable(requireContext(), R.drawable.expense)!!,
                backgroundColor = R.color.spent_money
            ),
            HomeCardModel(
                title = "Total",
                amount = "R$ 0,00",
                imagem = ContextCompat.getDrawable(requireContext(), R.drawable.ic_total)!!,
                backgroundColor = R.color.green
            )
        )
    }

    private fun setupRecyclerView(homeCardList: List<HomeCardModel>) {
        homeAdapter = HomeAdapter(homeCardList)
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun setupButtons() {
        binding.btnNewTransaction.setOnClickListener {
            callTransactions()
        }
    }

    fun callTransactions() {
        findNavController().navigate(R.id.action_homeFragment_to_addTransactionFragment)
    }
}
