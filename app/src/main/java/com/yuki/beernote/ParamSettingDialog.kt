package com.yuki.beernote

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment

class ParamSettingDialog(private val dao:BeerDao,private val adapter: FirstFragment.BeerAdapter) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val paramSettingView = inflater.inflate(R.layout.setting_param,null)

        builder.setView(paramSettingView)
            .setTitle("ビールの味の評価")
            .setPositiveButton(R.string.ok) { dialog, id ->
                var name: String =
                    paramSettingView.findViewById<EditText>(R.id.new_beer_name).text.toString()
                val body: Float =
                    paramSettingView.findViewById<RatingBar>(R.id.rating_param_body).rating
                val sharpness: Float =
                    paramSettingView.findViewById<RatingBar>(R.id.rating_param_sharpness).rating
                val sour: Float =
                    paramSettingView.findViewById<RatingBar>(R.id.rating_param_sour).rating
                val sweet: Float =
                    paramSettingView.findViewById<RatingBar>(R.id.rating_param_sweet).rating
                val bitter: Float =
                    paramSettingView.findViewById<RatingBar>(R.id.rating_param_bitter).rating
                val newBeer: Beer = Beer(0, name, body, sharpness, sweet, bitter, sour)
                dao.insert(newBeer)
                adapter.beers = dao.getAll()
            }
            .setNegativeButton(R.string.cancel, {dialog,id -> })
        return builder.create()




    }
}