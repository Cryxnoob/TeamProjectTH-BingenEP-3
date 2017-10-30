package project2

class Network(c:Int,trains:List<Train>, segmentcount:Int,schedulelength:Int) {
    private var capacity:Int=c
    private var trains:List<Train> =trains
    private var segments:MutableList<Segment> = mutableListOf()
    private var segmentcount = segmentcount
    private var schedulelength:Int = schedulelength
    private var currentstep:Int = 0

    public fun createSegments(){

        var x:Int = 0
        while(x<segmentcount){

            segments.add(Segment(x+1))
            x++
        }


    }

    fun simulateoneStep(){
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
            if (segment.getcurrentcount()>=capacity){


                for (train in trains){
                    if (train.getcurrentSegment(currentstep)==segment.getid() ){
                        train.setDelayed(true)
                    }

                }
            }

        }
        currentstep++


    }
}