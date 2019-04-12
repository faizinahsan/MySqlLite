package com.example.mysqllite;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private ArrayList<Note> listNotes = new ArrayList<>();
    private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Note> getListNotes() {
        return listNotes;
    }
    public void setListNotes(ArrayList<Note> listNotes){
        if (listNotes.size() > 0) {
            this.listNotes.clear();
        }
        this.listNotes.addAll(listNotes);
        notifyDataSetChanged();
    }
//3 metode untuk menambahkan, memperbaharui dan menghapus Item di RecyclerView.
    public void addItem(Note note){
        this.listNotes.add(note);
        notifyItemInserted(listNotes.size()-1);
    }
    public void updateItem(int position, Note note){
        this.listNotes.set(position,note);
        notifyItemChanged(position,note);
    }
    public void removeItem(int position){
        this.listNotes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listNotes.size());
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note,viewGroup,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position) {
        noteViewHolder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemCLickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity,NoteAddUpdateActivity.class);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, listNotes.get(position));
                activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvDate;
        final CardView cvNote;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
