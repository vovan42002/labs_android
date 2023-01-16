package ua.vovan42002.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ua.vovan42002.R
import ua.vovan42002.data.Ship

class ShipDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ship: Ship = arguments?.getSerializable(SHIP) as Ship
        val titleShip: TextView = view.findViewById(R.id.nameShip)
        val homePort: TextView = view.findViewById(R.id.homePort)
        val imageShip: ImageView = view.findViewById(R.id.imageShip)
        val model: TextView = view.findViewById(R.id.type)

        titleShip.text = ship.name
        homePort.text = ship.home_port
        model.text = ship.type
        Glide
            .with(requireContext())
            .load(ship.image)
            .placeholder(R.drawable.no_image)
            .into(imageShip)
    }

    companion object{
        private const val SHIP = "Ship"
    }
}