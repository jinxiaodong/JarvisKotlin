package com.jarvis.learnkotlin

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jarvis.baselibrary.BaseView
import com.jarvis.baselibrary.utils.CacheUtils
import com.jarvis.learnkotlin.entity.Lesson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener {

    override val presenter: LessonPresenter by lazy {
        LessonPresenter(this)
    }


//    var token: String
//        set(value) {
//            CacheUtils.save("token", value)
//        }
//        get() {
//            return CacheUtils.get("token")!!
//        }

    var token: String by Saver("token")
    var token1: String by Saver("token1")


    class Saver(var key: String) {
        operator fun getValue(thisRef: LessonActivity, property: KProperty<*>): String {
            return CacheUtils.get(key)!!
        }

        operator fun setValue(thisRef: LessonActivity, property: KProperty<*>, value: String) {
            CacheUtils.save(key, value)
        }
    }

    private val lessonAdapter = LessonAdapter()
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        //
        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        //
        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener { presenter.fetchData() }
            isRefreshing = true
        }
        presenter.fetchData()
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        presenter.showPlayback()
        return false
    }

}



