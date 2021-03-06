package com.example.alkrox.usefulseconds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.List;

public class RewardedVideoActivity extends AppCompatActivity implements  RewardedVideoAdListener{

    public static final String APP_ID = "ca-app-pub-4481500732535790~8436306462";
    public static final String ANNOUNCE_ID = "ca-app-pub-3940256099942544/5224354917";
    public static final int MONEY = 2;

    //ca-app-pub-3940256099942544/5224354917 GOOGLE TEST
    // ca-app-pub-4481500732535790/5343239260 LE NOTRE
    private RewardedVideoAd mAd;
    private ProgressDialog progress;
    public int numberOfAssoc;
    public Boolean isCategoryClicked = false;
    public String title = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);
        MobileAds.initialize(this, APP_ID);
        ProgressDialog progress = new ProgressDialog(this);

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);

        Intent intent = getIntent();

        this.numberOfAssoc = intent.getIntExtra("numberAssociation", 0);
        this.isCategoryClicked = intent.getBooleanExtra("isCategoryClicked", true);
        this.title = intent.getStringExtra("title");
        loadRewardedVideoAd();
    }

    private void startRewardedVideo() {
        if (mAd.isLoaded()) {
            mAd.show();
        } else {
            Toast.makeText(this, "Vidéo pas encore chargée", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadRewardedVideoAd() {
        mAd.loadAd(ANNOUNCE_ID, new AdRequest.Builder().build() );
        progress = new ProgressDialog(this);
        progress.setTitle("Chargement");
        progress.setMessage("La vidéo est en cours de chargement...");
        progress.setCancelable(false);
        progress.show();
    }

    // Required to reward the user.
    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
        //       reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
        if (isCategoryClicked) {
            Toast.makeText(this, "Merci ! Vous venez de faire un don de " + MONEY + " euros à un fond commun de " + numberOfAssoc + " associations " + this.title + " !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Merci ! Vous venez de faire un don de " + MONEY + " euros à l'association " + this.title + " !", Toast.LENGTH_LONG).show();
        }
        this.finish();
    }

    // The following listener methods are optional.
    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(this, "onRewardedVideoAdLeftApplication",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        this.finish();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "Erreur de chargement de la vidéo", Toast.LENGTH_SHORT).show();
        this.finish();

    }

    @Override
    public void onRewardedVideoAdLoaded() {
        progress.dismiss();
        mAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

}
