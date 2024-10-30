package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.AbilityInfoRepository
import com.aredruss.warmaster.domain.DetachmentInfoRepository
import com.aredruss.warmaster.domain.database.model.Amendment
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.Faq
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.domain.database.model.WargearAbility
import kotlinx.coroutines.launch

class AbilityInfoViewModel(
    private val abilityId: String,
    private val abilityName: String,
    private val type: ScreenType,
    private val abilityInfoRepository: AbilityInfoRepository,
    private val detachmentInfoRepository: DetachmentInfoRepository
) : ViewModel() {

    companion object {
        enum class ScreenType(val id: Int) {
            FACTION(id = 0),
            GEAR(id = 1),
            DATASHEET(id = 2),
            PUBLICATION(id = 3),
            FAQ(id = 4),
            AMEND(id = 5)
        }
    }

    var nameState: String? by mutableStateOf(null); private set
    var screenTypeState: ScreenType? by mutableStateOf(null); private set
    var factionAbility: List<RuleContainerComponent>? by mutableStateOf(null); private set
    var gearAbility: WargearAbility? by mutableStateOf(null); private set
    var datasheetAbility: DatasheetAbility? by mutableStateOf(null); private set
    var faqs: List<Faq>? by mutableStateOf(null); private set
    var amendments: List<Amendment>? by mutableStateOf(null); private set

    init {
        screenTypeState = type
        nameState = abilityName
        viewModelScope.launch {
            when (type) {
                ScreenType.FACTION -> factionAbility = getFactionAbilities()
                ScreenType.DATASHEET -> datasheetAbility = getDatasheetAbilities()
                ScreenType.GEAR -> gearAbility = getGearAbilities()
                ScreenType.PUBLICATION -> factionAbility = getFactionPublicationAbilities()
                ScreenType.FAQ -> faqs = getFactionFaqs()
                ScreenType.AMEND -> amendments = getAmendmentsForFaction()
            }
        }

    }

    private fun getFactionAbilities() = abilityInfoRepository.getFactionAbility(abilityId)
    private fun getGearAbilities() = abilityInfoRepository.getWargearAbility(abilityId)
    private fun getDatasheetAbilities() = abilityInfoRepository.getDatasheetAbility(abilityId)
    private suspend fun getFactionPublicationAbilities() =
        detachmentInfoRepository.getArmyRulesSteps(abilityId)

    private suspend fun getFactionFaqs() = detachmentInfoRepository.getFaqForPublication(abilityId)

    private suspend fun getAmendmentsForFaction() =
        detachmentInfoRepository.getAmendmentsForPublication(abilityId)
}