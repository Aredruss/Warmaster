package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.AbilityInfoRepository
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.domain.database.model.WargearAbility
import kotlinx.coroutines.launch

class AbilityInfoViewModel(
    private val abilityId: String,
    private val abilityName: String,
    private val type: ScreenType,
    private val abilityInfoRepository: AbilityInfoRepository
) : ViewModel() {

    companion object {
        enum class ScreenType(val id: Int) {
            FACTION(id = 0),
            GEAR(id = 1),
            DATASHEET(id = 2)
        }
    }

    var nameState: String? by mutableStateOf(""); private set
    var screenTypeState: ScreenType? by mutableStateOf(null); private set
    var factionAbility: List<RuleContainerComponent>? by mutableStateOf(null); private set
    var gearAbility: WargearAbility? by mutableStateOf(null); private set
    var datasheetAbility: DatasheetAbility? by mutableStateOf(null); private set

    init {
        screenTypeState = type
        nameState = abilityName
        viewModelScope.launch {
            when (type) {
                ScreenType.FACTION -> factionAbility = getFactionAbility()
                ScreenType.DATASHEET -> datasheetAbility = getDatasheetAbility()
                ScreenType.GEAR -> gearAbility = getGearAbility()
            }
        }

    }

    private suspend fun getFactionAbility() = abilityInfoRepository.getFactionAbility(abilityId)
    private suspend fun getGearAbility() = abilityInfoRepository.getWargearAbility(abilityId)
    private suspend fun getDatasheetAbility() = abilityInfoRepository.getDatasheetAbility(abilityId)

}

sealed class AbilityScreenTypeState {
    data object FactionAbilityState : AbilityScreenTypeState()
    data object GearAbilityState : AbilityScreenTypeState()
    data object DatasheetAbilityState : AbilityScreenTypeState()

}