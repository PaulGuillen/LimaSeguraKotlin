package devpaul.business.safetylima.lifecycle

import android.os.Bundle

abstract class BaseFragmentModule : EmptyDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}