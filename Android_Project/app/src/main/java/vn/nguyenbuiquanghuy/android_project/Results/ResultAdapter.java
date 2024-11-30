package vn.nguyenbuiquanghuy.android_project.Results;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.nguyenbuiquanghuy.android_project.Quiz.Questions;
import vn.nguyenbuiquanghuy.android_project.R;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    Context context;
    List<Questions> questionList;

    public ResultAdapter(Context context, List<Questions> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vItem = inflater.inflate(R.layout.topic_items, parent, false);
        return new ResultViewHolder(vItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Questions question = questionList.get(position);

        // Gán câu hỏi và các đáp án
        holder.tvQuestion.setText(question.getQuestion());
        holder.rbOption1.setText(question.getOption1());
        holder.rbOption2.setText(question.getOption2());
        holder.rbOption3.setText(question.getOption3());
        holder.rbOption4.setText(question.getOption4());

        // Kiểm tra và thay đổi màu nền của các đáp án
        highlightCorrectAnswer(holder, question);
    }

    private void highlightCorrectAnswer(ResultViewHolder holder, Questions question) {
        // Reset lại màu nền của tất cả các đáp án
        holder.rbOption1.setBackgroundColor(Color.TRANSPARENT);
        holder.rbOption2.setBackgroundColor(Color.TRANSPARENT);
        holder.rbOption3.setBackgroundColor(Color.TRANSPARENT);
        holder.rbOption4.setBackgroundColor(Color.TRANSPARENT);

        // Đánh dấu câu trả lời đúng và sai
        if (question.getAnswer().equals(holder.rbOption1.getText().toString())) {
            holder.rbOption1.setBackgroundColor(Color.GREEN);
        } else {
            holder.rbOption1.setBackgroundColor(Color.RED);
        }

        if (question.getAnswer().equals(holder.rbOption2.getText().toString())) {
            holder.rbOption2.setBackgroundColor(Color.GREEN);
        } else {
            holder.rbOption2.setBackgroundColor(Color.RED);
        }

        if (question.getAnswer().equals(holder.rbOption3.getText().toString())) {
            holder.rbOption3.setBackgroundColor(Color.GREEN);
        } else {
            holder.rbOption3.setBackgroundColor(Color.RED);
        }

        if (question.getAnswer().equals(holder.rbOption4.getText().toString())) {
            holder.rbOption4.setBackgroundColor(Color.GREEN);
        } else {
            holder.rbOption4.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        RadioGroup rgOptions;
        RadioButton rbOption1, rbOption2, rbOption3, rbOption4;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_results);
            rgOptions = itemView.findViewById(R.id.rg_results);
            rbOption1 = itemView.findViewById(R.id.rb_result1);
            rbOption2 = itemView.findViewById(R.id.rb_result2);
            rbOption3 = itemView.findViewById(R.id.rb_result3);
            rbOption4 = itemView.findViewById(R.id.rb_result4);
        }
    }
}
