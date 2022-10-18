package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class HandlerStorage(
    operationStorage: OperationStorage,
    htmlView: ContextAwareViewRender,
) {
    val showEmployeesHandler: HttpHandler = ShowEmployeesHandler(
        operationStorage.listEmployeesOperation,
        htmlView,
    )

    val showEmployeeHandler: HttpHandler = ShowEmployeeHandler(
        operationStorage.fetchEmployeeOperation,
        htmlView,
    )

    val showEquipmentHandler: HttpHandler = ShowEquipmentHandler(
        operationStorage.fetchEquipmentOperation,
        htmlView,
    )

    val showEquipmentListHandler: HttpHandler = ShowEquipmentListHandler(
        operationStorage.listEquipmentOperation,
        htmlView,
    )

    val showStartPageHandler: HttpHandler = ShowStartPageHandler(
        htmlView,
    )

    val showNewEquipmentHandler: HttpHandler = ShowNewEquipmentHandler(
        htmlView
    )

    val newEquipmentHandler: HttpHandler = NewEquipmentHandler(
        operationStorage.addEquipmentOperation,
        operationStorage.RemoveEquipmentOperation,
        operationStorage.fetchEquipmentOperation,
        htmlView
    )

    val showChangeEquipmentHandler: HttpHandler = ShowChangeEquipmentHandler(
        operationStorage.addEquipmentOperation,
        operationStorage.RemoveEquipmentOperation,
        operationStorage.fetchEquipmentOperation,
        htmlView
    )

    val changeEquipmentHandler: HttpHandler = ChangeEquipmentHandler(
        operationStorage.addEquipmentOperation,
        operationStorage.RemoveEquipmentOperation,
        operationStorage.fetchEquipmentOperation,
        htmlView
    )
}
