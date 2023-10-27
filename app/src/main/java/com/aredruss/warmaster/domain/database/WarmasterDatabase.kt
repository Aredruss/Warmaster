package com.aredruss.warmaster.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
import com.aredruss.warmaster.domain.database.model.AllModelWargearChoice
import com.aredruss.warmaster.domain.database.model.AllModelWargearChoiceSet
import com.aredruss.warmaster.domain.database.model.AllModelWargearChoiceWargearItem
import com.aredruss.warmaster.domain.database.model.AllegianceAbility
import com.aredruss.warmaster.domain.database.model.AllegianceAbilityGroup
import com.aredruss.warmaster.domain.database.model.AlliedFaction
import com.aredruss.warmaster.domain.database.model.AlliedFactionAllegianceAbility
import com.aredruss.warmaster.domain.database.model.AlliedFactionAllowedWarlordMiniature
import com.aredruss.warmaster.domain.database.model.AlliedFactionDatasheet
import com.aredruss.warmaster.domain.database.model.AlliedFactionKeyword
import com.aredruss.warmaster.domain.database.model.AlliedFactionParentFactionKeyword
import com.aredruss.warmaster.domain.database.model.ArmyRule
import com.aredruss.warmaster.domain.database.model.ArmyRuleFactionKeyword
import com.aredruss.warmaster.domain.database.model.BaseMiniLoadout
import com.aredruss.warmaster.domain.database.model.BaseMiniLoadoutWargearOption
import com.aredruss.warmaster.domain.database.model.BulletPoint
import com.aredruss.warmaster.domain.database.model.ConditionalKeyword
import com.aredruss.warmaster.domain.database.model.Datasheet
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetAbilityBond
import com.aredruss.warmaster.domain.database.model.DatasheetDamage
import com.aredruss.warmaster.domain.database.model.DatasheetFactionKeyword
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.domain.database.model.DatasheetSubAbility
import com.aredruss.warmaster.domain.database.model.Detachment
import com.aredruss.warmaster.domain.database.model.DetachmentDetail
import com.aredruss.warmaster.domain.database.model.DetachmentDetailBulletPoint
import com.aredruss.warmaster.domain.database.model.DetachmentExcludedDatasheet
import com.aredruss.warmaster.domain.database.model.DetachmentFactionKeyword
import com.aredruss.warmaster.domain.database.model.DetachmentGrantedWarlordMiniature
import com.aredruss.warmaster.domain.database.model.DetachmentRule
import com.aredruss.warmaster.domain.database.model.Enhancement
import com.aredruss.warmaster.domain.database.model.EnhancementDatasheetAbility
import com.aredruss.warmaster.domain.database.model.EnhancementExcludedKeyword
import com.aredruss.warmaster.domain.database.model.EnhancementKeywordPointsCost
import com.aredruss.warmaster.domain.database.model.EnhancementRequiredKeyword
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.database.model.FactionKeywordAlliedFaction
import com.aredruss.warmaster.domain.database.model.FactionKeywordExcludedDatasheet
import com.aredruss.warmaster.domain.database.model.Faq
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Keyword
import com.aredruss.warmaster.domain.database.model.KeywordRestrictionGroup
import com.aredruss.warmaster.domain.database.model.KeywordRestrictionGroupKeyword
import com.aredruss.warmaster.domain.database.model.LimitedWargearChoice
import com.aredruss.warmaster.domain.database.model.LimitedWargearChoiceSet
import com.aredruss.warmaster.domain.database.model.LimitedWargearChoiceWargearItem
import com.aredruss.warmaster.domain.database.model.LoadoutChoice
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceSet
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceWargearItem
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.MiniatureKeyword
import com.aredruss.warmaster.domain.database.model.Publication
import com.aredruss.warmaster.domain.database.model.RuleContainer
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.domain.database.model.RuleSection
import com.aredruss.warmaster.domain.database.model.SecondaryObjective
import com.aredruss.warmaster.domain.database.model.Strategem
import com.aredruss.warmaster.domain.database.model.UnitComposition
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature
import com.aredruss.warmaster.domain.database.model.WargearAbility
import com.aredruss.warmaster.domain.database.model.WargearItem
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import com.aredruss.warmaster.domain.database.model.WargearItemProfileItemAbility
import com.aredruss.warmaster.domain.database.model.WargearLimit
import com.aredruss.warmaster.domain.database.model.WargearOption
import com.aredruss.warmaster.domain.database.model.WargearOptionGroup
import com.aredruss.warmaster.domain.database.model.WargearRule
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DatasheetRuleDao
import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.InvSaveDao
import com.aredruss.warmaster.domain.database.dao.KeywordsDao
import com.aredruss.warmaster.domain.database.dao.MiniatureDao
import com.aredruss.warmaster.domain.database.dao.MiniatureKeywordDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionMiniatureDao

@Database(
    entities = [
        AllegianceAbility::class,
        AllegianceAbilityGroup::class,
        AlliedFaction::class,
        AlliedFactionAllegianceAbility::class,
        AlliedFactionAllowedWarlordMiniature::class,
        AlliedFactionDatasheet::class,
        AlliedFactionKeyword::class,
        AlliedFactionParentFactionKeyword::class,
        AllModelWargearChoice::class,
        AllModelWargearChoiceSet::class,
        AllModelWargearChoiceWargearItem::class,
        ArmyRule::class,
        ArmyRuleFactionKeyword::class,
        BaseMiniLoadout::class,
        BaseMiniLoadoutWargearOption::class,
        BulletPoint::class,
        ConditionalKeyword::class,
        Datasheet::class,
        DatasheetAbility::class,
        DatasheetAbilityBond::class,
        DatasheetDamage::class,
        DatasheetFactionKeyword::class,
        DatasheetRule::class,
        DatasheetSubAbility::class,
        Detachment::class,
        DetachmentDetail::class,
        DetachmentDetailBulletPoint::class,
        DetachmentExcludedDatasheet::class,
        DetachmentFactionKeyword::class,
        DetachmentGrantedWarlordMiniature::class,
        DetachmentRule::class,
        Enhancement::class,
        EnhancementDatasheetAbility::class,
        EnhancementExcludedKeyword::class,
        EnhancementKeywordPointsCost::class,
        EnhancementRequiredKeyword::class,
        FactionKeyword::class,
        FactionKeywordAlliedFaction::class,
        FactionKeywordExcludedDatasheet::class,
        Faq::class,
        InvSave::class,
        Keyword::class,
        KeywordRestrictionGroup::class,
        KeywordRestrictionGroupKeyword::class,
        LimitedWargearChoice::class,
        LimitedWargearChoiceSet::class,
        LimitedWargearChoiceWargearItem::class,
        LoadoutChoice::class,
        LoadoutChoiceSet::class,
        LoadoutChoiceWargearItem::class,
        Miniature::class,
        MiniatureKeyword::class,
        Publication::class,
        RuleContainer::class,
        RuleContainerComponent::class,
        RuleSection::class,
        SecondaryObjective::class,
        Strategem::class,
        UnitComposition::class,
        UnitCompositionMiniature::class,
        WargearAbility::class,
        WargearItem::class,
        WargearItemProfile::class,
        WargearItemProfileItemAbility::class,
        WargearLimit::class,
        WargearOption::class,
        WargearOptionGroup::class,
        WargearRule::class,
    ],
    version = 1
)
abstract class WarmasterDatabase : RoomDatabase() {
    abstract fun datasheetDao(): DatasheetDao
    abstract fun datasheetFactionKeywordDao(): DatasheetFactionKeywordDao
    abstract fun factionKeywordDao(): FactionKeywordDao
    abstract fun miniatureDao(): MiniatureDao
    abstract fun datasheetAbilityDao(): DatasheetAbilityDao
    abstract fun invSaveDao(): InvSaveDao
    abstract fun unitCompositionDao(): UnitCompositionDao
    abstract fun unitCompositionMiniatureDao(): UnitCompositionMiniatureDao
    abstract fun datasheetRuleDao(): DatasheetRuleDao
    abstract fun datasheetAbilityBondDao(): DatasheetAbilityBondDao
    abstract fun keywordsDao(): KeywordsDao
    abstract fun miniatureKeywordsDao(): MiniatureKeywordDao
}
