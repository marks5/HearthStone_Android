package me.gabriel.hearthstone.presentation.adapter

import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso
import me.gabriel.hearthstone.R
import me.gabriel.hearthstone.databinding.ItemCardBinding
import me.gabriel.hearthstone.domain.HearthStoneDomainModel

class HearthStoneViewHolder(binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
    private val image: ImageView = binding.photoImage

    fun bind(item: HearthStoneDomainModel?, listener: (HearthStoneDomainModel?) -> Unit) {
        image.apply {
            val circularProgressDrawable = CircularProgressDrawable(context).apply {
                strokeWidth = 5f
                centerRadius = 90f
                setColorSchemeColors(
                    getColor(context, R.color.purple_700)
                )
                start()
            }

            if (!item?.img.isNullOrEmpty()) {
                Picasso.get()
                    .load(item?.img)
                    .resize(160, 220)
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(image)
            }

            setOnClickListener {
                listener(item)
            }
        }
    }
}
