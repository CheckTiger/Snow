package cn.sxh.snowfox.UI.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.view.DefinedAndCollectView.ViewStudyStepOne;

public class ViewStudyActivity extends AppCompatActivity {

    private static final String TAG = ViewStudyActivity.class.getSimpleName();
    @BindView(R.id.view_step)
    ViewStudyStepOne viewStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_study);
        ButterKnife.bind(this);
        changeViewType(ViewStudyStepOne.HISTOGRAM_VIEW);
    }

    private void changeViewType(int type) {
        viewStep.setViewType(type);
    }
}
