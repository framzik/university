package ru.university.web;

public class SecurityUtil {
    private static int id = 1;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int setAuthUserId(){
        return  id;
    }

    public static void setId(int id) {
        SecurityUtil.id = id;
    }
}
