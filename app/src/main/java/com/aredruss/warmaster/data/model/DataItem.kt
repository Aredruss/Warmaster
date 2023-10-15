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
    val dataVersion:Int,
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
    val factions: List<Faction>
)
