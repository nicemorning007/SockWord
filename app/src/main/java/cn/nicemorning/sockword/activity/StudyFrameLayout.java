package cn.nicemorning.sockword.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assetsbasedata.AssetsDatabaseManager;

import java.util.List;
import java.util.Random;

import cn.nicemorning.greendao.entity.greendao.DaoMaster;
import cn.nicemorning.greendao.entity.greendao.DaoSession;
import cn.nicemorning.greendao.entity.greendao.WisdomEntity;
import cn.nicemorning.greendao.entity.greendao.WisdomEntityDao;
import cn.nicemorning.sockword.R;

public class StudyFrameLayout extends Fragment {
    private TextView difficultyTv,
            wisdomEnglish,
            wisdomChina,
            alreadyStudyText,
            alreadyMasteredText,
            wrongText;
    private SharedPreferences sharedPreferences;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private WisdomEntityDao questionDao;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.
                inflate(R.layout.study_fragment_layout, null);
        sharedPreferences = getActivity().getSharedPreferences("share",
                Context.MODE_PRIVATE);
        difficultyTv = view.findViewById(R.id.difficulty_text);
        wisdomEnglish = view.findViewById(R.id.wisdom_english);
        wisdomChina = view.findViewById(R.id.wisdom_china);
        alreadyMasteredText = view.findViewById(R.id.already_mastered);
        alreadyStudyText = view.findViewById(R.id.already_study);
        wrongText = view.findViewById(R.id.wrong_text);
        AssetsDatabaseManager.initManager(getActivity());
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        SQLiteDatabase db1 = mg.getDatabase("wisdom.db");
        mDaoMaster = new DaoMaster(db1);
        mDaoSession = mDaoMaster.newSession();
        questionDao = mDaoSession.getWisdomEntityDao();
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void onStart() {
        super.onStart();
        difficultyTv.setText(sharedPreferences.
                getString("difficulty", "四级") + "英语");
        List<WisdomEntity> datas = questionDao.queryBuilder().list();
        Random random = new Random();
        int i = random.nextInt(10);
        wisdomEnglish.setText(datas.get(i).getEnglish());
        wisdomChina.setText(datas.get(i).getChina());
        setText();
    }

    @SuppressLint("SetTextI18n")
    private void setText() {
        alreadyMasteredText.setText(sharedPreferences.
                getInt("alreadyMastered", 0) + "");
        alreadyStudyText.setText(sharedPreferences.
                getInt("alreadyStudy", 0) + "");
        wrongText.setText(sharedPreferences.getInt("wrong", 0) + "");
    }
}