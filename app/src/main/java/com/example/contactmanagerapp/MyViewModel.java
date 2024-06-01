package com.example.contactmanagerapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModel.*;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Contacts>> allContacts;
    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allContacts = repository.getAllContacts();
    }




    public LiveData<List<Contacts>> getAllContacts()
    {
       return allContacts;
    }
    public void addNewContacts(Contacts contacts)
    {
        repository.addContact(contacts);
    }
    public void deleteContacts(Contacts contacts)
    {
        repository.deleteContact(contacts);
    }
}
