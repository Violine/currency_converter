package com.alexander.korovin.currency.converter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "currency_items")
data class Currency (@PrimaryKey val name: String, val value: Double)
