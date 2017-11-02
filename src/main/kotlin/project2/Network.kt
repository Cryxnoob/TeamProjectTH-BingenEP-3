package project2

import java.util.*

class Network(val capacity:Int,val trains:List<Train>, val scheduleLength:Int, val capacities: MutableList<Int>) {
    private var segments:MutableList<Segment> = mutableListOf()
    private var currentStep:Int = 0
    private var probPrio1: Int = 0
    private var probPrio2: Int = 0
    private var probPrio3: Int = 0
    private var segmentTrainMapping: MutableList<MutableList<Train>> = mutableListOf()

    init{

        var x:Int= 1
        for (capacity in capacities){
            segments.add(Segment(x, capacity))
            x++
        }


    }

    fun runSimulation() {
        while (currentStep < scheduleLength) {
            simulateOneStep()
        }
    }


    fun simulateOneStep(){


        var diff: Int
        var x: Int
        var delayedTrains: MutableList<Int> = mutableListOf()
        /*
        reset segment train count
        */
        segmentTrainMapping = mutableListOf()
        for(segment in segments) {
            segment.setCurrentCount(0)
            segmentTrainMapping.add(mutableListOf())
        }
        for(train in trains) {
            train.setDelayed(false)
        }

        for(train in trains) {
            var segment:Int = train.getCurrentSegment(currentStep) - 1
            if(segment > -1) {
                segments[segment].increaseCount()
                segmentTrainMapping[segment].add(train)
            }
        }

        var y = 0
        for (segment in segments) {
            if (segment.getCurrentCount() > segment.capacity) {
                diff = segment.getCurrentCount() - segment.capacity
                x = diff

                // calculate probabilities
                var count1: Int = 0
                var count2: Int = 0
                var count3: Int = 0

                for(mappedTrain in segmentTrainMapping[y]) {
                    when(mappedTrain.priority) {
                        1 -> count1++
                        2 -> count2++
                        3 -> count3++
                        else -> {
                            println("error: unknown priority")
                        }
                    }
                }

                if (count1 > 0 && count2 > 0 && count3 > 0) {
                    probPrio1 = (100 / (count1 + (0.5 * count2) + (0.25 * count3))).toInt()
                    probPrio2 = probPrio1 / 2
                    probPrio3 = probPrio2 / 2
                }
                else if (count1 > 0 && count2 > 0 && count3 == 0) {
                    probPrio1 = (100 / (count1 + (0.5 * count2) + (0.25 * count3))).toInt()
                    probPrio2 = probPrio1 / 2
                    probPrio3 = 0
                }
                else if (count1 > 0 && count2 == 0 && count3 > 0) {
                    probPrio1 = (100 / (count1 + (0.5 * count2) + (0.25 * count3))).toInt()
                    probPrio2 = 0
                    probPrio3 = probPrio1 / 2
                }
                else if (count1 > 0 && count2 == 0 && count3 == 0) {
                    probPrio1 = 100
                    probPrio2 = 0
                    probPrio3 = 0
                }
                else if (count1 == 0 && count2 > 0 && count3 > 0) {
                    probPrio1 = 0
                    probPrio2 = (100 / (count1 + (0.5 * count2) + (0.25 * count3))).toInt()
                    probPrio3 = probPrio2 / 2
                }
                else if (count1 == 0 && count2 > 0 && count3 == 0) {
                    probPrio1 = 0
                    probPrio2 = 100
                    probPrio3 = 0
                }
                else if (count1 == 0 && count2 == 0 && count3 > 0) {
                    probPrio1 = 0
                    probPrio2 = 0
                    probPrio3 = 100
                }

                var delayList: MutableList<Int> = mutableListOf()

                for(mappedTrain in segmentTrainMapping[y]) {
                    var counter = 0
                    when(mappedTrain.priority) {
                        1 -> {
                            counter = probPrio1
                        }
                        2 -> {
                            counter = probPrio2
                        }
                        3 -> {
                            counter = probPrio3
                        }
                        else -> {
                            println("error: unknown priority")
                        }
                    }
                    while (counter > 0) {
                        delayList.add(mappedTrain.id)
                        counter--
                    }
                }

                while (x > 0) {
                    var differentTrain: Boolean = false
                    while (!differentTrain) {
                        val newValue = Random().nextInt(delayList.size)
                        if(delayList[newValue] !in delayedTrains) {
                            delayedTrains.add(delayList[newValue])
                            differentTrain = true
                        }
                    }
                    x--
                }

                for(mappedTrain in segmentTrainMapping[y]) {
                    if(mappedTrain.id in delayedTrains) {
                        mappedTrain.setDelayed(true)
                        mappedTrain.shiftSchedule(currentStep)
                    }
                }
            }
            segment.persistCapacity()

            y++
        }

        for (train in trains) {
            train.persistStep(currentStep)
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