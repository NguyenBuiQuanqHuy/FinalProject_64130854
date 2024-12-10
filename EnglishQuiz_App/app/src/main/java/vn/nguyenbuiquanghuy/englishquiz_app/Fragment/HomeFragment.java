package vn.nguyenbuiquanghuy.englishquiz_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.nguyenbuiquanghuy.englishquiz_app.R;
import vn.nguyenbuiquanghuy.englishquiz_app.Model.Topic;
import vn.nguyenbuiquanghuy.englishquiz_app.Adapter.TopicAdapter;

public class HomeFragment extends Fragment {
    TopicAdapter topicAdapter;
    ArrayList<Topic> TopicRecycler;
    ArrayList<Topic> filteredTopics;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TopicRecycler=getTopicRecycler();
        filteredTopics = new ArrayList<>(TopicRecycler);
        recyclerView=view.findViewById(R.id.rv_topics);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        topicAdapter=new TopicAdapter(getActivity(),filteredTopics);
        recyclerView.setAdapter(topicAdapter);
        return view;
    }


    ArrayList<Topic> getTopicRecycler(){
        ArrayList<Topic> ListData=new ArrayList<>();
        ListData.add(new Topic("vocabulary","Vocabulary"));
        ListData.add(new Topic("grammar","Grammar"));
        ListData.add(new Topic("animal","Animals"));
        ListData.add(new Topic("vehicle","Vehicle"));
        ListData.add(new Topic("fruit","Fruit"));
        return ListData;
    }

    public void filterTopics(String query) {
        filteredTopics.clear();

        if (query.isEmpty()) {
            filteredTopics.addAll(TopicRecycler);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Topic topic : TopicRecycler) {
                if (topic.getTopic().toLowerCase().contains(lowerCaseQuery)) {
                    filteredTopics.add(topic);
                }
            }
        }
        topicAdapter.notifyDataSetChanged();
    }
}
