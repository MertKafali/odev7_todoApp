package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.NotlarDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetayFragmentViewModel @Inject constructor (var krepo : NotlarDaoRepository) : ViewModel() {
    fun guncelle(not_id:Int,not_baslik:String,not_icerik:String){
        krepo.notGuncelle(not_id,not_baslik,not_icerik)
    }
}