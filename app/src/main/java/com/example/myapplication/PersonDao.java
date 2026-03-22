package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person person);

    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("Select * from person where id = :id")
    Person getById(int id);
}