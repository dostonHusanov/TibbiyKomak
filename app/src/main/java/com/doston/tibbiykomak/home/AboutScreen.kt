package com.doston.tibbiykomak.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.ui.theme.AColor
import com.doston.tibbiykomak.ui.theme.DAColor
import com.doston.tibbiykomak.ui.theme.DMainColor
import com.doston.tibbiykomak.ui.theme.DRegColor
import com.doston.tibbiykomak.ui.theme.DTextColor
import com.doston.tibbiykomak.ui.theme.DTextColor2
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.RegColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TextColor2
import com.doston.tibbiykomak.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Scaffold(
        containerColor = MainColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.ilova_haqida),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textColor2
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainColor,
                    titleContentColor = textColor
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.oraga_qytish),
                            tint = textColor2
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(mainColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .background(mainColor)
            ) {
                SectionHeader(
                    stringResource(R.string.tibbiy_ko_mak_sizning_shaxsiy_sog_liqni_saqlash_yordamchingiz),
                    viewModel
                )
                Spacer(modifier = Modifier.height(10.dp))
                SectionText(
                    viewModel = viewModel,
                    text = stringResource(R.string.tibbiy_ko_mak_bu_sizning_kundalik_sog_lig_ingizni_nazorat_qilishda_yordam_beradigan_ilova_dori_eslatmalari_kasalliklar_haqida_ma_lumotlar_va_ko_plab_foydali_funksiyalarni_taqdim_etadi)
                )

                Spacer(modifier = Modifier.height(20.dp))
                SectionHeader(stringResource(R.string.asosiy_xususiyatlar), viewModel)

                val features = listOf(
                    stringResource(R.string.dori_eslatmalari),
                    stringResource(R.string.kasalliklar_haqida_ma_lumot),
                    stringResource(R.string.shaxsiy_profil),
                    stringResource(R.string.bog_lanish_funksiyasi),
                    stringResource(R.string.tungi_va_kunduzgi_rejimlar)
                )
                BulletList(items = features, viewModel)

                Spacer(modifier = Modifier.height(20.dp))
                SectionHeader(stringResource(R.string.kimlar_uchun_mo_ljallangan), viewModel)

                val people = listOf(
                    stringResource(R.string.doimiy_dori_iste_mol_qiluvchi_insonlar_uchun),
                    stringResource(R.string.yoshi_katta_eslatmalarga_muhtoj_foydalanuvchilar_uchun),
                    stringResource(R.string.farzandlariga_dori_eslatmalarini_sozlamoqchi_bo_lgan_ota_onalar_uchun),
                    stringResource(R.string.sog_lig_ini_nazorat_qilmoqchi_bo_lgan_foydalanuvchilar_uchun),
                    stringResource(R.string.oddiy_va_o_zbek_tilidagi_ilovani_afzal_ko_radiganlar_uchun),
                    stringResource(R.string.murakkab_tibbiy_ilovalardan_charchagan_foydalanuvchilar_uchun),
                    stringResource(R.string.nogironligi_bor_va_eslatmaga_ehtiyoji_bo_lgan_insonlar_uchun),
                    stringResource(R.string.tibbiy_ma_lumotlarga_tezkor_kirishni_xohlaydiganlar_uchun)
                )
                BulletList(items = people, viewModel)

                Spacer(modifier = Modifier.height(20.dp))
                SectionHeader(stringResource(R.string.nima_uchun_tibbiy_ko_mak), viewModel)

                val reasons = listOf(
                    stringResource(R.string.oson_va_tushunarli_dizayn),
                    stringResource(R.string.to_liq_o_zbek_tilida),
                    stringResource(R.string.internetga_bog_liq_bo_lmagan_holda_ishlaydi),
                    stringResource(R.string.sog_lig_ingiz_siz_uchun_muhim_bo_lgani_kabi_biz_uchun_ham_muhim)
                )
                BulletList(items = reasons, viewModel)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.ilova_versiyasi_1_0_0),
                    fontSize = 14.sp,
                    color = textColor2
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string._2025_tibbiy_ko_mak_barcha_huquqlar_himoyalangan),
                    fontSize = 12.sp,
                    color = textColor2
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor2,
        modifier = Modifier
    )
}

@Composable
fun SectionText(text: String, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Text(
        text = text,
        fontSize = 16.sp,
        color = textColor,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
    )
}

@Composable
fun BulletList(items: List<String>, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    items.forEach { item ->
        Row(modifier = Modifier.padding(horizontal = 4.dp), verticalAlignment = Alignment.Top) {
            Text(
                text = " • ",
                fontSize = 16.sp,
                color = textColor,
                modifier = Modifier.padding(vertical = 2.dp), fontWeight = FontWeight.Bold
            )
            Text(
                text = item,
                fontSize = 16.sp,
                color = textColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}
