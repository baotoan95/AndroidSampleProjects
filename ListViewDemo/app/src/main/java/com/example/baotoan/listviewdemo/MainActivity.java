package com.example.baotoan.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, IContactInfo {
    private ListView listView;
    private ImageView imgAvatar;
    private TextView lblName;

    private ArrayList<ContactModel> contacts = new ArrayList<>();
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        listView = (ListView) findViewById(R.id.list_view);
        customAdapter = new CustomAdapter(this, contacts, this);
        listView.setAdapter(customAdapter);

        imgAvatar = (ImageView) findViewById(R.id.img_avatar);
        lblName = (TextView) findViewById(R.id.lbl_user_name);

        listView.setOnItemClickListener(this);

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

    public void updateUserInfo(ContactModel model) {
        lblName.setText(model.getName());
        imgAvatar.setImageResource(model.getImageId());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, contacts.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
