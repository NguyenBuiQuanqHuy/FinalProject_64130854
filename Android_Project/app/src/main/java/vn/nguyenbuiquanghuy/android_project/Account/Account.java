package vn.nguyenbuiquanghuy.android_project.Account;

public class Account {
    String userName,email,password,imageUrl;

    public Account(String userName, String email, String password, String imageUrl) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Account() {
    }
}
