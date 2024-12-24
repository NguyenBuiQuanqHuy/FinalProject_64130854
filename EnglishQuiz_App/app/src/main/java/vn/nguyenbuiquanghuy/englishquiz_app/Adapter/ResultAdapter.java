package vn.nguyenbuiquanghuy.englishquiz_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class ResultAdapter extends BaseAdapter {
   Context context;
   List<String> questions;
   List<String> correctAnswers;
   List<String> selectedAnswers;

    public ResultAdapter(Context context, List<String> questions, List<String> correctAnswers, List<String> selectedAnswers) {
        this.context = context;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.selectedAnswers = selectedAnswers;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        }

        TextView tvQuestion = convertView.findViewById(R.id.tv_question);
        TextView tvCorrectAnswer = convertView.findViewById(R.id.tv_correct_answer);
        TextView tvSelectedAnswer = convertView.findViewById(R.id.tv_selected_answer);

        if (correctAnswers.get(position).equals(selectedAnswers.get(position))) {
            // Đáp án chọn đúng, thay đổi màu xanh
            tvSelectedAnswer.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark ));
        } else {
            // Đáp án chọn sai, thay đổi màu đỏ
            tvSelectedAnswer.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }

        tvQuestion.setText(questions.get(position));
        tvCorrectAnswer.setText("True: " + correctAnswers.get(position));
        tvSelectedAnswer.setText("Your Answer: " + selectedAnswers.get(position));

        return convertView;
    }
}

