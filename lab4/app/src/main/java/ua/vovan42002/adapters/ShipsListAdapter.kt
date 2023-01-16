package ua.vovan42002.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ua.vovan42002.R
import ua.vovan42002.data.Ship
import java.util.*

class ShipsListAdapter(
    context: Context,
    ships: ArrayList<Ship>
) : BaseAdapter() {

    private var context: Context = context
    private var ships: List<Ship> = ships

    override fun getCount(): Int {
        return ships.size //returns total of items in the list
    }

    override fun getItem(position: Int): Any {
        return ships[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var convertView: View? = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.custom_list, parent, false)
        }
        val currentShip = getItem(position) as Ship
        val name = convertView
            ?.findViewById(R.id.title) as TextView
        val homePort = convertView
            ?.findViewById(R.id.description) as TextView

        val iconImageView = convertView
            ?.findViewById(R.id.icon) as ImageView

        name.text = currentShip.name
        homePort.text = currentShip.home_port
        Glide
            .with(context)
            .load(currentShip.image)
            .placeholder(R.drawable.no_image)
            .into(iconImageView)

        return convertView
    }
}