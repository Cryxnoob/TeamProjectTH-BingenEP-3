package project2

import java.util.*

class Network(val capacity:Int,val trains:List<Train>, val scheduleLength:Int, val capacities: MutableList<Int>) {
    private var segments:MutableList<Segment> = mutableListOf()
    private var currentStep:Int = 0

    init{

        var x:Int= 1
        for (capacity in capacities){
            segments.add(Segment(x, capacity))
            x++
        }


    }

    fun simulateOneStep(){

        var diff:Int
        var x:Int
        var y:Int
        var delayedTrains:MutableList<Int> = mutableListOf()
        /*
        reset segment Train count
        */
        for (segment in segments) {

            segment.setCurrentCount(0)
        }
        for (train in trains){
            train.setDelayed(false)
        }
        for (train in trains){

            var segment:Int = train.getCurrentSegment(currentStep)-1
            if (segment>-1){
                segments[segment].increaseCount()
            }
        }

        for (segment in segments){
            if (segment.getCurrentCount()>segment.capacity){

                diff = segment.getCurrentCount()-segment.capacity
                x = diff
                while (x > 0 ){
                    var differentTrain: Boolean = false
                    while (!differentTrain) {
                        val newValue = Random().nextInt(segment.getCurrentCount())
                        if(newValue !in delayedTrains) {
                            delayedTrains.add(newValue)
                            differentTrain = true
                        }
                    }
                    x--

                }
                y = 0

                for (train in trains){
                    if (train.getCurrentSegment(currentStep)==segment.id){
                        for (delayedTrain in delayedTrains){
                            if (delayedTrain == y){
                                train.setDelayed(true)

                            }
                        }

                        y++
                    }

                }
            }
            segment.persistCapacity()
        }
        currentStep++


    }
    fun getSegmentsCapacityTracking(): MutableList<MutableList<Int>> {
        var masterList: MutableList<MutableList<Int>> = mutableListOf()
        for(segment in segments) {
            masterList.add(segment.getPersistentCapacities())
        }
        return masterList
    }
}