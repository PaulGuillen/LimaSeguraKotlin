package devpaul.business.safetylima.domain.uitl

import androidx.cardview.widget.CardView

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
