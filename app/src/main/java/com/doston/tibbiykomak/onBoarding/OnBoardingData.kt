package com.doston.tibbiykomak.onBoarding

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.doston.tibbiykomak.R

class OnBoardingData(val image:Int,val title:String,val desc:String)
fun getOnBoardingData(context: Context): List<OnBoardingData> {
    return listOf(
        OnBoardingData(
            image = R.drawable.logo,
            title = context.getString(R.string.xush_kelibsiz),
            desc = context.getString(R.string.tibbiy_ko_mak_ilovasi_orqali_sog_lig_ingiz_haqida_tez_va_foydali_maslahatlar_oling)
        ),
        OnBoardingData(
            image = R.drawable.b,
            title = context.getString(R.string.barcha_muammolar_tartiblangan),
            desc = context.getString(R.string.ruhiy_surunkali_bolalar_ayollar_va_boshqa_sog_liq_muammolari_qulay_turkumlarda)
        ),
        OnBoardingData(
            image = R.drawable.c,
            title = context.getString(R.string.belgilar_orqali_qidiring),
            desc = context.getString(R.string.faqatgina_muammoni_yozing_sizga_to_g_ri_keladigan_maslahatni_topamiz)
        ),
        OnBoardingData(
            image = R.drawable.d,
            title = context.getString(R.string.ishonchli_maslahatlar),
            desc = context.getString(R.string.shifokor_maslahatiga_asoslangan_dorilar_va_uy_sharoitidagi_tavsiyalar)
        ),
    )
}