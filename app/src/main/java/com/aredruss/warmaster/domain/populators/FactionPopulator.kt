package com.aredruss.warmaster.domain.populators

import android.provider.SyncStateContract.Helpers.insert
import com.aredruss.warmaster.domain.database.dao.ArmyRuleDao
import com.aredruss.warmaster.domain.database.dao.BulletPointDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDetailBulletPointDao
import com.aredruss.warmaster.domain.database.dao.DetachmentDetailDao
import com.aredruss.warmaster.domain.database.dao.DetachmentFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DetachmentRuleDao
import com.aredruss.warmaster.domain.database.dao.EnhancementDao
import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.PublicationDao
import com.aredruss.warmaster.domain.database.dao.SecondaryObjectiveDao
import com.aredruss.warmaster.domain.database.dao.StrategemDao
import com.aredruss.warmaster.domain.database.model.ArmyRule
import com.aredruss.warmaster.domain.database.model.BulletPoint
import com.aredruss.warmaster.domain.database.model.DatasheetFactionKeyword
import com.aredruss.warmaster.domain.database.model.Detachment
import com.aredruss.warmaster.domain.database.model.DetachmentDetail
import com.aredruss.warmaster.domain.database.model.DetachmentDetailBulletPoint
import com.aredruss.warmaster.domain.database.model.DetachmentFactionKeyword
import com.aredruss.warmaster.domain.database.model.DetachmentRule
import com.aredruss.warmaster.domain.database.model.Enhancement
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.database.model.Publication
import com.aredruss.warmaster.domain.database.model.SecondaryObjective
import com.aredruss.warmaster.domain.database.model.Strategem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class FactionPopulator(
    private val factionKeywordDao: FactionKeywordDao,
    private val datasheetFactionKeywordDao: DatasheetFactionKeywordDao,
    private val publicationDao: PublicationDao,
    private val detachmentDao: DetachmentDao,
    private val detachmentFactionKeywordDao: DetachmentFactionKeywordDao,
    private val detachmentRuleDao: DetachmentRuleDao,
    private val strategemDao: StrategemDao,
    private val enhancementDao: EnhancementDao,
    private val secondaryObjectiveDao: SecondaryObjectiveDao,
    private val detachmentDetailBulletPointDao: DetachmentDetailBulletPointDao,
    private val detachmentDetailDao: DetachmentDetailDao,
    private val armyRuleDao: ArmyRuleDao,
    private val bulletPointDao: BulletPointDao
) {
    suspend fun insertFactionKeyword(data: List<FactionKeyword>) =
        withContext(Dispatchers.IO + Job()) {
            factionKeywordDao.insert(data)
        }

    suspend fun insertDatasheetFactionKeyword(data: List<DatasheetFactionKeyword>) =
        withContext(Dispatchers.IO + Job()) {
            datasheetFactionKeywordDao.insert(data)
        }

    suspend fun insertDetachments(data: List<Detachment>) =
        withContext(Dispatchers.IO + Job()) {
            detachmentDao.insert(data)
        }

    suspend fun insertDetachmentFactionKeywords(data: List<DetachmentFactionKeyword>) =
        withContext(Dispatchers.IO + Job()) {
            detachmentFactionKeywordDao.insert(data)
        }

    suspend fun insertStrategems(data: List<Strategem>) =
        withContext(Dispatchers.IO + Job()) {
            strategemDao.insert(data)
        }

    suspend fun insertDetachmentRules(data: List<DetachmentRule>) =
        withContext(Dispatchers.IO + Job()) {
            detachmentRuleDao.insert(data)
        }

    suspend fun insertPublications(data: List<Publication>) =
        withContext(Dispatchers.IO + Job()) {
            publicationDao.insert(data)
        }

    suspend fun insertEnhancements(data: List<Enhancement>) =
        withContext(Dispatchers.IO + Job()) {
            enhancementDao.insert(data)
        }

    suspend fun insertSecondaryObjective(data: List<SecondaryObjective>) =
        withContext(Dispatchers.IO + Job()) {
            secondaryObjectiveDao.insert(data)
        }

    suspend fun insertDetachmentDetails(data: List<DetachmentDetail>) =
        withContext(Dispatchers.IO + Job()) {
            detachmentDetailDao.insert(data)
        }

    suspend fun insertDetachmentBulletPoints(data: List<DetachmentDetailBulletPoint>) =
        withContext(Dispatchers.IO + Job()) {
            detachmentDetailBulletPointDao.insert(data)
        }

    suspend fun insertArmyRules(data: List<ArmyRule>) =
        withContext(Dispatchers.IO + Job()) {
            armyRuleDao.insert(data)
        }

    suspend fun insertBulletPoints(data: List<BulletPoint>) =
        withContext(Dispatchers.IO + Job()) {
            bulletPointDao.insert(data)
        }
}