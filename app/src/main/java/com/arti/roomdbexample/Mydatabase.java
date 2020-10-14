package com.arti.roomdbexample;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version=1)
public abstract class Mydatabase extends RoomDatabase{
    public abstract MyDao myDao();
}

