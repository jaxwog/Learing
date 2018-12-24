package com.love.jax.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.love.jax.R;


/**
 * Created by jf on 2018/6/20.
 * 根据产品要求设计的简单计数器
 * 1 动态调整计数范围
 * 2 长按连续计数
 */

public class CounterView extends FrameLayout{

    TextView btnReduce;
    TextView btnAdd;
    EditText viewEdit;

    int colorBtnAct;
    int colorBtnNeg;

    int maxCount=100;
    int minCount=1;

    private OnCountChangeListener onCountChangeListener;

    public CounterView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CounterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CounterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public EditText getViewEdit(){
        return viewEdit;
    }

    private void init(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.view_counter2,this,true);

        btnReduce=findViewById(R.id.btn_reduce);
        btnAdd=findViewById(R.id.btn_add);
        viewEdit=findViewById(R.id.view_edit);

        colorBtnAct= Color.parseColor("#4C5979");
        colorBtnNeg=Color.parseColor("#8A91A4");


        btnReduce.setOnTouchListener(new GestureTouchListener() {
            @Override
            public void onLongPressing() {
                viewEdit.setText(String.valueOf(getCount()-1));
                moveCursorToEnd();
            }

            @Override
            public void onClick() {
                viewEdit.setText(String.valueOf(getCount()-1));
                moveCursorToEnd();
            }
        });

        btnAdd.setOnTouchListener(new GestureTouchListener() {
            @Override
            public void onLongPressing() {
                viewEdit.setText(String.valueOf(getCount()+1));
                moveCursorToEnd();
            }

            @Override
            public void onClick() {
                viewEdit.setText(String.valueOf(getCount()+1));
                moveCursorToEnd();
            }
        });


        viewEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("haha","is active:"+SystemUI.isViewInputActive(viewEdit));
                moveCursorToEnd();
            }
        });


        viewEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    return;
                }

                int num=Integer.parseInt(s.toString());

                if (num==maxCount){
                    setBtnSt(2);
                }else if (num==minCount){
                    setBtnSt(0);
                }else{
                    setBtnSt(1);
                }

                if (num>=minCount && num<=maxCount){
                    if (onCountChangeListener!=null){
                        onCountChangeListener.onCountChange(num);
                    }
                }

                if (num>maxCount){
                    viewEdit.setText(String.valueOf(maxCount));
                }else if (num<minCount){
                    viewEdit.setText(String.valueOf(minCount));
                }
                moveCursorToEnd();
            }
        });


        viewEdit.setHint(String.valueOf(minCount));
        viewEdit.setText(String.valueOf(minCount));


    }

    public void setMaxCount(int maxCount){
        this.maxCount=maxCount;
        if (getCount()>maxCount){
            setCount(maxCount);
        }
    }

    public void setMinCount(int minCount){
        this.minCount=minCount;
        if (getCount()<minCount){
            setCount(minCount);
        }
    }

    public void setCount(int count){
        viewEdit.setText(String.valueOf(count));
    }

    public void setOnCountChangeListener(OnCountChangeListener onCountChangeListener){
        this.onCountChangeListener=onCountChangeListener;
    }

    public OnCountChangeListener getOnCountChangeListener() {
        return onCountChangeListener;
    }

    /**
     * 获取计数结果
     * @return
     */
    public int getResult(){
        String content=viewEdit.getText().toString();
        if (TextUtils.isEmpty(content)){
            content=viewEdit.getHint().toString();
        }
        if (TextUtils.isEmpty(content)){
            return minCount;
        }
        return Integer.valueOf(content);
    }

    /**
     * 光标移至末尾
     */
    void moveCursorToEnd(){
        String content=viewEdit.getText().toString();
        if (TextUtils.isEmpty(content)) return;
        viewEdit.setSelection(content.length());
    }

    private int getCount(){
        String content=viewEdit.getText().toString();
        if (TextUtils.isEmpty(content)) return 0;
        return Integer.parseInt(viewEdit.getText().toString());
    }

    void setBtnSt(int st){
        switch (st){
            case 0:
                btnReduce.setTextColor(colorBtnNeg);
//                btnReduce.setBackgroundColor(colorBtnNeg);
                btnAdd.setTextColor(colorBtnAct);
                break;
            case 1:
                btnReduce.setTextColor(colorBtnAct);
//                btnReduce.setBackgroundColor(colorBtnAct);
                btnAdd.setTextColor(colorBtnAct);
                break;
            case 2:
                btnReduce.setTextColor(colorBtnAct);
//                btnReduce.setBackgroundColor(colorBtnAct);
                btnAdd.setTextColor(colorBtnNeg);
                break;
        }
    }


    public interface OnCountChangeListener{

        void onCountChange(int count);
    }


    /**
     * 长按手势
     */
    public abstract static class GestureTouchListener implements OnTouchListener {

        public static final int LONG_PRESS=1;
        public static final int CLICK=0;
        public static final int LONG_TIMEOUT= 500;
        public static final int LONG_REPEAT_DELAY=100;

        int mode=-1;

        GestureHandler handler=new GestureHandler();

        public abstract void onLongPressing();

        public abstract void onClick();

        private boolean hasLongPressed(){
            return mode== LONG_PRESS;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mode=-1;
                    handler.sendEmptyMessageDelayed(LONG_PRESS,LONG_TIMEOUT);
                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    handler.removeMessages(LONG_PRESS);
                    if (!hasLongPressed()){
                        handler.sendEmptyMessage(CLICK);
                    }
                    break;
            }
            return true;
        }


        public class GestureHandler extends Handler {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case LONG_PRESS:
                        mode=LONG_PRESS;
                        onLongPressing();
                        sendEmptyMessageDelayed(LONG_PRESS,LONG_REPEAT_DELAY);
                        break;
                    case CLICK:
                        mode=CLICK;
                        onClick();
                        break;
                }
            }
        }
    }



}
