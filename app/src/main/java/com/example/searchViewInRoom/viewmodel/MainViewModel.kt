package com.example.searchViewInRoom.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.searchViewInRoom.data.Person
import com.example.searchViewInRoom.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// here we have our viewModel
/*
 and we are going to use it for receiving and observing data from our database
 */
class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val readData = repository.readData().asLiveData()

    fun insertData(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(person)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Person>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}