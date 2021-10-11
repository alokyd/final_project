package com.example.searchViewInRoom.data

import androidx.room.Database
import androidx.room.RoomDatabase
// it's our database and here we specify entities, our version and exportSchema
@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
// in our database we have just extended RoomDatabase class
abstract class PersonDatabase: RoomDatabase() {
// as you can see it is our abstract fun and it represent our Data Access Object (dao)
    abstract fun personDao(): PersonDao

}