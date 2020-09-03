package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.helloworld.model.Temperatures;

import java.util.ArrayList;

public class Adepter extends ArrayAdapter<Temperatures.ResultBean> {
    private ArrayList<Temperatures.ResultBean> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtTemperature, txtMoisture, txtDust1_0, txtDust2_5, txtDust10_0;
    }

    public Adepter(ArrayList<Temperatures.ResultBean> data, Context context) {
        super(context, R.layout.item_template, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Temperatures.ResultBean dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_template, parent, false);

            //หากต้องการเพิ่มอะไรใน list view ให้เพิ่ม code เข้าไปใน UI item_template แล้วมาประกาศตรงนี้
            viewHolder.txtTemperature = (TextView) convertView.findViewById(R.id.temp1);
            viewHolder.txtMoisture = (TextView) convertView.findViewById(R.id.moist1);
            viewHolder.txtDust1_0 = (TextView) convertView.findViewById(R.id.pm1_1);
            viewHolder.txtDust2_5 = (TextView) convertView.findViewById(R.id.pm2_5_1);
            viewHolder.txtDust10_0 = (TextView) convertView.findViewById(R.id.pm10_1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ///////////////////////////////////////////////////////////////////////////////////////

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_template, parent, false);

            //หากต้องการเพิ่มอะไรใน list view ให้เพิ่ม code เข้าไปใน UI item_template แล้วมาประกาศตรงนี้
            viewHolder.txtTemperature = (TextView) convertView.findViewById(R.id.temp2);
            viewHolder.txtMoisture = (TextView) convertView.findViewById(R.id.moist2);
            viewHolder.txtDust1_0 = (TextView) convertView.findViewById(R.id.pm1_2);
            viewHolder.txtDust2_5 = (TextView) convertView.findViewById(R.id.pm2_5_2);
            viewHolder.txtDust10_0 = (TextView) convertView.findViewById(R.id.pm10_2);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //ค่าที่ดึงได้จาก database set ใน TextView
        viewHolder.txtTemperature.setText(dataModel.getTemperature());
        viewHolder.txtMoisture.setText(dataModel.getMoisture());
        viewHolder.txtDust1_0.setText(dataModel.getDust1_0());
        viewHolder.txtDust2_5.setText(dataModel.getDust2_5());
        viewHolder.txtDust10_0.setText(dataModel.getDust10_0());

        // Return the completed view to render on screen
        return convertView;
    }
}