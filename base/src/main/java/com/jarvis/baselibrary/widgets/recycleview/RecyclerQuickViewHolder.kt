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
import android.util.SparseArray
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

/**
 * @author jinxiaodong
 * @description：
 * @date 2019-12-21
 */
class RecyclerQuickViewHolder(
    ctx: Context?,
    itemView: View?
) : RecyclerView.ViewHolder(itemView!!) {
    private val mViews: SparseArray<View?> = SparseArray()
    private fun <T : View?> findViewById(viewId: Int): T? {
        var view = mViews[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T?
    }

    fun getView(viewId: Int): View {
        return findViewById<View>(viewId)!!
    }

    fun getTextView(viewId: Int): TextView {
        return getView(viewId) as TextView
    }

    fun getButton(viewId: Int): Button {
        return getView(viewId) as Button
    }

    fun getImageView(viewId: Int): ImageView {
        return getView(viewId) as ImageView
    }

    fun getImageButton(viewId: Int): ImageButton {
        return getView(viewId) as ImageButton
    }

    fun getEditText(viewId: Int): EditText {
        return getView(viewId) as EditText
    }

    fun setText(viewId: Int, value: String?): RecyclerQuickViewHolder {
        val view = findViewById<TextView>(viewId)!!
        view.text = value
        return this
    }

    fun setBackground(viewId: Int, resId: Int): RecyclerQuickViewHolder {
        val view = findViewById<View>(viewId)!!
        view.setBackgroundResource(resId)
        return this
    }

    fun setClickListener(
        viewId: Int,
        listener: View.OnClickListener?
    ): RecyclerQuickViewHolder {
        val view = findViewById<View>(viewId)!!
        view.setOnClickListener(listener)
        return this
    }

}