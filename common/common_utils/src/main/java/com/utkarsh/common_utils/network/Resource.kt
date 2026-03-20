package com.utkarsh.common_utils.network

sealed class Resource<T> {
    class Loading<T>():Resource<T>()
    class Success<T>(val data:T?):Resource<T>()
    class Error<T>(val message:String,val data:T?=null):Resource<T>()
}