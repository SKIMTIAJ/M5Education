package com.imtiaj.m5education.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imtiaj.m5education.DBhelper;
import com.imtiaj.m5education.Dbconncet;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadSchool_pojo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeGridadapterClass extends RecyclerView.Adapter<HomeGridadapterClass.Myholder> {
    /*private int[] image;
    private String[] homeText;*/

    private ArrayList<uploadSchool_pojo> homeItem;
    Context context;
    DBhelper dBhelper;
    /*public HomeGridadapterClass(int[] image, String[] homeText, Context context) {
        this.image = image;
        this.homeText = homeText;
        this.context = context;
    }*/

    public HomeGridadapterClass(ArrayList<uploadSchool_pojo> homeItem, Context context) {
        this.homeItem = homeItem;
        this.context = context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myhomeInflate = LayoutInflater.from(context);
        View view = myhomeInflate.inflate(R.layout.home_first_item_recycler_layout,parent,false);
        /*View view = myhomeInflate.inflate(R.layout.gridview_forhome,parent,false);*/
        dBhelper = new DBhelper(context);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.textView.setText(homeItem.get(position).getUploadName());
       /* holder.imageView.setImageResource(homeItem[position]);*/
        Glide.with(context)
                .load(homeItem.get(position).getUploadImage())
                .centerCrop()
                .circleCrop()
                .into(holder.imageView);

        dBhelper.inspireImageSave(Dbconncet.INSPIRE_TABLE_NAME,homeItem.get(position).getUploadImage(),homeItem.get(position).getUploadName());
    }

    @Override
    public int getItemCount() {
        return homeItem.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView textView,textView2;
        ImageView imageView;
        public Myholder(@NonNull View itemView) {
            super(itemView);
           /* textView = (TextView)itemView.findViewById(R.id.ItemHomeTITLE);
            textView2 = (TextView)itemView.findViewById(R.id.ItemHomeDES);
            imageView = (ImageView)itemView.findViewById(R.id.ItemHomeIMG);*/

            textView = (TextView)itemView.findViewById(R.id.ItemHomeTITLE);
            //textView2 = (TextView)itemView.findViewById(R.id.ItemHomeDES);
            imageView = (ImageView)itemView.findViewById(R.id.ItemHomeIMG);
        }
    }
}
