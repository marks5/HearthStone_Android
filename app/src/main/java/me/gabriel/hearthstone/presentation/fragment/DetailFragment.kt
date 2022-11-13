package me.gabriel.hearthstone.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import me.gabriel.hearthstone.R
import me.gabriel.hearthstone.databinding.FragmentDetailBinding
import me.gabriel.hearthstone.domain.HearthStoneDomainModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        handleData()
        return binding.root
    }

    private fun handleData() {
        if (arguments != null) {
            val data = DetailFragmentArgs.fromBundle(requireArguments()).id
            setupData(data)
        }
    }

    private fun setupData(data: HearthStoneDomainModel) = with(binding) {
        Picasso.get().load(data.img).into(imgCard)
        tvDynamic.text = StringBuilder("Card info:").apply {
            with(data) {
                append(getString(R.string.card_artist, artist))
                append(getString(R.string.card_name, name))
                append(getString(R.string.card_flavor, flavor))
                append(getString(R.string.card_short, text))
                append(getString(R.string.card_set, cardSet))
                append(getString(R.string.card_type, type))
                append(getString(R.string.card_faction, faction))
                append(getString(R.string.card_rarity, rarity))
                append(getString(R.string.card_attack, attack?:""))
                append(getString(R.string.card_cost, cost.toString()))
                append(getString(R.string.card_helth, health.toString()))
            }
        }.toString()

    }


}