package devpaul.business.safetylima.activities.category

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import devpaul.business.safetylima.R
import devpaul.business.safetylima.fragments.DistrictFragment
import devpaul.business.safetylima.fragments.HomeFragment
import devpaul.business.safetylima.fragments.NewsFragment
import devpaul.business.safetylima.fragments.StationFragment
import devpaul.business.safetylima.lifecycle.BaseActivity

class CategoryActivity : BaseActivity() {

    private var bottomNavigation: BottomNavigationView? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        openFragment(HomeFragment())

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation?.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.item_inicio -> HomeFragment()
                R.id.item_noticia -> NewsFragment()
                R.id.item_estacion -> StationFragment()
                R.id.item_distrito -> DistrictFragment()
                else -> HomeFragment()
            }

            openFragment(fragment)
            true
        }

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

}