package com.example.contactmanagerapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {
    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    private ContactListItemBinding contactListItemBinding;

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactListItemBinding contactListItemBinding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,parent,false
                        );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contactListItemBinding.setContact(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        if(contacts!=null)
           return contacts.size();
        return 0;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;
        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding){
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding=contactListItemBinding;

        }

    }
}
