package com.sevencore.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtWidth, edtHeight, edtLenght;
    Button btnCalculate;
    TextView tvResult;
    private static final String STATE_RESULT = "string_result";
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLenght = findViewById(R.id.edt_lenght);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLenght = edtLenght.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLenght)) {
                isEmptyFields =  true ;
                edtLenght.setError("Field tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true ;
                edtWidth.setError("Field tidak bole kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true ;
                edtHeight.setError("Field tidak boleh kosong");
            }

            Double lenght = toDouble(inputLenght);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (lenght == null) {
                isInvalidDouble = true;
                edtLenght.setError("Field ini harus berupa nomer yang valid");
            }
            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomer yang valid");
            }
            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomer yang valid");
            }
            if (!isEmptyFields && !isInvalidDouble){
                double volume = lenght * width * height;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str) ;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
