package com.waslim.sispakudangvaname.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waslim.sispakudangvaname.R
import com.waslim.sispakudangvaname.databinding.GejalaListItemBinding
import com.waslim.sispakudangvaname.model.dataclass.gejala.Gejala

@SuppressLint("NotifyDataSetChanged")
class GejalaAdapter : RecyclerView.Adapter<GejalaAdapter.ChipViewHolder>() {
    private var listGejala: List<Gejala>? = null
    private val selectedChips: MutableList<Gejala> = mutableListOf()

    fun setListGejala(list: List<Gejala>?) {
        this.listGejala = list
        notifyDataSetChanged()
    }

    fun getSelectedChips(): List<Gejala> {
        return selectedChips
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val binding = GejalaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val gejala = listGejala?.get(position)

        holder.binding.chipItem.text = gejala?.gejala

        // Set background color based on isChecked status
        val backgroundColorRes = if (gejala in selectedChips) R.color.teal_100 else R.color.black
        holder.binding.chipItem.setChipBackgroundColorResource(backgroundColorRes)

        // Atur action ketika chip diklik
        holder.binding.chipItem.setOnClickListener {
            if (gejala != null) {
                if (gejala in selectedChips) {
                    selectedChips.remove(gejala)
                } else {
                    selectedChips.add(gejala)
                }
                notifyDataSetChanged()
            }
        }

        // Atur action ketika status checked pada chip berubah
        holder.binding.chipItem.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                gejala?.let { selectedChips.add(it) }
            } else {
                gejala?.let { selectedChips.remove(it) }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = listGejala?.size ?: 0

    inner class ChipViewHolder(val binding: GejalaListItemBinding) : RecyclerView.ViewHolder(binding.root)
}