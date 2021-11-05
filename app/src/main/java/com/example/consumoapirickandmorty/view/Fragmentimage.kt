package com.example.consumoapirickandmorty.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.consumoapirickandmorty.R
import com.example.consumoapirickandmorty.model.dataClass.Results
import com.example.consumoapirickandmorty.viewModel.ViewModelRick
import kotlinx.android.synthetic.main.fragment_fragmentimage.*

class Fragmentimage : Fragment(), CharacterAdapter.pAdapter{
    private lateinit var rickVM: ViewModelRick
    private lateinit var mAdapterfrag:CharacterAdapter

    var page=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            rickVM= ViewModelProvider(activity!!).get(ViewModelRick::class.java)
            mAdapterfrag= CharacterAdapter(mutableListOf(),this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragmentimage, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rickVM.InsertDataVM(page)

        nested_scrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v != null) {
                if(scrollY== v.getChildAt(0)?.measuredHeight!! - v.measuredHeight){
                    if(page<34){
                        page++
                        Log.d("Pame","pagina numero $page")
                        myprogressbar.visibility=View.VISIBLE
                        rickVM.InsertDataVM(page)
                        myprogressbar.visibility=View.GONE
                    }else{
                        myendofline.visibility=View.VISIBLE
                    }
                }
            }
        })
        rickVM.getRicky().observe(viewLifecycleOwner,{
            if(it!=null){
                mAdapterfrag.updateData(it as MutableList<Results>)
                Log.d("pame","dentro del if $it")
            }else{
                Log.d("pame","parece que llega nulo $it")}
        })
        myrecycler.adapter=mAdapterfrag
        myrecycler.layoutManager=GridLayoutManager(activity,2)


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Fragmentimage().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun idFromLonglick(id: Int) {
        rickVM.insertfavVM(id)
    }
}