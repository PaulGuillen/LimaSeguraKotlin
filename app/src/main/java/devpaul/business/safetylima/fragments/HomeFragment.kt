package devpaul.business.safetylima.fragments


import android.annotation.SuppressLint
import android.app.AlertDialog
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
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import cn.pedant.SweetAlert.SweetAlertDialog
import devpaul.business.safetylima.R
import devpaul.business.safetylima.activities.settings.SettingsActivity
import devpaul.business.safetylima.data.repository.DollarQuoteRepository
import devpaul.business.safetylima.entities.UIT

import devpaul.business.safetylima.providers.DolarUITProvider
import devpaul.business.safetylima.data.routes.RetrofitService
import devpaul.business.safetylima.domain.uitl.SingletonError
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.usecases.DollarQuoteUseCase
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    var TAG = "HomeFragment"
    var myView: View? = null
    lateinit var mService: RetrofitService
    lateinit var dialog: AlertDialog
    var currencyProvider = DolarUITProvider()


    //From JSON Data To Dolar Data
    var services: TextView? = null
    var buyDolar: TextView? = null
    var sellDolar: TextView? = null
    var officialSite: TextView? = null
    var cardViewDolar: CardView? = null
    var date: TextView? = null

    //ToUITDATA
    var servicioUIT: TextView? = null
    var valorUIT: TextView? = null
    var periodoUIT: TextView? = null
    var sitioUIT: TextView? = null
    var cardViewUIT: CardView? = null

    var setting: ImageButton? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false)

        services = myView?.findViewById(R.id.servicio)
        officialSite = myView?.findViewById(R.id.sitio)
        buyDolar = myView?.findViewById(R.id.compra)
        sellDolar = myView?.findViewById(R.id.venta)
        date = myView?.findViewById(R.id.Fecha)
        cardViewDolar = myView?.findViewById(R.id.cardview_dolar)

        setting = myView?.findViewById(R.id.ic_settings)
        setting?.setOnClickListener {
            val i = Intent(context, SettingsActivity::class.java)
            startActivity(i)
        }

        //Getting values from xml to card
        servicioUIT = myView?.findViewById(R.id.UIT_servicio)
        valorUIT = myView?.findViewById(R.id.UIT_valor)
        periodoUIT = myView?.findViewById(R.id.UIT_periodo)
        sitioUIT = myView?.findViewById(R.id.UIT_sitio)
        cardViewUIT = myView?.findViewById(R.id.cardview_UIT)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        date?.text = currentDate


        dialog = SpotsDialog.Builder().setCancelable(false).setContext(requireContext()).build()


        if (isOnline()) {
            getQuoteDollar()
            getUIT()
        } else {
            getConnectionValidation()
            /*      Snackbar.make(requireActivity().findViewById(android.R.id.content), "text to show", Snackbar.LENGTH_LONG).show();*/
        }

        return myView
    }

    private fun getQuoteDollar() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val dollarQuoteRepository = DollarQuoteRepository()
                val dollarUseCase = DollarQuoteUseCase(requireContext(), dollarQuoteRepository)
                val dollarQuoteRequest = dollarUseCase.dollarQuote()

                withContext(Dispatchers.Main) {
                    when (dollarQuoteRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = dollarQuoteRequest.data
                            val purchaseValue = data.Cotizacion[0].compra
                            val saleValue = data.Cotizacion[0].venta
                            val linkToDePeru = data.enlace
                            val service = data.servicio
                            val site = data.sitio

                            buyDolar?.text = purchaseValue.toString()
                            sellDolar?.text = saleValue.toString()
                            services?.text = service
                            officialSite?.text = site

                            cardViewDolar?.setOnClickListener {
                                if (!linkToDePeru.isNullOrBlank()) {
                                    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ver página oficial?")
                                        .setCancelText("Cancelar")
                                        .setConfirmText("Si")
                                        .setConfirmClickListener {
                                            val i = Intent(Intent.ACTION_VIEW)
                                            i.data = Uri.parse(linkToDePeru)
                                            startActivity(i)
                                            it.dismiss()
                                        }
                                        .showCancelButton(true).setCancelClickListener { sDialog ->
                                            sDialog.cancel()
                                        }.show()

                                }
                            }

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

    private fun getUIT() {

        currencyProvider.getCurrencyUIT()?.enqueue(object : Callback<UIT> {
            override fun onResponse(call: Call<UIT>, response: Response<UIT>) {
                if (response.body() != null) {
                    val servicioUIT = response.body()?.servicio
                    val valorUIT = response.body()?.UIT
                    val periodoUIT = response.body()?.periodo
                    val sitioUIT = response.body()?.sitio
                    val enlaceUIT = response.body()?.enlace

                    this@HomeFragment.servicioUIT?.text = servicioUIT
                    this@HomeFragment.valorUIT?.text = valorUIT.toString()
                    this@HomeFragment.periodoUIT?.text = periodoUIT.toString()
                    this@HomeFragment.sitioUIT?.text = sitioUIT

                    cardViewUIT?.setOnClickListener {
                        if (!enlaceUIT.isNullOrBlank()) {

                            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ver página oficial?")
                                .setCancelText("Cancelar").setConfirmText("Si")
                                .setConfirmClickListener {
                                    val url = enlaceUIT
                                    val i = Intent(Intent.ACTION_VIEW)
                                    i.data = Uri.parse(url)
                                    startActivity(i)
                                    it.dismiss()
                                }
                                .showCancelButton(true).setCancelClickListener { sDialog ->
                                    sDialog.cancel()
                                }.show()
                        }
                    }

                }
            }

            override fun onFailure(call: Call<UIT>, t: Throwable) {
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
                    Thread.sleep(3000)
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


