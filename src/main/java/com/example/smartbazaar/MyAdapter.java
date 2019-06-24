package com.example.smartbazaar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<ModelMy> mList;

    MyAdapter(Context context, ArrayList<ModelMy> list){
        mContext =context;
        mList=list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
       View view=   layoutInflater.inflate(R.layout.rv1_item,viewGroup,false);
          ViewHolder viewHolder=new ViewHolder(view);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       ModelMy myItem=mList.get(i);
        ImageView image = viewHolder.item_image;
        TextView name,place,price;

        name = viewHolder.item_name;
        place = viewHolder.item_place;
        price= viewHolder.item_price;

        image.setImageResource(myItem.getImage());

        name.setText(myItem.getName());
        place.setText(myItem.getPlace());
        price.setText(myItem.getPrice());





    }

    @Override
    public int getItemCount() {
        return mList .size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_image;
        TextView item_name,item_place,item_price;


        public ViewHolder(View itemView){
            super(itemView);

            item_image=itemView.findViewById(R.id.item_image);
            item_name=itemView.findViewById(R.id.item_name);
            item_place=itemView.findViewById(R.id.item_palace);
            item_price=itemView.findViewById(R.id.item_price);



        }
    }
}
