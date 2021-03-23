package com.jarvis.learnkotlin

import JTabBottomInfo
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jarvis.baselibrary.utils.dp
import com.jarvis.jui.tab.bottom.JTabBottomLayout
import com.jarvis.jui.tab.common.IJTablayout
import com.jarvis.learnkotlin.databinding.ActivityJLibMainBinding

class JLibMainActivity : AppCompatActivity() {

    private lateinit var contentView: ActivityJLibMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView = DataBindingUtil.setContentView<ActivityJLibMainBinding>(
            this,
            R.layout.activity_j_lib_main
        )


        contentView.tabLayout.bottomAlpha = 0.85f

        val bottomInfoList: MutableList<JTabBottomInfo<*>> = ArrayList()


        val homeInfo = JTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.app_home),
            null,
            "#ff656677",
            "#ffd44949"
        )
        val favoriteInfo = JTabBottomInfo(
            "收藏",
            "fonts/iconfont.ttf",
            getString(R.string.app_favorite),
            null,
            "#ff656677",
            "#ffd44949"
        )
//
//        val categoryInfo = JTabBottomInfo(
//            "分类",
//            "fonts/iconfont.ttf",
//            getString(R.string.app_category),
//            null,
//            "#ff656677",
//            "#ffd44949"
//        )

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.fire, null)
        val categoryInfo = JTabBottomInfo<String>("分类", bitmap, bitmap)

        val recommendInfo = JTabBottomInfo(
            "推荐",
            "fonts/iconfont.ttf",
            getString(R.string.app_recommend),
            null,
            "#ff656677",
            "#ffd44949"
        )
        val myInfo = JTabBottomInfo(
            "我的",
            "fonts/iconfont.ttf",
            getString(R.string.app_profile),
            null,
            "#ff656677",
            "#ffd44949"
        )
        bottomInfoList.add(homeInfo)
        bottomInfoList.add(favoriteInfo)
        bottomInfoList.add(categoryInfo)
        bottomInfoList.add(recommendInfo)
        bottomInfoList.add(myInfo)

        var listener = object :
            IJTablayout.OnTabSelectedListener<JTabBottomInfo<*>> {
            override fun onTabSelectedChange(
                index: Int,
                preInfo: JTabBottomInfo<*>?,
                nextInfo: JTabBottomInfo<*>
            ) {
                Toast.makeText(this@JLibMainActivity, nextInfo.name, Toast.LENGTH_SHORT).show()

            }
        }
        contentView.tabLayout.inflateInfo(bottomInfoList)

        contentView.tabLayout.addTabSelectedChangeListener(listener)

        val findTab = contentView.tabLayout.findTab(bottomInfoList[2])
        findTab?.resetHeight(66.dp.toInt())
    }
}
