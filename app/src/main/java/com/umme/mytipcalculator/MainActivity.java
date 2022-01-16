package com.umme.mytipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
       EditText amount;
       TextView percen;
       SeekBar bar;
       TextView tip;
       TextView total;
       TextView remark;
       TextView emoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.etbill);
        percen = findViewById(R.id.tvper);
        bar = findViewById(R.id.sb_percent);
        tip = findViewById(R.id.tvtip);
        remark=findViewById(R.id.tvrem);
                total = findViewById(R.id.tvtotl);
                bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        int percent= progress;
                        percen.setText(String.valueOf(percent)+"%");
                        calculate();

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                amount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    calculate();
                    }
                });
    }
    private void calculate()
    {
        if(amount.length()==0)
        {
            amount.requestFocus();
            amount.setError("enter amount");
        }
        else{
            double myamount = Double.parseDouble(amount.getText().toString());
            int percent = bar.getProgress();
            double tips = myamount*percent/100.0;
            double totals = myamount+tips;
            tip.setText(String.valueOf(tips));
            total.setText(String.valueOf(totals));
            if(percent<10){
                int unicode = 0x1F615;
                String emoji = getEmoji(unicode);
                remark.setText(String.valueOf("poor"+emoji));

            }
            else if(percent<15){
                int unicode = 0x1F612;
                String emoji = getEmoji(unicode);
                remark.setText(String.valueOf("nice"+emoji));
            }
            else if(percent<20)
            {
                int unicode = 0x263A;
                String emoji = getEmoji(unicode);
                remark.setText(String.valueOf("good"+emoji));
            }
            else if(percent<25)
            {
                int unicode = 0x1F600;
                String emoji = getEmoji(unicode);
                remark.setText(String.valueOf("great"+emoji));
            }
            else{
                int unicode = 0x1F60D;
                String emoji = getEmoji(unicode);
                remark.setText(String.valueOf("amazing"+emoji));
            }
        }
    }
    public String getEmoji(int uni)
    {
        return new String(Character.toChars(uni));
    }
}