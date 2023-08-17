package com.example.notifactionlistener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class notificationAdapter extends ArrayAdapter<NotificationDetails> {
    int mresource;
    Context mcontext;
    public notificationAdapter(@NonNull Context context, int resource, @NonNull List<NotificationDetails> objects) {
        super(context, resource, objects);
        mresource = resource;
        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String app_name = getItem(position).getAppName();
        String title = getItem(position).getTitle();
        String text = getItem(position).getText();
        String date = getItem(position).getDate();

        NotificationDetails nd = new NotificationDetails(date, app_name, title, text);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView appName = convertView.findViewById(R.id.app_name);
        TextView appTitle = convertView.findViewById(R.id.title);
        TextView appText = convertView.findViewById(R.id.text);
        TextView appDate = convertView.findViewById(R.id.date);

        appName.setText(app_name);
        appTitle.setText(title);
        appText.setText(text);
        appDate.setText(date);

        return convertView;
    }
}
