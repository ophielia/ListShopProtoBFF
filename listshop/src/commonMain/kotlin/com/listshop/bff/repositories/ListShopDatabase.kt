package com.listshop.bff.repositories

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.db.ListshopDb

class ListShopDatabase(
    sqlDriver: SqlDriver,
    val analytics: ListShopAnalytics,
) {
    val db: ListshopDb = ListshopDb(sqlDriver)
}
