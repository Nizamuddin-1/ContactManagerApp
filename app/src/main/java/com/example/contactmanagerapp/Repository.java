package com.example.contactmanagerapp;

import static android.os.Looper.getMainLooper;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

public class Repository {
    // Available data sources
    // RoomDatabase
    private final ContactDAO contactDAO;
    ExecutorService executor;
    Handler handler;
    public Repository(Application application) {
        ContactDatabase contactDatabase=ContactDatabase.getInstance(application);

        this.contactDAO = contactDatabase.getContactDAO();
        // Used for background database operations
        executor= Executors.newSingleThreadExecutor();
        //used for UI updating
        handler=new Handler(Looper.getMainLooper());
    }
    public void addContact(Contacts contacts)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });


    }
    public void deleteContact(Contacts contacts)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });

    }
    public LiveData<List<Contacts> >getAllContacts(){

        return contactDAO.getAllContact();
    }

}
