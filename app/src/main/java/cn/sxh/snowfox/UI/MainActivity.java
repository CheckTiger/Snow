package cn.sxh.snowfox.UI;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.fragment.MyFragment;
import cn.sxh.snowfox.adapter.FragmentViewPageAdapter;
import cn.sxh.snowfox.view.TabBottomView;

public class MainActivity extends AppCompatActivity {

    private TabBottomView tabBottomView;
    private FragmentViewPageAdapter pageAdapter;
    private Fragment[] fragments = new Fragment[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tabBottomView = (TabBottomView) findViewById(R.id.tab_container);
        initFragmentArray();
    }

    private void initFragmentArray() {
        for (int i = 0; i < 5; i++) {
            fragments[i] = new MyFragment();
        }
        initFragmentAdapter();
    }

    private void initFragmentAdapter() {
        pageAdapter = new FragmentViewPageAdapter(fragments,getSupportFragmentManager());
        tabBottomView.setAdapter(pageAdapter);
    }
}
