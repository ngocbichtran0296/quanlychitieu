package com.ngocbich.chitieucanhan;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ngoc Bich on 4/5/2018.
 */

public class AddActivity extends AppCompatActivity{

    private Button addButton;
    private Button cancelButton;

    private EditText content;
    private EditText amount;
    private int type=-1;
    private EditText note;

    private RadioButton radioButton;
    private RadioButton inCome,outCome;

    private EditText eddate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private int pickerDay,pickerMonth,pickerYear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        content=findViewById(R.id.edtContent);
        amount=findViewById(R.id.edtAmount);
        note=findViewById(R.id.edittextNote);

        outCome=findViewById(R.id.rbtOutcome);
        inCome=findViewById(R.id.rbtIncome);

        eddate=findViewById(R.id.edittextDate);
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                pickerDay=dayOfMonth;
                pickerMonth=month;
                pickerYear=year;
                String date=dayOfMonth+"/"+month+"/"+year;
                eddate.setText(date);
                Log.d("date",pickerDay+"/"+pickerMonth+"/"+pickerYear);
            }
        };

        addButton=findViewById(R.id.buttonAdd);
        cancelButton=findViewById(R.id.buttonCancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!content.getText().toString().equals("") && !amount.getText().toString().equals("") && checked==true && !eddate.getText().toString().equals("")){
                    ChiTieu chiTieu=new ChiTieu(content.getText().toString(),Integer.parseInt(amount.getText().toString()),type,
                            note.getText().toString(),pickerDay,pickerMonth,pickerYear);
                    MainActivity.list.add(chiTieu);
                    MainActivity.moneyDAO.insert(chiTieu);
                    Toast.makeText(AddActivity.this,"Đã thêm",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else {
                    Toast.makeText(AddActivity.this,"Không hợp lệ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checked;
    public void onRadioButtonClick(View v){
        checked=((RadioButton)v).isChecked();
        RadioButton inCome=findViewById(R.id.rbtIncome);
        RadioButton outCome=findViewById(R.id.rbtOutcome);

        switch (v.getId()){
            case R.id.rbtIncome:
                if(checked){
                    outCome.setChecked(false);
                    radioButton=findViewById(R.id.rbtIncome);
                    type=0;
                    Log.d("test",radioButton+"");
                }
                break;
            case R.id.rbtOutcome:
                if(checked){
                    inCome.setChecked(false);
                    radioButton=findViewById(R.id.rbtOutcome);
                    type=1;
                    Log.d("test",radioButton+"");
                }
                break;
        }
    }
}
