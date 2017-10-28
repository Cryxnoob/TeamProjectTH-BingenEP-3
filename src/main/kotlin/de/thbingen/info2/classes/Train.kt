package de.thbingen.info2.model

class Train (val trainnumber:Int, var segment:Segment, var delayed:Boolean) {
    var schedule = Schedule(segment)
}