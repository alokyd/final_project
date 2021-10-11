package com.example.searchViewInRoom.di

import android.content.Context
import androidx.room.Room
import com.example.searchViewInRoom.data.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
// this is a very important step actually
// here i used dependency injection library dagger hilt
// it actually provide our database object and dao
// I hope you all know about it ,because i am not going to explain it now.....

//it needs huge explanation so i am not gonna do that for now ......
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        PersonDatabase::class.java,
        "person_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: PersonDatabase) = database.personDao()

}