package com.codsearchengineprofile.presentation.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.codsearchengineprofile.R


class SpinnerAdapter(
    private val context: Context,
    var platforms: List<String>,
    var imagesIds: List<Int>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val itemHolder: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner_item, parent, false)
            itemHolder = ItemHolder(view)
            view?.tag = itemHolder
        } else {
            view = convertView
            itemHolder = view.tag as ItemHolder
        }
        itemHolder.label.text = platforms[position]
        itemHolder.img.setBackgroundResource(imagesIds[position])
        return view
    }

    override fun getItem(position: Int): Any {
        return platforms[position]
    }

    override fun getCount(): Int {
        return platforms.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        val img: ImageView

        init {
            label = row?.findViewById(R.id.text) as TextView
            img = row.findViewById(R.id.img) as ImageView
        }
    }

}
