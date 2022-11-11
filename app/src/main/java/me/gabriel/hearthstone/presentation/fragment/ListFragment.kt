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
import me.gabriel.hearthstone.databinding.FragmentListBinding
import me.gabriel.hearthstone.domain.HearthStoneDomainModel
import me.gabriel.hearthstone.extension.loading
import me.gabriel.hearthstone.presentation.adapter.HearthStoneRecyclerViewAdapter
import me.gabriel.hearthstone.presentation.viewmodel.HeathStoneListViewModel

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HeathStoneListViewModel by viewModels()

    lateinit var rvPhotos: RecyclerView
    lateinit var adapter: HearthStoneRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loader.loading(true)
        initMembers()
        setUpViews()
        setupViewModel()

        return view
    }

    private fun initMembers() {
        viewModel.getCardList()
        adapter = HearthStoneRecyclerViewAdapter {
            it?.let {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
                view?.findNavController()?.navigate(action)
            }
        }
    }

    private fun setUpViews() {
        rvPhotos = binding.rvList
        rvPhotos.layoutManager = GridLayoutManager(context, 2)
        rvPhotos.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.cardList.observe(viewLifecycleOwner, ::setupList)
    }

    private fun setupList(list: List<HearthStoneDomainModel>) {
        binding.loader.loading(false)
        if (list.isEmpty()) {
            viewModel.doFetchCardList().also {
                binding.loader.loading(true)
            }
        } else {
            adapter.setItems(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
