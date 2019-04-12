package com.example.mysqllite;

import android.view.View;

//Kelas di atas bertugas membuat item seperti CardView bisa diklik di dalam adapter. Caranya lakukan penyesuaian pada kelas event OnClickListener. Alhasil kita bisa mengimplementasikan interface listener yang baru bernama OnItemClickCallback. Kelas tersebut dibuat untuk menghindari nilai final dari posisi yang tentunya sangat tidak direkomendasikan.
public class CustomOnItemClickListener implements View.OnClickListener {
    private int position;
    private OnItemCLickCallback onItemCLickCallback;

    public CustomOnItemClickListener(int position, OnItemCLickCallback onItemCLickCallback){
        this.position = position;
        this.onItemCLickCallback = onItemCLickCallback;
    }
    @Override
    public void onClick(View v) {

    }
    public interface OnItemCLickCallback{
        void onItemClicked(View view, int position);
    }
}
