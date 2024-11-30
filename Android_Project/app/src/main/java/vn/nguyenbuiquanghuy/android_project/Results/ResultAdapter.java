package vn.nguyenbuiquanghuy.android_project.Results;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.nguyenbuiquanghuy.android_project.R;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<Result> resultList;

    public ResultAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }
    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = resultList.get(position);

        // Hiển thị câu hỏi
        holder.tvQuestion.setText(result.getQuestion());

        // Danh sách các đáp án và TextView tương ứng
        String[] options = {result.getOption1(), result.getOption2(), result.getOption3(), result.getOption4()};
        TextView[] optionViews = {holder.tvOption1, holder.tvOption2, holder.tvOption3, holder.tvOption4};

        // Cập nhật nội dung và màu sắc cho từng đáp án
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            optionViews[i].setText(result.getCorrectAnswer().equals(option) ? "✓ " + option : option);
            optionViews[i].setTextColor(getOptionColor(result, option));
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    // Đặt màu cho từng đáp án
    private int getOptionColor(Result result, String option) {
        if (result.getCorrectAnswer().equals(option)) {
            return Color.parseColor("#388E3C"); // Xanh lá cây
        } else if (result.getSelectedAnswer().equals(option) && !result.getCorrectAnswer().equals(option)) {
            return Color.parseColor("#D32F2F"); // Đỏ
        } else {
            return Color.BLACK; // Màu mặc định
        }
    }

    // ViewHolder class
    static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion, tvOption1, tvOption2, tvOption3, tvOption4;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            tvOption1 = itemView.findViewById(R.id.tv_option1);
            tvOption2 = itemView.findViewById(R.id.tv_option2);
            tvOption3 = itemView.findViewById(R.id.tv_option3);
            tvOption4 = itemView.findViewById(R.id.tv_option4);
        }
    }
}
