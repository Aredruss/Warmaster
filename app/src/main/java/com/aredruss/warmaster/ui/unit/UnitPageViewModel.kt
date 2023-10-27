package com.aredruss.warmaster.ui.unit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.database.model.Datasheet
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.UnitComposition
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature
import com.aredruss.warmaster.domain.UnitInfoRepository
import com.aredruss.warmaster.domain.database.model.DatasheetDamage
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll

class UnitPageViewModel(
    private val unitInfoRepository: UnitInfoRepository
) : ViewModel() {
    var datasheet: Datasheet? by mutableStateOf(null); private set
    var miniatureList: List<Miniature> by mutableStateOf(emptyList()); private set
    var ruleset: List<DatasheetRule>? by mutableStateOf(null); private set
    var keywords: List<String>? by mutableStateOf(emptyList()); private set
    var invSave: InvSave? by mutableStateOf(null); private set
    var unitCompositionsByMini: List<UnitCompositionMiniature>? by mutableStateOf(null); private set
    var unitCompositions: List<UnitComposition>? by mutableStateOf(null); private set
    var datasheetAbilities: List<DatasheetAbility>? by mutableStateOf(null); private set

    fun getInfoByDataSheetId(datasheetId: String) = viewModelScope.launch {

        datasheet = unitInfoRepository.getDatasheetById(datasheetId)
        miniatureList = unitInfoRepository.getMiniaturesByDatasheetId(datasheetId)
        invSave = unitInfoRepository.getInvSaveForUnit(datasheetId)
        ruleset = unitInfoRepository.getDatasheetRules(datasheetId)
        unitCompositions = unitInfoRepository.getUnitComposition(datasheetId)
        unitCompositionsByMini =
            unitInfoRepository.getUnitCompositionsMiniatureByDatasheet(datasheetId)
        datasheetAbilities = unitInfoRepository.getAbilitiesByDatasheet(datasheetId)

        miniatureList.firstOrNull()?.let {
            keywords = unitInfoRepository.getMiniatureKeywords(it.id)
        }
//
//            val factionKeywords =
//                it?.datasheetFactionKeywords?.filter { item ->
//                    item.datasheetId == datasheetId
//                }?.sortedBy { item -> item.displayOrder }
//                    ?.map { item -> item.factionKeywordId }
//                    ?: emptyList()
//
//            keywords =
//                it?.factionKeywords?.filter { item -> item.id in factionKeywords } ?: emptyList()
//
//
//
//            val datasheetAbilityBonds =
//                it?.datasheetAbilityBonds?.filter { item ->
//                    item.datasheetId == datasheetId
//                }?.sortedBy { item -> item.displayOrder }
//                    ?.map { item -> item.datasheetAbilityId } ?: emptyList()
//            datasheetAbilities =
//                it?.datasheetAbilities
//                    ?.filter { item ->
//                        item.id in datasheetAbilityBonds
//                    }
//                    ?.sortedBy { item -> item.abilityType }
//                    ?: emptyList()
//
//            mini?.let { _ ->
//                val unitCompositionsByMiniParsed = it.unitCompositionMiniature.filter { item ->
//                    item.miniatureId == mini.id
//                }
//                unitCompositionsByMini = unitCompositionsByMiniParsed
//
//                val ids = unitCompositionsByMiniParsed.map { item -> item.unitCompositionId }
//
//                unitCompositions = it.unitComposition.filter { item ->
//                    item.id in ids
//                }
//            }
//
//        }.launchIn(this)
    }

}