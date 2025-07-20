package com.aredruss.warmaster.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aredruss.warmaster.domain.database.dao.AmendmentDao
import com.aredruss.warmaster.domain.database.dao.ArmyRuleDao
import com.aredruss.warmaster.domain.database.dao.BulletPointDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DatasheetRuleDao
import com.aredruss.warmaster.domain.database.dao.DatasheetSubAbilityDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDetailBulletPointDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDetailDao
import com.aredruss.warmaster.domain.database.dao.DetachmentFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DetachmentRuleDao
import com.aredruss.warmaster.domain.database.dao.EnhancementDao
import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.FaqDao
import com.aredruss.warmaster.domain.database.dao.FavoriteUnitDao
import com.aredruss.warmaster.domain.database.dao.InvSaveDao
import com.aredruss.warmaster.domain.database.dao.KeywordsDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceSetDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceWargearItemDao
import com.aredruss.warmaster.domain.database.dao.MiniatureDao
import com.aredruss.warmaster.domain.database.dao.MiniatureKeywordDao
import com.aredruss.warmaster.domain.database.dao.PublicationDao
import com.aredruss.warmaster.domain.database.dao.RuleContainerComponentDao
import com.aredruss.warmaster.domain.database.dao.RuleContainerDao
import com.aredruss.warmaster.domain.database.dao.RuleSectionDao
import com.aredruss.warmaster.domain.database.dao.SecondaryObjectiveDao
import com.aredruss.warmaster.domain.database.dao.StrategemDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionMiniatureDao
import com.aredruss.warmaster.domain.database.dao.WargearAbilityDao
import com.aredruss.warmaster.domain.database.dao.WargearItemDao
import com.aredruss.warmaster.domain.database.dao.WargearItemProfileAbilityDao
import com.aredruss.warmaster.domain.database.dao.WargearItemProfileDao
import com.aredruss.warmaster.domain.database.dao.WargearOptionDao
import com.aredruss.warmaster.domain.database.dao.WargearOptionGroupDao
import com.aredruss.warmaster.domain.database.dao.WargearRuleDao
import com.aredruss.warmaster.domain.database.index.FavoriteUnit
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
import com.aredruss.warmaster.domain.database.model.Amendment
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
import com.aredruss.warmaster.domain.database.model.WargearItemProfileAbility
import com.aredruss.warmaster.domain.database.model.WargearLimit
import com.aredruss.warmaster.domain.database.model.WargearOption
import com.aredruss.warmaster.domain.database.model.WargearOptionGroup
import com.aredruss.warmaster.domain.database.model.WargearRule

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
        WargearItemProfileAbility::class,
        WargearLimit::class,
        WargearOption::class,
        WargearOptionGroup::class,
        WargearRule::class,
        FavoriteUnit::class,
        Amendment::class
    ],
    version = 6
)
abstract class WarmasterDatabase : RoomDatabase() {
    abstract fun favoriteUnitDao(): FavoriteUnitDao
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
    abstract fun datasheetSubAbilityDao(): DatasheetSubAbilityDao
    abstract fun keywordsDao(): KeywordsDao
    abstract fun miniatureKeywordsDao(): MiniatureKeywordDao
    abstract fun wargearOptionDao(): WargearOptionDao
    abstract fun wargearOptionGroupDao(): WargearOptionGroupDao
    abstract fun wargearItemProfileDao(): WargearItemProfileDao
    abstract fun wargearItemDao(): WargearItemDao
    abstract fun wargearAbilityDao(): WargearAbilityDao
    abstract fun wargearItemProfileAbilityDao(): WargearItemProfileAbilityDao
    abstract fun wargearRuleDao(): WargearRuleDao
    abstract fun ruleContainerComponentComponent(): RuleContainerComponentDao
    abstract fun faqDao(): FaqDao
    abstract fun amendmentDao(): AmendmentDao
    abstract fun loadoutChoiceDao(): LoadoutChoiceDao
    abstract fun loadoutChoiceSetDao(): LoadoutChoiceSetDao
    abstract fun loadoutChoiceWargearItemDao(): LoadoutChoiceWargearItemDao
    abstract fun publicationDao(): PublicationDao

    abstract fun detachmentDao(): DetachmentDao
    abstract fun detachmentFactionKeywordDao(): DetachmentFactionKeywordDao
    abstract fun detachmentRuleDao(): DetachmentRuleDao
    abstract fun strategemDao(): StrategemDao
    abstract fun enhancementDao(): EnhancementDao
    abstract fun detachmentDetailDao(): DetachmentDetailDao
    abstract fun secondaryObjectiveDao(): SecondaryObjectiveDao
    abstract fun detachmentDetailBulletPointDao(): DetachmentDetailBulletPointDao
    abstract fun armyRuleDao(): ArmyRuleDao
    abstract fun bulletDao(): BulletPointDao
    abstract fun ruleSectionDao(): RuleSectionDao
    abstract fun ruleContainerDao(): RuleContainerDao
}

val MIGRATION_2_3 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `Amendment` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `text` TEXT NOT NULL, `displayOrder` INTEGER NOT NULL, `publicationId` TEXT NOT NULL, `note` TEXT, PRIMARY KEY(`id`))");
    }
}

val MIGRATION_4_6 = object : Migration(4, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'RuleContainerComponent' ADD COLUMN 'trigger' TEXT")
        database.execSQL("ALTER TABLE 'RuleContainerComponent' ADD COLUMN 'effect' TEXT")

        database.execSQL("CREATE TABLE IF NOT EXISTS RuleContainerComponent_new (id TEXT PRIMARY KEY NOT NULL, textContent TEXT, displayOrder INTEGER NOT NULL, type TEXT NOT NULL, altText TEXT, imageUrl TEXT, title TEXT, subtitle TEXT, backgroundColor TEXT, ruleContainerId TEXT, armyRuleId TEXT, detachmentRuleId TEXT, trigger TEXT, effect TEXT)")
        database.execSQL("INSERT INTO RuleContainerComponent_new SELECT * FROM RuleContainerComponent")
        database.execSQL("DROP TABLE RuleContainerComponent")
        database.execSQL("ALTER TABLE RuleContainerComponent_new RENAME TO RuleContainerComponent")

        database.execSQL("CREATE TABLE IF NOT EXISTS BulletPoint_new (id TEXT PRIMARY KEY NOT NULL, text TEXT, ruleContainerComponentId TEXT, indent INTEGER, displayOrder INTEGER)")
        database.execSQL("INSERT INTO BulletPoint_new (id, text, ruleContainerComponentId, indent, displayOrder) SELECT id, text, ruleContainerComponentId, indent, displayOrder FROM BulletPoint")
        database.execSQL("DROP TABLE BulletPoint")
        database.execSQL("ALTER TABLE BulletPoint_new RENAME TO BulletPoint")

        database.execSQL("CREATE TABLE IF NOT EXISTS SecondaryObjective_new (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, publicationId TEXT NOT NULL, displayOrder INTEGER NOT NULL, lore TEXT, rules TEXT)")
        database.execSQL("INSERT INTO SecondaryObjective_new (id, name, publicationId, displayOrder, lore, rules) SELECT id, name, publicationId, displayOrder, lore, rules FROM SecondaryObjective")
        database.execSQL("DROP TABLE SecondaryObjective")
        database.execSQL("ALTER TABLE SecondaryObjective_new RENAME TO SecondaryObjective")
    }
}
