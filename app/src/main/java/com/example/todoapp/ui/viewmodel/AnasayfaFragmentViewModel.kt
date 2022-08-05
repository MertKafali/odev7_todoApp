package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.data.repo.NotlarDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor (var krepo : NotlarDaoRepository) : ViewModel() {

    var notlarListesi = MutableLiveData<List<Notlar>>()

    init {
        notlariYukle()
        notlarListesi = krepo.notlariGetir()
    }



    fun notAra(aramaKelimesi:String){
        krepo.notAra(aramaKelimesi)
    }

    fun notSil(not_id: Int){
       krepo.notSil(not_id)
    }

    fun notlariYukle(){
        krepo.tumNotlariAl()
    }

    fun kayit(not_baslik:String,not_icerik:String){
        krepo.notKayit(not_baslik,not_icerik)
    }

    fun guncelle(not_id:Int,not_baslik:String,not_icerik:String){
        krepo.notGuncelle(not_id,not_baslik,not_icerik)
    }
}