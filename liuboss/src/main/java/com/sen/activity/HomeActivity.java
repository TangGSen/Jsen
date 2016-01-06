package com.sen.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sen.iview.ILoginView;
import com.sen.liuboss.R;
import com.sen.model.LoginData;
import com.sen.presenter.imp.ILoginPersenter;
import com.sen.presenter.imp.LoginPersenter;
import com.sen.service.LoginService;
import com.sen.utils.AppSchecduler;
import com.sen.utils.IScheduler;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity implements ILoginView<LoginData> {

    @Bind(R.id.app_bar)
    Toolbar mToolbar;
    @Bind(R.id.action_bar_title)
    TextView actionBarTile;


    @Bind(R.id.user_name)
    EditText user_name;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.result)
    TextView result;

    private IScheduler mIScheduler;
    private LoginService mLoginService;
    private ILoginPersenter iLoginPersenter;

    public void initView() {
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        user_name.setText("wooooo");
        password.setText("000000");

    }

    @Override
    protected void initPersenter() {
        mIScheduler = new AppSchecduler();
        mLoginService = new LoginService();
        iLoginPersenter = new LoginPersenter(mIScheduler, mLoginService);
        iLoginPersenter.setView(this);
        iLoginPersenter.start();
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        mToolbar.setTitle("");
        mToolbar.setVisibility(View.GONE);
        actionBarTile.setText(R.string.loginString);
        mToolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
        setSupportActionBar(mToolbar);


    }

    @Override
    public void setLoading(boolean isLoading) {
        progressbar.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public void setModel(LoginData object) {
        result.setText("userName" + object.getUserName() + "____" + object.getPassword());
    }

    @Override
    public String getUserName() {
        return user_name.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }


    @Override
    public void error(Throwable throwable) {
        Toast.makeText(this, throwable + "", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        iLoginPersenter.finish();
        super.onDestroy();
    }


}
