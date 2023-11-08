package com.aredruss.warmaster.ui.unit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.AbilityInfoRepository
import com.aredruss.warmaster.domain.FavoriteUnitRepository
import com.aredruss.warmaster.domain.LoadoutChoiceRepository
import com.aredruss.warmaster.domain.UnitCompositionRepository
import com.aredruss.warmaster.domain.UnitInfoRepository
import com.aredruss.warmaster.domain.WargearRepository
import com.aredruss.warmaster.domain.database.index.AggregatedAbilities
import com.aredruss.warmaster.domain.database.index.IndexedComposition
import com.aredruss.warmaster.domain.database.model.Datasheet
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.WargearItem
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import com.aredruss.warmaster.domain.database.model.WargearRule
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class UnitPageViewModel(
    private val datasheetId: String,
    private val factionId: String,
    private val unitInfoRepository: UnitInfoRepository,
    private val unitCompositionRepository: UnitCompositionRepository,
    private val wargearRepository: WargearRepository,
    private val abilityInfoRepository: AbilityInfoRepository,
    private val loadoutChoiceRepository: LoadoutChoiceRepository,
    private val favoriteUnitRepository: FavoriteUnitRepository
) : ViewModel() {

    var loadingState: Boolean by mutableStateOf(true); private set
    var datasheet: Datasheet? by mutableStateOf(null); private set
    var miniatureList: List<Miniature> by mutableStateOf(emptyList()); private set
    var ruleset: List<DatasheetRule>? by mutableStateOf(null); private set
    var keywords: List<String>? by mutableStateOf(emptyList()); private set
    var invSave: InvSave? by mutableStateOf(null); private set
    var datasheetAbilities: AggregatedAbilities? by mutableStateOf(null); private set
    var unitComposition: List<IndexedComposition>? by mutableStateOf(null); private set
    var wargearOptionRules: List<WargearRule>? by mutableStateOf(null); private set
    var datasheetWargear: Map<String, List<WargearItemProfile>>? by mutableStateOf(null); private set
    var customWargearAbilities: List<WargearItem>? by mutableStateOf(null); private set

    var isFavorite: Boolean? by mutableStateOf(null); private set

    init {
        loadingState = true
        viewModelScope.launch {
            datasheet = unitInfoRepository.getDatasheetById(datasheetId)
            miniatureList = unitInfoRepository.getMiniaturesByDatasheetId(datasheetId)
            invSave = unitInfoRepository.getInvSaveForUnit(datasheetId)
            ruleset = unitInfoRepository.getDatasheetRules(datasheetId)
            datasheetAbilities = unitInfoRepository.getDatasheetAbilities(datasheetId)

            miniatureList.firstOrNull()?.let {
                keywords = unitInfoRepository.getMiniatureKeywords(it.id)
            }

            if (miniatureList.isNotEmpty()) {
                unitComposition = unitCompositionRepository.getIndexedUnitComposition(
                    miniatures = miniatureList,
                    datasheetId = datasheetId
                )
            }

            wargearOptionRules = wargearRepository.getWargearRules(datasheetId)
            datasheetWargear = wargearRepository.getDatasheetWargear(datasheetId)

            customWargearAbilities =
                loadoutChoiceRepository.getCustomWargearByDatasheet(datasheetId)

            favoriteUnitRepository.isFavorite(datasheetId).onEach {
                isFavorite = it
            }.launchIn(this)

            loadingState = false
            setAbilityData()
        }
    }

    fun setFavoriteState() {
        if (isFavorite == true) {
            removeFromFavorites()
        } else {
            addToFavorites()
        }
    }

    private fun addToFavorites() = viewModelScope.launch {
        favoriteUnitRepository.insertFavorite(datasheetId, factionId)
    }

    private fun removeFromFavorites() = viewModelScope.launch {
        favoriteUnitRepository.deleteFavorite(datasheetId)
    }

    private fun setAbilityData() {
        datasheetWargear?.let {
            abilityInfoRepository.setGearAbilities(it.values.flatten())
        }

        datasheetAbilities?.let {
            abilityInfoRepository.setDatasheetAbilities(
                it.normalAbilities.values.flatten().toList()
            )
            it.factionAbilities?.let { factionAbilities ->
                abilityInfoRepository.setFactionAbilities(factionAbilities)
            }
        }
    }


}