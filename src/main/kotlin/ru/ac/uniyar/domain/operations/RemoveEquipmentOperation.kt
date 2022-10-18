package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.EquipmentRepository
import java.util.*

fun interface RemoveEquipmentOperation {
    fun remove(id: UUID)
}
class RemoveEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository
):RemoveEquipmentOperation{
    override fun remove(id: UUID) {
        equipmentRepository.remove(id)
    }
}