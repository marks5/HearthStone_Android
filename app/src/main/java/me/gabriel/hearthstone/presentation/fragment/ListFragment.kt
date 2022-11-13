package me.gabriel.hearthstone.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.gabriel.hearthstone.R
import me.gabriel.hearthstone.databinding.FragmentListBinding
import me.gabriel.hearthstone.domain.HearthStoneDomainModel
import me.gabriel.hearthstone.extension.isNetworkAvailable
import me.gabriel.hearthstone.extension.loading
import me.gabriel.hearthstone.presentation.adapter.HearthStoneRecyclerViewAdapter
import me.gabriel.hearthstone.presentation.viewmodel.HeathStoneListViewModel

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HeathStoneListViewModel by viewModels()

    lateinit var rvCards: RecyclerView
    lateinit var cardsAdapter: HearthStoneRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        showError()
        binding.loader.loading(true)
        initMembers()
        setUpViews()
        setupViewModel()

        return view
    }

    private fun initMembers() {
        viewModel.getCardList()
        cardsAdapter = HearthStoneRecyclerViewAdapter {
            it?.let {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
                view?.findNavController()?.navigate(action)
            }
        }
    }

    private fun setUpViews() = with(binding) {
        rvCards = rvList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = cardsAdapter
        }

        errorButton.setOnClickListener {
            loader.loading(true)
            handleInternetConnection()
        }
    }

    private fun setupViewModel() {
        viewModel.cardList.observe(viewLifecycleOwner, ::setupList)
        viewModel.error.observe(viewLifecycleOwner, ::setupError)
    }

    private fun setupError(errorValidator: Boolean) = with(binding) {
        if (errorValidator) {
            loader.loading()
            showError(true)
        }
    }

    private fun showError(shouldShow: Boolean = false) = with(binding) {
        val stateVisible = if (shouldShow) View.VISIBLE else View.GONE
        error.apply {
            text = getString(R.string.connection_error)
            visibility = stateVisible
        }
        errorButton.visibility = stateVisible
    }

    private fun setupList(list: List<HearthStoneDomainModel>) {
        binding.loader.loading()
        if (list.isEmpty()) {
            handleInternetConnection()
        } else {
            cardsAdapter.setItems(list)
        }
    }

    private fun handleInternetConnection() = with(binding.loader) {
        if (requireContext().isNetworkAvailable()) {
            viewModel.doFetchCardList().also {
                showError()
                loading(true)
            }
        } else {
            showError(true)
            loading()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
