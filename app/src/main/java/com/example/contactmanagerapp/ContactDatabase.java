package com.example.contactmanagerapp;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Contacts.class,exportSchema = false,version = 1)
public abstract class ContactDatabase extends RoomDatabase {


    public abstract ContactDAO getContactDAO();
      private static ContactDatabase dbInstance;
      public static synchronized ContactDatabase getInstance(Context context)
      {
          if(dbInstance==null)
          {
              dbInstance= Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contact_db")
                      .fallbackToDestructiveMigration()
                      .build();
          }
          return dbInstance;
      }
}
