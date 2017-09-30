package cn.sxh.snowfox.UI.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.view.DefinedAndCollectView.StepView;

public class MiStepViewActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextWatcher {
    private StepView mSvStep;
    private SeekBar mSeekBar;
    private EditText mEtMax;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_step_view);
        initView();
        initEvent();
    }

    private void initView() {
        mSvStep = (StepView) findViewById(R.id.stepView);
        mSvStep.setMaxProgress(6000);
        mSvStep.setProgress(2000);
//        mSvStep.setColor(Color.parseColor("#00ffff"));
        mSvStep.setBigTextSize(120);
        mSvStep.setDotSize(12);
        mSvStep.setSmallTextSize(40);
        mSvStep.setLineDistance(30);
        mSvStep.setStrokeWidth(2);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mEtMax = (EditText) findViewById(R.id.et_max);
    }

    private void initEvent() {
        mSeekBar.setOnSeekBarChangeListener(this);
        mEtMax.addTextChangedListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSvStep.stopAnimator(5500);

                    }
                });
            }
        }).start();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSvStep.setProgress(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String max = s.toString();

        int maxProgress = 0;
        try {
            maxProgress = Integer.parseInt(max);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mSvStep.setMaxProgress(maxProgress);
    }
}
