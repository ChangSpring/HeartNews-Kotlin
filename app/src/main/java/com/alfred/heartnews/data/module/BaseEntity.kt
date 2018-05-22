package com.alfred.heartnews.data.module

import java.io.Serializable

/**
 * Created by alfred on 2018/5/18.
 */
data class BaseEntity<T> (var code : Int, var message : String, var  t : T) : Serializable