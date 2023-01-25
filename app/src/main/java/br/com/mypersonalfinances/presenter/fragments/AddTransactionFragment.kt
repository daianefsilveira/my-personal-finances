package br.com.mypersonalfinances.presenter.fragments


import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.mypersonalfinances.R
import br.com.mypersonalfinances.databinding.FragmentAddTransactionBinding
import br.com.mypersonalfinances.data.local.Category
import br.com.mypersonalfinances.data.local.Transaction
import br.com.mypersonalfinances.data.local.TransactionType
import br.com.mypersonalfinances.presenter.viewmodel.FinancesViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.*


class AddTransactionFragment : Fragment() {

    private var savedMoney: Boolean = false

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel : FinancesViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        setupDate()
    }

    private fun setupButton() {
        binding.btnSave.setOnClickListener {
            val transaction = setTransactionInformation()
            if (editTextsAreEmpty()) {
                Toast.makeText(context, "Preencha os campos corretamente!", Toast.LENGTH_LONG)
                    .show()
            } else {
                viewModel.add(transaction)
                findNavController().popBackStack()
            }
        }
    }

    private fun editTextsAreEmpty(): Boolean {
        return binding.etDescription.editText?.text?.isEmpty() == binding.etAmount.editText?.text?.isEmpty() == binding.etCategory.editText?.text?.isEmpty()
    }

    private fun setupDate() {
        binding.apply {
            dateInput.setOnClickListener {
                // create new instance of DatePickerFragment
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager
                // implement setFragmentResultListener
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        binding.dateInput.setText(date)
                    }
                }
                // show
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }
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
            binding.etAmount.editText?.text.toString().toDouble(),
            binding.etDescription.editText?.text.toString(),
            binding.etDate.editText?.text.toString(),
            Category.LAZER,
            convertRadioButtonType(savedMoney)
        )
    }

    private fun convertRadioButtonType(savedMoney: Boolean): TransactionType {
        return if (savedMoney) {
            TransactionType.INCOME
        } else {
            TransactionType.EXPENSE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
