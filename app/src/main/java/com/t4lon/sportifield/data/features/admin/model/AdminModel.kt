package com.t4lon.sportifield.data.features.admin.model

import java.util.Date

data class AdminModel (
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val role: String = "", // admin, manager, support
    val permissions: List<String> = emptyList(),
    val lastLogin: Date? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

data class AdminDashboardStats (
    val totalUsers: Int = 0,
    val totalBookings: Int = 0,
    val totalRevenue: Double = 0.0,
    val activeBookings: Int = 0,
    val pendingBookings: Int = 0,
    val equipmentSales: Int = 0,
    val foodOrders: Int = 0,
    val supportTickets: Int = 0,
    val openTickets: Int = 0,
    val updatedAt: Date = Date()
)

data class AdminReport (
    val id: String = "",
    val title: String = "",
    val type: String = "", // daily, weekly, monthly, custom
    val periodStart: Date = Date(),
    val periodEnd: Date = Date(),
    val data: Map<String, Any> = emptyMap(),
    val generatedBy: String = "",
    val generatedAt: Date = Date(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)