package com.kotlin.balancehero.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.balancehero.databinding.FragmentMainBinding
import com.kotlin.balancehero.ui.SharedViewModel

class Tab2Fragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = viewModel.getTab2Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        binding.nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if (scrollY == (v.measuredHeight - v.getChildAt(0).getMeasuredHeight())) {
           //     viewModel.getNextPage()
                binding.progressBar.visibility = View.VISIBLE
//            }
        }

        viewModel.getBeers().observe(requireActivity(), {
            if (it != null) {
                if (it.isNotEmpty()) {
                    viewModel.updateTab2Adapter(it)
                }
                binding.progressBar.visibility = View.GONE
            }
        })
    }
}
