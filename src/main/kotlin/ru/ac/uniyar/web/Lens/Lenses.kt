package ru.ac.uniyar.web.Lens

import org.http4k.core.Body
import org.http4k.lens.*
import java.time.format.DateTimeFormatter

class Lenses {
    companion object {
        val idFormLens = Query.string().optional("id")
        val nameFormLens = FormField.string().nonEmptyString().required("name")
        val productIdFormLens = FormField.string().nonEmptyString().required("productId")
        val descriptionIdFormLens = FormField.string().nonEmptyString().required("description")
        val submissionDateIdFormLens = FormField.string().nonEmptyString().required("submissionDate")
        val newEquipmentlens = Body.webForm(
            Validator.Feedback,
            nameFormLens,
            productIdFormLens,
            descriptionIdFormLens,
            submissionDateIdFormLens
        ).toLens()
    }
}