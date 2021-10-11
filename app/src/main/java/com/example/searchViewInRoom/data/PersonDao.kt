package com.example.searchViewInRoom.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// here we have our Dao having three different queries as follows.....
@Dao
interface PersonDao {

    // it's basically for reading our database
    @Query("SELECT * FROM person_table ORDER BY id ASC")
    fun readData(): Flow<List<Person>>

    // it's for inserting person object to our database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(person: Person)

    // it's very important one for this article because
    //it's a query for searching our database
    /*
    so here i have wrote basic sql query for searching our database
    basically will search from our person table or entity
    where our first name and last name contains some characters from our searchquery
     */
    @Query("SELECT * FROM person_table WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery")
    // and then search query will be passed through the perimeter of this function
    //and then function will return the flow of list of person
    fun searchDatabase(searchQuery: String): Flow<List<Person>>

}