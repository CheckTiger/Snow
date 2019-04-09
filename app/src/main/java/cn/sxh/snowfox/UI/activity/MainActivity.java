package cn.sxh.snowfox.UI.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.fragment.CategoryFragment;
import cn.sxh.snowfox.UI.fragment.MusicFragment;
import cn.sxh.snowfox.UI.fragment.ShopFragment;
import cn.sxh.snowfox.UI.fragment.SurpriseFragment;
import cn.sxh.snowfox.UI.fragment.TechnologyFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rg)
    RadioGroup rg;
    /**
     * 碎片内容区和碎片事务管理器
     */
    private MusicFragment musicFragment;
    private CategoryFragment categoryFragment;
    private SurpriseFragment surpriseFragment;
    private ShopFragment shopFragment;
    private TechnologyFragment technologyFragment;
    private FragmentTransaction mTransaction;

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        KLog.e("sxh","onCreate: --------->>>>>");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.e("sxh","onStop: --------->>>>>");
    }

    @Override
    protected void onStart() {
        super.onStart();
        KLog.e("sxh","onStart: --------->>>>>");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        KLog.e("sxh","onRestart: --------->>>>>");
    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.e("sxh","onPause: --------->>>>>");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.e("sxh","onDestroy: --------->>>>>");
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.e("sxh","onResume: --------->>>>>");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        KLog.e("sxh","onAttachedToWindow: --------->>>>>");
    }

    private void initView() {
        setTabSelectFragment(0);
        rg.setOnCheckedChangeListener(this);
    }

    private void setTabSelectFragment(int index) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        hideTabFragments(mTransaction);
        switch (index) {
            case 0:
                if (musicFragment == null) {
                    musicFragment = new MusicFragment();
                    mTransaction.add(R.id.home_content, musicFragment);
                } else {
                    mTransaction.show(musicFragment);
                }
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                    mTransaction.add(R.id.home_content, categoryFragment);
                } else {
                    mTransaction.show(categoryFragment);
                }
                break;
            case 2:
                if (surpriseFragment == null) {
                    surpriseFragment = new SurpriseFragment();
                    mTransaction.add(R.id.home_content, surpriseFragment);
                } else {
                    mTransaction.show(surpriseFragment);
                }
                break;
            case 3:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                    mTransaction.add(R.id.home_content, shopFragment);
                } else {
                    mTransaction.show(shopFragment);
                }
                break;
            case 4:
                if (technologyFragment == null) {
                    technologyFragment = new TechnologyFragment();
                    mTransaction.add(R.id.home_content, technologyFragment);
                } else {
                    mTransaction.show(technologyFragment);
                }
                break;
            default:
                break;
        }
        mTransaction.commit();
    }

    /**
     * 隐藏碎片
     */
    private void hideTabFragments(FragmentTransaction transaction) {

        if (musicFragment != null) {
            transaction.hide(musicFragment);
        }
        if (categoryFragment != null) {
            transaction.hide(categoryFragment);
        }
        if (surpriseFragment != null) {
            transaction.hide(surpriseFragment);
        }
        if (shopFragment != null) {
            transaction.hide(shopFragment);
        }
        if (technologyFragment != null) {
            transaction.hide(technologyFragment);
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_homepage:
                currentIndex = 0;
                setTabSelectFragment(0);
                break;
            case R.id.rb_category:
                currentIndex = 1;
                setTabSelectFragment(1);
                break;
            case R.id.rb_discover:
                currentIndex = 2;
                setTabSelectFragment(2);
                break;
            case R.id.rb_shoppingcart:
                currentIndex = 3;
                changedSelectedMenuUI();
                setTabSelectFragment(3);
                break;
            case R.id.rb_mine:
                currentIndex = 4;
                setTabSelectFragment(4);
                break;
        }
    }

    private void changedSelectedMenuUI() {
        RadioButton radioButton = (RadioButton) rg.getChildAt(currentIndex);
        radioButton.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
            case R.id.action_about:
                Toast.makeText(this, "正在施工中", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
