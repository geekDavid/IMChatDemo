package im.mvp.david.com.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/26.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRoot;
    protected Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initWindows();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layoutID = getContentLayoutID();
            View root = inflater.inflate(layoutID, container, false);
            initWeight(root);
            mRoot = root;
        } else if (mRoot.getParent() != null) {
            ((ViewGroup) mRoot.getParent()).removeView(mRoot);
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化数据
        initData();
    }

    /**
     * 获取布局id
     *
     * @return 布局layout文件的id
     */
    protected abstract int getContentLayoutID();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化窗口
     */
    protected void initWindows() {

    }

    /**
     * 初始化布局
     */
    protected void initWeight(View root) {
        mUnbinder = ButterKnife.bind(this, root);
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

    /**
     * 返回触发时
     *
     * @return 如果放回true表示自己要消费，false表示自己不消费
     */
    public boolean onBackPressed() {
        return false;
    }

}
