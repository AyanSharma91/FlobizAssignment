package com.world.flobiz_assgnment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.world.flobiz_assgnment.databinding.SingleRowListRecyclerBinding
import com.world.flobiz_assgnment.models.ModelItem
import de.hdodenhof.circleimageview.CircleImageView


class ListRecyclerAdapter(val context: Context, val arr: ArrayList<ModelItem>) :
    RecyclerView.Adapter<ListRecyclerAdapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_list_recycler, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arr.size
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item = arr[position]

        Picasso.get().load("https://picsum.photos/" + (200 + position).toString())
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(holder.mBinding?.image)


        holder.mBinding?.name?.text = item.author
    }

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mBinding: SingleRowListRecyclerBinding? = null

        init {
            mBinding = DataBindingUtil.bind(view)
        }

    }
}

