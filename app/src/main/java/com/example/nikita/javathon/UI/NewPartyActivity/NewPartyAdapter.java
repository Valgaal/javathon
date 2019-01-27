package com.example.nikita.javathon.UI.NewPartyActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikita.javathon.R;

import java.util.ArrayList;
import java.util.List;

public class NewPartyAdapter extends RecyclerView.Adapter<NewPartyAdapter.ViewHolder> {

    private List<MemberModel> members;
    private RemoveCallback mCallback;

    interface RemoveCallback{
        void remove(MemberModel memberModel);
    }

    NewPartyAdapter(RemoveCallback callback){
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.added_members_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(members.get(i).Name);
        viewHolder.imageView.setOnClickListener(view -> mCallback.remove(members.get(i)));
    }

    @Override
    public int getItemCount() {
        if(members == null){
            return 0;
        }else{
            return members.size();
        }
    }

    void setMember(MemberModel member){
        if(members == null || members.size() == 0){
            members = new ArrayList<>();
        }
        members.add(member);
        notifyItemInserted(members.size());
    }

    void removeMember(MemberModel memberModel){
        if(members == null || members.size() == 0){
        }else{
            members.remove(memberModel);
            notifyItemRemoved(members.size() + 1);
        }
    }

    List<MemberModel>  getMembers(){
        return  members;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTv);
            imageView = itemView.findViewById(R.id.removeIv);
        }
    }
}
