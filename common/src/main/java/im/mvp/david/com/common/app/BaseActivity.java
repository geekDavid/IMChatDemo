package im.mvp.david.com.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author david
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            int layoutID = getContentLayoutID();
            setContentView(layoutID);
            initWeight();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWindows() {

    }

    /**
     * 获取布局id
     *
     * @return 布局layout文件的id
     */
    protected abstract int getContentLayoutID();

    /**
     * 初始化布局
     */
    protected void initWeight() {
        mUnbinder = ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化参数
     *
     * @param bundle
     * @return
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        //当点击导航栏上的返回键是关闭页面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //获取该Activity中的所有Fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        //判断是否为空
        if (fragments != null && fragments.size() > 0) {
            //循环
            for (Fragment fragment : fragments) {
                //判断是否为BaseFragment类型
                if (fragment instanceof BaseFragment) {
                    //判断是否自己消费
                    if (((BaseFragment) fragment).onBackPressed()) {
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
