package com.terry.jetpackmvvm.sample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.widget.MarqueeVideoPlayer


class SecondActivity : AppCompatActivity() {
    private lateinit var videoPlayer: MarqueeVideoPlayer
    private lateinit var orientationUtils: OrientationUtils

    private var list: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        videoPlayer = findViewById(R.id.video_player)

        val source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
        videoPlayer.backButton.visibility = View.VISIBLE
        //设置旋转
        orientationUtils = OrientationUtils(this, videoPlayer)
//        orientationUtils.isEnable = true


        videoPlayer.setUp(source1, true, "测试视频")
        videoPlayer.fullscreenButton.setOnClickListener { // ------- ！！！如果不需要旋转屏幕，可以不调用！！！-------
            videoPlayer.startWindowFullscreen(this, true, true) as MarqueeVideoPlayer
        }
        videoPlayer.setVideoAllCallBack(object : GSYSampleCallBack() {
            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientationUtils.isEnable = true
            }

            override fun onEnterFullscreen(url: String?, vararg objects: Any?) {
                super.onEnterFullscreen(url, *objects)
                if (objects[1] is MarqueeVideoPlayer) {
                    val videoView = objects[1] as MarqueeVideoPlayer
                    setUpMarquee(videoView)
                }
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, *objects)

//                if (objects[1] is MarqueeVideoPlayer) {
//                    val videoView = objects[1] as MarqueeVideoPlayer
//                    setUpMarquee(videoView)
//                }
            }
        })

        setUpMarquee(getCurPlay() as MarqueeVideoPlayer)

        videoPlayer.startPlayLogic()
    }

    private fun setUpMarquee(videoView: MarqueeVideoPlayer) {
        val tvBanner = videoView.textBannerView
        list = ArrayList()
        list?.add("学好Java、Android、C#、C、ios、html+css+js")
        list?.add("走遍天下都不怕！！！！！")
        list?.add("不是我吹，就怕你做不到，哈哈")
        list?.add("superluo")
        list?.add("你是最棒的，奔跑吧孩子！")
        tvBanner.setDatas(list)
        tvBanner.setItemOnClickListener { data, position ->
            Log.i("xxx", "SecondActivity ItemOnClickListener")

            Toast.makeText(
                this,
                "$position>>$data",
                Toast.LENGTH_SHORT
            ).show()
        }
        tvBanner.startViewAnimator();
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onVideoResume()
        val videoView = getCurPlay() as MarqueeVideoPlayer
        videoView.textBannerView.startViewAnimator()
    }

    override fun onPause() {
        super.onPause()
        videoPlayer.currentPlayer.onVideoPause()
        val videoView = getCurPlay() as MarqueeVideoPlayer
        videoView.textBannerView.stopViewAnimator()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        orientationUtils.releaseListener()
    }

    private fun getCurPlay(): GSYVideoPlayer {
        return if (videoPlayer.fullWindowPlayer != null) {
            videoPlayer.fullWindowPlayer
        } else {
            videoPlayer
        }
    }
}