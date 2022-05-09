package devpaul.business.safetylima.fragments.newsfragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.newspackage.CommonNewsData
import devpaul.business.safetylima.adapter.newspackage.Data
import devpaul.business.safetylima.adapter.MyDataAdapter
import devpaul.business.safetylima.adapter.newspackage.RetrofitServiceNewsApart


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ArgentinaFragment : Fragment() {

    var TAG = "NewsFragment"

    var myView: View? = null
    lateinit var mService2: RetrofitServiceNewsApart
    lateinit var adapter2: MyDataAdapter
    var recyclerViewData: RecyclerView? = null
    var shimmerFrameLayout : ShimmerFrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_argentina, container, false)

        shimmerFrameLayout = myView?.findViewById(R.id.shimmerFrameLayout)

        mService2 = CommonNewsData.retrofitService

        recyclerViewData = myView?.findViewById(R.id.recyclerViewArgentina)
        recyclerViewData?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewData?.setHasFixedSize(true)

        if (isOnline()){
            getArgentinaNews()
        } else{
            getConnectionValidation()
        }
        return myView
    }

    private fun getArgentinaNews() {

        mService2.getDataList().enqueue(object : Callback<Data?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                val jsonResponse: Data? = response.body()
      /*          adapter = MyDataAdapter(requireContext(), response.body() as MutableList<Data.Articles>)*/
                val data2 = jsonResponse?.articles as MutableList<Data.Articles>
                /*  val data = ArrayList(listOf(jsonResponse?.items))*/
                adapter2 = MyDataAdapter(requireContext(), data2)
                recyclerViewData?.adapter = adapter2
                adapter2.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Log.d(TAG, t.message!!)
            }
        })

    }

    private fun getConnectionValidation() {
        try {
            val customDialog = Dialog(requireContext())
            customDialog.setContentView(R.layout.connection_dialog)
            customDialog.show()
            val mylamda = Thread {
                for (x in 0..10) {
                    Thread.sleep(3500)
                    customDialog.dismiss()
                }
            }
            startThread(mylamda)
        } catch (e: Exception) {
            Log.v(TAG, "Error: $e");
        }
    }

    private fun startThread(mylamda: Thread) {
        mylamda.start()
    }

    @Suppress("DEPRECATION")
    private fun isOnline(): Boolean {
        val conMgr = requireActivity().applicationContext
            .getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo
        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            Log.v(TAG, "Error: $netInfo");
            return false
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout?.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout?.startShimmerAnimation()
    }


}