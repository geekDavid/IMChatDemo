package imchat.david.com.imchat;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import net.qiujuer.genius.ui.widget.FloatActionButton;

import butterknife.BindView;
import butterknife.OnClick;
import im.mvp.david.com.common.app.BaseActivity;
import im.mvp.david.com.common.widget.view.PicassoView;
import imchat.david.com.imchat.helper.BottomMenuHelper;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.pv_icon)
    PicassoView mPvIcon;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_serach)
    ImageView mIvSerach;
    @BindView(R.id.appbar_fl)
    FrameLayout mAppbarFl;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.floatBtn)
    FloatActionButton mFloatBtn;
    @BindView(R.id.btmnv_menu)
    BottomNavigationView mBtmnvMenu;

    private BottomMenuHelper mBottomMenuHelper;

    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWeight() {
        super.initWeight();

        mBtmnvMenu.setOnNavigationItemSelectedListener(this);
        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(mAppbar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.iv_serach, R.id.floatBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_serach:
                break;
            case R.id.floatBtn:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return mBottomMenuHelper.profromClickMenu(item.getItemId());
    }
}
