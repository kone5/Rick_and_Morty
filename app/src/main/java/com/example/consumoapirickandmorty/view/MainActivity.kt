package com.example.consumoapirickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.consumoapirickandmorty.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFragmentimage()

        buttonfavoritos.setOnClickListener {
            initializeFragmentfav(FavoriteFragment.newInstance(),"Volver")
        }
    }
    fun initializeFragmentimage(){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentmain,Fragmentimage.newInstance()).commit()
    }
    fun initializeFragmentfav(mFragment: Fragment, backStackName: String){
        supportFragmentManager.beginTransaction().addToBackStack(backStackName).replace(R.id.fragmentmain,mFragment).commit()}
}