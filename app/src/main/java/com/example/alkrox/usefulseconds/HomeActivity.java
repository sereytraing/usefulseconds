package com.example.alkrox.usefulseconds;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AssociationDatabase associationDatabase = new AssociationDatabase(this);

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        listView = (ListView) findViewById(R.id.listView);
        ImageButton leftButton = (ImageButton) findViewById(R.id.imageButtonLeft);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView("humanitaire");
            }
        });

        ImageButton centerButton = (ImageButton) findViewById(R.id.imageButtonCenter);
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView("environnement");
            }
        });

        ImageButton rightButton = (ImageButton) findViewById(R.id.imageButtonRight);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView("artculture");
            }
        });

        this.refreshListView("humanitaire");


        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(HomeActivity.this, RewardedVideoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void refreshListView(String category) {
        List<Association> listeAssociation = associationDatabase.getAssocInCategory(category);
        String tabName[] = new String[listeAssociation.size()];
        for(int i = 0; i < listeAssociation.size(); i++) {
            tabName[i] = listeAssociation.get(i).getName();
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, tabName);
        listView.setAdapter(adapter);
    }
}
