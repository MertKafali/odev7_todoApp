package com.example.todoapp.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.FragmentAnasayfaBinding
import com.example.todoapp.ui.adapter.NotlarAdapter
import com.example.todoapp.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.todoapp.utils.gecisYap
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(),SearchView.OnQueryTextListener {
private lateinit var tasarim:FragmentAnasayfaBinding
private lateinit var viewModel: AnasayfaFragmentViewModel
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)
        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = ""

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        //tasarim.rv.layoutManager = LinearLayoutManager(requireContext())
        tasarim.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

viewModel.notlarListesi.observe(viewLifecycleOwner){
    val adapter = NotlarAdapter(requireContext(),it,viewModel)
    tasarim.notlarAdapter = adapter

    swipeRefreshLayout = tasarim.swipeRefreshLayout

    swipeRefreshLayout.setOnRefreshListener {
        Log.e("dgfdgdf","gfdgdfgdg")

        viewModel.notlariYukle()
    }
        /*Handler().postDelayed(Runnable {
            swipeRefreshLayout.isRefreshing = false
        }, 1000)*/
}

        return tasarim.root
    }


    fun fabTikla(view:View){
        //Navigation.gecisYap(view,R.id.kayitaGecis)
        val ad = AlertDialog.Builder(getActivity())
        val alerTasarim = layoutInflater.inflate(R.layout.fragment_kayit,null)
        val btnKaydet = alerTasarim.findViewById(R.id.buttonKaydet) as Button
        val editTxtAlert = alerTasarim.findViewById(R.id.editTextNotBaslik) as EditText
        val editTxtNot = alerTasarim.findViewById(R.id.editTextNot) as EditText
        ad.setView(alerTasarim)

        val d = ad.create()

        btnKaydet.setOnClickListener{
            val eklenecekNotBaslik = editTxtAlert.text.toString()
            val eklenecekNot = editTxtNot.text.toString()
            Log.e("Not Kayıtttt","$eklenecekNotBaslik")
            viewModel.kayit(eklenecekNotBaslik,eklenecekNot)
            showSnackbar( "Başarıyla Kaydedildi!")
            //Snackbar.make(it,"Başarıyla Kaydedildi!",Snackbar.LENGTH_LONG).show()
            d.dismiss()
        }

        d.show()


    }
    
    fun showSnackbar(mesaj:String){
        Snackbar.make(tasarim.root,mesaj,Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           /* R.id.action_ara -> {
                Log.e("Not Menu","Ara Seçildi")
                return true
            }*/
            R.id.action_about -> {
                //Navigation.gecisYap(tasarim.root,R.id.aboutaGecis)
                val ald = AlertDialog.Builder(getActivity())
                val alertTasarim = layoutInflater.inflate(R.layout.fragment_about,null)
                ald.setView(alertTasarim)

                val d = ald.create()

                d.show()
                return true
            }else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.notAra(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.notAra(newText)
        return true
    }

    fun ara(aramaKelimesi:String){
        Log.e("Kişi Ara",aramaKelimesi)
    }

    override fun onResume() {
        super.onResume()
        viewModel.notlariYukle()
    }
}
