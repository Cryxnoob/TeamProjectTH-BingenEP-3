package project2

import java.util.*

class Train(var schedule:MutableList<Int>, val priority: Int, val id: Int) {

    private var delayed:Boolean = false
    private var scheduleDriven: MutableList<Int> = mutableListOf()
    private var delayList: MutableList<Boolean> = mutableListOf()

    public fun setDelayed(d: Boolean){
        delayed= d
    }

    public fun getDelayed():Boolean{
        return delayed
    }
    public fun getCurrentSegment(stepNumber:Int):Int{


        return schedule[stepNumber]
    }
    fun getScheduleLength():Int{

        return schedule.size
    }

    public fun shiftSchedule(stepNumber: Int) {
        var x: Int = 0
        var tempSchedule: MutableList<Int> = mutableListOf()
        for (slot in schedule) {
            if (x == stepNumber) {
                tempSchedule.add(-1)
            }
            else if (x > stepNumber) {
                tempSchedule.add(schedule[x-1])
            }
            else if (x < stepNumber) {
                tempSchedule.add(schedule[x])
            }
            x++
        }
        schedule = tempSchedule
    }

    public fun persistStep(stepNumber: Int) {
        scheduleDriven.add(schedule[stepNumber])
        delayList.add(delayed)
    }

    public fun getScheduleDriven(): MutableList<Int> {
        return scheduleDriven
    }
}