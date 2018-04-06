package com.ngocbich.chitieucanhan;

import android.content.Intent;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    public static List<ChiTieu> list;
    private ListView listView;
    public static List<ChiTieu> chiTieuList;

    public static MoneyDAO moneyDAO;
    private DBHelper dbHelper;
    private ListViewAdapter adapter;
    private FloatingActionButton faButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       new DoGets().execute("http://10.0.3.2:9000/api/MoneyLog");

       dbHelper=new DBHelper(this,"quanlychitieu",null,1);
       moneyDAO=MoneyDAO.getInstence(this.getApplicationContext());

        faButton=findViewById(R.id.fabAdd);
        listView=findViewById(R.id.lvItem);

        chiTieuList=new ArrayList<>();
        list=new ArrayList<>();

        chiTieuList=moneyDAO.query();
      /*  chiTieuList.add(new ChiTieu("Tien luong",5000000,0,"",30,3,2018));
        chiTieuList.add(new ChiTieu("Tien phu cap",1000000,0,"",10,1,2018));
        chiTieuList.add(new ChiTieu("Tien thuong",1500000,0,"",27,2,2018));
        chiTieuList.add(new ChiTieu("Tien ban hang",2000000,0,"",8,3,2018));
        chiTieuList.add(new ChiTieu("Tien thue nha",1000000,1,"",1,2,2018));
        chiTieuList.add(new ChiTieu("Tien thue nha",1000000,1,"",1,3,2018));*/


        adapter=new ListViewAdapter(chiTieuList,R.layout.activity_listview,MainActivity.this);
        listView.setAdapter(adapter);

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                position=pos;
                Intent intent=new Intent(MainActivity.this,UpdateDeleteActivity.class);
                startActivity(intent);


                return true;
            }
        });
    }
    public static int position;
    @Override
    protected void onResume() {
        super.onResume();
        for(ChiTieu ct:list){
            chiTieuList.add(ct);
        }
        list.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mnIncome:
                Intent intent=new Intent(MainActivity.this,InComeActivity.class);
                startActivity(intent);
                break;
            case R.id.mnOutcome:
                Intent outComeIntent=new Intent(MainActivity.this,OutComeActivity.class);
                startActivity(outComeIntent);
                break;
        }
        return true;
    }

    class DoGets extends AsyncTask<String,Void,Integer> {

        @Override
        protected Integer doInBackground(String... paramas) {

            String urlString=paramas[0];
            Log.d("url",urlString+"");
            URL url=null;
            HttpsURLConnection httpsURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            Log.d("kn","abc");
            try{
                url = new URL(urlString);
                httpsURLConnection=(HttpsURLConnection)url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);

                httpsURLConnection.connect();
                inputStream=httpsURLConnection.getInputStream();

                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

             /*   JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;
                List<ChiTieu> chiTieus=new ArrayList<>();
                for (int i=0;i<jsonArray.length();i++){
                    jsonObject=jsonArray.getJSONObject(i);
                    chiTieus.add(new ChiTieu(jsonObject.getInt("id"),jsonObject.getInt("soTien"),
                            jsonObject.getString("BNAME")));
                }
                for (ChiTieu ct:chiTieus){
                    Log.d("Kết quả",ct.toString());
                }
*/
            }catch (Exception ex) {
                ex.printStackTrace();
                return 500;
            }
            return 200;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==500){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
    }
}
