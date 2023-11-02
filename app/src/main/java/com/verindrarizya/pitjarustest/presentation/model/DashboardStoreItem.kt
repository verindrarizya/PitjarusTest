package com.verindrarizya.pitjarustest.presentation.model

data class DashboardStoreItem(
    val name: String,
    val percentage: String,
    val target: Int,
    val achievement: Int
)

val dashboardStoreItemsPlaceholder = listOf<DashboardStoreItem>(
    DashboardStoreItem(
        name = "OSA",
        percentage = "50%",
        target = 40,
        achievement = 20
    ),
    DashboardStoreItem(
        name = "NPD",
        percentage = "80%",
        target = 100,
        achievement = 80
    ),
    DashboardStoreItem(
        name = "GBU",
        percentage = "65%",
        target = 80,
        achievement = 80
    )
)
