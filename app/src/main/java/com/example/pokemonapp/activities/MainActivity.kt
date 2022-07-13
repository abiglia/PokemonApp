package com.example.pokemonapp.activities

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.fragments.MainFragment
import com.example.pokemonapp.activities.fragments.TermsConditionFragment
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainFragment : MainFragment
    private lateinit var termsConditionFragment: TermsConditionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        mainFragment = MainFragment()
        termsConditionFragment = TermsConditionFragment()

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.navView.setNavigationItemSelectedListener(this)

        containerFragment(mainFragment) // con esta funcion pasaremos el fragment que qurremos reemplazar
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) { //elegiremos en el panel las opciones
            R.id.nav_item_one -> containerFragment(mainFragment)
            R.id.nav_terms_condition -> containerFragment(termsConditionFragment)
            R.id.nav_log_out -> dialogLogOut()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun dialogLogOut() { // metodo que nos volvera a la pantalla de login

        val alertDialog = AlertDialog.Builder(this) // dialogo para que el usuario confirme si quiere cerrar sesion o no

        alertDialog.apply {
            //setIcon(R.drawable.ic_hello)
            setTitle("Cerrar sesion")
            setMessage("Quiere cerrar sesion?")
            setPositiveButton("Si") { _: DialogInterface?, _: Int ->
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
            setNegativeButton("No") { _, _ ->

            }

        }.create().show()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun containerFragment(fragment : Fragment) { // metodo donde le pasaremos el fragmento para reemplarlo por el "containerFragment"
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragment, fragment)
        fragmentTransaction.commit()
    }

}