package com.littletree.thtplay.fragment;


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import com.littletree.thtplay.R
import com.littletree.thtplay.spots.SpotsListEntity
import com.littletree.thtplay.spots.SpotsService
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import kotlin.collections.HashMap


/**
 * A simple {@link Fragment} subclass.
 */
class SpotsFragment : Fragment() {

    private var lv_spots: ListView? = null
    private val ids = intArrayOf(R.id.title, R.id.info, R.id.img)
    private val keys = arrayOf("title", "info", "img")
    private var spotsDateList: List<Map<String, Any>>? = null

    private val subscriber = object : Subscriber<SpotsListEntity>() {
        override fun onCompleted() {
            // Toast.makeText(this, "Get Spots Completed", Toast.LENGTH_SHORT).show()
        }

        override fun onError(e: Throwable) {
            Log.e("Error", e.message)
            TODO("可以提前判断返回JSON中ret是否为0，抛出ApiException，就不有在onNext中再判断了。")
        }

        override fun onNext(sle: SpotsListEntity) {
            Log.d("SpotsList: ", sle.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_spots, container, false)

        lv_spots = view.findViewById(R.id.lv_spots) as ListView
        // val adapter = ArrayAdapter<String>(view.context, android.R.layout.simple_expandable_list_item_1, getData())
        val adapter = SimpleAdapter(view.context, getData(), R.layout.spots_grid_layout, keys, ids)
        lv_spots!!.adapter = adapter
        return view
    }

    private fun getData(): ArrayList<Map<String, Any>> {
        var data = ArrayList<Map<String, Any>>()
        var map = HashMap<String, Any>().apply {
            put("title", "G1")
            put("info", "google 1")
            put("img", R.drawable.welcomimg1)
        }
        data.add(map)

        return data
    }

    private fun getSpotsList() {
        SpotsService.create()
                .getSpotsList(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}
