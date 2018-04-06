package com.ngocbich.chitieucanhan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.crypto.Cipher;

/**
 * Created by Ngoc Bich on 4/5/2018.
 */

public class InComeActivity extends AppCompatActivity {
    private Button week;
    private Button month;
    private Button quarter;
    private Button year;
    private ReportAdapter adapter;
    private List<ChiTieu> chiTieuList;
    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_income);

        listView=findViewById(R.id.listviewItem);
        week=findViewById(R.id.buttonWeek);
        month=findViewById(R.id.buttonMonth);
        quarter=findViewById(R.id.buttonQuarter);
        year=findViewById(R.id.buttonYear);
        textView=findViewById(R.id.tvIncome);
        getList();


        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Danh sách thu theo tháng");
                int thang;
                List<Report> list=new ArrayList<>();
                for(thang=1;thang<=12;thang++){
                    int tong=0;
                    for(ChiTieu ct:chiTieuList){
                        if(ct.getThang()==thang){
                            tong+=ct.getSoTien();
                        }
                    }
                    if(tong>0){
                        list.add(new Report(thang,tong,"Tháng"));
                    }
                }
                adapter=new ReportAdapter(InComeActivity.this,R.layout.activity_listview_report,list);
                listView.setAdapter(adapter);
            }
        });

        quarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Danh sách thu theo quý");
                int quy;
                List<Report> list=new ArrayList<>();
                for(quy=1;quy<=4;quy++){
                    int tong=0;
                    for(ChiTieu ct:chiTieuList){
                        if(ct.getThang()>=quy*3-3+1 && ct.getThang()<=quy*3){
                            tong+=ct.getSoTien();
                        }
                    }
                    if(tong>0){
                        list.add(new Report(quy,tong,"Quý"));
                    }
                }
                adapter=new ReportAdapter(InComeActivity.this,R.layout.activity_listview_report,list);
                listView.setAdapter(adapter);
            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Danh sách thu theo năm");
                int nam;
                List<Report> list=new ArrayList<>();
                Log.d("nam",Calendar.getInstance().get(Calendar.YEAR)+"");
                for(nam=2010;nam<= Calendar.getInstance().get(Calendar.YEAR);nam++){
                    int tong=0;
                    for(ChiTieu ct:chiTieuList){
                        if(ct.getNam()==nam){
                            tong+=ct.getSoTien();
                        }
                    }
                    if(tong>0){
                        list.add(new Report(nam,tong,"Năm"));
                    }
                }
                adapter=new ReportAdapter(InComeActivity.this,R.layout.activity_listview_report,list);
                listView.setAdapter(adapter);
            }
        });
    }

    private void getList(){
        chiTieuList=new ArrayList<>();
        for(ChiTieu ct:MainActivity.chiTieuList){
            if(ct.getLoai()==0){
                chiTieuList.add(ct);
            }
        }
    }
}
