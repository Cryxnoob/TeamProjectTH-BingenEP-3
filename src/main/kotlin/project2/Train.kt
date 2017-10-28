package project2


import java.util.*

class Train(schedule:MutableList<Int>) {

    private var delayed:Boolean = false
    private var schedule:List<Int> = schedule

    public fun setDelayed(d: Boolean){
        delayed= d
    }

    public fun getDelayed():Boolean{
        return delayed
    }
    public fun getcurrentSegment():Int{


        return schedule[0]
    }
}