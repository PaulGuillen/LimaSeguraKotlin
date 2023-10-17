package devpaul.business.safetylima.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.MyNewsAdapter
import devpaul.business.safetylima.entities.Data
import devpaul.business.safetylima.adapter.MyDataAdapter
import devpaul.business.safetylima.data.repository.NewsPeruRepository
import devpaul.business.safetylima.data.repository.NewsRepository
import devpaul.business.safetylima.data.routes.RetrofitServiceNewsApart
import devpaul.business.safetylima.data.routes.RetrofitService
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.uitl.SingletonError
import devpaul.business.safetylima.domain.usecases.NewsPeruUseCase
import devpaul.business.safetylima.domain.usecases.NewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NewsFragment : Fragment(), View.OnClickListener {

    var TAG = "NewsFragment"

    var myView: View? = null
    lateinit var mService: RetrofitService
    lateinit var adapter1: MyNewsAdapter
    var recyclerViewNews: RecyclerView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null


    //NewsFragment
    lateinit var mService2: RetrofitServiceNewsApart
    lateinit var adapter2: MyDataAdapter


    //Buttons
    var btnDePeru: CardView? = null
    var btnArgentina: CardView? = null
    var btnColombia: CardView? = null
    var btnCuba: CardView? = null
    var btnMexico: CardView? = null
    var btnVenezuela: CardView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_news, container, false)


        shimmerFrameLayout = myView?.findViewById(R.id.shimmerFrameLayout)

//        mService = Common.retrofitService
//        mService2 = CommonNewsData.retrofitService
        recyclerViewNews = myView?.findViewById(R.id.recyclerView)
        recyclerViewNews?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewNews?.setHasFixedSize(true)

        btnDePeru = myView?.findViewById(R.id.btnPeru)
        btnArgentina = myView?.findViewById(R.id.btnArgentina)
        btnColombia = myView?.findViewById(R.id.btnColombia)
        btnCuba = myView?.findViewById(R.id.btnCuba)
        btnMexico = myView?.findViewById(R.id.btnMexico)
        btnVenezuela = myView?.findViewById(R.id.btnVenezuela)

        btnDePeru?.setOnClickListener(this)
        btnArgentina?.setOnClickListener(this)
        btnColombia?.setOnClickListener(this)
        btnCuba?.setOnClickListener(this)
        btnMexico?.setOnClickListener(this)
        btnVenezuela?.setOnClickListener(this)


        return myView
    }

    override fun onClick(view: View?) {
        when (view) {
            btnDePeru -> validationDePeru()
            btnArgentina -> validationArgentina()
            btnColombia -> validationColombia()
            btnCuba -> validationCuba()
            btnMexico -> validationMexico()
            btnVenezuela -> validationVenezuela()

        }
    }

    // Validating data
    private fun validationDePeru() {
        if (isOnline()) {
            getAllNewsList()
        } else {
            getConnectionValidation()
        }

    }

    private fun validationArgentina() {
        if (isOnline()) {
            getArgentinaNews()
        } else {
            getConnectionValidation()
        }
    }

    private fun validationColombia() {
        if (isOnline()) {
            getColombiaNews()
        } else {
            getConnectionValidation()
        }
    }

    private fun validationCuba() {
        if (isOnline()) {
            getCubaNews()
        } else {
            getConnectionValidation()
        }
    }

    private fun validationMexico() {
        if (isOnline()) {
            getMexicoNews()
        } else {
            getConnectionValidation()
        }
    }

    private fun validationVenezuela() {
        if (isOnline()) {
            getVenezuelaNews()
        } else {
            getConnectionValidation()
        }
    }


    private fun getVenezuelaNews() {


    }

    private fun getMexicoNews() {


    }

    private fun getCubaNews() {


    }

    private fun getColombiaNews() {


    }

    private fun getArgentinaNews() {

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsArgentinaRepository = NewsRepository()
                val newsArgentinaUseCase = NewsUseCase(requireContext(), newsArgentinaRepository)
                val newsArgentinaRequest = newsArgentinaUseCase.newsFromArgentina()

                withContext(Dispatchers.Main) {
                    when (newsArgentinaRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsArgentinaRequest.data
                            val dataInList = data.articles
                            shimmerFrameLayout?.visibility = View.GONE
                            recyclerViewNews?.visibility = View.VISIBLE
                            adapter2 = MyDataAdapter(requireContext(), dataInList)
                            adapter2.notifyDataSetChanged()
                            recyclerViewNews?.adapter = adapter2

                        }

                        is CustomResult.OnError -> {
                            val codeState = SingletonError.code
                            val titleState = SingletonError.title
                            val subTitleState = if (SingletonError.subTitle.isNullOrEmpty()) {
                                "No data"
                            } else {
                                SingletonError.subTitle
                            }
                        }
                    }
                }

            } catch (e: Exception) {

            }

        }
    }

    private fun getAllNewsList() {

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsPeruRepository = NewsPeruRepository()
                val newsPeruUseCase = NewsPeruUseCase(requireContext(), newsPeruRepository)
                val newsPeruRequest = newsPeruUseCase.newsPeru()

                withContext(Dispatchers.Main) {
                    when (newsPeruRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsPeruRequest.data
                            shimmerFrameLayout?.visibility = View.GONE
                            recyclerViewNews?.visibility = View.VISIBLE
                            adapter1 = MyNewsAdapter(requireContext(), data)
                            adapter1.notifyDataSetChanged()
                            recyclerViewNews?.adapter = adapter1

                        }

                        is CustomResult.OnError -> {
                            val codeState = SingletonError.code
                            val titleState = SingletonError.title
                            val subTitleState = if (SingletonError.subTitle.isNullOrEmpty()) {
                                "No data"
                            } else {
                                SingletonError.subTitle
                            }
                        }
                    }
                }

            } catch (e: Exception) {

            }

        }
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

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout?.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout?.startShimmerAnimation()
    }

    override fun onStart() {
        super.onStart()

        if (isOnline()) {
            getAllNewsList()
        } else {
            getConnectionValidation()
        }

    }

}