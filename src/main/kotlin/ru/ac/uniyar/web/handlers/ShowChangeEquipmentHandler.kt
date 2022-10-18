package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.routing.path
import ru.ac.uniyar.domain.operations.AddEquipmentOperation
import ru.ac.uniyar.domain.operations.FetchEquipmentOperation
import ru.ac.uniyar.domain.operations.RemoveEquipmentOperation
import ru.ac.uniyar.web.models.ShowChangeEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.lang.IllegalArgumentException
import java.util.*

class ShowChangeEquipmentHandler(
    private val addEquipmentOperation: AddEquipmentOperation,
    private val removeEquipmentOperation: RemoveEquipmentOperation,
    private val fetchEquipmentOperation: FetchEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val idFromRequest = request.path("id")
        val id: UUID
        try {
            id = UUID.fromString(idFromRequest)
        } catch (e: IllegalArgumentException) {
            return Response(Status.BAD_REQUEST)
        }
        val equipment = fetchEquipmentOperation.fetch(id) ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK)
            .with(htmlView(request) of ShowChangeEquipmentVM(equipment))
    }
}