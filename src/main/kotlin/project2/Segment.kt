package project2

class Segment(val id:Int,val capacity:Int) {
    private var currentcount:Int = 0


    public fun setcurrentcount(c: Int) {

        currentcount = c
    }

    public fun getcurrentcount():Int{
        return currentcount
    }

    fun increasecount(){

        currentcount++
    }
}