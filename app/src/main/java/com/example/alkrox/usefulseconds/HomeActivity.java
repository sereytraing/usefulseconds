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
    public static final String ENVIRONNEMENT = "environnement";
    public static final String HUMANITAIRE = "humanitaire";
    public static final String ARTCULTURE = "artculture";
    ListView listView;

    @Override
    protected void onStart() {
        super.onStart();
        //this.addDataInDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        listView = (ListView) findViewById(R.id.listView);
        ImageButton leftButton = (ImageButton) findViewById(R.id.imageButtonLeft);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView(HUMANITAIRE);
            }
        });
        leftButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int nb = getNumberOfAssoc(HUMANITAIRE);
                Intent intent = new Intent(HomeActivity.this, RewardedVideoActivity.class);
                intent.putExtra("numberAssociation", nb);
                intent.putExtra("isCategoryClicked", true);
                startActivity(intent);
                return true;
            }
        });

        ImageButton centerButton = (ImageButton) findViewById(R.id.imageButtonCenter);
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView(ENVIRONNEMENT);
            }
        });
        centerButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int nb = getNumberOfAssoc(ENVIRONNEMENT);
                Intent intent = new Intent(HomeActivity.this, RewardedVideoActivity.class);
                intent.putExtra("numberAssociation", nb);
                intent.putExtra("isCategoryClicked", true);
                startActivity(intent);
                return true;
            }
        });


        ImageButton rightButton = (ImageButton) findViewById(R.id.imageButtonRight);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView(ARTCULTURE);
            }
        });

        rightButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int nb = getNumberOfAssoc(ARTCULTURE);
                Intent intent = new Intent(HomeActivity.this, RewardedVideoActivity.class);
                intent.putExtra("numberAssociation", nb);
                intent.putExtra("isCategoryClicked", true);
                startActivity(intent);
                return true;
            }
        });

        this.refreshListView(HUMANITAIRE);

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

    public int getNumberOfAssoc(String category) {
        List<Association> listeAssociation = associationDatabase.getAssocInCategory(category);
        String tabName[] = new String[listeAssociation.size()];
        for(int i = 0; i < listeAssociation.size(); i++) {
            tabName[i] = listeAssociation.get(i).getName();
        }
        return tabName.length;
    }

    private void addDataInDatabase() {
        Association association1 = new Association("LA CROIX ROUGE FRANÇAISE", HUMANITAIRE, 2);
        Association association2 = new Association("MÉDECINS SANS FRONTIÈRES", HUMANITAIRE, 2);
        Association association3 = new Association("WWF", ENVIRONNEMENT, 2);
        Association association4 = new Association("SPA", ENVIRONNEMENT, 2);
        Association association5 = new Association("UNESCO", ARTCULTURE, 2);
        Association association6 = new Association("ICCROM", ARTCULTURE, 2);

        associationDatabase.addAssociation(association1);
        associationDatabase.addAssociation(association2);
        associationDatabase.addAssociation(association3);
        associationDatabase.addAssociation(association4);
        associationDatabase.addAssociation(association5);
        associationDatabase.addAssociation(association6);

    }
}
