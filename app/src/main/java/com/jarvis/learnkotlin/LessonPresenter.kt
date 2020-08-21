package com.jarvis.learnkotlin

import com.google.gson.reflect.TypeToken
import com.jarvis.baselibrary.http.EntityCallback
import com.jarvis.baselibrary.http.HttpClient.get
import com.jarvis.baselibrary.utils.Utils.toast
import com.jarvis.learnkotlin.entity.Lesson
import java.util.*

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
class LessonPresenter {

    companion object {
        //const 编译器常量
        const val LESSON_PATH = "lessons"
    }

    val activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    var lessons: List<Lesson> = ArrayList()

    val type = object : TypeToken<List<Lesson?>?>() {}.type

     fun fetchData() {
        get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(entity) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message!!) }
            }
        })
    }

    fun showPlayback() {
        //List 不可修改的集合 MutableList/ArrayList 可修改的集合
        //Map 不可修改的集合 MutableMap/HashMap 可修改的集合
//        val playbackLessons: MutableList<Lesson> = ArrayList()
//        for (lesson in lessons) {
//            if (lesson.state === Lesson.State.PLAYBACK) {
//                playbackLessons.add(lesson)
//            }
//        }

//        lessons.forEach {
//            if (it.state === Lesson.State.PLAYBACK) {
//                playbackLessons.add(it)
//            }
//        }

//        val playbackLessons = lessons.filter { it.state === Lesson.State.PLAYBACK }

        activity.showResult(lessons.filter { it.state === Lesson.State.PLAYBACK })


    }
}