package cn.sxh.snowfox.UI.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sxh.snowfox.R;
import cn.sxh.snowfox.utils.BankCheck;

public class BankActivity extends AppCompatActivity {

    @BindView(R.id.edit_one)
    EditText editOne;
    @BindView(R.id.edit_two)
    EditText editTwo;
    @BindView(R.id.checkBank)
    Button checkBank;
    @BindView(R.id.correct_content)
    TextView correctContent;
    @BindView(R.id.error_content)
    TextView errorContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        checkBank.setOnClickListener(view -> checkCard());
    }

    private void checkCard() {
        if (editOne.getText().toString() == null || editTwo.getText().toString() == null||
                "".equals(editOne.getText().toString())||"".equals(editTwo.getText().toString())||
                TextUtils.isEmpty(editOne.getText().toString())||TextUtils.isEmpty(editTwo.getText().toString())) {
            return;
        }
        String s = BankCheck.getNameOfBank(editOne.getText().toString());
        boolean isCorrect = BankCheck.checkBankCard(editTwo.getText().toString());
        correctContent.setText(s);
        errorContent.setText(isCorrect+"");
    }
}
