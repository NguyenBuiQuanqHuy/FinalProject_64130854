package vn.nguyenbuiquanghuy.englishquiz_app.Topic;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import vn.nguyenbuiquanghuy.englishquiz_app.Quiz.QuizActivity;
import vn.nguyenbuiquanghuy.englishquiz_app.R;


public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ItemTopicHolder> {
    Context context;
    ArrayList<Topic> listTopicData;

    public TopicAdapter(Context context, ArrayList<Topic> listTopicData) {
        this.context = context;
        this.listTopicData = listTopicData;
    }

    @NonNull
    @Override
    public ItemTopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View vItem=inflater.inflate(R.layout.topic_items,parent,false);
        ItemTopicHolder holderCreated =new ItemTopicHolder(vItem);

        return holderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTopicHolder holder, int position) {
        //Lấy đối tượng hiển thị
        Topic TopicView=listTopicData.get(position);
        //Trích Thông tin
        String name =TopicView.getTopic();
        String Anh=TopicView.getImageFile();
        //Đặt vào các trường thông tin của holder
        holder.tvTopicName.setText(name);
        //Đặt ảnh
        String packedName=holder.itemView.getContext().getPackageName();
        int imageID=holder.itemView.getResources().getIdentifier(Anh,"mipmap",packedName);
        holder.imageViewTopic.setImageResource(imageID);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(name.equals("Vocabulary")){
                    intent=new Intent(context, QuizActivity.class);
                    intent.putExtra("topic", TopicView.getTopic());
                    context.startActivity(intent);
                }
                else if(name.equals("Grammar")){
                    intent=new Intent(context, QuizActivity.class);
                    intent.putExtra("topic", TopicView.getTopic());
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listTopicData.size();
    }

    class ItemTopicHolder extends RecyclerView.ViewHolder{
        ImageView imageViewTopic;
        TextView tvTopicName;

       public ItemTopicHolder(@NonNull View itemView) {
           super(itemView);
           imageViewTopic=itemView.findViewById(R.id.img_topic);
           tvTopicName=itemView.findViewById(R.id.tv_topic_name);
       }
   }
}
