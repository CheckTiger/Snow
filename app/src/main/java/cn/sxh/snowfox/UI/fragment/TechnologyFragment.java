package cn.sxh.snowfox.UI.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.base.BaseFragment;
import cn.sxh.snowfox.view.DefinedAndCollectView.NavigationTabStrip;

/**
 * Created by snow on 2017/8/5.
 */

public class TechnologyFragment extends BaseFragment {
    private static final String TAG = TechnologyFragment.class.getSimpleName();
    @BindView(R.id.navigation_tab)
    NavigationTabStrip navigationTab;
    @BindView(R.id.context_container)
    FrameLayout contextContainer;
    private int mIndex = 0;

    private AllFragment allFragment;
    private KnowledgeFragment knowledgeFragment;
    private ToolsFragment toolsFragment;
    private DefinedFragment definedFragment;
    private IssueFragment issueFragment;
    private FragmentTransaction mTransaction;
    @Override
    protected int getContentView() {
        return R.layout.technology_fragment_view;
    }

    @Override
    protected void initUI(View view) {
        navigationTab.setTabIndex(0, true);
        changeContent(mIndex);
        navigationTab.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                mIndex = index;
            }

            @Override
            public void onEndTabSelected(String title, int index) {
                changeContent(mIndex);
            }
        });
    }

    private void changeContent(int mIndex) {
        mTransaction = getChildFragmentManager().beginTransaction();
        hideTabFragments(mTransaction);
        switch (mIndex) {
            case 0:
                if (allFragment == null) {
                    allFragment = new AllFragment();
                    mTransaction.add(R.id.context_container, allFragment);
                } else {
                    mTransaction.show(allFragment);
                }
                break;
            case 1:
                if (knowledgeFragment == null) {
                    knowledgeFragment = new KnowledgeFragment();
                    mTransaction.add(R.id.context_container, knowledgeFragment);
                } else {
                    mTransaction.show(knowledgeFragment);
                }
                break;
            case 2:
                if (definedFragment == null) {
                    definedFragment = new DefinedFragment();
                    mTransaction.add(R.id.context_container, definedFragment);
                } else {
                    mTransaction.show(definedFragment);
                }
                break;
            case 3:
                if (toolsFragment == null) {
                    toolsFragment = new ToolsFragment();
                    mTransaction.add(R.id.context_container, toolsFragment);
                } else {
                    mTransaction.show(toolsFragment);
                }
                break;
            case 4:
                if (issueFragment == null) {
                    issueFragment = new IssueFragment();
                    mTransaction.add(R.id.context_container, issueFragment);
                } else {
                    mTransaction.show(issueFragment);
                }
                break;
            default:
                break;
        }
        mTransaction.commit();
    }

    @Override
    protected void initData() {

    }

    /**
     * 隐藏碎片
     */
    private void hideTabFragments(FragmentTransaction transaction) {

        if (allFragment != null) {
            transaction.hide(allFragment);
        }
        if (knowledgeFragment != null) {
            transaction.hide(knowledgeFragment);
        }
        if (toolsFragment != null) {
            transaction.hide(toolsFragment);
        }
        if (definedFragment != null) {
            transaction.hide(definedFragment);
        }
        if (issueFragment != null) {
            transaction.hide(issueFragment);
        }

    }
}
