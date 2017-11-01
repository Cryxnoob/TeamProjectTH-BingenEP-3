package project2

import java.util.*

class Train(val schedule:MutableList<Int>) {

    private var delayed:Boolean = false

    public fun setDelayed(d: Boolean){
        delayed= d
    }

    public fun getDelayed():Boolean{
        return delayed
    }
    public fun getcurrentSegment(stepnumber:Int):Int{


        return schedule[stepnumber]
    }
    fun getschedulelenght():Int{

        return schedule.size
    }
}