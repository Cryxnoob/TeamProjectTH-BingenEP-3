package project2

import java.util.*

class Network(val capacity:Int,val trains:List<Train>, val schedulelength:Int, val capacities: MutableList<Int>) {
    private var segments:MutableList<Segment> = mutableListOf()
    private var currentstep:Int = 0

    init{

        var x:Int= 1
        for (capacity in capacities){
            segments.add(Segment(x, capacity))
            x++
        }


    }

    fun simulateoneStep(){

        var diff:Int
        var x:Int
        var y:Int
        var delayedtrains:MutableList<Int> = mutableListOf()
        /*
        reset segment Train count
        */
        for (segment in segments) {

            segment.setcurrentcount(0)
        }
        for (train in trains){
            train.setDelayed(false)
        }
        for (train in trains){

            var segment:Int = train.getcurrentSegment(currentstep)-1
            if (segment>-1){
                segments[segment].increasecount()
            }
        }

        for (segment in segments){
            if (segment.getcurrentcount()>segment.capacity){

                diff = segment.getcurrentcount()-segment.capacity
                x = diff
                while (x > 0 ){
                    delayedtrains.add(Random().nextInt(segment.getcurrentcount()))
                    x--
                }
                y = 0

                for (train in trains){
                    if (train.getcurrentSegment(currentstep)==segment.id){
                        for (delayedtrain in delayedtrains){
                            if (delayedtrain == y){
                                train.setDelayed(true)
                            }
                        }

                        y++
                    }

                }
            }

        }
        currentstep++


    }
}