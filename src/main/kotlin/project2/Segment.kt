package project2

class Segment(val id:Int,val capacity:Int) {
    private var currentCount:Int = 0
    private var scheduleCapacities:MutableList<Int> = mutableListOf()


    public fun setCurrentCount(c: Int) {

        currentCount = c
    }

    public fun getCurrentCount():Int{
        return currentCount
    }

    fun increaseCount(){

        currentCount++
    }

    public fun persistCapacity(){
        if(currentCount >= capacity){
            scheduleCapacities.add(0)
        }
        else {
            scheduleCapacities.add((capacity - currentCount))
        }
    }
    public fun getPersistentCapacities():MutableList<Int> {
        return scheduleCapacities
    }
}