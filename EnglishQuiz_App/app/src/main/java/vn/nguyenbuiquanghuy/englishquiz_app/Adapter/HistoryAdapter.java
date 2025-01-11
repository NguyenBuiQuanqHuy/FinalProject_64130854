package vn.nguyenbuiquanghuy.englishquiz_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.nguyenbuiquanghuy.englishquiz_app.Model.History;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<History> histories;

    public HistoryAdapter(List<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = histories.get(position);
        holder.tvTopic.setText("Grade: " + history.getTopic());
        holder.tvScore.setText("Score: " + history.getScore() + "/" + history.getTotalQuestions());
        holder.tvDateTime.setText("DateTime: " + history.getDateTime());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvTopic, tvScore, tvDateTime;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTopic=itemView.findViewById(R.id.tv_topic);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvDateTime = itemView.findViewById(R.id.tv_date_time);
        }
    }
}
