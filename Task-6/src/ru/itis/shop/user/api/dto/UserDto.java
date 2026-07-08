package ru.itis.shop.user.api.dto;

public class UserDto {
    private Integer id;
    private String email;
    private String profileDescription;
    private String name;

    public UserDto(Integer id, String email, String profileDescription, String name) {
        this.id = id;
        this.email = email;
        this.profileDescription = profileDescription;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public String getName() {
        return name;
    }
}
