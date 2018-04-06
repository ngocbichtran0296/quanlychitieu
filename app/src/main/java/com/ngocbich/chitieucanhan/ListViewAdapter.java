package com.ngocbich.chitieucanhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ngoc Bich on 4/5/2018.
 */

public class ListViewAdapter extends BaseAdapter{
    private List<ChiTieu> chiTieuList;
    private int layout;
    private Context context;

    public ListViewAdapter(List<ChiTieu> chiTieuList, int layout, Context context) {
        this.chiTieuList = chiTieuList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return chiTieuList.size();
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
        ChiTieu chiTieu=chiTieuList.get(position);

        TextView content=convertView.findViewById(R.id.tvContent);
        TextView amount=convertView.findViewById(R.id.tvAmount);
        TextView type=convertView.findViewById(R.id.tvType);
        TextView note=convertView.findViewById(R.id.tvNote);
        TextView date=convertView.findViewById(R.id.tvDate);

        content.setText("Nội dung: "+chiTieu.getNoidung());
        amount.setText("Số tiền: "+chiTieu.getSoTien());
        if(chiTieu.getLoai()==0){
            type.setText("Hình thức: thu");
        }
        else type.setText("Hình thức: chi");

        date.setText("Ngày: "+chiTieu.getNgay()+"/"+chiTieu.getThang()+"/"+chiTieu.getNam());
        note.setText("Ghi chú: "+chiTieu.getGhiChu());
        return convertView;
    }
}
