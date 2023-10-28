package com.aredruss.warmaster.ui.unit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.UnitInfoRepository
import com.aredruss.warmaster.domain.database.index.IndexedComposition
import com.aredruss.warmaster.domain.database.model.Datasheet
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Miniature
import kotlinx.coroutines.launch

class UnitPageViewModel(
    private val datasheetId: String,
    private val unitInfoRepository: UnitInfoRepository
) : ViewModel() {

    var datasheet: Datasheet? by mutableStateOf(null); private set
    var miniatureList: List<Miniature> by mutableStateOf(emptyList()); private set
    var ruleset: List<DatasheetRule>? by mutableStateOf(null); private set
    var keywords: List<String>? by mutableStateOf(emptyList()); private set
    var invSave: InvSave? by mutableStateOf(null); private set
    var datasheetAbilities: List<DatasheetAbility>? by mutableStateOf(null); private set
    var unitComposition: List<IndexedComposition>? by mutableStateOf(null); private set

    init {
        viewModelScope.launch {
            datasheet = unitInfoRepository.getDatasheetById(datasheetId)
            miniatureList = unitInfoRepository.getMiniaturesByDatasheetId(datasheetId)
            invSave = unitInfoRepository.getInvSaveForUnit(datasheetId)
            ruleset = unitInfoRepository.getDatasheetRules(datasheetId)
            datasheetAbilities = unitInfoRepository.getAbilitiesByDatasheet(datasheetId)

            miniatureList.firstOrNull()?.let {
                keywords = unitInfoRepository.getMiniatureKeywords(it.id)
            }
            if (miniatureList.isNotEmpty()) {
                unitComposition = unitInfoRepository.getIndexedUnitComposition(miniatureList, datasheetId)
            }

            if (miniatureList.isNotEmpty()) unitInfoRepository.getIndexedUnitComposition(
                miniatureList,
                datasheetId = datasheetId
            )
        }
    }

}