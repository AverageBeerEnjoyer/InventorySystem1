package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.routing.path
import ru.ac.uniyar.domain.operations.AddEquipmentOperation
import ru.ac.uniyar.domain.operations.FetchEquipmentOperation
import ru.ac.uniyar.domain.operations.RemoveEquipmentOperation
import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.web.Lens.Lenses
import ru.ac.uniyar.web.models.ShowChangeEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*


class ChangeEquipmentHandler(
    private val addEquipmentOperation: AddEquipmentOperation,
    private val removeEquipmentOperation: RemoveEquipmentOperation,
    private val fetchEquipmentOperation: FetchEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val id = try {
            UUID.fromString(request.path("id"))
        } catch (e: IllegalArgumentException) {
            return Response(Status.BAD_REQUEST)
        }
        val equipment= fetchEquipmentOperation.fetch(id) ?: return Response(Status.BAD_REQUEST)
        val webform = Lenses.newEquipmentlens(request)
        if (webform.errors.isNotEmpty()) return Response(Status.OK).with(htmlView(request) of ShowChangeEquipmentVM(equipment,"Заполните поля корректно"))
        removeEquipmentOperation.remove(id)
        val newEquipment=Equipment(
            id,
            Lenses.nameFormLens(webform),
            Lenses.productIdFormLens(webform),
            Lenses.descriptionIdFormLens(webform),
            LocalDate.parse(Lenses.submissionDateIdFormLens(webform))
        )
        addEquipmentOperation.add(newEquipment)
        return Response(Status.FOUND).header("location","/equipment/${id}")
    }
}