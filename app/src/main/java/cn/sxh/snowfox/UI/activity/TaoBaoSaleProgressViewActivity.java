package cn.sxh.snowfox.UI.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.view.DefinedAndCollectView.TaoBaoSaleProgressView;

public class TaoBaoSaleProgressViewActivity extends AppCompatActivity {

    @BindView(R.id.spv)
    TaoBaoSaleProgressView spv;
    @BindView(R.id.seek)
    SeekBar seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao_sale_progress_view);
        ButterKnife.bind(this);
        initTaoBaoView();
    }

    private void initTaoBaoView() {
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                spv.setTotalAndCurrentCount(100,i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
