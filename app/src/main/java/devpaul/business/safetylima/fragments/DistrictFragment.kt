package devpaul.business.safetylima.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.ViewDistrictAdapter
import devpaul.business.safetylima.entities.SectionDistrict
import devpaul.business.safetylima.entities.Station

class DistrictFragment : Fragment() {

    var myView: View? = null
    private val db = Firebase.firestore

    //LimaDistrict Items
    var sectionDistrictList = ArrayList<SectionDistrict>()
    private var limaSectionDistrictAdapter: ViewDistrictAdapter? = null
    private var recyclerViewDistrict: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_district, container, false)

        recyclerViewDistrict = myView?.findViewById(R.id.recyclerview)
        recyclerViewDistrict?.layoutManager = GridLayoutManager(
            requireContext(), 3,
            GridLayoutManager.VERTICAL, false)
        limaSectionDistrictAdapter = ViewDistrictAdapter(requireActivity())
        recyclerViewDistrict?.adapter = limaSectionDistrictAdapter
        recyclerViewDistrict?.setHasFixedSize(true)

        sectionDistrictList.add(SectionDistrict("Ancon",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Ate",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Barranco",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Breña",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Carabayllo",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Chaclacayo",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Chorrillos",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Cieneguilla",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("El Agustino",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Independencia",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Jesus Maria",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("La Molina",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("La Victoria",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Lima",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Lince",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Los Olivos",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Lurigancho",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Lurín",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Magdalena del Mar",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Miraflores",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Pachacamac",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Pueblo Libre",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Puente Piedra",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Punta Negra",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Borja",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Bartolo",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Isidro",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Juan de Lurigancho",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Luis",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Martin de Porres",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("San Miguel",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Santa Anita",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Santa Rosa",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Santiago de Surco",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Villa el Salvador",R.drawable.ic_serenazgo))
        sectionDistrictList.add(SectionDistrict("Villa María del Triunfo",R.drawable.ic_serenazgo))


        limaSectionDistrictAdapter?.setDataList(sectionDistrictList)

        return  myView
    }

}