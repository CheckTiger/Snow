package cn.sxh.snowfox.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.fragment.CategoryFragment;
import cn.sxh.snowfox.UI.fragment.MusicFragment;
import cn.sxh.snowfox.UI.fragment.ShopFragment;
import cn.sxh.snowfox.UI.fragment.SurpriseFragment;
import cn.sxh.snowfox.UI.fragment.TechnologyFragment;
import cn.sxh.snowfox.adapter.FragmentViewPageAdapter;
import cn.sxh.snowfox.view.Tab;
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
        fragments[0] = new MusicFragment();
        fragments[1] = new CategoryFragment();
        fragments[2] = new SurpriseFragment();
        fragments[3] = new ShopFragment();
        fragments[4] = new TechnologyFragment();
        initFragmentAdapter();
    }

    private void initFragmentAdapter() {
        pageAdapter = new FragmentViewPageAdapter(fragments,getSupportFragmentManager());
        tabBottomView.setAdapter(pageAdapter);
        tabBottomView.setOnTabSelectedListener(new Tab.OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                Toast.makeText(MainActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
