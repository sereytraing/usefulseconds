package com.example.alkrox.usefulseconds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AssociationDatabase associationDatabase = new AssociationDatabase(this);
    public static final String CATEGORY1 = "humanitaires";
    public static final String CATEGORY2 = "environnementales";
    public static final String CATEGORY3 = "artistiques et culturelles";
    ListView listView;

    public String category;

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
                refreshListView(CATEGORY1);
            }
        });
        leftButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setCategoryRewardView(CATEGORY1);
                return true;
            }
        });

        ImageButton centerButton = (ImageButton) findViewById(R.id.imageButtonCenter);
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView(CATEGORY2);
            }
        });
        centerButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setCategoryRewardView(CATEGORY2);
                return true;
            }
        });

        ImageButton rightButton = (ImageButton) findViewById(R.id.imageButtonRight);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshListView(CATEGORY3);
            }
        });
        rightButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setCategoryRewardView(CATEGORY3);
                return true;
            }
        });



        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, RewardedVideoActivity.class);
                String title = associationDatabase.getAssocInCategory(category).get((int)id).getName();
                intent.putExtra("title", title);
                intent.putExtra("isCategoryClicked", false);
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
        TextView noticeText = (TextView) findViewById(R.id.noticeText);
        noticeText.setVisibility(View.INVISIBLE);
        this.category = category;
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, tabName);
        listView.setAdapter(adapter);
    }

    public void setCategoryRewardView(String category){
        int nb = getNumberOfAssoc(category);
        Intent intent = new Intent(HomeActivity.this, RewardedVideoActivity.class);
        intent.putExtra("numberAssociation", nb);
        intent.putExtra("title", category);
        intent.putExtra("isCategoryClicked", true);
        startActivity(intent);
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
        Association association1 = new Association("LA CROIX ROUGE FRANÇAISE", CATEGORY1, 2);
        Association association2 = new Association("MÉDECINS SANS FRONTIÈRES", CATEGORY1, 2);
        Association association3 = new Association("WWF", CATEGORY2, 2);
        Association association4 = new Association("SPA", CATEGORY2, 2);
        Association association5 = new Association("UNESCO", CATEGORY3, 2);
        Association association6 = new Association("ICCROM", CATEGORY3, 2);

        associationDatabase.addAssociation(association1);
        associationDatabase.addAssociation(association2);
        associationDatabase.addAssociation(association3);
        associationDatabase.addAssociation(association4);
        associationDatabase.addAssociation(association5);
        associationDatabase.addAssociation(association6);

    }
}
