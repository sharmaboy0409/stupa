package com.digitalmeverick.stupa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myviewholder>{

    Context context;
    List<UserEntity> userlist;

    public RecyclerAdapter(Context context, List<UserEntity> userlist) {
        this.context = context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        if (userlist!=null){
            holder.name.setText(userlist.get(position).getUserName());
            holder.phone.setText(userlist.get(position).getPhone());
            holder.email.setText(userlist.get(position).getEmail());
            holder.country.setText(userlist.get(position).getCountry());

        }

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView name,phone,email,country;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameR);
            phone=itemView.findViewById(R.id.phoneR);
            email=itemView.findViewById(R.id.emailR);
            country=itemView.findViewById(R.id.countryR);
        }
    }
}
