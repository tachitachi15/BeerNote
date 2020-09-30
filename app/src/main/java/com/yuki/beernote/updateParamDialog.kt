package com.yuki.beernote

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.setting_param.*

class UpdateParamDialog(private val dao:BeerDao,private val adapter: FirstFragment.BeerAdapter,private val beer:Beer) :  DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val paramSettingView = inflater.inflate(R.layout.setting_param,null)

        val beerName:EditText = paramSettingView.findViewById<EditText>(R.id.new_beer_name)
        beerName.setText(beer.name)
        val bodyRatingBar:RatingBar = paramSettingView.findViewById<RatingBar>(R.id.rating_param_body)
        bodyRatingBar.rating = beer.body
        val sharpnessRatingBar:RatingBar= paramSettingView.findViewById<RatingBar>(R.id.rating_param_sharpness)
        sharpnessRatingBar.rating = beer.sharpness
        val sourRatingBar:RatingBar = paramSettingView.findViewById<RatingBar>(R.id.rating_param_sour)
        sourRatingBar.rating = beer.sour
        val sweetRatingBar:RatingBar = paramSettingView.findViewById<RatingBar>(R.id.rating_param_sweet)
        sweetRatingBar.rating = beer.sweet
        val bitterRatingBar:RatingBar = paramSettingView.findViewById<RatingBar>(R.id.rating_param_bitter)
        bitterRatingBar.rating = beer.bitter

        builder.setView(paramSettingView)
            .setTitle("ビールの味の評価")
            .setPositiveButton(R.string.ok) { dialog, id ->
                beer.body = bodyRatingBar.rating
                beer.sharpness = sharpnessRatingBar.rating
                beer.sweet = sweetRatingBar.rating
                beer.bitter = bitterRatingBar.rating
                beer.sour = sourRatingBar.rating
                dao.update(beer)
                adapter.beers = dao.getAll()
            }
            .setNegativeButton(R.string.cancel, {dialog,id -> })
        return builder.create()

    }
}