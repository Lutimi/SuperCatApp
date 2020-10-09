package com.example.SupercCatApp.controller.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.SupercCatApp.R
import com.example.SupercCatApp.adapter.CatAdapter
import com.example.SupercCatApp.database.CatDB
import com.example.SupercCatApp.models.Cat


class SaveFragment : Fragment(), CatAdapter.OnItemClickListener{

    var cat: List<Cat> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cat = CatDB.getInstance(view.context).getCatDAO().getAllCats()
        recyclerView = view.findViewById(R.id.CatSave)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CatAdapter(cat,view.context,this)
    }

    override fun onItemClicked(cat: Cat) {
        if (cat != null) {
            val alertbox = this.context?.let {
                AlertDialog.Builder(it)
                    .setMessage("¿Eliminar de favoritos?")
                    .setPositiveButton("Si") { arg0, arg1 ->
                        if (cat != null) {
                            CatDB.getInstance(this.context!!).getCatDAO().deleteCat(cat)
                            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                            if (Build.VERSION.SDK_INT >= 26) {
                                ft.setReorderingAllowed(false)
                            }
                            ft.detach(this).attach(this).commit()
                        }
                    }
                    .setNegativeButton(
                        "No" // do something when the button is clicked
                    ) { arg0, arg1 ->
                    }
                    .show()
            }
        }
    }
}