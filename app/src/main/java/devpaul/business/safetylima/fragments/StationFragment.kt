package devpaul.business.safetylima.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.StationAdapter
import devpaul.business.safetylima.entities.Station
import devpaul.business.safetylima.lifecycle.BaseFragmentModule

class StationFragment : BaseFragmentModule() {

    var myView: View? = null

    private var recyclerViewStation: RecyclerView? = null
    var stationList = ArrayList<Station>()
    private var stationAdapter: StationAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_station, container, false)

        recyclerViewStation = myView?.findViewById(R.id.recyclerView)
        recyclerViewStation?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewStation?.setHasFixedSize(true)
        stationAdapter = StationAdapter(requireActivity())
        recyclerViewStation?.adapter = stationAdapter


        stationList.add(Station("Bombero", R.drawable.ic_fireplace, "bombero"))
        stationList.add(Station("Policía", R.drawable.ic_local_police, "policia"))
        stationList.add(Station("Serenazgo", R.drawable.ic_serenazgo, "serenazgo"))

        stationAdapter?.setDataList(stationList)

        return myView
    }


}