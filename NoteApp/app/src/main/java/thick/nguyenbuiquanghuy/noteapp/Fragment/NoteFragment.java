package thick.nguyenbuiquanghuy.noteapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import thick.nguyenbuiquanghuy.noteapp.AddNoteActivity;
import thick.nguyenbuiquanghuy.noteapp.R;

public class NoteFragment extends Fragment {
    FloatingActionButton addNoteButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        addNoteButton=view.findViewById(R.id.addnote_btn);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), AddNoteActivity.class);
            }
        });
        return view;
    }
}