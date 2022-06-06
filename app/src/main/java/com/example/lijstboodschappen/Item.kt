package com.example.lijstboodschappen

class Item(var birdListName: String, var birdListImage: Int) {
    fun getbirdName(): String {
        return birdListName
    }

    fun getbirdImage(): Int {
        return birdListImage
    }
}