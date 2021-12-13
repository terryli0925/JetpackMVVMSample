package com.terry.jetpackmvvm.sample.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.terry.jetpackmvvm.sample.R;

import java.util.ArrayList;
import java.util.List;

public class MarqueeVideoPlayer extends StandardGSYVideoPlayer {

    private TextBannerView mTvBanner;
    private TextView mTvTest;
    private List<String> mList = new ArrayList<>();

    private IFullscreenListener mListener;

    public MarqueeVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MarqueeVideoPlayer(Context context) {
        super(context);
    }

    public MarqueeVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface IFullscreenListener {
        void onFullscreen(boolean state);
    }

    @Override
    public int getLayoutId() {
        return R.layout.marquee_video_player;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
//        setSaveEnabled(true);
        mTvBanner = findViewById(R.id.tv_banner);
        mTvTest = findViewById(R.id.tv_test);

//        mList = new ArrayList<>();
//        mList.add("学好Java、Android、C#、C、ios、html+css+js");
//        mList.add("走遍天下都不怕！！！！！");
//        mList.add("不是我吹，就怕你做不到，哈哈");
//        mList.add("superluo");
//        mList.add("你是最棒的，奔跑吧孩子！");
//        /**
//         * 设置数据，方式一
//         */
//        mTvBanner.setDatas(mList);
    }

    @Override
    public void onPrepared() {
        super.onPrepared();
//        onPrepareMarquee(this);
    }



    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        GSYBaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
//        if (gsyBaseVideoPlayer != null) {
//            MarqueeVideoPlayer gsyVideoPlayer = (MarqueeVideoPlayer) gsyBaseVideoPlayer;
//            TextBannerView tvBanner = gsyVideoPlayer.getTextBannerView();
//            ArrayList<String> list = new ArrayList<>();
//            list.add("学好Java、Android、C#、C、ios、html+css+js");
//            list.add("走遍天下都不怕！！！！！");
//            list.add("不是我吹，就怕你做不到，哈哈");
//            list.add("superluo");
//            list.add("你是最棒的，奔跑吧孩子！");
//            tvBanner.setDatas(list);
//            tvBanner.startViewAnimator();
//        }
        return gsyBaseVideoPlayer;
    }

//    private void onPrepareMarquee(MarqueeVideoPlayer gsyVideoPlayer) {
//        if (gsyVideoPlayer.getTextBannerView() != null && !gsyVideoPlayer.getDanmakuView().isPrepared() && gsyVideoPlayer.getParser() != null) {
//            gsyVideoPlayer.getDanmakuView().prepare(gsyVideoPlayer.getParser(),
//                    gsyVideoPlayer.getDanmakuContext());
//        }
//    }

//    @Override
//    public Parcelable onSaveInstanceState()
//    {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("superState", super.onSaveInstanceState());
//        return bundle;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Parcelable state)
//    {
//        if (state instanceof Bundle) // implicit null check
//        {
//        }
//        super.onRestoreInstanceState(state);
//    }

    public TextBannerView getTextBannerView() {
        return mTvBanner;
    }
    public TextView getTvTest() {
        return mTvTest;
    }
    public List<String> getList() {
        return mList;
    }
}
