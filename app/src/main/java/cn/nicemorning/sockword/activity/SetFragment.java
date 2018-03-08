package cn.nicemorning.sockword.activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import cn.nicemorning.sockword.R;

/**
 * Created by Nicemorning on 08-Mar-18.
 * In package cn.nicemorning.sockword.activity
 */

public class SetFragment extends Fragment implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    private SwitchButton switchButton;
    private Spinner spinnerDifficulty;
    private Spinner spinnerAllNum;
    private Spinner spinnerNewNum;
    private Spinner spinnerReviewNum;
    private ArrayAdapter<String> adapterDifficulty, adapterAllNum,
            adapterNewNum, adapterReviewNUm;
    String[] difficulty = new String[]{"小学", "初中", "高中", "四级", "六级"};
    String[] allNum = new String[]{"2道", "4道", "6道", "8道"};
    String[] newNum = new String[]{"10", "30", "50", "100"};
    String[] revicwNum = new String[]{"10", "30", "50", "100"};
    SharedPreferences.Editor editor = null;

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_fragment_layout, null);
    }

    private void init(View view) {
        sharedPreferences = getActivity().getSharedPreferences("share",
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switchButton = (SwitchButton) view.findViewById(R.id.switch_btn);
        switchButton.setOnClickListener(this);
        spinnerDifficulty = (Spinner) view.findViewById(R.id.spinner_difficulty);
        spinnerAllNum = (Spinner) view.findViewById(R.id.spinner_all_number);
        spinnerNewNum = (Spinner) view.findViewById(R.id.spinner_new_number);
        spinnerReviewNum = (Spinner) view.findViewById(R.id.spinner_revise_number);
        adapterDifficulty = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, difficulty);
        spinnerDifficulty.setAdapter(adapterDifficulty);
        setSpinnerItemSelectedByValue(spinnerDifficulty,
                sharedPreferences.getString("difficulty", "四级"));
        this.spinnerDifficulty.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                editor.putString("difficulty", msg);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterAllNum = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, allNum);
        spinnerAllNum.setAdapter(adapterAllNum);
        setSpinnerItemSelectedByValue(spinnerAllNum,
                sharedPreferences.getInt("allNum", 2) + "道");
        this.spinnerAllNum.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                int i = Integer.parseInt(msg.substring(0, 1));
                editor.putInt("allNum", i);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterNewNum = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, newNum);
        spinnerNewNum.setAdapter(adapterNewNum);
        setSpinnerItemSelectedByValue(spinnerNewNum,
                sharedPreferences.getString("newNum", "10"));
        this.spinnerNewNum.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                editor.putString("newNum", msg);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterReviewNUm = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, revicwNum);
        spinnerReviewNum.setAdapter(adapterReviewNUm);
        setSpinnerItemSelectedByValue(spinnerReviewNum,
                sharedPreferences.getString("reviewNum", "10"));
        this.spinnerReviewNum.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                editor.putString("reviewNum", msg);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setSpinnerItemSelectedByValue(Spinner spinnerAllNum, String value) {
        SpinnerAdapter spinnerAdapter = spinnerAllNum.getAdapter();
        int k = spinnerAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(spinnerAdapter.getItem(i).toString())) {
                spinnerAllNum.setSelection(i, true);
            }
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_btn:
                if (switchButton.isSwitchOpen()) {
                    switchButton.closeSwitch();
                    editor.putBoolean("btnTf", false);
                } else {
                    switchButton.openSwitch();
                    editor.putBoolean("btnTf", true);
                }
                editor.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sharedPreferences.getBoolean("btnTf", false)) {
            switchButton.openSwitch();
        } else {
            switchButton.closeSwitch();
        }
    }
}
