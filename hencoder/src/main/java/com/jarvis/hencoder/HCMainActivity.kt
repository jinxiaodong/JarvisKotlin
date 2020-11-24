package com.jarvis.hencoder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.baselibrary.widgets.recycleview.BaseQuickRecyclerAdapter
import com.jarvis.baselibrary.widgets.recycleview.RecyclerQuickViewHolder
import com.jarvis.hencoder.databinding.HcActivityMainBinding

@Route(path = ArouterPath.Hencoder.HCMainActivity)
class HCMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView =
            DataBindingUtil.setContentView<HcActivityMainBinding>(this, R.layout.hc_activity_main)

        val mutableListOf = mutableListOf<String>("View_Draw")
        contentView.recycleview.layoutManager = LinearLayoutManager(this)
        contentView.recycleview.adapter =
            object : BaseQuickRecyclerAdapter<String>(this, mutableListOf) {
                override fun getItemLayoutId(viewType: Int): Int {
                    return R.layout.hc_item_button
                }

                override fun bindData(
                    holder: RecyclerQuickViewHolder,
                    position: Int,
                    item: String
                ) {
                    val button = holder.getButton(R.id.button)
                    button.setOnClickListener(View.OnClickListener {
                        handleClick(position, item)
                    })
                }

            }
    }

    private fun handleClick(position: Int, item: String) {
        when (position) {
            0 -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.HCViewDrawActivity)
                .navigation()
        }
    }

}
