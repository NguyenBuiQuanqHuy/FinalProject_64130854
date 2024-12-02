package vn.nguyenbuiquanghuy.englishquiz_app;

public class Topic {
    String imageFile;
    String Topic;

    public Topic(String imageFile, String topic) {
        this.imageFile = imageFile;
        Topic = topic;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }
}