package cn.nicemorning.sockword.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import cn.nicemorning.BaseApplication;
import cn.nicemorning.ScreenListener;
import cn.nicemorning.sockword.R;

/**
 * Created by Nicemorning on 10-Mar-18.
 * In package cn.nicemorning.sockword.activity
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ScreenListener screenListener;
    private SharedPreferences sharedPreferences;
    private android.app.FragmentTransaction transaction;
    private StudyFrameLayout studyFragment;
    private Fragment setFragment;
    private Button wrongBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_layout);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("share",
                Context.MODE_PRIVATE);
        wrongBtn = (Button) findViewById(R.id.wrong_btn);
        wrongBtn.setOnClickListener(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        screenListener = new ScreenListener(this);
        screenListener.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                if (sharedPreferences.getBoolean("btnTf", false)) {
                    if (sharedPreferences.getBoolean("tf", false)) {
                        Intent intent = new Intent(HomeActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onScreenOff() {
                editor.putBoolean("tf", true);
                editor.commit();
                BaseApplication.destroyActivity("mainActivity");
            }

            @Override
            public void onUserPresent() {
                editor.putBoolean("tf", false);
                editor.commit();
            }
        });
        studyFragment = new StudyFrameLayout();
        setFragment(studyFragment);
    }

    public void setFragment(Fragment fragment) {
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    public void onStudy(View view) {
        if (studyFragment == null) {
            studyFragment = new StudyFrameLayout();
        }
        setFragment(studyFragment);
    }

    public void onSet(View view) {
        if (setFragment == null) {
            setFragment = new Fragment();
        }
        setFragment(setFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wrong_btn:
                Toast.makeText(this, "跳转到错题界面", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenListener.unregisterListener();
    }
}
