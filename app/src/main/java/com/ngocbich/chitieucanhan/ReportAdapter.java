package com.ngocbich.chitieucanhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ngoc Bich on 4/6/2018.
 */

public class ReportAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Report> reportList;

    public ReportAdapter(Context context, int layout, List<Report> reportList) {
        this.context = context;
        this.layout = layout;
        this.reportList = reportList;
    }

    @Override
    public int getCount() {
        return reportList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        Report re=reportList.get(position);

        TextView category=convertView.findViewById(R.id.textview);
        TextView total=convertView.findViewById(R.id.total);
        TextView noidung=convertView.findViewById(R.id.tvnoidung);

        noidung.setText(re.getNoiDung()+" ");
        category.setText(re.getCategory()+"");
        total.setText("Tổng thu: "+re.getTotal());
        return convertView;
    }
}
