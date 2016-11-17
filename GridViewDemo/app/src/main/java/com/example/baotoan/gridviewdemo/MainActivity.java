package com.example.baotoan.gridviewdemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IContactInfo {
    private GridView gridView;
    private ContactAdapter contactAdapter;
    private ImageView avatar;
    private TextView lblName;

    private ArrayList<ContactModel> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grid_view);
        avatar = (ImageView) findViewById(R.id.img_avatar);
        lblName = (TextView) findViewById(R.id.lbl_user_name);

        initData();
        contactAdapter = new ContactAdapter(this, contacts, this);
        gridView.setAdapter(contactAdapter);

    }

    private void initData() {
        contacts.add(new ContactModel(R.drawable.user7, "Luong Thi Dan Quynh", "346346324234"));
        contacts.add(new ContactModel(R.drawable.user1, "Ngo Bao Toan", "01649001142"));
        contacts.add(new ContactModel(0, "Hoang Thi Tu Yeu", "43534534534"));
        contacts.add(new ContactModel(R.drawable.user9, "Mai Duy Linh", "32463642345"));
        contacts.add(new ContactModel(R.drawable.user7, "Luong Thi Dan Quynh", "346346324234"));
        contacts.add(new ContactModel(R.drawable.user3, "Le Nhat Duc", "34535345343"));
        contacts.add(new ContactModel(0, "Huynh Thi Khanh Linh", "463454354434"));
        contacts.add(new ContactModel(R.drawable.user9, "Mai Duy Linh", "32463642345"));
        contacts.add(new ContactModel(R.drawable.user3, "Le Nhat Duc", "34535345343"));
        contacts.add(new ContactModel(R.drawable.user4, "Nguyen Thi Thu Thuy", "34637424534"));
        contacts.add(new ContactModel(0, "Huynh Thi Khanh Linh", "463454354434"));
        contacts.add(new ContactModel(R.drawable.user6, "Tran Van Tinh", "353452342342"));
        contacts.add(new ContactModel(R.drawable.user7, "Luong Thi Dan Quynh", "346346324234"));
        contacts.add(new ContactModel(R.drawable.user8, "Nguyen Duc Huy", "345324523452"));
        contacts.add(new ContactModel(R.drawable.user9, "Mai Duy Linh", "32463642345"));
        contacts.add(new ContactModel(R.drawable.user10, "Huynh Nu Le Thi", "23453453452"));
        contacts.add(new ContactModel(R.drawable.user7, "Luong Thi Dan Quynh", "346346324234"));
        contacts.add(new ContactModel(R.drawable.user3, "Le Nhat Duc", "34535345343"));
        contacts.add(new ContactModel(0, "Huynh Thi Khanh Linh", "463454354434"));
        contacts.add(new ContactModel(R.drawable.user9, "Mai Duy Linh", "32463642345"));
        contacts.add(new ContactModel(0, "Huynh Thi Khanh Linh", "463454354434"));
        contacts.add(new ContactModel(R.drawable.user6, "Tran Van Tinh", "353452342342"));
        contacts.add(new ContactModel(R.drawable.user7, "Luong Thi Dan Quynh", "346346324234"));
        contacts.add(new ContactModel(R.drawable.user8, "Nguyen Duc Huy", "345324523452"));
        contacts.add(new ContactModel(R.drawable.user9, "Mai Duy Linh", "32463642345"));
        contacts.add(new ContactModel(R.drawable.user10, "Huynh Nu Le Thi", "23453453452"));
        contacts.add(new ContactModel(R.drawable.user7, "Luong Thi Dan Quynh", "346346324234"));

    }

    @Override
    public void updateUserInfo(ContactModel model) {
        avatar.setImageResource(model.getImageId());
        lblName.setText(model.getName());
    }
}
