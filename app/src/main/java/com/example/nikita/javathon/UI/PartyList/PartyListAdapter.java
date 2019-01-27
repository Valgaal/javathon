package com.example.nikita.javathon.UI.PartyList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikita.javathon.R;

import java.util.List;

public class PartyListAdapter extends RecyclerView.Adapter<PartyListAdapter.ViewHolder> {

    private List<PartyListModel> parties;
    private PartiesCallback mCallback;

    interface PartiesCallback{
        void partyClick(int partyId);
    }

    PartyListAdapter(PartiesCallback callback){
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.party_list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.count.setText(parties.get(i).Price);
        viewHolder.name.setText(parties.get(i).Name);
        viewHolder.itemView.setOnClickListener(view -> mCallback.partyClick(parties.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        if(parties == null){
            return 0;
        }else{
            return parties.size();
        }
    }

    void setParties(List<PartyListModel> partyListModels){
        this.parties = partyListModels;
    }

    void setParty(PartyListModel party){
        parties.add(party);
        notifyItemInserted(parties.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView count;
        private TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.countTv);
            name = itemView.findViewById(R.id.nameTv);
        }
    }
}
