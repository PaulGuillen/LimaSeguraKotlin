package devpaul.business.safetylima.domain.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import devpaul.business.safetylima.R

fun <T : CardView?> applyButtonSelectionLogic(
    buttons: List<T>,
    selectedColor: Int,
    unselectedColor: Int,
    onClick: (T) -> Unit
) {
    var selectedButton: T? = null
    buttons.forEach { button ->
        button?.setOnClickListener {
            selectedButton?.setBackgroundColor(unselectedColor)
            button.setBackgroundColor(selectedColor)
            selectedButton = button
            onClick(button)
        }
    }
}

fun showDialogGoToPage(
    context: Context,
    linkToDePeru: String? = null
) {
    val customDialogView = LayoutInflater.from(context).inflate(R.layout.item_card_go_to_link, null)

    val dialog = AlertDialog.Builder(context)
        .setView(customDialogView)
        .setCancelable(false)
        .create()

    val btnDismiss = customDialogView.findViewById<TextView>(R.id.btnDismiss)
    val btnGoToPage: TextView = customDialogView.findViewById(R.id.btnGoToPage)

    if (dialog.window != null) {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    btnGoToPage.setOnClickListener {
        if (!linkToDePeru.isNullOrBlank()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkToDePeru))
            context.startActivity(intent)
            dialog.dismiss()
        }
    }
    btnDismiss.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}