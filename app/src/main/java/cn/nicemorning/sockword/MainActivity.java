package cn.nicemorning.sockword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTimeText;
    private TextView mDateText;
    private TextView mWordText;
    private ImageView mPlayVioce;
    private TextView mEnglishText;
    private RadioButton mChooseBtnOne;
    private RadioButton mChooseBtnTwo;
    private RadioButton mChooseBtnThree;
    private RadioGroup mChooseGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTimeText = findViewById(R.id.time_text);
        mDateText = findViewById(R.id.date_text);
        mWordText = findViewById(R.id.word_text);
        mPlayVioce = findViewById(R.id.play_vioce);
        mEnglishText = findViewById(R.id.english_text);
        mChooseBtnOne = findViewById(R.id.choose_btn_one);
        mChooseBtnTwo = findViewById(R.id.choose_btn_two);
        mChooseBtnThree = findViewById(R.id.choose_btn_three);
        mChooseGroup = findViewById(R.id.choose_group);
    }
}
