package com.example.pokemonapp.activities

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.fragments.MainFragment
import com.example.pokemonapp.activities.fragments.TermsConditionFragment
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.fragments.WebFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainFragment : MainFragment
    private lateinit var termsConditionFragment: TermsConditionFragment
    private lateinit var webFragment : WebFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        webFragment = WebFragment()
        mainFragment = MainFragment()
        termsConditionFragment = TermsConditionFragment()

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.navView.setNavigationItemSelectedListener(this)

        containerFragment(mainFragment)  //con esta funcion pasaremos el fragment que quremos reemplazar
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) { //elegiremos en el panel las opciones
            R.id.nav_item_one -> containerFragment(mainFragment)
            R.id.nav_item_web -> containerFragment(webFragment)
            R.id.nav_terms_condition -> containerFragment(termsConditionFragment)
            R.id.nav_log_out -> dialogLogOut()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun goWeb() {  //metodo que al clickear nos mande al sitio web elegido
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pokemon.com/el/pokedex/"))
        startActivity(browserIntent)
    }

    private fun dialogLogOut() {  //metodo que nos volvera a la pantalla de login

        val alertDialog = AlertDialog.Builder(this)  //dialogo para que el usuario confirme si quiere cerrar sesion o no

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

    private fun containerFragment(fragment : Fragment) {  //metodo donde le pasaremos el fragmento para reemplazarlo por el "containerFragment"
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragment, fragment)
        fragmentTransaction.commit()
    }

}