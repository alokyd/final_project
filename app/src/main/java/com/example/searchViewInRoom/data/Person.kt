package com.example.searchViewInRoom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// it's our data class that we have annotate @Entity to create it as table
@Entity(tableName = "person_table")
data class Person(
    // table is basically having 3 fields as follow....
    var firstName: String,
    var lastName: String,
    var age: Int
){
    @PrimaryKey(autoGenerate = true)
    // we make id as primary key and it will autogenerate
    var id: Int = 0
}