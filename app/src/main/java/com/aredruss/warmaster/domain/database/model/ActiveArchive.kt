package com.aredruss.warmaster.domain.database.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    @SerialName("data_version")
    val dataVersion: Long,
)

@Serializable
data class WarhammerData(
    @SerialName("datasheet")
    val datasheets: List<Datasheet>,
    @SerialName("miniature")
    val miniatures: List<Miniature>,
    @SerialName("datasheet_faction_keyword")
    val datasheetFactionKeywords: List<DatasheetFactionKeyword>,
    @SerialName("faction_keyword")
    val factionKeywords: List<FactionKeyword>,
    @SerialName("invulnerable_save")
    val invSaves: List<InvSave>,
    @SerialName("datasheet_rule")
    val datasheetRule: List<DatasheetRule>,
    @SerialName("datasheet_damage")
    val datasheetDamageRule: List<DatasheetRule>,
    @SerialName("datasheet_ability")
    val datasheetAbilities: List<DatasheetAbility>,
    @SerialName("datasheet_datasheet_ability")
    val datasheetAbilityBonds: List<DatasheetAbilityBond>,
    @SerialName("unit_composition")
    val unitComposition: List<UnitComposition>,
    @SerialName("unit_composition_miniature")
    val unitCompositionMiniature: List<UnitCompositionMiniature>,
    @SerialName("wargear_item")
    val wargearItems: List<WargearItem>,
    @SerialName("wargear_item_profile")
    val wargearItemProfiles: List<WargearItemProfile>,
    @SerialName("wargear_rule")
    val wargearRules: List<WargearRule>,
    @SerialName("wargear_option_group")
    val wargearOptionGroups: List<WargearOptionGroup>,
    @SerialName("wargear_option")
    val wargearOptions: List<WargearOption>,
    @SerialName("keyword")
    val keywords: List<Keyword>,
    @SerialName("miniature_keyword")
    val miniatureKeywords: List<MiniatureKeyword>
)
