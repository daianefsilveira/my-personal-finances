package br.com.mypersonalfinances.presenter.fragments

import android.app.Application
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mypersonalfinances.presenter.adapter.HomeAdapter
import br.com.mypersonalfinances.presenter.HomeCardModel
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.databinding.FragmentHomeBinding
import br.com.mypersonalfinances.presenter.viewmodel.FinancesViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var application: Application

    lateinit var homeAdapter: HomeAdapter

    private val viewModel by lazy {
        ViewModelProvider(this, FinancesViewModel.FinancesViewModelFactory(application))[FinancesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        application = requireActivity().application!!

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateList()
        setupButtons()
        setupObserver()
    }

   /* private fun createList(): List<HomeCardModel> {
        return
    }*/

    private fun setupObserver() {
        viewModel.balance.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
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
