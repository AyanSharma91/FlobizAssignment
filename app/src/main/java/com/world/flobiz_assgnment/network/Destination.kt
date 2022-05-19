package com.world.flobiz_assgnment.network


import com.world.flobiz_assgnment.models.Model
import retrofit2.Response
import retrofit2.http.*

interface Destination {

  @GET("v2/list")
  suspend fun  getList() : Response<Model>

}