package ua.vovan42002.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.vovan42002.data.Ship
import java.util.*

class ShipViewModel: ViewModel() {
    val listShips = MutableLiveData<ArrayList<Ship>>()
}