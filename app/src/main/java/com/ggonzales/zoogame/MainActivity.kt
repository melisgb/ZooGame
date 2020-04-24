package com.ggonzales.zoogame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_element.*
import kotlinx.android.synthetic.main.animal_element.view.*
import java.io.FileReader

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var animalsAdapter : AnimalsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAnimals()
    }

    fun loadAnimals(){
//        readAnimalsFile()
        listOfAnimals.add(Animal("Dog", "(Canis lupus familiaris) are domesticated mammals, not natural wild animals. They were originally bred from wolves. They have been bred by humans for a long time, and were the first animals ever to be domesticated.", R.drawable.dog_image, false))
        listOfAnimals.add(Animal("Cat", "(Felis catus), are small, carnivorous (meat-eating) mammals, of the family Felidae. Domestic cats are often called house cats when kept as indoor pets.", R.drawable.cat_image, false))
        listOfAnimals.add(Animal("Monkey", "is a common name that may refer to groups or species of mammals, in part, the simians of infraorder Simiiformes. The term is applied descriptively to groups of primates, such as families of New World monkeys and Old World monkeys. ", R.drawable.monkey_image, false))
        listOfAnimals.add(Animal("Dolphin", "is a common name of aquatic mammals within the infraorder Cetacea. The term dolphin usually refers to the extant families Delphinidae (the oceanic dolphins), Platanistidae (the Indian river dolphins), Iniidae (the New World river dolphins), and Pontoporiidae (the brackish dolphins), and the extinct Lipotidae (baiji or Chinese river dolphin).", R.drawable.dolphin_image, true))
        listOfAnimals.add(Animal("Elephant", "are mammals of the family Elephantidae and the largest existing land animals. Three species are currently recognised: the African bush elephant, the African forest elephant, and the Asian elephant. Elephantidae is the only surviving family of the order Proboscidea; extinct members include the mastodons. The family Elephantidae also contains several now-extinct groups, including the mammoths and straight-tusked elephants. African elephants have larger ears and concave backs, whereas Asian elephants have smaller ears, and convex or level backs. Distinctive features of all elephants include a long trunk, tusks, large ear flaps, massive legs, and tough but sensitive skin.", R.drawable.elephant_image, true))
        listOfAnimals.add(Animal("Lion", "(Panthera leo) is a species in the family Felidae; it is a muscular, deep-chested cat with a short, rounded head, a reduced neck and round ears, and a hairy tuft at the end of its tail. It is sexually dimorphic; adult male lions have a prominent mane, which is the most recognisable feature of the species. With a typical head-to-body length of 184–208 cm (72–82 in) they are larger than females at 160–184 cm (63–72 in). It is a social species, forming groups called prides.", R.drawable.lion_image, true))
        listOfAnimals.add(Animal("Tiger", "(Panthera tigris) is the largest cat species and a member of the genus Panthera. It is most recognisable for its dark vertical stripes on orange-brown fur with a lighter underside. It is an apex predator, primarily preying on ungulates such as deer and wild boar.", R.drawable.tiger_image, true))
        animalsAdapter = AnimalsAdapter(this, listOfAnimals)
        animalsListView.adapter = animalsAdapter
    }

    fun readAnimalsFile(){
//        filedir /data/user/0/com.ggonzales.zoogame/files
        val filePath = "animalsinfo.txt"
        var fileI = FileReader(filePath)
        var arrayLines = fileI.readLines()
        for(line in arrayLines){
//            val arr =line.split("|")
//            val name = arr[0]
//            val desc = arr[1]
//            var nAnimal = Animal(name, desc, 1)
//            println("$name, $desc")
        }
        Log.d("App message", filesDir.toString())

    }

    fun deleteAnimal(pos : Int){
        listOfAnimals.removeAt(pos)
        animalsAdapter!!.notifyDataSetChanged()
    }
    fun copyAnimal(index :Int, animal : Animal){
        listOfAnimals.add(index, animal)
        animalsAdapter!!.notifyDataSetChanged()
    }

    //for a class to use a func inside the other class, must be ineer
    inner class AnimalsAdapter : BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context : Context? = null
        constructor(context : Context, listOfAnimals : ArrayList<Animal>) : super(){
            this.context = context
            this.listOfAnimals = listOfAnimals
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val curAnimal = listOfAnimals[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            if(curAnimal.isSavage!!){
                var curView = inflater.inflate(R.layout.animal_savage_element, null)
                curView.animalNameTextView.text = curAnimal.name
                curView.animalDescTextView.text = curAnimal.description
                curView.animalImageButton.setImageResource(curAnimal.image!!)
                curView.animalImageButton.setOnClickListener{
//                    intent takes two parameters: thisActivity, destinationActivity
                    var intent = Intent(context, ViewAnimal::class.java)
                    intent.putExtra("name", curAnimal.name)
                    intent.putExtra("description", curAnimal.description)
                    intent.putExtra("image", curAnimal.image!!)
                    context!!.startActivity(intent)
                }
                return curView
            }
            else{
                var curView = inflater.inflate(R.layout.animal_element, null)
                curView.animalNameTextView.text = curAnimal.name
                curView.animalDescTextView.text = curAnimal.description
                curView.animalImageButton.setImageResource(curAnimal.image!!)
                curView.animalImageButton.setOnClickListener{
                    //intent takes two parameters: thisActivity, destinationActivity
                    var intent = Intent(context, ViewAnimal::class.java)
                    intent.putExtra("name", curAnimal.name)
                    intent.putExtra("description", curAnimal.description)
                    intent.putExtra("image", curAnimal.image!!)
                    context!!.startActivity(intent)

                    //example to add an animal when the image Button is clicked
//                    copyAnimal(position, curAnimal)
                    //another example to delete an animal when the image Button is clicked
//                    deleteAnimal(position)
                }
                return curView
            }
        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
