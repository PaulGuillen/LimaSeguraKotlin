package devpaul.business.safetylima.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.MyNewsAdapter
import devpaul.business.safetylima.adapter.MyDataAdapter
import devpaul.business.safetylima.data.repository.NewsPeruRepository
import devpaul.business.safetylima.data.repository.NewsRepository
import devpaul.business.safetylima.databinding.FragmentNewsBinding
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.util.SingletonError
import devpaul.business.safetylima.domain.util.applyButtonSelectionLogic
import devpaul.business.safetylima.domain.usecases.NewsPeruUseCase
import devpaul.business.safetylima.domain.usecases.NewsUseCase
import devpaul.business.safetylima.lifecycle.BaseFragmentModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NewsFragment : BaseFragmentModule() {

    private var binding: FragmentNewsBinding? = null
    private var isPressed = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding?.root

        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.setHasFixedSize(true)


        selectItemsNews()

        return view
    }

    private fun selectItemsNews() {
        isPressed = true
        val grayColor = ContextCompat.getColor(requireContext(), R.color.mid_gray_card)
        val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)

        val btnPeru = binding?.includeHorizontalCardNews?.btnPeru
        val btnArgentina = binding?.includeHorizontalCardNews?.btnArgentina
        val btnColombia = binding?.includeHorizontalCardNews?.btnColombia
        val btnCuba = binding?.includeHorizontalCardNews?.btnCuba
        val btnMexico = binding?.includeHorizontalCardNews?.btnMexico
        val btnVenezuela = binding?.includeHorizontalCardNews?.btnVenezuela

        val cardViewButtons = listOf(
            btnPeru, btnArgentina, btnColombia, btnCuba, btnMexico, btnVenezuela
        )

        btnPeru?.setBackgroundColor(grayColor)

        applyButtonSelectionLogic(cardViewButtons, grayColor, whiteColor) { button ->
            cardViewButtons.filter { it != button }.forEach { it?.setBackgroundColor(whiteColor) }

            when (button) {
                btnPeru -> getNewsFromPeru()
                btnArgentina -> getNewsForCountry("Argentina")
                btnColombia -> getNewsForCountry("Colombia")
                btnCuba -> getNewsForCountry("Cuba")
                btnMexico -> getNewsForCountry("Mexico")
                btnVenezuela -> getNewsForCountry("Venezuela")
            }
        }
    }

    private fun getNewsForCountry(country: String) {
        binding?.shimmerFrameLayout?.visibility = View.VISIBLE
        binding?.recyclerView?.visibility = View.GONE

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsRepository = NewsRepository()
                val newsUseCase = NewsUseCase(requireContext(), newsRepository)
                val newsRequest = when (country) {
                    "Venezuela" -> newsUseCase.newsFromVenezuela()
                    "Mexico" -> newsUseCase.newsFromMexico()
                    "Cuba" -> newsUseCase.newsFromCuba()
                    "Colombia" -> newsUseCase.newsFromColombia()
                    "Argentina" -> newsUseCase.newsFromArgentina()
                    else -> return@launch
                }

                delay(4000)

                withContext(Dispatchers.Main) {
                    when (newsRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsRequest.data
                            val adapter = MyDataAdapter(requireContext(), data.articles)
                            adapter.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                        }

                        is CustomResult.OnError -> {
                            val codeState = SingletonError.code
                            val titleState = SingletonError.title
                            val subTitleState = SingletonError.subTitle ?: "No data"
                        }
                    }
                }
            } catch (e: Exception) {
                // Manejar la excepción
            }
        }
    }

    private fun getNewsFromPeru() {
        if (isPressed) {
            binding?.shimmerFrameLayout?.visibility = View.VISIBLE
            binding?.recyclerView?.visibility = View.GONE
        }

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsPeruRepository = NewsPeruRepository()
                val newsPeruUseCase = NewsPeruUseCase(requireContext(), newsPeruRepository)
                val newsPeruRequest = newsPeruUseCase.newsPeru()

                delay(4000)

                withContext(Dispatchers.Main) {
                    when (newsPeruRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsPeruRequest.data
                            val adapter = MyNewsAdapter(requireContext(), data)
                            adapter.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
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

    override fun onStart() {
        super.onStart()
        getNewsFromPeru()
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