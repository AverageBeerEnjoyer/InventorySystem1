package ru.ac.uniyar.web.models

import org.http4k.lens.WebForm
import org.http4k.template.ViewModel

class ShowNewEquipmentVM(
    val webForm: WebForm = WebForm(),
    val error: String = ""
) : ViewModel