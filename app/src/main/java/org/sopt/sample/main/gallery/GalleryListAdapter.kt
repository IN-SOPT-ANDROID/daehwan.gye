package org.sopt.sample.main.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.databinding.LayoutGalleryElementBinding

class GalleryListAdapter(context: Context) :
    RecyclerView.Adapter<GalleryListAdapter.GalleryViewHolder>() {
    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    private var itemList: List<GalleryElement> = emptyList()

    class GalleryViewHolder(
        private val binding: LayoutGalleryElementBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GalleryElement) {
            binding.tvListItemName.text = data.nickname
            binding.tvListItemBody.text = data.email
            Glide.with(binding.root)
                .load(data.profileImage)
                .centerCrop()
                .into(binding.ivMyFaceImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            binding = LayoutGalleryElementBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun setList(list: List<GalleryElement>) {
        this.itemList = list.toList()

        if (this.itemList.listChanged(list)) {
            notifyDataSetChanged()
        }
    }

    private fun <E> List<E>.listChanged(toBe: List<E>) =
        !(this.size == toBe.size && this.containsAll(toBe))
}

data class GalleryElement(
    val nickname: String,
    val email: String,
    val profileImage: String
) {
    companion object {
        fun convert(
            response: ReqresResponse
        ): List<GalleryElement> {

            return response.data.map {
                GalleryElement(
                    "${it.lastName} ${it.firstName}",
                    it.email,
                    it.avatar
                )
            }
        }
    }
}