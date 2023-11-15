package com.example.traviling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    ArrayList<Traviling>list;
    Context context ;

    public MyAdapter(Context context, ArrayList<Traviling> list) {
        this.context = context;
        list = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<Traviling> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_main,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Traviling product=list.get(position);
        holder.Name.setText(product.getName());
        holder.Price.setText(product.getPhone());
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name,Price;
        ImageView proPicture;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.passsignup);
            Price=itemView.findViewById(R.id.confirmpasssignup);
            proPicture=itemView.findViewById(R.id.loginbtn_signup);
        }
    }
}
