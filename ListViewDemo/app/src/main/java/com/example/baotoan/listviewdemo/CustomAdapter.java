package com.example.baotoan.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ContactModel> contacts;
    private IContactInfo iContactInfo;

    public CustomAdapter(Context context, ArrayList<ContactModel> contacts, IContactInfo iContactInfo) {
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

    /*
    * Khi listview có nhiều item thì nó tốn rất nhiều bộ nhớ
    * Vì vậy khi list view scroll down (item mới được hiển thị) thì item cũ ở trên nhất sẽ dần ẩn đi và bị xóa bỏ khỏi adapter
    * để tiết kiệm bộ nhớ, item đó ko bị xóa ngay mà nó được lưu vào đối tượng convertView nên chúng ta có thể sử dụng lại nó
    * để tránh việc phải create một rowView mới (tiết kiệm thời gian), lần đầu khởi tạo thì rowView này sẽ là null.
    * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("convertView", convertView + "");
        View rowView = convertView;
        if(null == rowView) {
             /*
            * LayoutInflater lấy Activity hiện tại và inflate một view vào trong đó
            * */
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            /*
            * Parse layout.xml thành đối tượng view để inflate vào Activity nguồn, mất khá nhiều time
            * nếu layout.xml phức tạp - nhiều thành phần
            * */
            rowView = layoutInflater.inflate(R.layout.listview_item, parent, false);
        }

        /*
        * findViewById() sẽ duyệt toàn bộ layout.xml để tìm ra id tương ứng phù hợp
        * Nó mất rất nhiều thời gian, để tiết kiệm time thì sử dụng ViewHolder design pattern
        * để nắm giữ reference cho view
        * */
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.avatar = (ImageView) rowView.findViewById(R.id.avatar);
        viewHolder.lblName = (TextView) rowView.findViewById(R.id.lbl_user_name);
        viewHolder.lblPhone = (TextView) rowView.findViewById(R.id.lbl_phone);
        viewHolder.btnCall = (ImageView) rowView.findViewById(R.id.btn_call);
        rowView.setTag(viewHolder);

        final ContactModel contactModel = contacts.get(position);

        ViewHolder holder = (ViewHolder) rowView.getTag();

        if(contactModel.getImageId() == 0) {
            holder.avatar.setImageResource(R.drawable.user_default);
            contactModel.setImageId(R.drawable.user_default);
        } else {
            holder.avatar.setImageResource(contactModel.getImageId());
        }
        holder.lblName.setText(contactModel.getName());
        holder.lblPhone.setText(contactModel.getPhone());
        holder.btnCall.setImageResource(R.drawable.call);

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iContactInfo.updateUserInfo(contactModel);
            }
        });

        return rowView;
    }

    class ViewHolder {
        ImageView avatar;
        TextView lblName;
        TextView lblPhone;
        ImageView btnCall;
    }
}
