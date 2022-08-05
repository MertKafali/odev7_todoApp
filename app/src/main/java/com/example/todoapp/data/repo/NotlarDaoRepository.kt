package com.example.todoapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.room.NotlarDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotlarDaoRepository(var ndao:NotlarDao) {
    var notlarListesi:MutableLiveData<List<Notlar>>

    init {
        notlarListesi = MutableLiveData()
    }

    fun notlariGetir() : MutableLiveData<List<Notlar>>{
        return notlarListesi
    }

    fun notKayit(not_baslik:String,not_icerik:String){
        val job = CoroutineScope(Dispatchers.Main).launch{
            val yeniNot = Notlar(0,not_baslik,not_icerik)
            ndao.notEkle(yeniNot)
            tumNotlariAl()
        }
    }

    fun notGuncelle(not_id:Int,not_baslik:String,not_icerik:String){
        val job = CoroutineScope(Dispatchers.Main).launch{
            val guncellenenNot = Notlar(not_id,not_baslik,not_icerik)
            ndao.notGuncelle(guncellenenNot)
        }
    }

    fun notAra(aramaKelimesi:String){
        val job = CoroutineScope(Dispatchers.Main).launch{
            notlarListesi.value = ndao.notAra(aramaKelimesi)
        }
    }


    fun notSil(not_id: Int){
        val job = CoroutineScope(Dispatchers.Main).launch{
            val silinenNot = Notlar(not_id,"","")
            ndao.notSil(silinenNot)
            tumNotlariAl()
        }
    }

    fun tumNotlariAl(){
        val job = CoroutineScope(Dispatchers.Main).launch{
            notlarListesi.value = ndao.tumNotlar()
        }
    }
}