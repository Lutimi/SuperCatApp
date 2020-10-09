package com.example.SupercCatApp.controller.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.SupercCatApp.R
import com.example.SupercCatApp.adapter.CatAdapter
import com.example.SupercCatApp.database.CatDB
import com.example.SupercCatApp.models.Cat
import com.example.SupercCatApp.network.CatService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatFragment : Fragment(), CatAdapter.OnItemClickListener{
    var cat: List<Cat> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvCats)

        loadCats(view.context, "A")

        editText = view.findViewById(R.id.editText)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                loadCats(view.context, s.toString())
            }
        })
    }

    private fun loadCats(context: Context, query: String){

        Log.d("Init load", "Init")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val catService: CatService
        catService = retrofit.create(CatService::class.java)
        val request = catService.getCats("5", "10", "Desc")

        request.enqueue(object : Callback<List<Cat> > {

            override fun onFailure(call: Call<List<Cat> >, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

            override fun onResponse(call: Call<List<Cat> >, responseDetails: Response<List<Cat> >) {

                if (responseDetails.isSuccessful) {
                    println("Entro BIEN AL ONRESPONSE")
                    Log.d("MSG", responseDetails.message())
                    Log.d("Error Body", responseDetails.errorBody().toString())
                    Log.d("Activity Success", responseDetails.raw().toString())
                    Log.d("Activity Success", responseDetails.body().toString())

                    val cats: List<Cat> = responseDetails.body()!!?: ArrayList()
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = CatAdapter(cats, context, this@CatFragment)
                } else {
                    Log.d("Activity Fail", "Error: " + responseDetails.code())
                    println("Entro al else en onResponse")
                }
            }

        })
    }

    override fun onItemClicked(cat: Cat) {
        if (cat != null) {
            if (this.context?.let { CatDB.getInstance(it).getCatDAO().getACat(cat.id) } == null) {
                val alertbox = this.context?.let {
                    AlertDialog.Builder(it)
                        .setMessage("¿Agregar a favoritos?")
                        .setPositiveButton("Si") { arg0, arg1 ->
                            if (cat != null) {
                                CatDB.getInstance(this.context!!).getCatDAO().insertCat(cat)
                            }
                        }
                        .setNegativeButton(
                            "No" // do something when the button is clicked
                        ) { arg0, arg1 ->
                        }
                        .show()
                }
            }
            else
            {
                val alertbox = this.context?.let {
                    AlertDialog.Builder(it)
                        .setMessage("Cat ya en favoritos")
                        .setNeutralButton("Ok") { arg0, arg1 ->
                        }
                        .show()
                }
            }
        }
    }
}