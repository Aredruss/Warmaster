package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.AmendmentDao
import com.aredruss.warmaster.domain.database.dao.ArmyRuleDao
import com.aredruss.warmaster.domain.database.dao.BulletPointDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDetailBulletPointDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDetailDao
import com.aredruss.warmaster.domain.database.dao.DetachmentFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DetachmentRuleDao
import com.aredruss.warmaster.domain.database.dao.EnhancementDao
import com.aredruss.warmaster.domain.database.dao.FaqDao
import com.aredruss.warmaster.domain.database.dao.RuleContainerComponentDao
import com.aredruss.warmaster.domain.database.dao.SecondaryObjectiveDao
import com.aredruss.warmaster.domain.database.dao.StrategemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class DetachmentInfoRepository(
    private val detachmentDao: DetachmentDao,
    private val detachmentFactionKeywordDao: DetachmentFactionKeywordDao,
    private val detachmentRuleDao: DetachmentRuleDao,
    private val strategemDao: StrategemDao,
    private val ruleContainerComponentDao: RuleContainerComponentDao,
    private val armyRuleDao: ArmyRuleDao,
    private val bulletPointDao: BulletPointDao,
    private val detachmentBulletPointDao: DetachmentDetailBulletPointDao,
    private val enhancementDao: EnhancementDao,
    private val detailDao: DetachmentDetailDao,
    private val secondaryObjectiveDao: SecondaryObjectiveDao,
    private val faqDao: FaqDao,
    private val amendmentDao: AmendmentDao
) {

    suspend fun getDetachmentInfoById(id: String) = withContext(Dispatchers.IO + Job()) {
        val detachmentInfo = detachmentDao.getItemById(id)
        val detail = detailDao.getItemById(detachmentInfo.id)
        detachmentInfo.bullets = detail?.let {
            detachmentBulletPointDao.getItemsByDetachmentDetailId(
                it.id
            )
        } ?: emptyList()
        return@withContext detachmentInfo
    }

    suspend fun getDetachmentsForFactionId(
        factionId: String,
        isSubfaction: Boolean
    ) =
        withContext(Dispatchers.IO + Job()) {
            val ids = detachmentFactionKeywordDao.getItemsByFactionId(factionId)
            return@withContext detachmentDao.getItemsByIds(ids).filter {
                if (isSubfaction) it.displayOrder == 0L else true
            }
        }

    suspend fun getDetachmentsForPublicationId(
        publicationId: String
    ) = withContext(Dispatchers.IO + Job()) {
        return@withContext detachmentDao.getItemsByPublicationId(publicationId)
    }

    suspend fun getArmyRules(publicationId: String) = withContext(Dispatchers.IO + Job()) {
        return@withContext armyRuleDao.getItemsByPubId(publicationId)
    }

    suspend fun getDetachmentRules(detachmentId: String) = withContext(Dispatchers.IO + Job()) {
        val rules = detachmentRuleDao.getItemsById(detachmentId).map {
            it.rules = ruleContainerComponentDao.getItemsByDetachmentRuleId(it.id)
                .sortedBy { subrule -> subrule.displayOrder }
                .map { subrule ->
                    subrule.bullets = getRuleBullets(it.id)
                    subrule
                }
            it
        }
        return@withContext rules
    }

    suspend fun getStrategems(id: String, shouldUseDetachments: Boolean) =
        withContext(Dispatchers.IO + Job()) {
            return@withContext if (shouldUseDetachments) {
                strategemDao.getItemsByDetachmentId(id)
            } else {
                strategemDao.getItemsByPublicationId(id)
            }
        }

    suspend fun getEnhancements(id: String, shouldUseDetachments: Boolean) =
        withContext(Dispatchers.IO + Job()) {
            return@withContext if (shouldUseDetachments) {
                enhancementDao.getItemsByDetachmentId(id)
            } else {
                enhancementDao.getItemsByPublicationId(id)
            }
        }

    suspend fun getSecondaryObjectives(id: String) =
        withContext(Dispatchers.IO + Job()) {
            return@withContext secondaryObjectiveDao.getItemsByPublicationId(id)
        }

    suspend fun getArmyRulesSteps(abilityId: String) = withContext(Dispatchers.IO + Job()) {
        val rules = ruleContainerComponentDao.getItemsByArmyRuleId(abilityId)
        return@withContext rules.map {
            if (it.type == "bullets") {
                it.bullets = it.bullets.plus(getRuleBullets(it.id))
            }
            it
        }
    }

    suspend fun checkIfHasFaq(publicationId: String) =
        getFaqForPublication(publicationId).isNotEmpty()

    suspend fun checkIfHasAmendments(publicationId: String) =
        getAmendmentsForPublication(publicationId).isNotEmpty()

    suspend fun getAmendmentsForPublication(publicationId: String) =
        withContext(Dispatchers.IO + Job()) {
            amendmentDao.getItemsByPublication(publicationId)
        }

    suspend fun getFaqForPublication(publicationId: String) =
        withContext(Dispatchers.IO + Job()) {
            faqDao.getItemsByPublication(publicationId)
        }

    private suspend fun getRuleBullets(ruleId: String) = withContext(Dispatchers.IO + Job()) {
        return@withContext bulletPointDao.getItemsByRuleComponentId(ruleId)
    }
}