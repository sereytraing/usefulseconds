package com.example.alkrox.usefulseconds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Home extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button_go_rewarded_video = (Button) findViewById(R.id.goRewardedVideo);
        button_go_rewarded_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRewardedVideoActivity();
            }
        });

        Button button_go_banner = (Button) findViewById(R.id.goBanner);
        button_go_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBannerActivity();
            }
        });

        Button button_go_interstitial = (Button) findViewById(R.id.goInterstitial);
        button_go_interstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitialActivity();
            }
        });

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void showRewardedVideoActivity() {
        Intent rewardedVideoIntent = new Intent(this, RewardedVideoActivity.class);
        startActivity(rewardedVideoIntent);
    }

    private void showBannerActivity() {
        //A REMPLIR
    }

    private void showInterstitialActivity() {

    }
}
