package com.jwhh.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.jwhh.notekeeper.databinding.ActivityItemsBinding
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.content_activity_items.*

class ActivityItems : AppCompatActivity(),
            NavigationView.OnNavigationItemSelectedListener{

    private val linearLayoutManager  by lazy {
        LinearLayoutManager(this)
    }


    private val courseLinearLayoutManager  by lazy {
        GridLayoutManager(this, 2)
    }

    private val noteRecyclerViewAdapter by lazy {
        NoteRecyclerViewAdapter(this, DataManager.notes)
}
    private val courseViewAdapter by lazy {
        CourseRecyclerAdapter(this,
            DataManager.courses.values.toList())
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarActivityItems.toolbar)

        binding.appBarActivityItems.fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))

        }

        displayNotes()
        displayCourses()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_activity_items)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_course, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this@ActivityItems)
    }

    private fun displayCourses() {
        listItems.layoutManager = courseLinearLayoutManager
        listItems.adapter = courseViewAdapter

        nav_view.menu.findItem(R.id.nav_course).isChecked = true


    }

    private fun displayNotes() {
        listItems.layoutManager = linearLayoutManager
        listItems.adapter = noteRecyclerViewAdapter

        nav_view.menu.findItem(R.id.nav_notes).isChecked = true

    }


    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_items, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_activity_items)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                when(p0.itemId){
                    R.id.nav_notes->{
                    displayNotes()
                    }
                    R.id.nav_course->{
                    displayCourses()
                    }
                }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true;
    }

}