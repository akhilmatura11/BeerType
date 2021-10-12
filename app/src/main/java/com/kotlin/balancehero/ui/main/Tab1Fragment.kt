package com.kotlin.balancehero.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.databinding.FragmentMainBinding
import com.kotlin.balancehero.ui.SharedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Tab1Fragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val beers: Beers = data?.getParcelableExtra("ActivityResult")!!
                    val checked: Boolean = data.getBooleanExtra("checkedResult", false)
                    viewModel.onCheckboxClicked(checked, beers)
                }
            }
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
//        viewModel.getBeersList().observe(requireActivity(), {
//            if (it != null && it.isNotEmpty()) {
//                viewModel.updateTab1Adapter(it)
//            }
//        })

        viewModel.getSelectedItem().observe(requireActivity(), {
            if (it != null) {
                val intent: Intent = Intent(requireActivity(), ItemDetailActivity::class.java)
                intent.putExtra("beerItem", it)
                resultLauncher.launch(intent)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = viewModel.getTab1Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        requireActivity().lifecycleScope.launch(
            viewModel.beers.collectLatest { pageData ->
                viewModel.submitDataTab1(pageData)
            })
    }
}
