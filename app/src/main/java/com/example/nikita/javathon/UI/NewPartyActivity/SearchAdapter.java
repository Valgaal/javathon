package com.example.nikita.javathon.UI.NewPartyActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikita.javathon.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<MemberModel> members;
    private SearchCallback mCallback;

    interface SearchCallback{
        void searchClick(MemberModel memberModel);
    }

    SearchAdapter(SearchCallback callback){
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(members.get(i).Name);
        viewHolder.itemView.setOnClickListener(view -> mCallback.searchClick(members.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        if(members == null){
            return 0;
        }else{
            return members.size();
        }
    }

    void setMembers(List<MemberModel> members){
        this.members = members;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTv);
        }
    }
}
