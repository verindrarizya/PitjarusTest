package com.verindrarizya.pitjarustest.util

import com.verindrarizya.pitjarustest.data.source.local.entity.StoreEntity
import com.verindrarizya.pitjarustest.data.source.remote.response.StoreResponse
import com.verindrarizya.pitjarustest.presentation.model.Store

object DataMapper {

    fun storeResponseToEntity(storeResponse: StoreResponse): StoreEntity =
        StoreEntity(
            storeId = storeResponse.storeId,
            storeCode = storeResponse.storeCode,
            channelName = storeResponse.channelName,
            areaName = storeResponse.areaName,
            address = storeResponse.address,
            dcName = storeResponse.dcName,
            latitude = storeResponse.latitude,
            regionId = storeResponse.regionId,
            areaId = storeResponse.areaId,
            accountId = storeResponse.accountId,
            dcId = storeResponse.dcId,
            subchannelId = storeResponse.subchannelId,
            accountName = storeResponse.accountName,
            storeName = storeResponse.storeName,
            subchannelName = storeResponse.subchannelName,
            regionName = storeResponse.regionName,
            channelId = storeResponse.channelId,
            longitude = storeResponse.longitude
        )

    fun storeEntityToUiModel(storeEntity: StoreEntity): Store = Store(
        storeId = storeEntity.storeId,
        storeCode = storeEntity.storeCode,
        channelName = storeEntity.channelName,
        areaName = storeEntity.areaName,
        address = storeEntity.address,
        dcName = storeEntity.dcName,
        latitude = storeEntity.latitude,
        regionId = storeEntity.regionId,
        areaId = storeEntity.areaId,
        accountId = storeEntity.accountId,
        dcId = storeEntity.dcId,
        subchannelId = storeEntity.subchannelId,
        accountName = storeEntity.accountName,
        storeName = storeEntity.storeName,
        subchannelName = storeEntity.subchannelName,
        regionName = storeEntity.regionName,
        channelId = storeEntity.channelId,
        longitude = storeEntity.longitude,
        isVisited = storeEntity.isVisited
    )

    fun storeUiModelToEntity(store: Store): StoreEntity = StoreEntity(
        storeId = store.storeId,
        storeCode = store.storeCode,
        channelName = store.channelName,
        areaName = store.areaName,
        address = store.address,
        dcName = store.dcName,
        latitude = store.latitude,
        regionId = store.regionId,
        areaId = store.areaId,
        accountId = store.accountId,
        dcId = store.dcId,
        subchannelId = store.subchannelId,
        accountName = store.accountName,
        storeName = store.storeName,
        subchannelName = store.subchannelName,
        regionName = store.regionName,
        channelId = store.channelId,
        longitude = store.longitude,
        isVisited = store.isVisited
    )
}