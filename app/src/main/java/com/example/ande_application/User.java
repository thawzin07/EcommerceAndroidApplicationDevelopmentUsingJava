package com.example.ande_application;

public class User {
    private String username , useremail , userimageurl;
    private int userphone ;

    public String getUsername() {
        return username;
    }

    public String getUseremail() {
        return useremail;
    }

    public int getUserphone() {
        return userphone;
    }

    public String getUserimageurl() {
        return userimageurl;
    }

    public void setUserimageurl(String userimageurl) {
        this.userimageurl = userimageurl;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setUserphone(int userphone) {
        this.userphone = userphone;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
