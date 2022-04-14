package devpaul.business.safetylima.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
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
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.MyNewsAdapter
import devpaul.business.safetylima.api.Common
import devpaul.business.safetylima.entities.News
import devpaul.business.safetylima.routes.RetrofitService
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*

class NewsFragment : Fragment() {

    var TAG = "NewsFragment"

    var myView: View? = null
    lateinit var  mService : RetrofitService
    lateinit var  adapter : MyNewsAdapter
    var recyclerViewNews: RecyclerView? = null

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_news, container, false)

        @Suppress("DEPRECATION")
        progressDialog = ProgressDialog(requireContext())

        recyclerViewNews = myView?.findViewById(R.id.recyclerView)
        recyclerViewNews?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewNews?.setHasFixedSize(true)

        mService = Common.retrofitService


        if (isOnline()){
          getAllNewsList()
        } else{
            getConnectionValidation()
        }

        return myView
    }

    private fun getAllNewsList() {

        progressDialog!!.show()
        progressDialog?.setContentView(R.layout.charge_dialog)
        Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)

        mService.getNewsList().enqueue(object : Callback<MutableList<News>>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<MutableList<News>>, response: Response<MutableList<News>>) {

                if (response.body() != null){
                    adapter = MyNewsAdapter(requireContext(), response.body() as MutableList<News>)
                    adapter.notifyDataSetChanged()
                    recyclerViewNews?.adapter = adapter
                    progressDialog?.dismiss()
                }

            }

            override fun onFailure(call: Call<MutableList<News>>, t: Throwable) {
                Log.v(TAG, "Error: ${t.message}")
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
            /*     Toast.makeText(this@ViewAllSectionActivity, "Sin conexion a internet!", Toast.LENGTH_LONG).show()*/
            return false
        }
        return true
    }



}