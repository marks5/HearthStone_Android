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
        tvDynamic.text = StringBuilder(getString(R.string.card_head)).apply {
            with(data) {
                append(getString(R.string.card_artist, artist.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_name, name.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_flavor, flavor.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_short, text.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_set, cardSet.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_type, type.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_faction, faction.getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_rarity, rarity).getDefaultTextIfIsEmpty())
                append(getString(R.string.card_attack, attack.toString().getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_cost, cost.toString().getDefaultTextIfIsEmpty()))
                append(getString(R.string.card_helth, health.toString().getDefaultTextIfIsEmpty()))
            }
        }.toString()

    }

    private fun String.getDefaultTextIfIsEmpty() = if (isNullOrEmpty() || equals("0")) {
        getString(R.string.card_default)
    } else this

}