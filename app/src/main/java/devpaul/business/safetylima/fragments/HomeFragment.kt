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
import devpaul.business.safetylima.api.Common
import devpaul.business.safetylima.entities.Dolar
import devpaul.business.safetylima.entities.UIT

import devpaul.business.safetylima.providers.DolarUITProvider
import devpaul.business.safetylima.routes.RetrofitService
import dmax.dialog.SpotsDialog
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
    var site: TextView? = null
    var cardViewDolar: CardView? = null
    var date: TextView? = null

    //ToUITDATA
    var servicioUIT: TextView? = null
    var valorUIT: TextView? = null
    var periodoUIT: TextView? = null
    var sitioUIT: TextView? = null
    var cardViewUIT: CardView? = null

    var setting : ImageButton ? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false)

        services = myView?.findViewById(R.id.servicio)
        site = myView?.findViewById(R.id.sitio)
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

        mService = Common.retrofitService

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(requireContext()).build()


        if (isOnline()) {
            getdollardata()
            getUIT()
        } else {
            getConnectionValidation()
      /*      Snackbar.make(requireActivity().findViewById(android.R.id.content), "text to show", Snackbar.LENGTH_LONG).show();*/
        }

        return myView
    }

    private fun getdollardata() {
        currencyProvider.getCurrencyDolar()?.enqueue(object : Callback<Dolar> {
            override fun onResponse(call: Call<Dolar>, response: Response<Dolar>) {

                if (response.body() != null) {
                    val servicio = response.body()?.servicio
                    val sitio = response.body()?.sitio
                    val enlace = response.body()?.enlace
                    val Compra = response.body()?.Cotizacion?.get(0)?.Compra // ArrayList obteniendo data por posicion
                    val Venta = response.body()?.Cotizacion?.get(0)?.Venta // ArrayList obteniendo data por posicion

                    services?.text = servicio
                    site?.text = sitio
                    buyDolar?.text = Compra.toString()
                    sellDolar?.text = Venta.toString()
                    cardViewDolar?.setOnClickListener {
                        if (!enlace.isNullOrBlank()) {

                            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ver p??gina oficial?").setCancelText("Cancelar")
                                .setConfirmText("Si")
                                .setConfirmClickListener {
                                    val url = enlace
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

            override fun onFailure(call: Call<Dolar>, t: Throwable) {
                Log.v(TAG, "Error: ${t.message}")
            }

        })
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

                            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ver p??gina oficial?")
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


