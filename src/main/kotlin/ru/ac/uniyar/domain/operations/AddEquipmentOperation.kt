package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.domain.storage.EquipmentRepository

fun interface AddEquipmentOperation{
    fun add(equipment:Equipment)
}
class AddEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository
): AddEquipmentOperation {
    override fun add(equipment: Equipment){
        equipmentRepository.add(equipment)
    }
}