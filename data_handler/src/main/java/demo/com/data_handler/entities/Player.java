package demo.com.data_handler.entities;

import com.squareup.moshi.Json;

public class Player {
    @Json(name = "name") private String name;
    @Json(name = "position") private String position;
    @Json(name = "avatar") private String profileImage;
    @Json(name = "age") private int age;

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public int getAge() {
        return age;
    }
}
