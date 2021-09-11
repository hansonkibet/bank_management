package com.hanson.app.interfaces;

import java.util.List;

public interface UsersControllerI<T>{
    String create(T t);
    void edit(T t);
    void delete(int id);
    List<T> displayAll();
}
