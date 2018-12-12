package com.love.jax.activity;


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



}
