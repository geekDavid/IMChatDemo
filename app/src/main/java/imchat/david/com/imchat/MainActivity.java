package imchat.david.com.imchat;

import android.widget.TextView;

import butterknife.BindView;
import im.mvp.david.com.common.app.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.txt_test)
    TextView txt_test;

    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWeight() {
        super.initWeight();
    }

    @Override
    protected void initData() {
        super.initData();
        txt_test.setText("哈哈成功了");
    }
}
