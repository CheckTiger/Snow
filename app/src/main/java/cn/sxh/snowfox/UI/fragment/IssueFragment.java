package cn.sxh.snowfox.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sxh.snowfox.view.DefinedAndCollectView.StepViewNew;

/**
 * @author by snow on 2017/9/9
 * @time 23:53
 * @mail snowtigersong@gmail.com
 */

public class IssueFragment extends Fragment {
    private String title[] = {"宋学虎","陈聪","李惠","王立峰"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("问题总结");
        StepViewNew stepViewNew = new StepViewNew(getContext());
        stepViewNew.setTitle(title);
        return stepViewNew;
    }

}
