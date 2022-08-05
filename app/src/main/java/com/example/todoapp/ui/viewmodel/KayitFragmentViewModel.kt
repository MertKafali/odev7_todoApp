package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.NotlarDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KayitFragmentViewModel @Inject constructor (var krepo : NotlarDaoRepository) : ViewModel() {
    fun kayit(not_baslik:String,not_icerik:String){
        krepo.notKayit(not_baslik,not_icerik)
    }
}