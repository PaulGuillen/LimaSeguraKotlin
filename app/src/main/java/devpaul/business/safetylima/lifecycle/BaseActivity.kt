package devpaul.business.safetylima.lifecycle

import android.app.Dialog
import android.os.Bundle
import devpaul.business.safetylima.domain.util.ChargeDialog

abstract class BaseActivity : InitialActivity() {

    private var progressDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun hideLoading() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    fun showLoading() {
        hideLoading()
        progressDialog = ChargeDialog.showLoadingDialog(this)
    }

}