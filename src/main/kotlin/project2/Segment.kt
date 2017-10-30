package project2

class Segment(id:Int) {
    private var currentcount:Int = 0
    private var id:Int = id

    public fun getid():Int{
        return id
    }


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