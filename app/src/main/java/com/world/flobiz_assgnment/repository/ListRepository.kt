package com.world.flobiz_assgnment.repository

import com.world.flobiz_assgnment.models.Model
import com.world.flobiz_assgnment.network.Destination
import com.world.flobiz_assgnment.sealedClasses.DataState



class ListRepository
constructor(
    var destination: Destination
    )
{

   suspend  fun getListData() : DataState<Model> {
       return try {
           val response = destination.getList()
           val result = response.body()
           if (response.isSuccessful) {
               val dataList = result!!
               DataState.Success(dataList)
           } else {
               DataState.Error(response.message())
           }
       } catch (e: Exception) {
           DataState.Error(e.message ?: "An error occured")
       }
   }

}