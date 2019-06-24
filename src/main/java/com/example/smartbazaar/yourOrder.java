package com.example.smartbazaar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class yourOrder extends AppCompatActivity {

    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);

        getSupportActionBar().setTitle("Your Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show();

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
    public void getJSON(View view){
        new BackgroundTask().execute();
        Toast.makeText(getBaseContext(), "GetJSON: " , Toast.LENGTH_SHORT).show();
    }
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;
        @Override
        protected void onPreExecute(){
            json_url = "https://gih2018.000webhostapp.com/json_get-data.php";
            Toast.makeText(getBaseContext(), "All OK Con" , Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                Toast.makeText(getBaseContext(), "BackJSON: " , Toast.LENGTH_SHORT).show();


                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!= null){

                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String aVoid){

            Toast.makeText(getBaseContext(), "Our Products: "+aVoid.toString().trim() , Toast.LENGTH_SHORT).show();

            TextView proname= (TextView)findViewById(R.id.pro_name);
            proname.setText(aVoid);

            TextView proco= (TextView)findViewById(R.id.pro_co);
            proco.setText(aVoid);

            TextView proprice= (TextView)findViewById(R.id.pro_price);
            proprice.setText(aVoid);

            //////////
            super.onPostExecute(aVoid);
        }
    }

}
