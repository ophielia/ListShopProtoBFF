package com.listshop.bffpoc.client.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.listshop.bffpoc.test.android.R
import com.listshop.bff.data.model.Tag


class TagsRvAdapter(var tags: List<Tag>) : RecyclerView.Adapter<TagsRvAdapter.TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tag, parent, false)
            .run(::TagViewHolder)
    }

    override fun getItemCount(): Int = tags.count()

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bindData(tags[position])
    }

    inner class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tagNameTextView = itemView.findViewById<TextView>(R.id.tagName)
        private val tagTypeTextView = itemView.findViewById<TextView>(R.id.tagType)
        private val tagIdsTextView = itemView.findViewById<TextView>(R.id.tagIds)

        fun bindData(tag: Tag) {
            val ctx = itemView.context
            tagNameTextView.text = ctx.getString(R.string.tag_name_field, tag.name)
            tagTypeTextView.text = ctx.getString(R.string.tag_type_field, tag.tagType)
            tagIdsTextView.text = ctx.getString(R.string.tag_id_field, tag.externalId)
            val isChild = tag.parentId != null && tag.parentId != "0"
            if (isChild ) {
                tagIdsTextView.setTextColor(
                    (ContextCompat.getColor(
                        itemView.context,
                        R.color.colorNoData
                    ))
                )
            } else {
                tagIdsTextView.setTextColor(
                    (ContextCompat.getColor(
                        itemView.context,
                        R.color.colorSuccessful
                    ))
                )
            }
        }
    }
}