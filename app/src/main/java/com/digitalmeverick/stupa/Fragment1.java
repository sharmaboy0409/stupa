package com.digitalmeverick.stupa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends Fragment {//main fargment showing data

  RecyclerView recyclerView;
  RecyclerAdapter recyclerAdapter;



    public Fragment1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view= inflater.inflate(R.layout.fragment_1, container, false);

                recyclerView= view.findViewById(R.id.recviewUser);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserDatabase userDatabase=UserDatabase.getUserDatabase(getContext());
                        List<UserEntity>userLIST=userDatabase.userDao().getAlluser();
                        recyclerAdapter=new RecyclerAdapter(getContext(),userLIST);
                        recyclerView.setAdapter(recyclerAdapter);

                    }
                }).start();

                return view;
    }
}