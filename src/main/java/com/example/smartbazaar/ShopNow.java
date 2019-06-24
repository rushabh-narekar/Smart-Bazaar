package com.example.smartbazaar;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ShopNow extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zx1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_now);
        zx1 = new ZXingScannerView(this);
        setContentView(zx1);

        getSupportActionBar().setTitle("Shop Now");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        zx1.setResultHandler(this);
        zx1.startCamera();

    }
    @Override
    protected void onPause() {
        super.onPause();
        zx1.stopCamera();
    }
    //Cart Activity
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main2, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Cart Activity Intent
    @Override
    public boolean onOptionsItemSelected(MenuItem item  ) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            // Handle the camera action
            Intent i=new Intent(getApplicationContext(),cartActivity.class);
            startActivity(i);
        }
        else
        {
            this.finish();
        }

            return super.onOptionsItemSelected(item);
    }
    //END Action bar
    @Override
    public void handleResult(Result result) {
        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
        toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_AUTOREDIAL_LITE, 4000);
        finish();
        Toast.makeText(getApplicationContext(), result.getText().toString(), Toast.LENGTH_LONG).show();

    }
}


