package com.aredruss.warmaster.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RuleInfo(
    @SerialName("metadata")
    val metaData: MetaData,
    @SerialName("data")
    val data: Data,
)

@Serializable
data class MetaData(
    @SerialName("data_version")
    val dataVersion: Int,
)

@Serializable
data class Data(
    @SerialName("datasheet")
    val datasheets: List<Datasheet>,
    @SerialName("miniature")
    val miniatures: List<Miniature>,
    @SerialName("datasheet_faction_keyword")
    val datasheetFactionKeywords: List<DatasheetFactionKeyword>,
    @SerialName("faction_keyword")
    val factions: List<Faction>,
    @SerialName("invulnerable_save")
    val invSaves: List<InvSave>,
    @SerialName("datasheet_rule")
    val datasheetRule: List<DatasheetRule>,
    @SerialName("datasheet_damage")
    val datasheetDamageRule: List<DatasheetRule>,
    @SerialName("datasheet_ability")
    val datasheetAbilities: List<DatasheetAbility>,
    @SerialName("datasheet_datasheet_ability")
    val datasheetAbilityBonds: List<DatasheetAbilityBond>
)
