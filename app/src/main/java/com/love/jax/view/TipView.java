package com.love.jax.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.love.jax.R;

import java.util.List;

/**
 * Created by linchunshu on 2019年7月20日
 * 待办上下滚动轮播
 */

public class TipView extends FrameLayout {

    private OnLooperPositionListener onLooperPositionListener;

    /**
     * 动画间隔
     */
    private static final int ANIM_DELAYED_MILLIONS = 3 * 1000;
    /**
     * 动画持续时长
     */
    private static final int ANIM_DURATION = 1 * 1000;
    /**
     * 默认字体颜色
     */
    private static final String DEFAULT_TEXT_COLOR = "#2F4F4F";
    /**
     * 默认字体大小  dp
     */
    private static final int DEFAULT_TEXT_SIZE = 13;
    private Animation anim_out, anim_in;
    private View tv_tip_out, tv_tip_in;
    private Context mContext;

    static  int  coutn=0;

    /**
     * 循环播放的消息
     */
    private List<String> tipList;
    /**
     * 当前轮播到的消息索引
     */
    private int curTipIndex = 0;
    private long lastTimeMillis;
//    private Drawable head_boy, head_girl;

    public TipView(Context context) {
        this(context, null);
    }

    public TipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initTipFrame();
        initAnimation();
    }

    private void initTipFrame() {
//        head_boy = loadDrawable(R.drawable.ic_launcher_logo);
//        head_girl = loadDrawable(R.drawable.ic_launcher_logo);
//        tv_tip_out = newTextView();
//        tv_tip_in = newTextView();
        tv_tip_out = newRLView();
        tv_tip_in = newRLView();
        addView(tv_tip_in);
        addView(tv_tip_out);
    }

    /**
     * 设置要循环播放的信息
     *
     * @param tipList
     */
    public void setTipList(List<String> tipList) {
        this.tipList = tipList;
        curTipIndex = 0;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();
    }

    private void initAnimation() {
        anim_out = newAnimation(0, -1);
        anim_in = newAnimation(1, 0);
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();
            }
        });
    }

    //更新显示文案和启动动画
    public void updateTipAndPlayAnimation() {
        if (curTipIndex % 2 == 0) {
            updateTip(tv_tip_out);
            tv_tip_in.startAnimation(anim_out);
            tv_tip_out.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_in);
        } else {
            updateTip(tv_tip_in);
            tv_tip_out.startAnimation(anim_out);
            tv_tip_in.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_out);
        }
    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000) {
            return;
        }
        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }

//    private void updateTip(TextView tipView) {
////        if (new Random().nextBoolean()) {
////            tipView.setCompoundDrawables(head_boy, null, null, null);
////        } else {
////            tipView.setCompoundDrawables(head_girl, null, null, null);
////        }
//        String tip = getNextTip();
//        if (!TextUtils.isEmpty(tip)) {
//            if (tip.contains(":")) {
//                String[] tips = tip.split(":");
//                if (tips.length == 2) {
//                    SpannableString spanStr = new SpannableString(tips[0] + "   " + tips[1]);
//                    spanStr.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_1F2845)), 0, tips[0].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                    spanStr.setSpan(new RelativeSizeSpan(0.9f), tips[0].length(), spanStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                    spanStr.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_8A91A4)), tips[0].length(), spanStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                    tipView.setText(spanStr);
//                }
//            } else {
//                tipView.setText(tip);
//            }
//        }
//    }

    private void updateTip(View tipView) {
//        if (new Random().nextBoolean()) {
//            tipView.setCompoundDrawables(head_boy, null, null, null);
//        } else {
//            tipView.setCompoundDrawables(head_girl, null, null, null);
//        }
        String tip = getNextTip();
        if (!TextUtils.isEmpty(tip)) {
            if (tip.contains(":")) {
                String[] tips = tip.split(":");
                if (tips.length == 2) {
                    SpannableString spanStr = new SpannableString(tips[0] + "   " + tips[1]);
                    spanStr.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_1F2845)), 0, tips[0].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanStr.setSpan(new RelativeSizeSpan(0.9f), tips[0].length(), spanStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanStr.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_8A91A4)), tips[0].length(), spanStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                }
            } else {
              ViewHolder  mViewHolder = new ViewHolder(tipView);
              mViewHolder.tvTitle.setText(tip);
              mViewHolder.tvState.setText("待定");
              if (curTipIndex%2==0){
                  mViewHolder.tvState.setVisibility(GONE);
                  mViewHolder.tvHospital.setVisibility(GONE);
                  mViewHolder.tvTime.setVisibility(GONE);
              }else {
                  mViewHolder.tvState.setVisibility(VISIBLE);
                  mViewHolder.tvHospital.setVisibility(VISIBLE);
                  mViewHolder.tvTime.setVisibility(VISIBLE);
              }
//              mViewHolder.tvPeople.setText("待定");
//              mViewHolder.tvHospital.setText("待定");
//              mViewHolder.tvTime.setText("待定");




            }
        }
    }

    public static class ViewHolder  {
        TextView tvTitle;
        TextView tvState;
        TextView tvPeople;
        TextView tvHospital;
        TextView tvTime;

        public ViewHolder(View itemView) {
            tvTitle = itemView.findViewById(R.id.tv_birden_content_title);
            tvState = itemView.findViewById(R.id.tv_birden_content_state);
            tvPeople= itemView.findViewById(R.id.tv_birden_content_people);
            tvHospital= itemView.findViewById(R.id.tv_birden_content_hospital);
            tvTime= itemView.findViewById(R.id.tv_birden_content_time);

        }
    }

    /**
     * 获取下一条消息
     *
     * @return
     */
    private String getNextTip() {
        if (isListEmpty(tipList)) {
            return null;
        }
        int curIndex = curTipIndex++ % tipList.size();
        if (onLooperPositionListener != null) {
            onLooperPositionListener.onLooperPosition(curIndex);
        }
        return tipList.get(curIndex);
    }

    private TextView newTextView() {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setCompoundDrawablePadding(10);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.parseColor(DEFAULT_TEXT_COLOR));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE);
        return textView;
    }

    private View newRLView() {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.birthday_dental_item_list, this, false);
        return view;
    }

    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromYValue, Animation.RELATIVE_TO_SELF, toYValue);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    /**
     * 将资源图片转换为Drawable对象
     *
     * @param ResId
     * @return
     */
    private Drawable loadDrawable(int ResId) {
        Drawable drawable = getResources().getDrawable(ResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth() - 10, drawable.getMinimumHeight() - 10);
        return drawable;
    }

    /**
     * list是否为空
     *
     * @param list
     * @return true 如果是空
     */
    public static boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }


    public interface OnLooperPositionListener {
        void onLooperPosition(int position);
    }

    public void setOnLooperPositionListener(OnLooperPositionListener onLooperPositionListener) {
        this.onLooperPositionListener = onLooperPositionListener;
    }
}
