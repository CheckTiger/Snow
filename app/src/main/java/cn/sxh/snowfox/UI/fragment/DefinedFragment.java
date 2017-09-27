package cn.sxh.snowfox.UI.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sxh.snowfox.AppContext;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.UI.activity.ClockViewActivity;
import cn.sxh.snowfox.UI.activity.MiStepViewActivity;
import cn.sxh.snowfox.UI.activity.NewsActivity;
import cn.sxh.snowfox.UI.activity.TaoBaoSaleProgressViewActivity;
import cn.sxh.snowfox.adapter.AllFragmentAdapter;
import cn.sxh.snowfox.base.BaseFragment;

/**
 * @author by snow on 2017/9/9
 * @time 23:53
 * @mail snowtigersong@gmail.com
 */

public class DefinedFragment extends BaseFragment {
    private static final String TAG = "AllFragment";
    private ListView mListView;
    private List<String> list = new ArrayList<>();
    private AllFragmentAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.all_fragment_layout;
    }

    @Override
    protected void initUI(View view) {
        mListView = view.findViewById(R.id.all_fragment_listView);
        adapter = new AllFragmentAdapter(getContext(),list);
    }

    @Override
    protected void initData() {
        List<String> technologyName = Arrays.asList(AppContext.getInstance().getResources().getStringArray(R.array.all_defined_view));
        for (int i = 0; i < technologyName.size(); i++) {
            list.add(technologyName.get(i));
        }
        mListView.setAdapter(adapter);
        adapter.setOnLinearLayoutListener((holder, position) -> gotoActivity( position));
    }
    private void gotoActivity(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getActivity(), ClockViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(getActivity(), MiStepViewActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(getActivity(), TaoBaoSaleProgressViewActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
