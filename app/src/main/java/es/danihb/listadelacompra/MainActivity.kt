package es.danihb.listadelacompra

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val alimentoFragment = alimentoFragment()
    private val alimentoFragment2 = addAlimentoFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                cambioFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                cambioFragment(2)

                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cambioFragment(1)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private fun cambioFragment(id: Int) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (id == 1) {
            fragmentTransaction.replace(R.id.fragmentView, alimentoFragment)
        } else {
            alimentoFragment2.arguments?.clear()
            fragmentTransaction.replace(R.id.fragmentView, alimentoFragment2)

        }

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
