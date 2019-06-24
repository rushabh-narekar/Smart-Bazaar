package com.example.smartbazaar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String JSON_STRING;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    //GridView gridView;

    RecyclerView recyclerView;

    ArrayList<ModelMy> myArrayList;

   //   String productname[] = {"Apples", "Vim Bar","Odonil","Mangos","Khari","Spirte","Coca Cola"};
    //int productimages[] = {R.drawable.pro6, R.drawable.pro9, R.drawable.pro10, R.drawable.pro5, R.drawable.pro3, R.drawable.pro8, R.drawable.pro7};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            recyclerView=findViewById(R.id.recv);
            myArrayList = new ArrayList<>();

            myArrayList.add(new ModelMy(R.drawable.pro10, "HouseHold", "Mangoes", "1000"));
            myArrayList.add(new ModelMy(R.drawable.pro2, "Branded Foods", "Wiliam", "20"));
            myArrayList.add(new ModelMy(R.drawable.pro3, "Cloths", "Shoes", "200"));
            myArrayList.add(new ModelMy(R.drawable.pro4, "Bakery", "Khari", "10"));

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            RecyclerView.LayoutManager rvlayoutManager = layoutManager;
             recyclerView.setLayoutManager(rvlayoutManager);

             MyAdapter adapter= new MyAdapter(this,myArrayList);
            recyclerView.setAdapter(adapter);


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //gridView = findViewById(R.id.gridview);

            setSupportActionBar(toolbar);
            Toast.makeText(getApplicationContext(),"Main",Toast.LENGTH_SHORT).show();
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();


            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);



          //  CustomAdapter customadapter = new CustomAdapter();
            //gridView.setAdapter(customadapter);

            //gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              //  @Override
                //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   // Intent intent= new Intent(getApplicationContext(), ProductsDetailsActivity.class);
                    //intent.putExtra("name" , productname[position]);
                    //intent.putExtra("image" , productimages[position]);
                    //startActivity(intent);
                }
            //});
        public void getJSON(View view){
           new BackgroundTask().execute();
        }
        class BackgroundTask extends AsyncTask<Void, Void, String>{
            String json_url;
            @Override
            protected void onPreExecute(){
                json_url = "https://gih2018.000webhostapp.com/json_get-data.php";
            }
            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL(json_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

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

                Toast.makeText(getBaseContext(), aVoid.toString().trim() , Toast.LENGTH_SHORT).show();
                TextView itemname= (TextView)findViewById(R.id.item_name);
                itemname.setText(aVoid);

                //////////
                super.onPostExecute(aVoid);
            }
        }
        //}

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected( @NonNull MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.home) {
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                // Handle the camera action
            }else if (id == R.id.shopnow) {
                 Intent intent= new Intent(getApplicationContext(),ShopNow.class);
                 startActivity(intent);

            } else if (id == R.id.order)
            {
                Intent intent= new Intent(getApplicationContext(),yourOrder.class);
                startActivity(intent);

            } else if (id == R.id.payment) {

            } else if (id == R.id.category) {

            } else if (id == R.id.faq) {

            } else if (id == R.id.account) {

            } else if (id == R.id.about) {

            } else if (id == R.id.privacy) {

            } else if (id == R.id.login) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }
        @Override
        public void onBackPressed()
        {
            if (drawer.isDrawerOpen(GravityCompat.START))
            {
                drawer.closeDrawer(GravityCompat.START);
            }
            else
            {
                super.onBackPressed();
            }

        }

        public boolean onCreateOptionsMenu(Menu menu)
        {
            getMenuInflater().inflate(R.menu.main2, menu);
            return super.onCreateOptionsMenu(menu);
        }

        //CART ACTIVITY OPEN...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent=new Intent(MainActivity.this, cartActivity.class);
            startActivity(intent);
            return super.onOptionsItemSelected(item);
    }
/*
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return productimages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_data, null);
            TextView name = view.findViewById(R.id.products);
            ImageView image = view.findViewById(R.id.imageView);

            name.setText(productname[position]);
            image.setImageResource(productimages[position]);
             return view;
        }
    }
*/
    }

