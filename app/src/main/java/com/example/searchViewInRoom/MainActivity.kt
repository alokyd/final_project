package com.example.searchViewInRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchViewInRoom.adapter.MyAdapter
import com.example.searchViewInRoom.data.Person
import com.example.searchViewInRoom.databinding.ActivityMainBinding
import com.example.searchViewInRoom.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    /*
    as you can see this is my main activity and
    here I have created view binding for binding our views
     */
    private lateinit var binding: ActivityMainBinding

    // here i have initialized our mainViewModel
    private val mainViewModel: MainViewModel by viewModels()
    // and recycler view adapter
    private val myAdapter: MyAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // here i am using linear layout manager for our recyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //here i am setting my recyclerView to custom adapter that i have already made
        binding.recyclerView.adapter = myAdapter

        // here i am observing data by mainViewModel using readData variable
        mainViewModel.readData.observe(this, {
            // using custom recyclerView adapter i have set the data to our recycler view
            myAdapter.setData(it)
        })

    }
    // for getting just press ctrl+o and searct this function then press enter
    // we have override this function so that we can add our menu here
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        // after this you need implement your setOnQueryTextListener in main activity for pass 'this'
        //for implement just go above and add it to last like this
        //  class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener

        return true
    }
    //after that just alt + enter after moving your cursor to the warning shown
    // and then implement these two functions
    override fun onQueryTextSubmit(query: String?): Boolean {
        //it will triggered when we submit the written test
        return true
    }
    // this function will triggered when we write even a single char in search view
    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }
    // I have just created this function for searching our database
    private fun searchDatabase(query: String) {
        // %" "% because our costume sql query will require that
        val searchQuery = "%$query%"

        mainViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                myAdapter.setData(it)
            }
        })
    }

    fun additem(view: android.view.View) {
        Toast.makeText(this,"geeks added...",Toast.LENGTH_SHORT).show()
        mainViewModel.insertData(Person("Krish", "joshi", 18))
        mainViewModel.insertData(Person("Sukant", "desai", 38))
        mainViewModel.insertData(Person("Anye", "jems", 40))
        mainViewModel.insertData(Person("Geek", "geek", 76))
        mainViewModel.insertData(Person("Alok", "pro", 45))
        mainViewModel.insertData(Person("Kushi", "singh", 34))
        mainViewModel.insertData(Person("Final", "step", 23))
        mainViewModel.insertData(Person("Vidyut", "sharma", 20))
        mainViewModel.insertData(Person("Ankit", "chaudhary", 19))
        mainViewModel.insertData(Person("Abhay", "yadav", 16))
    }

}














