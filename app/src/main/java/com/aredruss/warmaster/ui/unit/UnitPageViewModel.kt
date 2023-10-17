package com.aredruss.warmaster.ui.unit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.data.InfoRepository
import com.aredruss.warmaster.data.model.Datasheet
import com.aredruss.warmaster.data.model.DatasheetAbility
import com.aredruss.warmaster.data.model.DatasheetDamageRule
import com.aredruss.warmaster.data.model.DatasheetFactionKeyword
import com.aredruss.warmaster.data.model.DatasheetRule
import com.aredruss.warmaster.data.model.Faction
import com.aredruss.warmaster.data.model.InvSave
import com.aredruss.warmaster.data.model.Miniature
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale.filter

class UnitPageViewModel(private val infoRepository: InfoRepository) : ViewModel() {
    var datasheet: Datasheet? by mutableStateOf(null); private set
    var miniature: Miniature? by mutableStateOf(null); private set

    var ruleset: List<DatasheetRule>? by mutableStateOf(null); private set

    var damageRuleset: List<DatasheetRule>? by mutableStateOf(null); private set

    var keywords: List<Faction>? by mutableStateOf(null); private set

    var invSave: InvSave? by mutableStateOf(null); private set

    var datasheetAbilities: List<DatasheetAbility>? by mutableStateOf(null); private set

    fun getInfoByDataSheetId(datasheetId: String) = viewModelScope.launch {
        infoRepository.ruleInfoStatListener.onEach {
            val data = it?.data
            datasheet = data?.datasheets?.firstOrNull { item ->
                item.id == datasheetId
            }
            miniature = data?.miniatures?.firstOrNull { item ->
                item.datasheetId == datasheetId
            }

            val factionKeywords =
                data?.datasheetFactionKeywords?.filter { item ->
                    item.datasheetId == datasheetId
                }?.sortedBy { item -> item.displayOrder }
                    ?.map { item -> item.factionKeywordId }
                    ?: emptyList()

            keywords = data?.factions?.filter { item -> item.id in factionKeywords } ?: emptyList()

            ruleset = data?.datasheetRule?.filter { item ->
                item.datasheetId == datasheetId
            }?.sortedBy { item ->
                item.displayOrder
            } ?: emptyList()

            damageRuleset =
                data?.datasheetDamageRule?.filter { item ->
                    item.datasheetId == datasheetId
                }?.sortedBy { item ->
                    item.displayOrder
                } ?: emptyList()

            invSave = data?.invSaves?.firstOrNull { item ->
                item.datasheetId == datasheetId
            }

            val datasheetAbilityBonds =
                data?.datasheetAbilityBonds?.filter { item ->
                    item.datasheetId == datasheetId
                }?.sortedBy { item -> item.displayOrder }
                    ?.map { item -> item.datasheetAbilityId } ?: emptyList()
            datasheetAbilities =
                data?.datasheetAbilities
                    ?.filter { item ->
                        item.id in datasheetAbilityBonds
                    }
                    ?.sortedBy { item -> item.abilityType }
                    ?: emptyList()

        }.launchIn(this)
    }

}