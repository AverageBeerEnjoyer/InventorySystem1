package ru.ac.uniyar.web

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import ru.ac.uniyar.web.handlers.HandlerStorage

fun formRouter(
    handlerStorage: HandlerStorage,
) = routes(
    "/" bind Method.GET to handlerStorage.showStartPageHandler,
    "/employees" bind Method.GET to handlerStorage.showEmployeesHandler,
    "/employees/{id}" bind Method.GET to handlerStorage.showEmployeeHandler,
    "/equipment" bind Method.GET to handlerStorage.showEquipmentListHandler,
    "/equipment/new" bind Method.GET to handlerStorage.showNewEquipmentHandler,
    "/equipment/new" bind Method.POST to handlerStorage.newEquipmentHandler,
    "/equipment/{id}" bind Method.GET to handlerStorage.showEquipmentHandler,
    "/equipment/{id}/change" bind Method.GET to handlerStorage.showChangeEquipmentHandler,
    "equipment/{id}/change" bind Method.POST to handlerStorage.changeEquipmentHandler,
    "/ping" bind Method.GET to { Response(Status.OK) },
)
