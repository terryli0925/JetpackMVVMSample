package com.terry.jetpackmvvm.sample.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.superluo.textbannerlibrary.TextBannerView;
import com.terry.jetpackmvvm.sample.R;

public class MarqueeVideoPlayer extends StandardGSYVideoPlayer {

    private TextBannerView mTvBanner;

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
        mTvBanner = findViewById(R.id.tv_banner);
    }

    public TextBannerView getTextBannerView() {
        return mTvBanner;
    }
}
