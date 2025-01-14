package com.example.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.contactmanagerapp.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {
    private ActivityAddNewContactBinding  binding;
    private AddNewClickHandler handler;
    private Contacts contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        contacts=new Contacts();
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_new_contact
        );

        MyViewModel myViewModel=new ViewModelProvider(this).get(MyViewModel.class);
        handler=new AddNewClickHandler(contacts,this,myViewModel);
        binding.setContacts(contacts);
        binding.setClickHandler(handler);

    }

}