package com.ngocbich.chitieucanhan;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

/**
 * Created by Ngoc Bich on 4/6/2018.
 */

public class UpdateDeleteActivity extends AppCompatActivity {

    private Button updateButton;
    private Button deleteButton;

    private EditText content;
    private EditText amount;
    private int type=-1;
    private EditText note;

    private RadioButton radioButton,inCome,outCome;

    private EditText eddate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private int pickerDay,pickerMonth,pickerYear;

    private ChiTieu ct;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        ct=MainActivity.chiTieuList.get(MainActivity.position);

        pickerDay=ct.getNgay();
        pickerMonth=ct.getThang();
        pickerYear=ct.getNam();

        inCome=findViewById(R.id.rbtIncome);
        outCome=findViewById(R.id.rbtOutcome);
        if(ct.getLoai()==0){
            inCome.setChecked(true);

        }
        else{
            outCome.setChecked(true);
        }
        type=ct.getLoai();

        content=findViewById(R.id.edtContent);
        amount=findViewById(R.id.edtAmount);
        note=findViewById(R.id.edittextNote);

        eddate=findViewById(R.id.edittextDate);

        content.setText(ct.getNoidung());
        amount.setText(ct.getSoTien()+"");
        note.setText(ct.getGhiChu());
        eddate.setText(ct.getNgay()+"/"+ct.getThang()+"/"+ct.getNam());

        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(UpdateDeleteActivity.this,
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

        updateButton=findViewById(R.id.buttonUpdate);
        deleteButton=findViewById(R.id.buttonDelete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTieu ct=MainActivity.chiTieuList.get(MainActivity.position);
                MainActivity.chiTieuList.remove(ct);
                Toast.makeText(UpdateDeleteActivity.this,"Đã xóa",Toast.LENGTH_SHORT).show();
                MainActivity.moneyDAO.delete(ct);
                onBackPressed();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!content.getText().toString().equals("") && !amount.getText().toString().equals("")  && !eddate.getText().toString().equals("")){
                    ChiTieu chiTieu=new ChiTieu(content.getText().toString(),Integer.parseInt(amount.getText().toString()),type,
                            note.getText().toString(),pickerDay,pickerMonth,pickerYear);
                    MainActivity.chiTieuList.get(MainActivity.position).setNoidung(content.getText().toString());
                    MainActivity.chiTieuList.get(MainActivity.position).setSoTien(Integer.parseInt(amount.getText().toString()));
                    MainActivity.chiTieuList.get(MainActivity.position).setLoai(type);
                    MainActivity.chiTieuList.get(MainActivity.position).setGhiChu(note.getText().toString());
                    MainActivity.chiTieuList.get(MainActivity.position).setNgay(pickerDay);
                    MainActivity.chiTieuList.get(MainActivity.position).setThang(pickerMonth);
                    MainActivity.chiTieuList.get(MainActivity.position).setNam(pickerYear);

                    MainActivity.moneyDAO.update(MainActivity.chiTieuList.get(MainActivity.position));
                    Toast.makeText(UpdateDeleteActivity.this,"Đã thay đổi",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else {
                    Toast.makeText(UpdateDeleteActivity.this,"Không hợp lệ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checked;
    public void onRadioButtonClick(View v){

        checked=((RadioButton)v).isChecked();
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
