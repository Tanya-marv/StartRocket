package com.example.startrocket.presentation.screen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.startrocket.R
import com.example.startrocket.domain.Launch

class MainAdapter(
    private val onClick: (Launch) -> Unit,
    private val onLongClick: (Launch) -> Unit
) : ListAdapter<Launch, LaunchViewHolder>(LAUNCH_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_launch, parent, false)
        return LaunchViewHolder(view, onClick, onLongClick)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class LaunchViewHolder(
    private val view: View,
    private val onClick: (Launch) -> Unit,
    private val onLongClick: (Launch) -> Unit
) : RecyclerView.ViewHolder(view) {

    private var _launch: Launch? = null

    init {
        view.findViewById<View>(R.id.cl_item).setOnClickListener {
            _launch?.let(onClick)
        }
        view.findViewById<View>(R.id.cl_item).setOnLongClickListener {
            _launch?.let(onLongClick)
            true
        }
    }

    fun bind(launch: Launch) {
        _launch = launch
        view.findViewById<TextView>(R.id.tv_name).text = launch.name
    }
}

val LAUNCH_DIFF = object : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return newItem.name == oldItem.name &&
                newItem.details == oldItem.details &&
                newItem.dateUtc == oldItem.dateUtc
    }
}