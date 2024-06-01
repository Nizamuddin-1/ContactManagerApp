package com.example.contactmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

public class AddNewClickHandler {
    Contacts contacts;
    Context context;
    MyViewModel myViewModel;
    public AddNewClickHandler(Contacts contacts, Context context,MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel=myViewModel;
    }
    public  void onSubmitBtnClicked(View view)
    {
        if(contacts.getName()==null||contacts.getEmail()==null)
        {
            Toast.makeText(context,"Fields Can be Empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent i=new Intent(context,MainActivity.class);
          //  i.putExtra("Name",contacts.getName());
          //  i.putExtra("Email",contacts.getEmail());

            Contacts c =new Contacts(contacts.getName(),contacts.getEmail());
            myViewModel.addNewContacts(c);
            context.startActivity(i);
        }
    }
}
