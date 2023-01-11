package br.com.mypersonalfinances.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.adapter.HomeAdapter
import br.com.mypersonalfinances.databinding.FragmentAddTransactionBinding
import br.com.mypersonalfinances.model.Category
import br.com.mypersonalfinances.model.Transaction

class AddTransactionFragment : Fragment() {

    private var savedMoney: Boolean = false
    private lateinit var transactionAdapter: HomeAdapter

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)

        val categories = resources.getStringArray(R.array.categories_array)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, categories)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupRadioButton()
    }

    private fun setupButton() {
        binding.btnSave.setOnClickListener {
            if (editTextsAreEmpty()) {
                Toast.makeText(context, "Preencha os campos corretamente!", Toast.LENGTH_LONG)
                    .show()
            } else {
                val transactionInfo = setTransactionInformation()
                findNavController().popBackStack()
            }

        }
    }

    private fun editTextsAreEmpty(): Boolean {
        return binding.etDescription.editText?.text?.isEmpty() == binding.etAmount.editText?.text?.isEmpty() == binding.etCategory.editText?.text?.isEmpty()
    }

    private fun setupSpinner() {

    }

    private fun setupRadioButton() {
        binding.rbSavedMoney.setOnClickListener {
            savedMoney = true
        }
        binding.rbSpentMoney.setOnClickListener {
            savedMoney = false
        }
    }

    private fun setTransactionInformation(): Transaction {
        return Transaction(
            null,
            binding.etAmount.editText!!.text.toString().toDouble(),
            binding.etDescription.editText!!.text.toString(),
            binding.etDate.editText.toString(),
            Category.LAZER,
            savedMoney
        )
    }
}
