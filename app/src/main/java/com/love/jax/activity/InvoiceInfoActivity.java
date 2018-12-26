package com.love.jax.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.utils.Logger;

import butterknife.BindView;

public class InvoiceInfoActivity extends BaseActivity {
//    @BindView(R.id.titleBar)
//    MyTitleBar titleBar;
    @BindView(R.id.invoiceTipTv)
    TextView invoiceTipTv;
    @BindView(R.id.billTypeArrowIv)
    ImageView billTypeArrowIv;
    @BindView(R.id.billTypeTv)
    TextView billTypeTv;
    @BindView(R.id.billTypeLay)
    RelativeLayout billTypeLay;
    @BindView(R.id.companyRB)
    RadioButton companyRB;
    @BindView(R.id.presonalRB)
    RadioButton presonalRB;
    @BindView(R.id.groupTypeLay)
    RelativeLayout groupTypeLay;
    @BindView(R.id.invoiceTitleEt)
    EditText invoiceTitleEt;
    @BindView(R.id.invoiceTitleLay)
    RelativeLayout invoiceTitleLay;
    @BindView(R.id.dutyParagraphEt)
    EditText dutyParagraphEt;
    @BindView(R.id.dutyParagraphLay)
    RelativeLayout dutyParagraphLay;
    @BindView(R.id.registerAddressEt)
    EditText registerAddressEt;
    @BindView(R.id.registerAddressLay)
    RelativeLayout registerAddressLay;
    @BindView(R.id.registerPhoneEt)
    EditText registerPhoneEt;
    @BindView(R.id.registerPhoneLay)
    RelativeLayout registerPhoneLay;
    @BindView(R.id.depositBankEt)
    EditText depositBankEt;
    @BindView(R.id.depositBankLay)
    RelativeLayout depositBankLay;
    @BindView(R.id.depositBankAccountEt)
    EditText depositBankAccountEt;
    @BindView(R.id.depositBankAccountLay)
    RelativeLayout depositBankAccountLay;
    @BindView(R.id.descTv)
    TextView descTv;
    @BindView(R.id.descLay)
    RelativeLayout descLay;
    @BindView(R.id.invoiceMailAddressTv)
    TextView invoiceMailAddressTv;
    @BindView(R.id.invoiceMailAddressLay)
    RelativeLayout invoiceMailAddressLay;
    @BindView(R.id.contactNameEt)
    EditText contactNameEt;
    @BindView(R.id.contactNameLay)
    RelativeLayout contactNameLay;
    @BindView(R.id.contactPhoneEt)
    EditText contactPhoneEt;
    @BindView(R.id.contactPhoneLay)
    RelativeLayout contactPhoneLay;
    @BindView(R.id.saveeBtn)
    TextView saveeBtn;
    @BindView(R.id.groupTypeRG)
    RadioGroup groupTypeRG;
    @BindView(R.id.layout_all)
    LinearLayout mLinearLayout;
    @BindView(R.id.scroll_invoice)
    ScrollView mScrollView;

    String invoiceTitlePuCompanyStr = "";//抬头-普票-单位
    String invoiceTitlePuPersionalStr = "";//抬头-普票-个人
    String invoiceTitleZhuanStr = "";//抬头-专票
    int  heightPix = -1;
    int count  = 0;
    int topHeight = 0;

    @Override
    protected void initJestListener() {
        mLinearLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int
                    oldLeft, int oldTop, int oldRight, int oldBottom) {
                Logger.e("tang",bottom+" ,"+oldBottom);
                count++;
                if (oldBottom==0){
                    heightPix = bottom;
                    Logger.e("tang","屏幕高度=="+heightPix);
                }
                Rect r = new Rect();
                //获取当前界面可视部分
                InvoiceInfoActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
//                int screenHeight =  InvoiceInfoActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                final int heightDifference = heightPix - r.bottom;
                topHeight = r.top;
                Logger.i("tang", "键盘高度为：" + heightDifference);
                ViewGroup.LayoutParams layoutParams = mLinearLayout.getLayoutParams();
//                layoutParams.height = heightPix-heightDifference-r.top;

                Logger.i("tang","首次进入加载次数"+count);
                if (count>2 ) {
                    if (heightDifference != 0) {
                        layoutParams.height = heightPix * 6 / 10 - r.top;
                    } else {
                        layoutParams.height = heightPix - r.top;
                    }
                    mLinearLayout.setLayoutParams(layoutParams);
                }

            }
        });



    }

    @Override
    protected void initView() {
        initInvoice();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_invoice_info;
    }


    /**
     * 根据增值税票据类型和发票类型判断是否显示税号
     * ------------增值税普通发票单位
     * ------------增值税专票
     */
    private void showDutyParagraph() {
        // 除增值税普通发票外都显示
        if(billTypeTv.getText().toString().equals(getResources().getString(R.string.string_vat_ordinary_invoice))
                && groupTypeRG.getCheckedRadioButtonId()==presonalRB.getId()){
            dutyParagraphLay.setVisibility(View.GONE);
        } else {
            dutyParagraphLay.setVisibility(View.VISIBLE);
        }
    }

    // 增值税普通发票
    private void initVatOrdinaryInvoiceView() {
        billTypeTv.setText(getResources().getString(R.string.string_vat_ordinary_invoice));
        // 当切换到增值税普通发票时，显示类型，默认为单位
        groupTypeLay.setVisibility(View.VISIBLE);
        groupTypeRG.check(companyRB.getId());
        // 抬头
        if(invoiceTitlePuCompanyStr != null) {
            invoiceTitleEt.setText(invoiceTitlePuCompanyStr);
        }
        // 税号
        showDutyParagraph();
        // 注册地址：只有增值税专票时显示
        registerAddressLay.setVisibility(View.GONE);
        // 注册电话：只有增值税专票时显示
        registerPhoneLay.setVisibility(View.GONE);
        // 开户银行：只有增值税专票时显示
        depositBankLay.setVisibility(View.GONE);
        // 银行账号：只有增值税专票时显示
        depositBankAccountLay.setVisibility(View.GONE);
        // 内容：只有增值税普通发票时显示
        descLay.setVisibility(View.VISIBLE);
    }

    // 增值税专票
    private void initVatTicketView() {
        billTypeTv.setText(getResources().getString(R.string.string_vat_ticket));
        // 当切换到增值税专票时，不显示类型
        groupTypeLay.setVisibility(View.GONE);
        // 抬头
        invoiceTitleEt.setHint("请填入企业名称");
        if(invoiceTitleZhuanStr != null) {
            invoiceTitleEt.setText(invoiceTitleZhuanStr);
        }
        // 税号
        showDutyParagraph();
        // 注册地址：只有增值税专票时显示
        registerAddressLay.setVisibility(View.VISIBLE);
        // 注册电话：只有增值税专票时显示
        registerPhoneLay.setVisibility(View.VISIBLE);
        // 开户银行：只有增值税专票时显示
        depositBankLay.setVisibility(View.VISIBLE);
        // 银行账号：只有增值税专票时显示
        depositBankAccountLay.setVisibility(View.VISIBLE);
        // 内容：只有增值税普通发票时显示
        descLay.setVisibility(View.GONE);
    }

    private void initInvoice() {
        // 默认是增值税普通发票
        initVatOrdinaryInvoiceView();
        // 类型选状态变化监听-单位个人
        groupTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 单位个人切换时，显示各自的抬头
                if(checkedId == presonalRB.getId()) {//个人
                    if(invoiceTitlePuPersionalStr != null) {
                        invoiceTitleEt.setText(invoiceTitlePuPersionalStr);
                    }
                    invoiceTitleEt.setHint("请填入您的姓名");
                } else {//单位
                    if(invoiceTitlePuCompanyStr != null) {
                        invoiceTitleEt.setText(invoiceTitlePuCompanyStr);
                    }
                    invoiceTitleEt.setHint("请填入企业名称");
                }
                // 根据增值税票据类型和发票类型判断是否显示税号
                showDutyParagraph();
            }
        });
//        // 联系人姓名、联系人电话默认为操作用户的姓名、手机号
//        if(mUser!=null && !TextUtils.isEmpty(mUser.getName())){
//            contactNameEt.setText(mUser.getName());
//        } else {
//            contactNameEt.setText("");
//        }
//        if(mUser!=null && !TextUtils.isEmpty(mUser.getMobile())){
//            contactPhoneEt.setText(mUser.getMobile());
//        } else {
//            contactPhoneEt.setText("");
//        }
        // 发票邮寄地址显示为默认地址，点击进入到地址列表
//        getAddressList();
        // 抬头做位数限制，最多50位，超出则无法输入并toast提示：抬头最多可输入50位
        invoiceTitleEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = invoiceTitleEt.getText();
                int len = editable.length();

                if (len > 50) {

                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 50);
                    invoiceTitleEt.setText(newStr);
                    editable = invoiceTitleEt.getText();

                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String invoiceTitle = invoiceTitleEt.getText().toString();
                // 保存抬头
                String billType = billTypeTv.getText().toString();
                if(billType.equals(getResources().getString(R.string.string_vat_ticket))){//专票
                    invoiceTitleZhuanStr = invoiceTitle;
                } else if(groupTypeRG.getCheckedRadioButtonId() == presonalRB.getId()) {//普票-个人
                    invoiceTitlePuPersionalStr = invoiceTitle;
                } else {//普票单位
                    invoiceTitlePuCompanyStr = invoiceTitle;
                }
            }
        });

    }
}
