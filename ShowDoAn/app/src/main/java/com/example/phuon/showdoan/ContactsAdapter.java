package com.example.phuon.showdoan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by namphuong97 on 3/22/18.
 */

public class ContactsAdapter extends ArrayAdapter {

    List list = new ArrayList();
    Context context;

    public ContactsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }


    public void add(Contacts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowl;
        rowl = convertView;
        ContactHolder contactHolder;

        if (rowl == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowl = layoutInflater.inflate(R.layout.row,parent,true);
            contactHolder = new ContactHolder();
            //contactHolder.id = (TextView) rowl.findViewById(R.id.ID);
            contactHolder.humi = rowl.findViewById(R.id.textHum);
            contactHolder.temp = rowl.findViewById(R.id.textTemp);
            contactHolder.time = rowl.findViewById(R.id.textTime);
            rowl.setTag(contactHolder);
        } else {
            contactHolder = (ContactHolder) rowl.getTag();
        }
        final Contacts contacts = (Contacts) this.getItem(position);
        //contactHolder.id.setText(contacts.getId());
        contactHolder.humi.setText(String.valueOf(contacts.getHumid())+ "%");
        contactHolder.temp.setText(String.valueOf(contacts.getTemperature())+"*C");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        contactHolder.time.setText(formatter.format(new Date(contacts.getTime())));

        return rowl;
    }

    static class ContactHolder{
        TextView id,temp,humi,time;
    }
}
