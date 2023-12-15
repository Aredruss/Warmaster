package com.aredruss.warmaster.domain.database.index

import com.aredruss.warmaster.domain.database.model.ArmyRule
import com.aredruss.warmaster.domain.database.model.BulletPoint
import com.aredruss.warmaster.domain.database.model.Datasheet
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetAbilityBond
import com.aredruss.warmaster.domain.database.model.DatasheetFactionKeyword
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.domain.database.model.DatasheetSubAbility
import com.aredruss.warmaster.domain.database.model.Detachment
import com.aredruss.warmaster.domain.database.model.DetachmentDetail
import com.aredruss.warmaster.domain.database.model.DetachmentDetailBulletPoint
import com.aredruss.warmaster.domain.database.model.DetachmentFactionKeyword
import com.aredruss.warmaster.domain.database.model.DetachmentRule
import com.aredruss.warmaster.domain.database.model.Enhancement
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Keyword
import com.aredruss.warmaster.domain.database.model.LoadoutChoice
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceSet
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceWargearItem
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.MiniatureKeyword
import com.aredruss.warmaster.domain.database.model.Publication
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.domain.database.model.SecondaryObjective
import com.aredruss.warmaster.domain.database.model.Strategem
import com.aredruss.warmaster.domain.database.model.UnitComposition
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature
import com.aredruss.warmaster.domain.database.model.WargearAbility
import com.aredruss.warmaster.domain.database.model.WargearItem
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import com.aredruss.warmaster.domain.database.model.WargearItemProfileAbility
import com.aredruss.warmaster.domain.database.model.WargearOption
import com.aredruss.warmaster.domain.database.model.WargearOptionGroup
import com.aredruss.warmaster.domain.database.model.WargearRule
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
    @SerialName("datasheet_sub_ability")
    val datasheetSubAbilities: List<DatasheetSubAbility>,
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
    @SerialName("wargear_ability")
    val wargearAbilities: List<WargearAbility>,
    @SerialName("wargear_item_profile_wargear_ability")
    val wargearItemProfileAbilities: List<WargearItemProfileAbility>,
    @SerialName("keyword")
    val keywords: List<Keyword>,
    @SerialName("miniature_keyword")
    val miniatureKeywords: List<MiniatureKeyword>,
    @SerialName("rule_container_component")
    val ruleContainerComponents: List<RuleContainerComponent>,
    @SerialName("loadout_choice_set")
    val loadoutChoiceSets: List<LoadoutChoiceSet>,
    @SerialName("loadout_choice")
    val loadoutChoices: List<LoadoutChoice>,
    @SerialName("loadout_choice_wargear_item")
    val loadoutChoiceWargearItems: List<LoadoutChoiceWargearItem>,
    @SerialName("publication")
    val publications: List<Publication>,
    @SerialName("detachment")
    val detachments: List<Detachment>,
    @SerialName("stratagem")
    val strategems: List<Strategem>,
    @SerialName("detachment_faction_keyword")
    val detachmentFactionKeywords: List<DetachmentFactionKeyword>,
    @SerialName("detachment_rule")
    val detachmentRules: List<DetachmentRule>,
    @SerialName("enhancement")
    val enhancements: List<Enhancement>,
    @SerialName("detachment_detail")
    val detachmentDetails: List<DetachmentDetail>,
    @SerialName("detachment_detail_bullet_point")
    val detailBulletPoints: List<DetachmentDetailBulletPoint>,
    @SerialName("secondary_objective")
    val secondaryObjectives: List<SecondaryObjective>,
    @SerialName("army_rule")
    val armyRules: List<ArmyRule>,
    @SerialName("bullet_point")
    val bulletPoints: List<BulletPoint>,
)
