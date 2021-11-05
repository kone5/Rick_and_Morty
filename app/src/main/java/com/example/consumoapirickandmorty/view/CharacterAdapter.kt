package com.example.consumoapirickandmorty.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.consumoapirickandmorty.R
import com.example.consumoapirickandmorty.model.dataClass.Favorite
import com.example.consumoapirickandmorty.model.dataClass.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.holderimage.view.*

class CharacterAdapter(var mAdapterCharacter: MutableList<Results>,var pAdapCharacter: pAdapter): RecyclerView.Adapter<CharacterAdapter.ViewHolderCharacter>() {

    fun updateData(mCharacter:MutableList<Results>?){
        if (mCharacter != null) {
            mAdapterCharacter= mCharacter
        }
        notifyDataSetChanged()
    }

    inner class ViewHolderCharacter(itemView: View): RecyclerView.ViewHolder(itemView){

        fun mbind(result:Results) {
            itemView.charactername.text=result.name
            Picasso.get().load(result.image).placeholder(R.drawable.ic_launcher_background).into(itemView.imageCharacter)
            itemView.setOnLongClickListener {
                pAdapCharacter.idFromLonglick(result.id!!)
                Toast.makeText(itemView.context," added as favorite", Toast.LENGTH_LONG).show()
                itemView.corazonfavorite.visibility=View.VISIBLE
                true

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        return ViewHolderCharacter(LayoutInflater.from(parent.context).inflate(R.layout.holderimage,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        holder.mbind(mAdapterCharacter[position])
    }

    override fun getItemCount(): Int {
        return mAdapterCharacter.size
    }
    interface pAdapter{
        fun idFromLonglick(id:Int)

    }
}