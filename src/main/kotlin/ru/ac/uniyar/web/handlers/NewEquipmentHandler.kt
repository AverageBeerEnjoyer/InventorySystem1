package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import ru.ac.uniyar.domain.operations.AddEquipmentOperation
import ru.ac.uniyar.domain.operations.FetchEquipmentOperation
import ru.ac.uniyar.domain.operations.RemoveEquipmentOperation
import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.web.Lens.Lenses
import ru.ac.uniyar.web.models.ShowNewEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.time.LocalDate
import java.util.UUID

class NewEquipmentHandler(
    private val addEquipmentOperation: AddEquipmentOperation,
    private val removeEquipmentOperation: RemoveEquipmentOperation,
    private val fetchEquipmentOperation: FetchEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val webform = Lenses.newEquipmentlens(request)

        if (webform.errors.isNotEmpty())
            return Response(Status.OK).with(htmlView(request) of ShowNewEquipmentVM(webform,"Заполните поля корректно"))
        val equipment = Equipment(
            UUID.randomUUID(),
            Lenses.nameFormLens(webform),
            Lenses.productIdFormLens(webform),
            Lenses.descriptionIdFormLens(webform),
            LocalDate.parse(Lenses.submissionDateIdFormLens(webform))
        )
        addEquipmentOperation.add(equipment)
        return Response(Status.FOUND).header("location", "/equipment/${equipment.id}")
    }

}
