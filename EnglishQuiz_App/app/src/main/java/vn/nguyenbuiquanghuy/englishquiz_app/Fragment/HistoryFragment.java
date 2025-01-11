package vn.nguyenbuiquanghuy.englishquiz_app.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import vn.nguyenbuiquanghuy.englishquiz_app.Adapter.HistoryAdapter;
import vn.nguyenbuiquanghuy.englishquiz_app.Database.HistoryDatabase;
import vn.nguyenbuiquanghuy.englishquiz_app.Model.History;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class HistoryFragment extends Fragment {
    RecyclerView rvHistory;
    HistoryDatabase dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        rvHistory = view.findViewById(R.id.rv_history);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        dbHelper = new HistoryDatabase(getActivity());

        loadHistory();
        return view;
    }

    private void loadHistory() {
        Cursor cursor = dbHelper.getAllQuizResults();
        List<History> results = new ArrayList<>();
        while (cursor.moveToNext()) {
            results.add(new History(
                    cursor.getString(cursor.getColumnIndexOrThrow(HistoryDatabase.COLUMN_TOPIC)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(HistoryDatabase.COLUMN_SCORE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(HistoryDatabase.COLUMN_TOTAL_QUESTIONS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(HistoryDatabase.COLUMN_DATE_TIME))
            ));
        }
        cursor.close();

        HistoryAdapter adapter = new HistoryAdapter(results);
        rvHistory.setAdapter(adapter);
    }
}
