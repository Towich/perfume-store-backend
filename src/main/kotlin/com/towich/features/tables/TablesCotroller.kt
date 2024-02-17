package com.towich.features.tables

import com.towich.database.tables.Tables
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class TablesController: ITablesController {
    override suspend fun performGetTablesByRestaurantId(call: ApplicationCall) {
        var restaurantId: UUID = UUID.randomUUID()

        try{
            restaurantId = UUID.fromString(call.parameters["restaurant_id"]) ?: UUID.randomUUID()
        } catch (e: IllegalArgumentException){
            call.respond(HttpStatusCode.NotFound)
        }

        val listOfTables: List<TablesRespondRemote> =
            Tables.getAllTablesByRestaurantId(restaurantId)
                .map { it.convertToTablesRespondRemote() }

        call.respond(listOfTables)
    }

    override suspend fun performGetTableById(call: ApplicationCall){
        var tableId = UUID.randomUUID()
        try{
            tableId = UUID.fromString(call.parameters["table_id"]) ?: UUID.randomUUID()
        } catch (e: IllegalArgumentException){
            call.respond(HttpStatusCode.NotFound)
        }

        val foundTable: TablesRespondRemote? = Tables.getTableById(tableId)?.convertToTablesRespondRemote()
        call.respond(foundTable ?: HttpStatusCode.NotFound)
    }

    override suspend fun performUpdateTableIsFreeById(call: ApplicationCall){
        val receivedData = call.receive(TablesRequestChangeIsFreeRemote::class)

        val isDataUpdated = Tables.updateTableIsFreeById(receivedData.id, receivedData.is_free)

        call.respond(if(isDataUpdated) HttpStatusCode.OK else HttpStatusCode.NotModified)
    }
}