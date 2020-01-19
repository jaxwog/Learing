package com.love.jax.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.love.jax.JaxApplication;
import com.love.jax.R;
import com.love.jax.activity.animation.AnimaFramework1Activity;
import com.love.jax.activity.animation.AnimatorSetActivity;
import com.love.jax.activity.animation.ArrtAnimatorActivity;
import com.love.jax.activity.animation.CustomViewGroupActivity;
import com.love.jax.activity.animation.OptionsTransitionActivity;
import com.love.jax.activity.animation.ParallaxSplashActivity;
import com.love.jax.activity.animation.RevealEffectActivity;
import com.love.jax.activity.animation.SvgVectorActivity;
import com.love.jax.activity.animation.TipViewActivity;
import com.love.jax.activity.design.DbFrameworkActivity;
import com.love.jax.activity.design.DbUpdateActivity;
import com.love.jax.activity.design.FastJsonActivity;
import com.love.jax.activity.design.ImageLoaderActivity;
import com.love.jax.activity.design.MvpActivity;
import com.love.jax.activity.design.VollyActivity;
import com.love.jax.activity.design.XUtilsActivity;
import com.love.jax.activity.design.mvvm.MvvmListActivity;
import com.love.jax.activity.design.mvvm.MvvmObjectActivity;
import com.love.jax.activity.events.CanvasBasisActivity;
import com.love.jax.activity.events.CanvasSeniorActivity;
import com.love.jax.activity.events.MaskFilterActivity;
import com.love.jax.activity.events.PaintPathActivity;
import com.love.jax.activity.events.PaintProgressActivity;
import com.love.jax.activity.events.PathAnimaActivity;
import com.love.jax.activity.events.PathMeasureActivity;
import com.love.jax.activity.events.SearchIconActivity;
import com.love.jax.activity.events.SearchView2Activity;
import com.love.jax.activity.events.SeniorRendActivity;
import com.love.jax.activity.events.SlideMenuActivity;
import com.love.jax.activity.events.SlideMenuItemActivity;
import com.love.jax.activity.events.TouchEventSLActivity;
import com.love.jax.activity.events.ViewPagerActivity;
import com.love.jax.activity.events.WaveViewActivity;
import com.love.jax.activity.fragment.TabLayoutBottomActivity;
import com.love.jax.activity.fragment.TabLayoutTopActivity;
import com.love.jax.activity.materialdesign.CardViewActivity;
import com.love.jax.activity.materialdesign.CollapseToolbarActivity;
import com.love.jax.activity.materialdesign.CustomerBehavior1Activity;
import com.love.jax.activity.materialdesign.CustomerBehavior2Activity;
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
import com.love.jax.activity.performance.AopActivity;
import com.love.jax.activity.performance.LoadMoreActivity;
import com.love.jax.activity.recycleview.CityListActivity;
import com.love.jax.activity.recycleview.HeaderActivity;
import com.love.jax.activity.recycleview.RcDividerActivity;
import com.love.jax.activity.recycleview.RcSimpleActivity;
import com.love.jax.activity.recycleview.RcTouchActivity;
import com.love.jax.activity.recycleview.ThemeActivity;
import com.love.jax.activity.tkjobs.CarClaimsInfoActivity;
import com.love.jax.activity.tkjobs.IpSetActivity;
import com.love.jax.activity.tkjobs.OrderActivity;
import com.love.jax.activity.tkjobs.SelectCityActivity;
import com.love.jax.adapter.InFuncAdapter;
import com.love.jax.bean.LettersEntity;
import com.love.jax.bean.OrderEntity;
import com.love.jax.bean.dao.DbHelper;
import com.love.jax.callback.IDbHelper;
import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.ListUtils;
import com.love.jax.utils.Logger;
import com.love.jax.utils.StringShowUtils;
import com.love.jax.view.CanvasBasisView;
import com.love.jax.view.HistoryFlowLayout;
import com.love.jax.view.search.LimitEditText;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 首页入口
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rec_but)
    RecyclerView mRecFunction;
    @BindView(R.id.et_search)
    LimitEditText mEditText;
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
    private RxPermissions rxPermissions;

    InFuncAdapter mInFuncAdapter;
    private String[] mStrings = new String[]{
            "屏幕适配","商品订单","理赔列表","主题适配","列表简单使用","列表间隔线","列表头尾","列表交互动画","侧滑效果一","侧滑效果二"
            ,"底部弹窗","文本输入","标题栏","顶部透明","颜色获取","顶部标题","底部导航","顶部沉浸","底部沉浸","卡片布局","悬浮按钮"
            ,"隐藏交互动画","隐藏动画2","隐藏动画3","平行空间","导航折叠","运转状态1","运转状态2","属性动画","属性动画集","揭露动画"
            ,"转场动画","矢量图像","动画框架一","动画框架二","滑动冲突一","滑动冲突二","侧滑效果三","条目侧滑","画笔一","进度条圆环"
            ,"高级渲染","滤镜效果","基础画布","高级画布","搜索图标一","搜索图标二","波形路径","路径截取","波浪行驶","城市列表","城市选择"
            ,"自定义容器","网络地址","条目滚动","操作通话","面向切面","数据库框架","分库升级","网络框架","图片框架","JSON","XUtils","MVP"
            ,"MVVM1","MVVM2"
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
            case "理赔列表":
                //传递过去json数据
                sBundle.putString(ConfigSet.INTENT_STRING,"理赔列表");
                jumpToActivity(CarClaimsInfoActivity.class, sBundle);
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
            case "运转状态1":
                sBundle.putString(ConfigSet.INTENT_STRING,"运转状态1");
                jumpToActivity(CustomerBehavior1Activity.class, sBundle);
                break;
            case "运转状态2":
                sBundle.putString(ConfigSet.INTENT_STRING,"运转状态2");
                jumpToActivity(CustomerBehavior2Activity.class, sBundle);
                break;
            case "属性动画":
                sBundle.putString(ConfigSet.INTENT_STRING,"属性动画");
                jumpToActivity(ArrtAnimatorActivity.class, sBundle);
                break;
            case "属性动画集":
                sBundle.putString(ConfigSet.INTENT_STRING,"属性动画集");
                jumpToActivity(AnimatorSetActivity.class, sBundle);
                break;
            case "揭露动画":
                sBundle.putString(ConfigSet.INTENT_STRING,"揭露动画");
                jumpToActivity(RevealEffectActivity.class, sBundle);
                break;
            case "转场动画":
                sBundle.putString(ConfigSet.INTENT_STRING,"转场动画");
                jumpToActivity(OptionsTransitionActivity.class, sBundle);
                break;
            case "矢量图像":
                sBundle.putString(ConfigSet.INTENT_STRING,"矢量图像");
                jumpToActivity(SvgVectorActivity.class, sBundle);
                break;
            case "动画框架一":
                sBundle.putString(ConfigSet.INTENT_STRING,"动画框架一");
                jumpToActivity(AnimaFramework1Activity.class, sBundle);
                break;
            case "动画框架二":
                sBundle.putString(ConfigSet.INTENT_STRING,"动画框架二");
                jumpToActivity(ParallaxSplashActivity.class, sBundle);
                break;
            case "滑动冲突一":
                sBundle.putString(ConfigSet.INTENT_STRING,"滑动冲突一");
                jumpToActivity(TouchEventSLActivity.class, sBundle);
                break;
            case "滑动冲突二":
                sBundle.putString(ConfigSet.INTENT_STRING,"滑动冲突二");
                jumpToActivity(ViewPagerActivity.class, sBundle);
                break;
            case "侧滑效果三":
                sBundle.putString(ConfigSet.INTENT_STRING,"侧滑效果三");
                jumpToActivity(SlideMenuActivity.class, sBundle);
                break;
            case "条目侧滑":
                sBundle.putString(ConfigSet.INTENT_STRING,"条目侧滑");
                jumpToActivity(SlideMenuItemActivity.class, sBundle);
                break;
            case "画笔一":
                sBundle.putString(ConfigSet.INTENT_STRING,"画笔一");
                jumpToActivity(PaintPathActivity.class, sBundle);
                break;
            case "进度条圆环":
                sBundle.putString(ConfigSet.INTENT_STRING,"进度条圆环");
                jumpToActivity(PaintProgressActivity.class, sBundle);
                break;
            case "高级渲染":
                sBundle.putString(ConfigSet.INTENT_STRING,"高级渲染");
                jumpToActivity(SeniorRendActivity.class, sBundle);
                break;
            case "滤镜效果":
                sBundle.putString(ConfigSet.INTENT_STRING,"滤镜效果");
                jumpToActivity(MaskFilterActivity.class, sBundle);
                break;
            case "基础画布":
                sBundle.putString(ConfigSet.INTENT_STRING,"基础画布");
                jumpToActivity(CanvasBasisActivity.class, sBundle);
                break;
            case "高级画布":
                sBundle.putString(ConfigSet.INTENT_STRING,"高级画布");
                jumpToActivity(CanvasSeniorActivity.class, sBundle);
                break;
            case "搜索图标一":
                sBundle.putString(ConfigSet.INTENT_STRING,"搜索图标一");
                jumpToActivity(SearchIconActivity.class, sBundle);
                break;
            case "搜索图标二":
                sBundle.putString(ConfigSet.INTENT_STRING,"搜索图标二");
                jumpToActivity(SearchView2Activity.class, sBundle);
                break;
            case "波形路径":
                sBundle.putString(ConfigSet.INTENT_STRING,"波形路径");
                jumpToActivity(WaveViewActivity.class, sBundle);
                break;
            case "路径截取":
                sBundle.putString(ConfigSet.INTENT_STRING,"路径截取");
                jumpToActivity(PathMeasureActivity.class, sBundle);
                break;
            case "波浪行驶":
                sBundle.putString(ConfigSet.INTENT_STRING,"波浪行驶");
                jumpToActivity(PathAnimaActivity.class, sBundle);
                break;
            case "城市列表":
                // 判断环境兼容，检查自己的权限，是否被同意
//                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                    //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
//                    ActivityCompat.requestPermissions(this,new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//                }else {
//                    Logger.i("wog","同意权限信息了222");
//
//                }
                sBundle.putString(ConfigSet.INTENT_STRING,"城市列表");
                jumpToActivity(CityListActivity.class, sBundle);

                break;
            case "城市选择":
                sBundle.putString(ConfigSet.INTENT_STRING,"城市选择");
                jumpToActivity(SelectCityActivity.class, sBundle);
                break;
            case "自定义容器":
                sBundle.putString(ConfigSet.INTENT_STRING,"自定义容器");
                jumpToActivity(CustomViewGroupActivity.class, sBundle);
                break;
            case "网络地址":
                sBundle.putString(ConfigSet.INTENT_STRING,"网络地址");
                jumpToActivity(IpSetActivity.class, sBundle);
                break;
            case "条目滚动":
                sBundle.putString(ConfigSet.INTENT_STRING,"条目滚动");
                jumpToActivity(TipViewActivity.class, sBundle);
                break;
            case "操作通话":
                sBundle.putString(ConfigSet.INTENT_STRING,"操作通话");
                jumpToActivity(LoadMoreActivity.class, sBundle);
                break;
            case "面向切面":
                sBundle.putString(ConfigSet.INTENT_STRING,"面向切面");
                jumpToActivity(AopActivity.class, sBundle);
                break;
            case "数据库框架":
                sBundle.putString(ConfigSet.INTENT_STRING,"数据库框架");
                jumpToActivity(DbFrameworkActivity.class, sBundle);
                break;
            case "分库升级":
                sBundle.putString(ConfigSet.INTENT_STRING,"分库升级");
                jumpToActivity(DbUpdateActivity.class, sBundle);
                break;
            case "网络框架":
                sBundle.putString(ConfigSet.INTENT_STRING,"网络框架");
                jumpToActivity(VollyActivity.class, sBundle);
                break;
            case "图片框架":
                sBundle.putString(ConfigSet.INTENT_STRING,"图片框架");
                jumpToActivity(ImageLoaderActivity.class, sBundle);
                break;
            case "JSON":
                sBundle.putString(ConfigSet.INTENT_STRING,"JSON");
                jumpToActivity(FastJsonActivity.class, sBundle);
                break;
            case "XUtils":
                sBundle.putString(ConfigSet.INTENT_STRING,"XUtils");
                jumpToActivity(XUtilsActivity.class, sBundle);
                break;
            case "MVP":
                sBundle.putString(ConfigSet.INTENT_STRING,"MVP");
                jumpToActivity(MvpActivity.class, sBundle);
                break;
            case "MVVM1":
                sBundle.putString(ConfigSet.INTENT_STRING,"MVVM1");
                jumpToActivity(MvvmObjectActivity.class, sBundle);
                break;
            case "MVVM2":
                sBundle.putString(ConfigSet.INTENT_STRING,"MVVM2");
                jumpToActivity(MvvmListActivity.class, sBundle);
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
        rxPermissions = new RxPermissions(this);
        checkLocal();
        mStringList.addAll(Arrays.asList(mStrings));
        Collections.reverse(mStringList);
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
                Logger.e("wog","beforeTextChanged:"+s + "\nstart:"+start+"\ncount:"+count+"\nafter:"+after);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logger.e("wog","onTextChanged:"+s + "\nstart:"+start+"\ncount:"+count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.e("wog","afterTextChanged:"+s );
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
        for (int i = 0; i < mEntityList.size(); i++) {
            mEntityList.get(i).setTitle(StringShowUtils.delTag(mEntityList.get(i).getTitle()));
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //判断请求码
        switch (requestCode){
            case 1:
                //如果同意，就拨打
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   Logger.i("wog","同意权限信息了111");
                }else{
                    Toast.makeText(this,"哈哈哈",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //检查读写权限
    private void checkLocal() {

        rxPermissions.request(Manifest.permission.READ_CALL_LOG,Manifest.permission.WRITE_CALL_LOG,Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {


            }
        },new Consumer<Throwable>(){

            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }
}
