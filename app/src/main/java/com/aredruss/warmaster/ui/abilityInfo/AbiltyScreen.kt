package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.util.DismissibleDialog
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Destination(style = DismissibleDialog::class)
@Composable
fun AbilityScreen(
    abilityName: String,
    abilityId: String,
    abilityType: AbilityInfoViewModel.Companion.ScreenType,
    navigator: DestinationsNavigator,
) {
    val abilityViewModel = getViewModel<AbilityInfoViewModel> {
        parametersOf(abilityId, abilityName, abilityType)
    }

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = abilityViewModel.nameState ?: "",
                navigationAction = { navigator.popBackStack() })
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                when (abilityViewModel.screenTypeState) {
                    AbilityInfoViewModel.Companion.ScreenType.GEAR -> {
                        abilityViewModel.gearAbility?.let { ability ->
                            GearAbilityView(
                                modifier = Modifier.fillMaxWidth(),
                                ability = ability
                            )
                        }
                    }

                    AbilityInfoViewModel.Companion.ScreenType.DATASHEET -> {
                        abilityViewModel.datasheetAbility?.let { ability ->
                            DatasheetAbilityView(
                                modifier = Modifier.fillMaxWidth(),
                                datasheetAbility = ability
                            )
                        }
                    }

                    AbilityInfoViewModel.Companion.ScreenType.FAQ -> {
                        abilityViewModel.faqs?.let { faqs ->
                            FaqListView(
                                faqs = faqs
                            )
                        }
                    }

                    AbilityInfoViewModel.Companion.ScreenType.AMEND -> {
                        abilityViewModel.amendments?.let { amendments ->
                            AmendmentsListView(
                                faqs = amendments
                            )
                        }
                    }

                    else -> {
                        abilityViewModel.factionAbility?.let { rules ->
                            FactionAbilityView(
                                modifier = Modifier.fillMaxSize(),
                                rules = rules
                            )
                        }
                    }
                }
            }
        }
    )
}