package com.stacydevino.androidanimationexample

import android.app.Application

import com.codemonkeylabs.fpslibrary.FrameDataCallback
import com.codemonkeylabs.fpslibrary.TinyDancer

class AnimationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TinyDancer.create()
                .show(this)


        //you can add a callback to get frame times and the calculated
        //number of dropped frames within that window
        TinyDancer.create()
                .addFrameDataCallback { previousFrameNS, currentFrameNS, droppedFrames ->
                    //collect your stats here
                }
                .show(this)
    }
}
