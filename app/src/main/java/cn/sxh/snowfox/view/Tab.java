package cn.sxh.snowfox.view;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.sxh.snowfox.R;

/**
 * Created by snow on 2017/8/2.
 */

public class Tab {
    private Context context;
    private int index;
    private boolean isSelected;
    /**
     * 文本信息
     */
    private String text;
    private int textColor;
    private int selectedTextColor;
    private int textSize;
    private int drawablePadding;
    /**
     * icon信息
     */
    private int iconImage;
    private int selectedIconImge;
    private int iconHeight;
    private int iconWidth;
    /**
     * Tab布局信息
     */
    private RelativeLayout childView;
    private LinearLayout rootView;
    private ImageView iconImageView;
    private TextView textTextView;
    private boolean hasMsg;

    private OnTabSelectedListener onTabSelectedListener;


    public Tab(Context context, String text,int index, int textColor, int selectedTextColor, int textSize, int drawablePadding, int iconImage, int selectedIconImge, int iconHeight, int iconWidth, boolean hasMsg) {
        this.context = context;
        this.index = index;
        this.text = text;
        this.textColor = textColor;
        this.selectedTextColor = selectedTextColor;
        this.textSize = textSize;

        this.drawablePadding = drawablePadding;
        this.iconImage = iconImage;
        this.selectedIconImge = selectedIconImge;
        this.iconHeight = iconHeight;
        this.iconWidth = iconWidth;
        this.hasMsg = hasMsg;

        init();
    }

    private void init() {
        initView();
        /**
         * 每个tab的父布局
         */
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabSelected();
            }
        });
    }

    /**
     * 初始化相关的view信息
     */
    private void initView() {
        rootView = new LinearLayout(context);
        childView = new RelativeLayout(context);
        LinearLayout.LayoutParams rootViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootViewLp.weight = 1;
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setPadding(0,20,0,20);
        rootView.setLayoutParams(rootViewLp);
        textTextView = new TextView(context);
        iconImageView = new ImageView(context);

        /**
         * icon---View
         */
        iconImageView.setImageResource(iconImage);
        RelativeLayout.LayoutParams iconParam = new RelativeLayout.LayoutParams(iconWidth == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : iconWidth,
                iconHeight == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : iconHeight);
        iconParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        iconImageView.setLayoutParams(iconParam);
        iconImageView.setId(index+1);
        childView.addView(iconImageView);


        /**
         *  text view
         */
        textTextView.setText(text);
        textTextView.setTextColor(textColor);
        textTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        textTextView.setPadding(0,drawablePadding,0,0);
        RelativeLayout.LayoutParams txParam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        txParam.addRule(RelativeLayout.BELOW,childView.getChildAt(0).getId());
        txParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textTextView.setLayoutParams(txParam);
        childView.addView(textTextView);
        /**
         * 设置是否有消息提示的红点
         */
        if(hasMsg){
            ImageView circleView=new ImageView(context);
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(30,30);
            param.addRule(RelativeLayout.RIGHT_OF,iconImageView.getId());
            // TODO: 2017/8/2 需要重新修改消息的提示图标
            circleView.setBackgroundResource(R.mipmap.ic_launcher);
            circleView.setLayoutParams(param);
            childView.addView(circleView);
        }
        RelativeLayout.LayoutParams childParam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(childParam);
        rootView.addView(childView);
    }

    /**
     * 选中的tab监听
     */
    private void tabSelected(){
        if (onTabSelectedListener != null) {
            onTabSelectedListener.onTabSelected(this);
        }
    }

    /**
     * 得到rootView
     * @return
     */
    public LinearLayout getRootView(){
        return rootView;
    }

    public int getIndex() {
        return index;
    }

    public String getText(){
         return text;
     }

     public void setTabSelected(boolean isSelected){
         if (this.isSelected == isSelected) {
             return;
         }
         iconImageView.setImageResource(isSelected ? selectedIconImge : iconImage);
         textTextView.setTextColor(isSelected ? selectedTextColor : textColor);
         this.isSelected = isSelected;
     }
    /**
     * tab的选中监听
     */
    public interface OnTabSelectedListener{
        void onTabSelected(Tab tab);
    }

    /**
     * 对外提供选中的接口回掉事件
     * @param onTabSelectedListener
     */
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }
}
