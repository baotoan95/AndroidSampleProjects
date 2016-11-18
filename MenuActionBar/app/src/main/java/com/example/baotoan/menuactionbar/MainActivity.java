package com.example.baotoan.menuactionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        toolBar.setTitle("BTIT95");
        toolBar.setSubtitle("Share To Study");

        setSupportActionBar(toolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        switch (itemSelected) {
            case R.id.add_item: Toast.makeText(this, "Action add", Toast.LENGTH_SHORT).show(); break;
            case R.id.save_item: Toast.makeText(this, "Action save", Toast.LENGTH_SHORT).show(); break;
            case R.id.attach_item: Toast.makeText(this, "Action attach", Toast.LENGTH_SHORT).show(); break;
            case R.id.setting_item: Toast.makeText(this, "Action setting", Toast.LENGTH_SHORT).show(); break;
        }
        return true;
    }
}
