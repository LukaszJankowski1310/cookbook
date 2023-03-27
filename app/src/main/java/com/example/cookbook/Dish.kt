package com.example.cookbook

class Dish(val name : String, val recipe : String) {

    companion object {
        val dishes : ArrayList<Dish> = arrayListOf(
            Dish("kotlet", "napierdalać młotkiem"),
            Dish("ryba", "wyfiletwaoć")
        )
    }

}