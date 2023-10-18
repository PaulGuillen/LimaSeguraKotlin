package devpaul.business.safetylima.fragments

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.MyNewsAdapter
import devpaul.business.safetylima.adapter.MyDataAdapter
import devpaul.business.safetylima.data.repository.NewsPeruRepository
import devpaul.business.safetylima.data.repository.NewsRepository
import devpaul.business.safetylima.databinding.FragmentNewsBinding
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.uitl.SingletonError
import devpaul.business.safetylima.domain.uitl.applyButtonSelectionLogic
import devpaul.business.safetylima.domain.usecases.NewsPeruUseCase
import devpaul.business.safetylima.domain.usecases.NewsUseCase
import devpaul.business.safetylima.lifecycle.BaseFragmentModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NewsFragment : BaseFragmentModule() {

    lateinit var adapter1: MyNewsAdapter
    lateinit var adapter2: MyDataAdapter

    private var binding: FragmentNewsBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding?.root

        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.setHasFixedSize(true)


        selectItemsNews()

        return view
    }

    private fun selectItemsNews() {
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

        applyButtonSelectionLogic(cardViewButtons, grayColor, whiteColor) { button ->
            when (button) {
                btnPeru -> getNewsFromPeru()
                btnArgentina -> getArgentinaNews()
                btnColombia -> getColombiaNews()
                btnCuba -> getCubaNews()
                btnMexico -> getMexicoNews()
                btnVenezuela -> getVenezuelaNews()
            }
        }
    }

    private fun getVenezuelaNews() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsVenezuelaRepository = NewsRepository()
                val newsVenezuelaUseCase = NewsUseCase(requireContext(), newsVenezuelaRepository)
                val newsVenezuelaRequest = newsVenezuelaUseCase.newsFromVenezuela()

                withContext(Dispatchers.Main) {
                    when (newsVenezuelaRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsVenezuelaRequest.data
                            val dataInList = data.articles
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                            adapter2 = MyDataAdapter(requireContext(), dataInList)
                            adapter2.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter2

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

    private fun getMexicoNews() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsMexicoRepository = NewsRepository()
                val newsMexicoUseCase = NewsUseCase(requireContext(), newsMexicoRepository)
                val newsMexicoRequest = newsMexicoUseCase.newsFromMexico()

                withContext(Dispatchers.Main) {
                    when (newsMexicoRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsMexicoRequest.data
                            val dataInList = data.articles
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                            adapter2 = MyDataAdapter(requireContext(), dataInList)
                            adapter2.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter2

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

    private fun getCubaNews() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsCubaRepository = NewsRepository()
                val newsCubaUseCase = NewsUseCase(requireContext(), newsCubaRepository)
                val newsCubaRequest = newsCubaUseCase.newsFromCuba()

                withContext(Dispatchers.Main) {
                    when (newsCubaRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsCubaRequest.data
                            val dataInList = data.articles
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                            adapter2 = MyDataAdapter(requireContext(), dataInList)
                            adapter2.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter2

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

    private fun getColombiaNews() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsColombiaRepository = NewsRepository()
                val newsColombiaUseCase = NewsUseCase(requireContext(), newsColombiaRepository)
                val newsColombiaRequest = newsColombiaUseCase.newsFromColombia()

                withContext(Dispatchers.Main) {
                    when (newsColombiaRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsColombiaRequest.data
                            val dataInList = data.articles
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                            adapter2 = MyDataAdapter(requireContext(), dataInList)
                            adapter2.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter2

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
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                            adapter2 = MyDataAdapter(requireContext(), dataInList)
                            adapter2.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter2

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

    private fun getNewsFromPeru() {

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val newsPeruRepository = NewsPeruRepository()
                val newsPeruUseCase = NewsPeruUseCase(requireContext(), newsPeruRepository)
                val newsPeruRequest = newsPeruUseCase.newsPeru()

                withContext(Dispatchers.Main) {
                    when (newsPeruRequest) {
                        is CustomResult.OnSuccess -> {
                            val data = newsPeruRequest.data
                            binding?.shimmerFrameLayout?.visibility = View.GONE
                            binding?.recyclerView?.visibility = View.VISIBLE
                            adapter1 = MyNewsAdapter(requireContext(), data)
                            adapter1.notifyDataSetChanged()
                            binding?.recyclerView?.adapter = adapter1

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