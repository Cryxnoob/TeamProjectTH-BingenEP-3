package de.thbingen.info2.model

class Segment (val track:Int, var traincounter:Int, var delayed: Boolean){
 fun increaseCounter (){
     traincounter++
     if (traincounter>3){
         delayed = true
     }
 }
    fun reset (){
        traincounter = 0
        delayed = false
    }
}
