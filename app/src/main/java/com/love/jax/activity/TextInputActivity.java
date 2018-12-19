package com.love.jax.activity;


import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.love.jax.R;

import butterknife.BindView;

/**
 * todo 文本输入框，提示用户输入信息
 * app:hintAnimationEnabled="true" ： 是否获得焦点的时候hint提示问题会动画地移动上去。
 * app:errorEnabled="true" 是都打开错误提示。
 * 要使用错误提示还得自己定义规则。
 * app:counterTextAppearance="" 可以自己修改计数的文字样式。
 * app:counterOverflowTextAppearance="" 超出字数范围后显示的警告的文字样式
 */
public class TextInputActivity extends BaseActivity {


    @BindView(R.id.textInputLayout)
    TextInputLayout mInputLayout;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
//检测长度应该低于6位数
        mInputLayout.getEditText().addTextChangedListener(new MinLengthTextWatcher(mInputLayout, "长度应低于6位数!"));

        //开启计数
        mInputLayout.setCounterEnabled(true);
        mInputLayout.setCounterMaxLength(6);//最大输入限制数
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_text_input;
    }

    class MinLengthTextWatcher implements TextWatcher {

        private String errorStr;
        private TextInputLayout textInputLayout;

        public MinLengthTextWatcher(TextInputLayout textInputLayout, String errorStr) {
            // TODO Auto-generated constructor stub
            this.textInputLayout = textInputLayout;
            this.errorStr = errorStr;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // 文字变化前回调

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // 改变的时候回调

        }

        @Override
        public void afterTextChanged(Editable s) {
            // 文字改变后回调
            if(textInputLayout.getEditText().getText().toString().length()<=6){
                textInputLayout.setErrorEnabled(false);
            }else{
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errorStr);
            }
        }

    }

    //颜色渐变

    /**
     * @see android.support.design.widget.CollapsingTextHelper 三个矩形区域测量绘制
     * @param color1
     * @param color2
     * @param ratio
     * @return
     */
    private static int blendColors(int color1, int color2, float ratio) {
        float inverseRatio = 1.0F - ratio;
        float a = (float) Color.alpha(color1) * inverseRatio + (float)Color.alpha(color2) * ratio;
        float r = (float)Color.red(color1) * inverseRatio + (float)Color.red(color2) * ratio;
        float g = (float)Color.green(color1) * inverseRatio + (float)Color.green(color2) * ratio;
        float b = (float)Color.blue(color1) * inverseRatio + (float)Color.blue(color2) * ratio;
        return Color.argb((int)a, (int)r, (int)g, (int)b);
    }



}
