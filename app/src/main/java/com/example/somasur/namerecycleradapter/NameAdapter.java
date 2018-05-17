package com.example.somasur.namerecycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {

    private Context mContext;
    final ArrayList<String> mNames = new ArrayList<>();
    private Random mRandom = new Random();
    private RecyclerView mrecyclerView;

    public NameAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mrecyclerView = recyclerView;
        for (int i = 0; i < 5; i++) {
            mNames.add(getRandomName());
        }
    }

    private String getRandomName() {
        String[] names = new String[]{
                "Hannah", "Emily", "Sarah", "Madison", "Brianna",
                "Kaylee", "Kaitlyn", "Hailey", "Alexis", "Elizabeth",
                "Michael", "Jacob", "Matthew", "Nicholas", "Christopher",
                "Joseph", "Zachary", "Joshua", "Andrew", "William"
        };
        return names[mRandom.nextInt(names.length)];
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mNames.get(position);
        holder.mNameTextView.setText(name);
        holder.mPositionTextView.setText(String.format("I'm in %d", (position + 1)));
    }


    public void addName() {
        mNames.add(0, getRandomName());
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mNames.size());
        //notifyDataSetChanged();
        mrecyclerView.getLayoutManager().scrollToPosition(0);
    }

    public void removeName(int position) {
        mNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mNames.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mPositionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeName(getAdapterPosition());
                    return false;
                }
            });

            mNameTextView = itemView.findViewById(R.id.name_view);
            mPositionTextView = itemView.findViewById(R.id.position_view);
        }
    }
}
