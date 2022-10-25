package ru.ac.uniyar.web.models

import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.storage.Equipment

class ShowChangeEquipmentVM(
    val equipment: Equipment,
    val error: String = ""
) : ViewModel