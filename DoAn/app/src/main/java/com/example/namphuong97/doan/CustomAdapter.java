package com.example.namphuong97.doan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Intent.getIntent;

/**
 * Created by namphuong97 on 3/13/18.
 */

public class CustomAdapter extends ArrayAdapter<Device> {

    public String mssv;
    private Context context;
    private int resource;
    private ArrayList<Device> list;

    public CustomAdapter(Context context, int resource, ArrayList<Device> list, String mssv) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.mssv = mssv;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtMssv = convertView.findViewById(R.id.txt_mssv);
            viewHolder.txtTemperature = convertView.findViewById(R.id.txt_temperature);
            viewHolder.txtHumid = convertView.findViewById(R.id.txt_humid);
            viewHolder.txtTime = convertView.findViewById(R.id.txt_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Device device = list.get(list.size() - 1 - position);

        if ((device.getMssv().indexOf(mssv) != -1) && (device.getMssv().length() < mssv.length()+2)) {
            viewHolder.txtMssv.setText(device.getMssv());
            viewHolder.txtTemperature
                    .setText(String.valueOf(device.getTemperature()));
            viewHolder.txtHumid.setText(String.valueOf(device.getHumid()));

            SimpleDateFormat formatter =
                    new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            viewHolder.txtTime
                    .setText(formatter.format(new Date(device.getTime())));

            return convertView;
        } else return null;
    }

    public class ViewHolder {
        TextView txtMssv, txtTemperature, txtHumid, txtTime;
    }
}
