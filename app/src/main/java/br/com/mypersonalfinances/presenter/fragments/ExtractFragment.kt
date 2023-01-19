package br.com.mypersonalfinances.presenter.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mypersonalfinances.databinding.FragmentExtractBinding
import br.com.mypersonalfinances.presenter.HomeCardModel
import br.com.mypersonalfinances.presenter.adapter.ExtractAdapter
import br.com.mypersonalfinances.presenter.viewmodel.FinancesViewModel

class ExtractFragment : Fragment() {

    private var _binding: FragmentExtractBinding? = null
    private val binding get() = _binding!!

    lateinit var application: Application

    lateinit var extractAdapter: ExtractAdapter

    private val viewModel by lazy {
        ViewModelProvider(this, FinancesViewModel.FinancesViewModelFactory(application))[FinancesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        application = requireActivity().application!!

        // Inflate the layout for this fragment
        _binding = FragmentExtractBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.updateList()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.balance.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
    }

//    fun delete(position: Int) {
//        viewModel.remove(position)
//        setupRecyclerView()
//    }

    private fun setupRecyclerView(homeCardList: List<HomeCardModel> = mutableListOf()) {
        extractAdapter = ExtractAdapter(homeCardList)
        binding.recyclerViewExtract.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewExtract.setHasFixedSize(true)
        binding.recyclerViewExtract.adapter = extractAdapter
    }
}