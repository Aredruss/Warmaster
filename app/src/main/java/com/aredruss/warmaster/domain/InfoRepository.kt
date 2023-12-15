package com.aredruss.warmaster.domain

import android.content.Context
import com.aredruss.warmaster.domain.database.index.MetaData
import com.aredruss.warmaster.domain.database.index.WarhammerData
import com.aredruss.warmaster.domain.populators.CompositionPopulator
import com.aredruss.warmaster.domain.populators.DatasheetPopulator
import com.aredruss.warmaster.domain.populators.FactionPopulator
import com.aredruss.warmaster.domain.populators.LoadoutPopulator
import com.aredruss.warmaster.domain.populators.WargearPopulator
import com.aredruss.warmaster.domain.util.ClearUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import timber.log.Timber

class InfoRepository(
    context: Context,
    private val prefs: WarmasterPrefs,
    private val compositionPopulator: CompositionPopulator,
    private val datasheetPopulator: DatasheetPopulator,
    private val factionPopulator: FactionPopulator,
    private val wargearPopulator: WargearPopulator,
    private val loadoutPopulator: LoadoutPopulator,
    private val clearUtil: ClearUtil
) {

    private val json = Json { ignoreUnknownKeys = true }
    private val metaDataFile = context.resources.assets.open("metadata.json")
    private val dataFile = context.resources.assets.open("data.json")

    suspend fun parseDataFile(): Result<Boolean> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            if (isDataUploadRequired()) {
                val jsonData = json.decodeFromString(
                    WarhammerData.serializer(),
                    dataFile.bufferedReader().use {
                        it.readText()
                    }
                )
                dataFile.close()
                populateDatabase(jsonData)
            } else {
                true
            }
        }.onFailure {
            Timber.e(it)
        }
    }

    private suspend fun isDataUploadRequired(): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val metadata = json.decodeFromString(
                MetaData.serializer(),
                metaDataFile.bufferedReader().use {
                    it.readText()
                }
            )
            (metadata.dataVersion > prefs.currentDataVersion).also {
                if (prefs.currentDataVersion != 0L) {

                }
                prefs.currentDataVersion = metadata.dataVersion
            }
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun populateDatabase(data: WarhammerData): Boolean =
        withContext(Dispatchers.IO) {
            clearUtil.purgeDatabase()
            data.apply {
                factionPopulator.insertArmyRules(armyRules)
                factionPopulator.insertDatasheetFactionKeyword(datasheetFactionKeywords)
                factionPopulator.insertFactionKeyword(factionKeywords)
                factionPopulator.insertPublications(publications)
                factionPopulator.insertDetachments(detachments)
                factionPopulator.insertDetachmentFactionKeywords(detachmentFactionKeywords)
                factionPopulator.insertDetachmentRules(detachmentRules)
                factionPopulator.insertStrategems(strategems)
                factionPopulator.insertEnhancements(enhancements)
                factionPopulator.insertDetachmentDetails(detachmentDetails)
                factionPopulator.insertDetachmentBulletPoints(detailBulletPoints)
                factionPopulator.insertSecondaryObjective(secondaryObjectives)
                factionPopulator.insertBulletPoints(bulletPoints)

                datasheetPopulator.insertDatasheet(datasheets)
                datasheetPopulator.insertMiniatures(miniatures)
                datasheetPopulator.insertInvSave(invSaves)
                datasheetPopulator.insertDatasheetAbility(datasheetAbilities)
                compositionPopulator.insertUnitComposition(unitComposition)
                compositionPopulator.insertUnitCompositionMiniature(unitCompositionMiniature)
                datasheetPopulator.insertDatasheetRule(datasheetRule + datasheetDamageRule)
                datasheetPopulator.insertDatasheetAbilityBond(datasheetAbilityBonds)
                datasheetPopulator.insertDatasheetSubAbility(datasheetSubAbilities)
                datasheetPopulator.insertKeywords(keywords)
                datasheetPopulator.insertMiniatureKeyword(miniatureKeywords)

                wargearPopulator.insertWargearOption(wargearOptions)
                wargearPopulator.insertWargearOptionGroup(wargearOptionGroups)
                wargearPopulator.insertWargearItem(wargearItems)
                wargearPopulator.insertWargearItemProfile(wargearItemProfiles)
                wargearPopulator.insertWargearAbility(wargearAbilities)
                wargearPopulator.insertWargearItemProfileAbility(wargearItemProfileAbilities)
                wargearPopulator.insertWargearRule(wargearRules)
                wargearPopulator.insertRuleContainerComponent(ruleContainerComponents)

                loadoutPopulator.insertLoadoutChoiceWargearItem(loadoutChoiceWargearItems)
                loadoutPopulator.insertLoadoutChoiceSet(loadoutChoiceSets)
                loadoutPopulator.insertLoadoutChoice(loadoutChoices)
            }
            return@withContext true
        }
}