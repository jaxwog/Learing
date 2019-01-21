package com.love.jax.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.love.jax.R;
import com.love.jax.activity.fragment.TabLayoutBottomActivity;
import com.love.jax.activity.fragment.TabLayoutTopActivity;
import com.love.jax.activity.materialdesign.CardViewActivity;
import com.love.jax.activity.materialdesign.CollapseToolbarActivity;
import com.love.jax.activity.materialdesign.DrawerLayoutActivity;
import com.love.jax.activity.materialdesign.FabRecAnimatorActivity;
import com.love.jax.activity.materialdesign.FabRecAnimatorActivity2;
import com.love.jax.activity.materialdesign.FloatingActionButtonActivity;
import com.love.jax.activity.materialdesign.NavigationActivity;
import com.love.jax.activity.materialdesign.PaletteActivity;
import com.love.jax.activity.materialdesign.ParallelSpaceActivity;
import com.love.jax.activity.materialdesign.SnackbarActivity;
import com.love.jax.activity.materialdesign.TabLayoutAppBarActivity;
import com.love.jax.activity.materialdesign.TextInputActivity;
import com.love.jax.activity.materialdesign.ToolbarActivity;
import com.love.jax.activity.materialdesign.TranslucentActivity;
import com.love.jax.activity.materialdesign.TranslucentTopActivity;
import com.love.jax.activity.materialdesign.TransparentToolbarActivity;
import com.love.jax.activity.recycleview.HeaderActivity;
import com.love.jax.activity.recycleview.RcDividerActivity;
import com.love.jax.activity.recycleview.RcSimpleActivity;
import com.love.jax.activity.recycleview.RcTouchActivity;
import com.love.jax.activity.recycleview.ThemeActivity;
import com.love.jax.activity.tkjobs.OrderActivity;
import com.love.jax.adapter.InFuncAdapter;
import com.love.jax.bean.LettersEntity;
import com.love.jax.bean.OrderEntity;
import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.ListUtils;
import com.love.jax.utils.Logger;
import com.love.jax.utils.StringShowUtils;
import com.love.jax.view.HistoryFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页入口
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rec_but)
    RecyclerView mRecFunction;
    @BindView(R.id.et_search)
    EditText mEditText;
    @BindView(R.id.iv_delect_tv)
    ImageView mViewdel;//搜索框内容删除
    @BindView(R.id.iv_back)
    ImageView mViewBack;//返回按键
    @BindView(R.id.item_his_search)
    LinearLayout mLayoutHis;
    @BindView(R.id.iv_his_delhis)
    ImageView delhis;
    //历史搜索
    @BindView(R.id.fl_search_history)
    HistoryFlowLayout mHistoryFlowLayoutHis;

    private static final String TAG = "MainActivity";

    List<String> mStringList = new ArrayList<>();
    List<String> mHislist = new ArrayList<>();
    //将文字转换为拼音
    List<LettersEntity> mEntityList;
    private String mContent;

    private Bundle sBundle = new Bundle();
    Gson mGson = new Gson();

    InFuncAdapter mInFuncAdapter;
    private String[] mStrings = new String[]{
            "屏幕适配","商品订单","主题适配","列表简单使用","列表间隔线","列表头尾","列表交互动画","侧滑效果一","侧滑效果二"
            ,"底部弹窗","文本输入","标题栏","顶部透明","颜色获取","顶部标题","底部导航","顶部沉浸","底部沉浸","卡片布局","悬浮按钮"
            ,"隐藏交互动画","隐藏动画2","隐藏动画3","平行空间","导航折叠"
//            ,"南辕北辙","得陇望蜀","明修栈道","暗度陈仓","叶公好龙","无理取闹","风风火火","恍恍惚惚","德玛西亚"
//            ,"剑圣偷塔","艾欧尼亚","暗影之道","五光十色","诺克萨斯","德邦总管","加里奥","凯南","武器大师"
//            ,"金属大师","盖伦","德莱文","卢锡安","战争女神","黑暗骑士","斯嘉丽","黑寡妇","泰勒斯威夫特"
    };

    /**
     * 跳转到功能指定页面
     * @param position 位置信息
     */
    private void skipFuncActivity(int position) {
        sBundle.clear();
        String tempStr = StringShowUtils.delTag(mInFuncAdapter.getList().get(position).getTitle());
        switch (tempStr){
            case "屏幕适配":
                sBundle.putString(ConfigSet.INTENT_STRING,"屏幕适配");
                jumpToActivity(ScreenAdaptationActivity.class, sBundle);
                if (mBundle==null){
                    Logger.i(TAG,"数据为空啊~");
                }
                break;
            case "商品订单":
                //传递过去json数据
                OrderEntity entity = new OrderEntity("超声波洁牙套餐","20181115","高端款","400",9);
                sBundle.putString(ConfigSet.INTENT_STRING, mGson.toJson(entity));
                jumpToActivity(OrderActivity.class, sBundle);
                break;
            case "列表简单使用":
                sBundle.putString(ConfigSet.INTENT_STRING,"列表简单使用");
                jumpToActivity(RcSimpleActivity.class, sBundle);
                break;
            case "主题适配":
                sBundle.putString(ConfigSet.INTENT_STRING,"主题适配");
                jumpToActivity(ThemeActivity.class, sBundle);
                break;
            case "列表间隔线":
                sBundle.putString(ConfigSet.INTENT_STRING,"列表间隔线");
                jumpToActivity(RcDividerActivity.class, sBundle);
                break;
            case "列表头尾":
                sBundle.putString(ConfigSet.INTENT_STRING,"列表头尾");
                jumpToActivity(HeaderActivity.class, sBundle);
                break;
            case "列表交互动画":
                sBundle.putString(ConfigSet.INTENT_STRING,"列表交互动画");
                jumpToActivity(RcTouchActivity.class, sBundle);
                break;
            case "侧滑效果一":
                sBundle.putString(ConfigSet.INTENT_STRING,"侧滑效果一");
                jumpToActivity(DrawerLayoutActivity.class, sBundle);
                break;
            case "侧滑效果二":
                sBundle.putString(ConfigSet.INTENT_STRING,"侧滑效果二");
                jumpToActivity(NavigationActivity.class, sBundle);
                break;
            case "底部弹窗":
                sBundle.putString(ConfigSet.INTENT_STRING,"底部弹窗");
                jumpToActivity(SnackbarActivity.class, sBundle);
                break;
            case "文本输入":
                sBundle.putString(ConfigSet.INTENT_STRING,"文本输入");
                jumpToActivity(TextInputActivity.class, sBundle);
                break;
            case "标题栏":
                sBundle.putString(ConfigSet.INTENT_STRING,"标题栏");
                jumpToActivity(ToolbarActivity.class, sBundle);
                break;
            case "顶部透明":
                sBundle.putString(ConfigSet.INTENT_STRING,"顶部透明");
                jumpToActivity(TransparentToolbarActivity.class, sBundle);
                break;
            case "颜色获取":
                sBundle.putString(ConfigSet.INTENT_STRING,"颜色获取");
                jumpToActivity(PaletteActivity.class, sBundle);
                break;
            case "顶部标题":
                sBundle.putString(ConfigSet.INTENT_STRING,"顶部标题");
                jumpToActivity(TabLayoutTopActivity.class, sBundle);
                break;
            case "底部导航":
                sBundle.putString(ConfigSet.INTENT_STRING,"底部导航");
                jumpToActivity(TabLayoutBottomActivity.class, sBundle);
                break;
            case "顶部沉浸":
                sBundle.putString(ConfigSet.INTENT_STRING,"顶部沉浸");
                jumpToActivity(TranslucentTopActivity.class, sBundle);
                break;
            case "底部沉浸":
                sBundle.putString(ConfigSet.INTENT_STRING,"底部沉浸");
                jumpToActivity(TranslucentActivity.class, sBundle);
                break;
            case "卡片布局":
                sBundle.putString(ConfigSet.INTENT_STRING,"卡片布局");
                jumpToActivity(CardViewActivity.class, sBundle);
                break;
            case "悬浮按钮":
                sBundle.putString(ConfigSet.INTENT_STRING,"悬浮按钮");
                jumpToActivity(FloatingActionButtonActivity.class, sBundle);
                break;
            case "隐藏交互动画":
                sBundle.putString(ConfigSet.INTENT_STRING,"隐藏交互动画");
                jumpToActivity(FabRecAnimatorActivity.class, sBundle);
                break;
            case "隐藏动画2":
                sBundle.putString(ConfigSet.INTENT_STRING,"隐藏动画2");
                jumpToActivity(FabRecAnimatorActivity2.class, sBundle);
                break;
            case "隐藏动画3":
                sBundle.putString(ConfigSet.INTENT_STRING,"隐藏动画3");
                jumpToActivity(TabLayoutAppBarActivity.class, sBundle);
                break;
            case "平行空间":
                sBundle.putString(ConfigSet.INTENT_STRING,"平行空间");
                jumpToActivity(ParallelSpaceActivity.class, sBundle);
                break;
            case "导航折叠":
                sBundle.putString(ConfigSet.INTENT_STRING,"导航折叠");
                jumpToActivity(CollapseToolbarActivity.class, sBundle);
                break;
            default:
                break;

        }
    }



    @Override
    protected void initView() {
        mInFuncAdapter = new InFuncAdapter(this);
        mRecFunction.setLayoutManager(new GridLayoutManager(
                this, 4));
        mRecFunction.setAdapter(mInFuncAdapter);
        mInFuncAdapter.setDatas(mEntityList);
        mHistoryFlowLayoutHis.setVariable(true);//控制顺序可变
    }
    @Override
    protected void initData() {
        mStringList.addAll(Arrays.asList(mStrings));
        mEntityList = ListUtils.addLetter(mStringList);
        for (int i = 0; i < mEntityList.size(); i++) {
            Logger.e(TAG,mEntityList.get(i).getTitle());
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mHistoryFlowLayoutHis.deleteAllWord();
        mHislist = mHistoryFlowLayoutHis.readFormSdCard(ConfigSet.HIS_KEY);
        for (int i = 0; i < mHislist.size(); i++) {
            Logger.i(TAG,"从新读取数据内容"+i+"="+mHislist.get(i));
        }
        if (mHislist.size()>14){
            //避免主站返回过多数据，造成界面卡顿
            mHislist = mHislist.subList(0,14);
        }
        if (ListUtils.isEmpty(mHislist)){
            mLayoutHis.setVisibility(View.GONE);
        }else {
            mLayoutHis.setVisibility(View.VISIBLE);
        }
        mHistoryFlowLayoutHis.addAllWord(mHislist);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    //添加到历史记录中(点击右侧搜索按钮和键盘搜索按钮)
    private void addWordToHis(){
        if (mLayoutHis.getVisibility()==View.GONE) {
            mLayoutHis.setVisibility(View.VISIBLE);
        }
        mHistoryFlowLayoutHis.addWord(mContent);



    }

    @SuppressLint("NewApi")
    @Override
    protected void initJestListener() {
        mInFuncAdapter.setOnItemSelectListener(new InFuncAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                skipFuncActivity(position);
            }
        });

        mHistoryFlowLayoutHis.setOnItemClickListener(new HistoryFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(HistoryFlowLayout parent, String word) {
                //点击后内容出现在搜索框中
                mEditText.setText(word);
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()){
                    mViewdel.setVisibility(View.VISIBLE);
                }else {
                    mViewdel.setVisibility(View.GONE);
                }
                Logger.i(TAG,
                        "afterTextChanged:s="+s);
                mEditText.setSelection(s.length());
                skipSearchList();
            }
        });

        //监听列表滑动效果，进行事件处理
        mRecFunction.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int
                    oldScrollY) {
            }
        });

          //NestedScrollView判断滑动到底部条件
//          mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
//               if (nestedScrollView.getScrollY() +nestedScrollView.getHeight() - nestedScrollView.getPaddingTop() -nestedScrollView.getPaddingBottom()
//                       == nestedScrollView.getChildAt(0).getHeight()){
//               }
//                Logger.i(TAG,"左侧条件="+(nestedScrollView.getScrollY() +nestedScrollView.getHeight() - nestedScrollView.getPaddingTop() -nestedScrollView.getPaddingBottom()
//                ));
//                Logger.i(TAG,"右侧条件="+nestedScrollView.getChildAt(0).getHeight());
//            }
//        });

    }

    @OnClick({R.id.tv_search,R.id.iv_delect_tv,R.id.iv_back,R.id.iv_his_delhis})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_search:
                skipSearchList();
                addWordToHis();
                break;
            case R.id.iv_delect_tv:
                mEditText.setText("");
                break;
            case R.id.iv_back:
                for (int i = 0; i < mEntityList.size(); i++) {
                mEntityList.get(i).setTitle(StringShowUtils.delTag(mEntityList.get(i).getTitle()));
                }
                mInFuncAdapter.setDatas(mEntityList);
                break;
            case R.id.iv_his_delhis:
                mHistoryFlowLayoutHis.deleteAllWord();
                mHistoryFlowLayoutHis.deleteFromSdCard(ConfigSet.HIS_KEY);
                mLayoutHis.setVisibility(View.GONE);
            break;
        }

    }




    private void skipSearchList(){
        mContent = mEditText.getText().toString();
//        mStringList = StringShowUtils.hitTarget(mStringList,mContent);
//        mInFuncAdapter.setDatas(mStringList);
        List<LettersEntity> list = StringShowUtils.hitTargetLet(mEntityList,mContent);
        mInFuncAdapter.setDatas(list);

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //监听键盘搜索按钮
        if (KeyEvent.KEYCODE_ENTER==event.getKeyCode()&& KeyEvent.ACTION_DOWN==event.getAction()){
            skipSearchList();
            addWordToHis();
            return true;
        }else if (KeyEvent.KEYCODE_BACK==event.getKeyCode() && KeyEvent.ACTION_DOWN==event.getAction()){
            for (int i = 0; i < mEntityList.size(); i++) {
                mEntityList.get(i).setTitle(StringShowUtils.delTag(mEntityList.get(i).getTitle()));
            }
            mInFuncAdapter.setDatas(mEntityList);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void finish() {
//        mHistoryFlowLayoutHis.saveToSdCard(ConfigSet.HIS_KEY);
        super.finish();
    }

    @Override
    protected void onPause() {
        mHistoryFlowLayoutHis.saveToSdCard(ConfigSet.HIS_KEY);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
