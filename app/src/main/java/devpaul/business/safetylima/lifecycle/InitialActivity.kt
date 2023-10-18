package devpaul.business.safetylima.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import devpaul.business.safetylima.domain.util.TimberFactory

abstract class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TimberFactory.setupOnDebug()
    }

}