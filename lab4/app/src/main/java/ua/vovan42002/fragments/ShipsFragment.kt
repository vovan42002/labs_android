package ua.vovan42002.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import ua.vovan42002.R
import ua.vovan42002.adapters.ShipsListAdapter
import ua.vovan42002.data.AppDatabase
import ua.vovan42002.data.Ship
import ua.vovan42002.viewModels.ShipViewModel
import java.net.URL
import kotlin.collections.ArrayList

class ShipsFragment : Fragment() {

    private val fetchUrl = "https://api.spacexdata.com/v4/ships"

    private val viewModel: ShipViewModel by viewModels()

    private var okHttpClient: OkHttpClient = OkHttpClient()
    private val jackson = jacksonObjectMapper()
    private lateinit var listAdapter: ShipsListAdapter
    private var ships: ArrayList<Ship> = ArrayList<Ship>()
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ships, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        listView = view.findViewById(R.id.listView)

        listView.setOnItemClickListener { parent, _, position, _ ->
            val selectedShip = parent.getItemAtPosition(position) as Ship
            val bundle = Bundle()
            bundle.putSerializable(SHIP, selectedShip)
            findNavController().navigate(R.id.shipDetailsFragment, bundle)
        }

        val db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, "ship"
        ).build()
        val shipDao = db.shipDao()

        if (shipDao.getAll().value != null) {
            ships = ArrayList<Ship>(shipDao.getAll().value)
        }

        listAdapter = ShipsListAdapter(requireActivity(), ships)
        listView.adapter = listAdapter
        listAdapter.notifyDataSetChanged()

        viewModel.listShips.observe(requireActivity()) {
            listAdapter = ShipsListAdapter(requireActivity(), it)
            listView.adapter = listAdapter
        }

        fetch(fetchUrl)
    }

    private fun deleteUnnamed(ships: ArrayList<Ship>): ArrayList<Ship> {
        for (ship in ships) {
            if (ship.name == null)
                ships.remove(ship)
        }
        return ships
    }

    private fun fetch(sUrl: String) {

        lifecycleScope.launch(Dispatchers.IO) {
            val result = getRequest(sUrl)
            Log.d(TAG, result.toString())
            if (result != null) {
                try {
                    ships = jackson.readValue(result)
                    ships = deleteUnnamed(ships)
                    withContext(Dispatchers.Main) {
                        viewModel.listShips.value = ships
                        lifecycleScope.launch(Dispatchers.Default) {
                            val db = Room.databaseBuilder(
                                requireActivity(),
                                AppDatabase::class.java, "ship"
                            ).build()
                            val shipDao = db.shipDao()
                            shipDao.deleteAll()
                            shipDao.insertAll(ships)
                        }

                    }
                } catch (err: Error) {
                    Log.d(TAG, "Error when parsing JSON: " + err.localizedMessage)
                }
            } else {
                Log.d(TAG, "Error: Get request returned no response")
            }
        }
    }

    private fun getRequest(sUrl: String): String? {
        var result: String? = null
        try {
            val url = URL(sUrl)
            val request = Request.Builder().url(url).build()
            val response = okHttpClient.newCall(request).execute()
            result = response.body?.string()
        } catch (err: Error) {
            Log.d(TAG, "Error when executing get request: " + err.localizedMessage)
        }
        return result
    }

    companion object {
        private const val TAG = "ShipsFragment"
        private const val SHIP = "Ship"
    }
}