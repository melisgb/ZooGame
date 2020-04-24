package com.ggonzales.zoogame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_animal.*

class ViewAnimal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animal)

        animalNameTView.text = intent.extras!!.get("name").toString()
        animalDescTView.text = intent.extras!!.get("description").toString()

    }
}
