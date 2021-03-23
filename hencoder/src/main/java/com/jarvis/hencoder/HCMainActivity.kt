package com.jarvis.hencoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.jarvis.baselibrary.router.constants.ArouterPath
import com.jarvis.baselibrary.widgets.recycleview.BaseQuickRecyclerAdapter
import com.jarvis.baselibrary.widgets.recycleview.RecyclerQuickViewHolder
import com.jarvis.hencoder.databinding.HcActivityMainBinding
import com.jarvis.hencoder.view.api.Api
import com.jarvis.hencoder.view.api.Repo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Route(path = ArouterPath.Hencoder.HCMainActivity)
class HCMainActivity : AppCompatActivity() {

    private lateinit var contentView: HcActivityMainBinding

    private val mutableList: MutableList<String>
        get() {
            return mutableListOf(
                "View_Draw",
                "Xfemode",
                "MaterialEdittext",
                "ScaleableImageView",
                "TouchView",
                "泛型",
                "LeakCanary"
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView =
            DataBindingUtil.setContentView<HcActivityMainBinding>(this, R.layout.hc_activity_main)

        val mutableListOf = mutableList
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
                    button.text = item
                    button.setOnClickListener(View.OnClickListener {
                        handleClick(position, item)
                    })
                }

            }
    }

    private fun handleClick(position: Int, item: String) {
        when (item) {
            mutableList[0] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.HCViewDrawActivity)
                .navigation()
            mutableList[1] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.HCXferModeActivity)
                .navigation()
            mutableList[2] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.HCMaterialEditTextActivity)
                .navigation()
            mutableList[3] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.HCScaleableImageViewActivity)
                .navigation()
            mutableList[4] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.TouchViewActivity)
                .navigation()
            mutableList[5] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.TouchViewActivity)
                .navigation()
            mutableList[6] -> ARouter.getInstance()
                .build(ArouterPath.Hencoder.LeakCanaryActivity)
                .navigation()
            else -> delete()
        }
    }

    private fun delete() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        api.getRepos("rengwuxian")
//            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MutableList<Repo>?> {
                override fun onSuccess(t: MutableList<Repo>?) {
                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onError(e: Throwable?) {
                }
            })

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
            }
        })

        okHttpClient.newCall(request).execute()


        Glide.with(this).load("").into(contentView.imageview)
    }


    private fun rxJava3(): Unit {
//        startActivity()
        Single.just("1")
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(t: String?) {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onError(e: Throwable?) {
                }
            })

        Single.just(1)
            .map(object : Function<Int, String> {
                override fun apply(t: Int?): String {
                    return t.toString()
                }
            })
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(t: String?) {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onError(e: Throwable?) {
                }
            })

        Single.just(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int?) {
                    TODO("Not yet implemented")
                }

                override fun onSubscribe(d: Disposable?) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

            })
        Single.just(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int?) {
                    TODO("Not yet implemented")
                }

            })

    }

}

