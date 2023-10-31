package com.verindrarizya.pitjarustest.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.verindrarizya.pitjarustest.data.source.local.entity.StoreEntity

data class StoreResponse(

    @field:SerializedName("store_id")
    val storeId: String,

    @field:SerializedName("store_code")
    val storeCode: String,

    @field:SerializedName("channel_name")
    val channelName: String,

    @field:SerializedName("area_name")
    val areaName: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("dc_name")
    val dcName: String,

    @field:SerializedName("latitude")
    val latitude: String,

    @field:SerializedName("region_id")
    val regionId: String,

    @field:SerializedName("area_id")
    val areaId: String,

    @field:SerializedName("account_id")
    val accountId: String,

    @field:SerializedName("dc_id")
    val dcId: String,

    @field:SerializedName("subchannel_id")
    val subchannelId: String,

    @field:SerializedName("account_name")
    val accountName: String,

    @field:SerializedName("store_name")
    val storeName: String,

    @field:SerializedName("subchannel_name")
    val subchannelName: String,

    @field:SerializedName("region_name")
    val regionName: String,

    @field:SerializedName("channel_id")
    val channelId: String,

    @field:SerializedName("longitude")
    val longitude: String
) {
    fun toStoreEntity(): StoreEntity = StoreEntity(
        storeId = storeId,
        storeCode = storeCode,
        channelName = channelName,
        areaName = areaName,
        address = address,
        dcName = dcName,
        latitude = latitude,
        regionId = regionId,
        areaId = areaId,
        accountId = accountId,
        dcId = dcId,
        subchannelId = subchannelId,
        accountName = accountName,
        storeName = storeName,
        subchannelName = subchannelName,
        regionName = regionName,
        channelId = channelId,
        longitude = longitude
    )
}