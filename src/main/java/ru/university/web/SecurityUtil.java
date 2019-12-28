package ru.university.web;

public class SecurityUtil {
    private static int id = 1;
    public static int authFacultyId(){
        return  id;
    }

    public static void setId(int id) {
        SecurityUtil.id = id;
    }
}
