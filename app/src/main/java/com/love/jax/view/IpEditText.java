package com.love.jax.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.love.jax.R;


/*****************************************************
 * 北京振中电子技术有限公司版权所有
 * 创建日期: 2017/6/9 16:05
 * ****************************************************
 * 更改记录: 更新人:    更新时间:    更新概要:
 *
 * ****************************************************
 *  类功能说明: ip输入框
 *  自定义ip输入框
 * ****************************************************/
public class IpEditText extends LinearLayout {

    private EditText mFirstIP;
    private EditText mSecondIP;
    private EditText mThirdIP;
    private EditText mFourthIP;

    private String mText1;
    private String mText2;
    private String mText3;
    private String mText4;

//    private SharedPreferences mPreferences;

    public IpEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * 初始化控件
         */
        View view = LayoutInflater.from(context).inflate(
                R.layout.ip_edit_text, this);
        mFirstIP = (EditText) findViewById(R.id.ip_first);
        mFirstIP.setBackgroundColor(context.getResources().getColor(R.color.color_F0F0F0));
        mSecondIP = (EditText) findViewById(R.id.ip_second);
        mSecondIP.setBackgroundColor(context.getResources().getColor(R.color.color_F0F0F0));
        mThirdIP = (EditText) findViewById(R.id.ip_third);
        mThirdIP.setBackgroundColor(context.getResources().getColor(R.color.color_F0F0F0));
        mFourthIP = (EditText) findViewById(R.id.ip_fourth);
        mFourthIP.setBackgroundColor(context.getResources().getColor(R.color.color_F0F0F0));

//        mPreferences = context.getSharedPreferences("config_IP",
//                Context.MODE_PRIVATE);

        OperatingEditText(context);
    }

    /**
     * 获得EditText中的内容,当每个Edittext的字符达到三位时,自动跳转到下一个EditText,当用户点击.时,
     * 下一个EditText获得焦点
     */
    private void OperatingEditText(final Context context) {
        mFirstIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /**
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0) {
                    if (s.length() > 2 || s.toString().trim().contains(".")) {
                        if (s.toString().trim().contains(".")) {
                            mText1 = s.toString().substring(0, s.length() - 1);
                            mFirstIP.setText(mText1);
                        } else {
                            mText1 = s.toString().trim();
                        }
                        if (Integer.parseInt(mText1) > 255) {
                            Toast.makeText(context, "请输入合法的ip地址",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                    } else if (s.length() > 0 && s.length() <= 2) {
                        mText1 = s.toString().trim();
                    }
//                    SharedPreferences.Editor editor = mPreferences.edit();
//                    editor.putInt("IP_FIRST", mText1.length());
//                    editor.commit();

                } else {
                    mText1 = null;
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSecondIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /**
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0) {
                    if (s.length() > 2 || s.toString().trim().contains(".")) {
                        if (s.toString().trim().contains(".")) {
                            mText2 = s.toString().substring(0, s.length() - 1);
                            mSecondIP.setText(mText2);
                        } else {
                            mText2 = s.toString().trim();
                        }

                        if (Integer.parseInt(mText2) > 255) {
                            Toast.makeText(context, "请输入合法的ip地址",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        mThirdIP.setFocusable(true);
                        mThirdIP.requestFocus();

                    } else if (s.length() > 0 && s.length() <= 2) {
                        mText2 = s.toString().trim();
                    }
//                    SharedPreferences.Editor editor = mPreferences.edit();
//                    editor.putInt("IP_SECOND", mText2.length());
//                    editor.commit();

                } else {
                    mText2 = null;
                }

                /**
                 * 当用户需要删除时,此时的EditText为空时,上一个EditText获得焦点
                 */
                if (start == 0 && s.length() == 0) {
                    mFirstIP.setFocusable(true);
                    mFirstIP.requestFocus();
                    if (mText1 != null) {
                        mFirstIP.setSelection(mText1.length());
                    } else {
                        mFirstIP.setSelection(0);
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mThirdIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /**
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0) {
                    if (s.length() > 2 || s.toString().trim().contains(".")) {
                        if (s.toString().trim().contains(".")) {
                            mText3 = s.toString().substring(0, s.length() - 1);
                            mThirdIP.setText(mText3);
                        } else {
                            mText3 = s.toString().trim();
                        }

                        if (Integer.parseInt(mText3) > 255) {
                            Toast.makeText(context, "请输入合法的ip地址",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        mFourthIP.setFocusable(true);
                        mFourthIP.requestFocus();

                    } else if (s.length() > 0 && s.length() <= 2) {
                        mText3 = s.toString().trim();
                    }
//                    SharedPreferences.Editor editor = mPreferences.edit();
//                    editor.putInt("IP_THIRD", mText3.length());
//                    editor.commit();
                } else {
                    mText3 = null;
                }

                /**
                 * 当用户需要删除时,此时的EditText为空时,上一个EditText获得焦点
                 */
                if (start == 0 && s.length() == 0) {
                    if (mText2 != null) {
                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                        mSecondIP.setSelection(mText2.length());
                    } else if (mText1 != null) {
                        mFirstIP.setFocusable(true);
                        mFirstIP.requestFocus();
                        mFirstIP.setSelection(mText2.length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFourthIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /**
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0) {
                    mText4 = s.toString().trim();

                    if (Integer.parseInt(mText4) > 255) {
                        Toast.makeText(context, "请输入合法的ip地址", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
//                    if (s.length() != 0) {
//                        mFourthIP.setSelection(mText4.length());
//                    }
//                    SharedPreferences.Editor editor = mPreferences.edit();
//                    editor.putInt("IP_FOURTH", mText4.length());
//                    editor.commit();
                } else {
                    mText4 = null;
                }

                /**
                 * 当用户需要删除时,此时的EditText为空时,上一个EditText获得焦点
                 */
                if (start == 0 && s.length() == 0) {
                    if (mText3 != null) {
                        mThirdIP.setFocusable(true);
                        mThirdIP.requestFocus();
                        mThirdIP.setSelection(mText3.length());
                    } else if (mText2 != null) {
                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                        mSecondIP.setSelection(mText2.length());
                    } else if (mText1 != null) {
                        mFirstIP.setFocusable(true);
                        mFirstIP.requestFocus();
                        mFirstIP.setSelection(mText1.length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public String getText() {
        if (TextUtils.isEmpty(mText1) || TextUtils.isEmpty(mText2)
                || TextUtils.isEmpty(mText3) || TextUtils.isEmpty(mText4)) {
//            Toast.makeText(context, "请输入合法的ip地址", Toast.LENGTH_LONG).show();
            return "";
        }
        return mText1 + "." + mText2 + "." + mText3 + "." + mText4;
    }

    public void setText(String value) {
        String[] ip = value.split("\\.");
        if (ip.length == 4) {
            mFirstIP.setText(ip[0]);
            mSecondIP.setText(ip[1]);
            mThirdIP.setText(ip[2]);
            mFourthIP.setText(ip[3]);
        } else {
            mFirstIP.setText("");
            mSecondIP.setText("");
            mThirdIP.setText("");
            mFourthIP.setText("");
        }
    }
}
