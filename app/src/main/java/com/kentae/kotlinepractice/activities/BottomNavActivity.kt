package com.kentae.kotlinepractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.fragnent.HomeFragment
import com.kentae.kotlinepractice.fragnent.MessageFragment
import com.kentae.kotlinepractice.fragnent.SettingsFragment

class BottomNavActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

     ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        drawerLayout = findViewById(R.id.drawerLayout);

        toggle = ActionBarDrawerToggle(this@BottomNavActivity, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        //toolbar?.title = "Nav"
        //toolbar?.subtitle = "Sub"
        toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_action_menu)
        toolbar?.setNavigationOnClickListener {
            drawerLayout.open();
        }




        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        navigationView = findViewById<NavigationView>(R.id.navView);

        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true;
                }
                R.id.message -> {
                    loadFragment(MessageFragment())
                    true;
                }
                R.id.settings -> {
                    loadFragment(SettingsFragment())
                    true;
                }
                else -> {
                    false;
                }
            }
        }

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.items -> {
                    drawerLayout.close()
                    val intent = Intent(this@BottomNavActivity, ItemActivity::class.java);
                    startActivity(intent);
                }
                R.id.addItem -> {
                    drawerLayout.close()
                    val intent = Intent(this@BottomNavActivity, AddItemActivity::class.java);
                    intent.putExtra("mode", "add");
                    startActivity(intent);
                }
                R.id.notes -> {
                    drawerLayout.close()
                    val intent = Intent(this@BottomNavActivity, NoteActivity::class.java);
                    intent.putExtra("mode", "add");
                    startActivity(intent);
                }
            }
            true
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}