package vn.nguyenbuiquanghuy.englishquiz_app.Model;

public class History {
    private String topic;
    private int score;
    private int totalQuestions;
    private String dateTime;

    public History(String topic, int score, int totalQuestions, String dateTime) {
        this.topic = topic;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
