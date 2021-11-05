package com.example.consumoapirickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumoapirickandmorty.R
import com.example.consumoapirickandmorty.model.dataClass.Favorite
import com.example.consumoapirickandmorty.viewModel.ViewModelRick
import kotlinx.android.synthetic.main.fragment_favorite.*

var page=1

class FavoriteFragment : Fragment(){
    private lateinit var mFavVM:ViewModelRick
    private lateinit var mAdapterfav: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFavVM = ViewModelProvider(activity!!).get(ViewModelRick::class.java)
            mAdapterfav = FavoriteAdapter()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView: View= inflater.inflate(R.layout.fragment_favorite, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFavVM.getallVMrepo()
        mFavVM.getallVMrepo().observe(viewLifecycleOwner, {
            mAdapterfav.updateData(it as MutableList<Favorite>?)
        })

        recyclerfav.adapter = mAdapterfav
        recyclerfav.layoutManager = LinearLayoutManager(activity)
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            FavoriteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}