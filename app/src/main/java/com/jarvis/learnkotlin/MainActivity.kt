package com.jarvis.learnkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.baselibrary.widgets.recycleview.BaseQuickRecyclerAdapter
import com.jarvis.baselibrary.widgets.recycleview.RecyclerQuickViewHolder
import com.jarvis.learnkotlin.databinding.ActivityMainBinding


/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
class MainActivity : AppCompatActivity() {


    private lateinit var contentView: ActivityMainBinding
    private val items = mutableListOf(
        "JLog",
        "Test"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        contentView.mainRecycleview.layoutManager = LinearLayoutManager(this)
        contentView.mainRecycleview.adapter =
            object : BaseQuickRecyclerAdapter<String>(this, items) {

                override fun getItemLayoutId(viewType: Int): Int {
                    return R.layout.item_button
                }

                override fun bindData(
                    holder: RecyclerQuickViewHolder,
                    position: Int,
                    item: String
                ) {
                    val button = holder.getButton(R.id.button)
                    button.text = item
                    button.setOnClickListener { handleClick(item) }
                }

            }

    }

    private fun handleClick(item: String) {
        when (item) {
            items[0] -> startActivity(Intent(this,JLogDemoActivity::class.java))
            else -> return
        }

    }


}