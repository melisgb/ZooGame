package com.ggonzales.zoogame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_animal.*
import kotlinx.android.synthetic.main.animal_element.*

class ViewAnimal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animal)

        val bundle : Bundle = intent.extras!!
        animalNameTView.text = bundle.getString("name")
        animalDescTView.text = bundle.getString("description")
        animalIButton.setImageResource(bundle.getInt("image"))

    }
}
