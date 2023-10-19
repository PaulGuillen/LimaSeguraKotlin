package devpaul.business.safetylima.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.pedant.SweetAlert.SweetAlertDialog
import devpaul.business.safetylima.activities.settings.SettingsActivity
import devpaul.business.safetylima.data.repository.DollarQuoteRepository
import devpaul.business.safetylima.data.repository.UITRepository
import devpaul.business.safetylima.databinding.FragmentHomeBinding
import devpaul.business.safetylima.domain.util.SingletonError
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.usecases.DollarQuoteUseCase
import devpaul.business.safetylima.domain.usecases.UITUseCase
import devpaul.business.safetylima.domain.util.showDialogGoToPage
import devpaul.business.safetylima.lifecycle.BaseFragmentModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class HomeFragment : BaseFragmentModule() {

    private var binding: FragmentHomeBinding? = null
    private var isDollarServiceFinished = false
    private var isUITServiceFinished = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root

        binding?.icSettings?.setOnClickListener {
            val i = Intent(context, SettingsActivity::class.java)
            startActivity(i)
        }

        binding?.newsFragment?.setOnClickListener {

        }

        getQuoteDollar()
        getUIT()

        return view
    }

    private fun getQuoteDollar() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val dollarQuoteRepository = DollarQuoteRepository()
                val dollarUseCase = DollarQuoteUseCase(requireContext(), dollarQuoteRepository)
                val dollarQuoteRequest = dollarUseCase.dollarQuote()


                delay(5000)

                withContext(Dispatchers.Main) {
                    when (dollarQuoteRequest) {
                        is CustomResult.OnSuccess -> {

                            isDollarServiceFinished = true
                            checkShimmerVisibility()

                            val data = dollarQuoteRequest.data
                            Timber.d("HomeFragmentResponse : $data")
                            val purchaseValue = data.Cotizacion[0].compra
                            val saleValue = data.Cotizacion[0].venta
                            val link = data.enlace
                            val site = data.sitio
                            val date = data.fecha

                            binding?.includeCardViewDollarQuote?.purchaseQuote?.text = purchaseValue.toString()
                            binding?.includeCardViewDollarQuote?.sellerQuote?.text = saleValue.toString()
                            binding?.includeCardViewDollarQuote?.siteQuote?.text = site
                            binding?.includeCardViewDollarQuote?.dateQuote?.text = date

                            binding?.includeCardViewDollarQuote?.cardViewQuoteDollar?.setOnClickListener {
                                if (!link.isNullOrBlank()) {
                                    showDialogGoToPage(requireContext(), link)
                                }
                            }
                        }

                        is CustomResult.OnError -> {

                            isDollarServiceFinished = true
                            checkShimmerVisibility()

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
                isDollarServiceFinished = true
                checkShimmerVisibility()
            }
        }
    }

    private fun getUIT() {

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val uitRepository = UITRepository()
                val uitUseCase = UITUseCase(requireContext(), uitRepository)
                val uitRequest = uitUseCase.dataUIT()

                delay(3000)

                withContext(Dispatchers.Main) {
                    when (uitRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = uitRequest.data
                            val valueUIT = data.UIT
                            val periodUIT = data.periodo
                            val siteUIT = data.sitio
                            val link = data.enlace

                            isUITServiceFinished = true
                            checkShimmerVisibility()

                            binding?.includeCardViewUIT?.valueUIT?.text = valueUIT.toString()
                            binding?.includeCardViewUIT?.periodUIT?.text = periodUIT.toString()
                            binding?.includeCardViewUIT?.siteUIT?.text = siteUIT

                            binding?.includeCardViewUIT?.cardViewUIT?.setOnClickListener {
                                if (!link.isNullOrBlank()) {
                                    showDialogGoToPage(requireContext(), link)
                                }
                            }
                        }

                        is CustomResult.OnError -> {

                            isUITServiceFinished = true
                            checkShimmerVisibility()

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
                isUITServiceFinished = true
                checkShimmerVisibility()
            }
        }
    }

    private fun checkShimmerVisibility() {
        if (isDollarServiceFinished && isUITServiceFinished) {
            binding?.shimmerFrameLayout?.visibility = View.GONE
            binding?.includeCardViewDollarQuote?.cardViewQuoteDollar?.visibility = View.VISIBLE
            binding?.includeCardViewUIT?.cardViewUIT?.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.shimmerFrameLayout?.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        binding?.shimmerFrameLayout?.startShimmerAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}


