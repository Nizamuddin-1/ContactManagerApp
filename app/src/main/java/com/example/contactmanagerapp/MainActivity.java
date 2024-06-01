package com.example.contactmanagerapp;

import static com.example.contactmanagerapp.BR.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.os.Bundle;
import android.util.Log;

import com.example.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList=new ArrayList<>();
    MyAdapter myAdapter;
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handler = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handler);
        RecyclerView recyclerView=mainBinding.recycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        contactDatabase =ContactDatabase.getInstance(this);
        //viewModel
        MyViewModel myViewModel =  new ViewModelProvider((ViewModelStoreOwner) this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);
        Contacts c1=new Contacts("Jack","some@gmail.com");
        myViewModel.addNewContacts(c1);

        myViewModel.getAllContacts().observe(this,new Observer<List<Contacts>>() {

            @Override
            public void onChanged(@Nullable List<Contacts> contacts) {
                // Update your UI or perform other actions here
                if (contactsArrayList == null) {
                    contactsArrayList = new ArrayList<>();
                } else {
                    contactsArrayList.clear();
                }
                for (Contacts c : contacts) {
                    Log.v("TAGY", c.getName());
                    contactsArrayList.add(c);
                }
                if (myAdapter != null) {
                    myAdapter.notifyDataSetChanged();
                }
            }

        });

        myAdapter =new MyAdapter(contactsArrayList);


        recyclerView.setAdapter(myAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
              Contacts c= contactsArrayList.get(viewHolder.getAdapterPosition());
              myViewModel.deleteContacts(c);
            }
        }).attachToRecyclerView(recyclerView);

    }
}