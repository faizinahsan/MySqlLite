package com.example.mysqllite;

import java.util.ArrayList;

public interface LoadNotesCallback {
    void preExecute();
    void postExecute(ArrayList<Note> notes);
}
