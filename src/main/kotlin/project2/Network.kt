package project2

class Network(val capacity:Int,val trains:List<Train>, val segmentcount:Int,val schedulelength:Int) {
    private var segments:MutableList<Segment> = mutableListOf()
    private var currentstep:Int = 0
    init {
        for (x in 0..segmentcount) {
            segments.add(Segment(x+1))
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
                    if (train.getcurrentSegment(currentstep)==segment.id) {
                        train.setDelayed(true)
                    }

                }
            }

        }
        currentstep++


    }
}