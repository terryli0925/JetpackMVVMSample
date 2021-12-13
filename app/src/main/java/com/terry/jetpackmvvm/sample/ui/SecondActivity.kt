package com.terry.jetpackmvvm.sample.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.superluo.textbannerlibrary.ITextBannerItemClickListener
import com.superluo.textbannerlibrary.TextBannerView
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.widget.MarqueeVideoPlayer
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack





class SecondActivity : AppCompatActivity() {
    private lateinit var videoPlayer: MarqueeVideoPlayer
    private lateinit var tvBanner: TextBannerView
    private lateinit var orientationUtils: OrientationUtils

    private var list: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        videoPlayer = findViewById(R.id.video_player)

        val source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
        videoPlayer.backButton.visibility = View.VISIBLE
        //设置旋转
        //设置旋转
        orientationUtils = OrientationUtils(this, videoPlayer)
        orientationUtils.isEnable = true
        videoPlayer.setUp(source1, true, "测试视频")
        videoPlayer.fullscreenButton.setOnClickListener { // ------- ！！！如果不需要旋转屏幕，可以不调用！！！-------
            // 不需要屏幕旋转，还需要设置 setNeedOrientationUtils(false)
            orientationUtils.resolveByClick()
            val gsyBaseVideoPlayer = videoPlayer.startWindowFullscreen(this, true, true) as MarqueeVideoPlayer
            initBanner(gsyBaseVideoPlayer.textBannerView)
//            finish()
        }
        videoPlayer.setVideoAllCallBack(object : GSYSampleCallBack() {
            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientationUtils.isEnable = videoPlayer.isRotateWithSystem()
            }

            override fun onAutoComplete(url: String, vararg objects: Any) {
                super.onAutoComplete(url, *objects)
            }

            override fun onClickStartError(url: String, vararg objects: Any) {
                super.onClickStartError(url, *objects)
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, *objects)

                // ------- ！！！如果不需要旋转屏幕，可以不调用！！！-------
                // 不需要屏幕旋转，还需要设置 setNeedOrientationUtils(false)
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo()
                }
            }
        })
        videoPlayer.backButton.setOnClickListener { onBackPressed() }
        videoPlayer.startPlayLogic()

        val tvBanner = videoPlayer.textBannerView
        initBanner(tvBanner)
//        list = ArrayList()
//        list?.add("学好Java、Android、C#、C、ios、html+css+js")
//        list?.add("走遍天下都不怕！！！！！")
//        list?.add("不是我吹，就怕你做不到，哈哈")
//        list?.add("superluo")
//        list?.add("你是最棒的，奔跑吧孩子！")
//        tvBanner.setDatas(list)
//        tvBanner.setItemOnClickListener(ITextBannerItemClickListener { data, position ->
//            Log.i("xxx", "SecondActivity ItemOnClickListener")
//
//            Toast.makeText(this,
//                "$position>>$data",
//                Toast.LENGTH_SHORT
//            ).show()
//        })
        videoPlayer.tvTest.text = "fsfsdf"
//        tvBanner = findViewById(R.id.tv_banner)
//        list = ArrayList()
//        list?.add("学好Java、Android、C#、C、ios、html+css+js")
//        list?.add("走遍天下都不怕！！！！！")
//        list?.add("不是我吹，就怕你做不到，哈哈")
//        list?.add("superluo")
//        list?.add("你是最棒的，奔跑吧孩子！")
//        tvBanner.setDatas(list)
//
//        tvBanner.setItemOnClickListener(ITextBannerItemClickListener { data, position ->
//            Toast.makeText(this,
//                "$position>>$data",
//                Toast.LENGTH_SHORT
//            ).show()
//        })
    }

    fun initBanner(tvBanner: TextBannerView) {
        list = ArrayList()
        list?.add("学好Java、Android、C#、C、ios、html+css+js")
        list?.add("走遍天下都不怕！！！！！")
        list?.add("不是我吹，就怕你做不到，哈哈")
        list?.add("superluo")
        list?.add("你是最棒的，奔跑吧孩子！")
        tvBanner.setDatas(list)
        tvBanner.setItemOnClickListener(ITextBannerItemClickListener { data, position ->
            Log.i("xxx", "SecondActivity ItemOnClickListener")

            Toast.makeText(this,
                "$position>>$data",
                Toast.LENGTH_SHORT
            ).show()
        })
        tvBanner.startViewAnimator();
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        videoPlayer.onConfigurationChanged(
//                this,
//                newConfig,
//                orientationUtils,
//                true,
//                true
//            )
//    }


    override fun onPause() {
        super.onPause()
        videoPlayer.currentPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (orientationUtils != null) orientationUtils.releaseListener()
    }

    override fun onBackPressed() {
///       不需要回归竖屏
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }
}