package com.sen.activity;


import android.widget.FrameLayout;

import com.sen.liuboss.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginRegisterActivity extends BaseActivity {

    @Bind(R.id.frameLayoutLoginRegister)
    FrameLayout frameLayoutLoginRegister;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this);


    }




}
