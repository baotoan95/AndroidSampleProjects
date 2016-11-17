package com.example.baotoan.gridviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BaoToan on 10/29/2016.
 */

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ContactModel> contacts;
    private IContactInfo iContactInfo;

    public ContactAdapter(Context context, ArrayList<ContactModel> contacts, IContactInfo iContactInfo) {
        this.context = context;
        this.contacts = contacts;
        this.iContactInfo = iContactInfo;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(null == rowView) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.gridview_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.imgAvatar = (ImageView) rowView.findViewById(R.id.avatar);
        viewHolder.lblName = (TextView) rowView.findViewById(R.id.lbl_name);
        viewHolder.lblPhone = (TextView) rowView.findViewById(R.id.lbl_phone);
        rowView.setTag(viewHolder);

        final ContactModel contactModel = contacts.get(position);
        ViewHolder holder = (ViewHolder) rowView.getTag();
        if(contactModel.getImageId() == 0) {
            holder.imgAvatar.setImageResource(R.drawable.user_default);
            contactModel.setImageId(R.drawable.user_default);
        } else {
            holder.imgAvatar.setImageResource(contactModel.getImageId());
        }
        holder.lblName.setText(contactModel.getName());
        holder.lblPhone.setText(contactModel.getPhone());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iContactInfo.updateUserInfo(contactModel);
            }
        });


        return rowView;
    }

    class ViewHolder {
        ImageView imgAvatar;
        TextView lblName;
        TextView lblPhone;
    }
}
