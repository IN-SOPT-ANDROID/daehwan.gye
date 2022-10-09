package org.sopt.sample.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.LayoutListItemBinding

class HomeListAdapter(context: Context) : RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {
    private val inflater by lazy {
        LayoutInflater.from(context)
    }
    private var itemList: List<HomeListItem> = emptyList()

    class HomeViewHolder(
        private val binding: LayoutListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeListItem) {
            binding.tvListItemName.text = data.name
            binding.tvListItemBody.text = data.body
            binding.ivMyFaceImg.setImageDrawable(binding.root.context.getDrawable(data.imageNo))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = LayoutListItemBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun setList(list: List<HomeListItem>) {
        this.itemList = list.toList()

        // 변경 감지
        if (this.itemList.listChanged(list)) {
            notifyDataSetChanged()
        }
    }
}

private fun <E> List<E>.listChanged(toBe: List<E>) =
    this.size == toBe.size && this.containsAll(toBe)
