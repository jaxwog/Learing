package com.love.jax.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.database.HistoryListData;
import com.love.jax.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * com.love.jax.view
 * Created by jax on 2018/11/13 11:17
 * TODO: 流式历史记录布局
 */
public class HistoryFlowLayout extends ViewGroup{

    public HistoryFlowLayout(Context context) {
        this(context, null);
    }

    public HistoryFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistoryFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mListData = new HistoryListData(context);
    }

    // 主要字段维护,之后可以做成适配器形式加上观察者，就可以使用notify更新
    private List<String> wordList = new ArrayList<>();
    private int childViewHightLastLine =0;
    private HistoryListData mListData;
    // 每行View数量
    private List<Integer> mLineViews = new ArrayList<>();
    // 每行高度
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    OnItemClickListener mOnItemClickListener;
    private boolean ivFireShow = false;//是否带图标
    private boolean variable = false;//是否可变

    public void setIvShow(boolean flag){
        ivFireShow = flag;
    }

    public void setVariable(boolean flag){
        variable = flag;
    }



    /**
     * 测量所有子View大小,确定ViewGroup的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLineViews.clear();
        mLineHeight.clear();

        //获取测量的模式和尺寸大小
        int widthMode =MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()-getPaddingRight();
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec)+getPaddingTop()+getPaddingBottom();

        //记录ViewGroup真实的测量宽高
        int viewGroupWidth = 0-getPaddingLeft()-getPaddingRight();
        int viewGroupHeight = getPaddingTop()+getPaddingBottom();

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            viewGroupWidth = widthSize;
            viewGroupHeight = heightSize;
        } else {
            //当前所占的宽高
            int currentLineWidth = 0;
            int currentLineHeight = 0;

            int lineViewCount = 0;
            for (int i = 0; i < getChildCount(); i++) {
                final View childView = getChildAt(i);

                if(childView != null){

                    //对子View进行测量
                    measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
                    int childViewWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                    int childViewHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                    if (currentLineWidth + childViewWidth > widthSize) {
                        //当前行宽+子View+左右外边距>ViewGroup的宽度,换行
                        currentLineHeight = Math.max(currentLineHeight, childViewHeight);
                        viewGroupHeight += currentLineHeight;
                        //添加行高
                        mLineHeight.add(currentLineHeight);
                        //添加行对象
                        mLineViews.add(lineViewCount);


                        currentLineWidth = childViewWidth;
                        lineViewCount = 1;
                    } else {
                        lineViewCount++;
                        //当前行宽+子View+左右外边距<=ViewGroup的宽度,不换行
                        currentLineWidth += childViewWidth;
                        currentLineHeight = Math.max(currentLineHeight, childViewHeight);
                        //添加行对象里的子View
                    }
                    if (i == getChildCount() - 1) {
                        //添加行高
                        mLineHeight.add(currentLineHeight);
                        //添加行对象
                        mLineViews.add(lineViewCount);

                        currentLineHeight = Math.max(currentLineHeight, childViewHeight);
                        viewGroupHeight += currentLineHeight;
                        childViewHightLastLine = childViewHeight;
                    }
                }
            }
        }

        //如果是两行，显示包裹的高度
        if (mLineViews.size()>2){
            viewGroupHeight -= childViewHightLastLine;
        }
        setMeasuredDimension(widthSize, viewGroupHeight);
    }

    /**
     * 设置ViewGroup里子View的具体位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (mLineViews.size()>2){

            wordList.remove(wordList.size()-1);
            resetViews();
            measure(getMeasuredWidth(),getMeasuredHeight());
//            return;
        }

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int wordIndex = 0 ;
        int mlinViewSize = mLineViews.size();
        if (mLineViews.size()>2){
            mlinViewSize = 2;
        }
        for (int i = 0; i < mlinViewSize; i++) {

            for (int j = 0; j < mLineViews.get(i); j++) {
                View view = getChildAt(wordIndex);
                if(view == null){
                    continue;
                }
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                int vl = left + marginLayoutParams.leftMargin;
                int vt = top + marginLayoutParams.topMargin;
                int vr = vl + view.getMeasuredWidth();
                int vb = vt + view.getMeasuredHeight();
                view.layout(vl, vt, vr, vb);

                left += view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                wordIndex ++;
            }
            left = getPaddingLeft();
            top += mLineHeight.get(i);
        }

    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(HistoryFlowLayout parent, String word);
    }

    public void addWord(String words){
        if (words.trim().isEmpty()){
            return;
        }
        if (wordList.contains(words)){
            wordList.remove(words);
        }
        wordList.add(0,words);
        resetViews();
    }

    public void addAllWord(List<String> list){
        if (list.isEmpty()){
            return;
        }
        wordList.addAll(list);
        resetViews();


    }

    public void deleteWord(int index){
        wordList.remove(index);
        resetViews();
    }

    public void deleteAllWord(){
        wordList.clear();
        resetViews();
    }

    private void resetViews(){
        removeAllViews();
        for(int i = 0 ;i< wordList.size();i++){
            if (i==0){
                addView(createView(this,i,wordList.get(i),true));
            }else {
                addView(createView(this, i, wordList.get(i), false));
            }
        }
    }

    public View createView(ViewGroup viewGroup,final int index, final String word,boolean flag){
        final View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_search, viewGroup, false);
        TextView tv = view.findViewById(R.id.tv_history_item);
        tv.setText(showWordLenth10(word));
        if (flag && ivFireShow){
            view.findViewById(R.id.iv_fire).setVisibility(View.VISIBLE);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deleteWord(index);
                Logger.i("jax", "onClick: "+index+"  ,context:"+wordList.get(index));
                if (variable){
                    addWord(word);
                }
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(HistoryFlowLayout.this,word);
                }
            }
        });
        return view;
    }

    private String showWordLenth10(String word){
        if (word.length()>10){
            word = word.substring(0,10)+"...";
        }
        return word;
    }

    //保存数据到本地
    public boolean saveToSdCard(String key){
        if (variable && !key.isEmpty()){
            // wordList 保存到本地
            mListData.setDataList(key,wordList);
            for (int i = 0; i < wordList.size(); i++) {
                Logger.i("jax","保存的数据信息"+i+"=="+wordList.get(i));
            }
            return true;
        }
        return false;
    }

    public List<String> readFormSdCard(String key){
        if (variable && !key.isEmpty()){
            // wordList 从本地读取
            List<String> list = mListData.getDataList(key);
            return list;
        }
        return null;
    }

    public void deleteFromSdCard(String key){
        if (variable && !key.isEmpty()){
            mListData.delDataList(key);
        }
    }



    /**
     * 指定ViewGroup的LayoutParams
     *
     * @param attrs
     * @return
     */

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
