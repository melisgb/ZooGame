package com.ggonzales.zoogame

class Animal{
    var name : String? = null
    var description : String? = null
    var image : Int? = null
    var isSavage: Boolean? = null

    constructor(name : String, description : String, image : Int, isSavage: Boolean){
        this.name = name
        this.description = description
        this.image = image
        this.isSavage = isSavage
    }
}