/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jarvis.baselibrary.widgets.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author jinxiaodong
 * @description：
 * @date 2019-12-21
 */
abstract class BaseQuickRecyclerAdapter<T>(
    ctx: Context,
    list: MutableList<T>?
) : RecyclerView.Adapter<RecyclerQuickViewHolder>() {
    private var mData: MutableList<T>?
    private val mContext: Context
    private val mInflater: LayoutInflater
    private var mClickListener: OnItemClickListener? =
        null
    private var mLongClickListener: OnItemLongClickListener? =
        null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerQuickViewHolder {
        val holder = RecyclerQuickViewHolder(
            mContext,
            mInflater.inflate(getItemLayoutId(viewType), parent, false)
        )
        if (mClickListener != null) {
            holder.itemView.setOnClickListener {
                mClickListener!!.onItemClick(
                    holder.itemView,
                    holder.layoutPosition
                )
            }
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mLongClickListener!!.onItemLongClick(holder.itemView, holder.layoutPosition)
                true
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerQuickViewHolder, position: Int) {
        bindData(holder, position, mData!![position])
    }

    fun getItem(pos: Int): T {
        return mData!![pos]
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    fun add(pos: Int, item: T) {
        mData!![pos] = item
        notifyItemInserted(pos)
    }

    fun delete(pos: Int) {
        mData!!.removeAt(pos)
        notifyItemRemoved(pos)
    }

    var data: MutableList<T>?
        get() = if (mData == null) ArrayList() else mData
        set(list) {
            mData = list ?: ArrayList()
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        mLongClickListener = listener
    }

    abstract fun getItemLayoutId(viewType: Int): Int
    abstract fun bindData(holder: RecyclerQuickViewHolder, position: Int, item: T)
    interface OnItemClickListener {
        fun onItemClick(itemView: View?, pos: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(itemView: View?, pos: Int)
    }

    init {
        mData = list ?: ArrayList()
        mContext = ctx
        mInflater = LayoutInflater.from(ctx)
    }
}