package com.example.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.databinding.CardTasarimBinding
import com.example.todoapp.ui.fragment.AnasayfaFragment
import com.example.todoapp.ui.fragment.AnasayfaFragmentDirections
import com.example.todoapp.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.todoapp.utils.gecisYap
import com.google.android.material.snackbar.Snackbar

class NotlarAdapter(var mContext:Context,
                    var notlarListesi:List<Notlar>,
                    var viewModel: AnasayfaFragmentViewModel)
    : RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:CardTasarimBinding = DataBindingUtil.inflate(layoutInflater,R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val not = notlarListesi.get(position)
        val t = holder.tasarim
        t.notNesnesi = not


        t.satirCard.setOnClickListener{
            val gecis = AnasayfaFragmentDirections.detayaGecis(not = not)
            Navigation.gecisYap(it,gecis)
        }
        t.imageViewSil.setOnClickListener{
            Snackbar.make(it,"${not.not_baslik} silinsin mi?",Snackbar.LENGTH_LONG).setAction("EVET"){
                viewModel.notSil(not.not_id)
            }.show()
        }
    }

    override fun getItemCount(): Int {
        return notlarListesi.size
    }
}