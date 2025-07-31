package com.doston.tibbiykomak.data

import android.content.Context
import com.doston.tibbiykomak.R

fun getIllnessList(categoryId: Int,context:Context): List<MainData> {
    return when (categoryId) {
        1 -> {
            listOf(
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.bosh_og_rig_i),
                    description = context.getString(R.string.bosh_og_rig_i_turli_sabablarga_ko_ra_yuzaga_keladigan_keng_tarqalgan_simptom),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.paratsetamol),
                            context.getString(R.string.og_riqni_kamaytiruvchi_va_isitmani_tushiruvchi_dori),
                            context.getString(R.string.kattalar_uchun_500_1000_mg_har_4_6_soatda_kunlik_maksimal_doza_4000_mg)
                        ),
                        Pill(
                            context.getString(R.string.ibuprofen),
                            context.getString(R.string.yallig_lanishga_qarshi_va_og_riqni_kamaytiruvchi_dori),
                            context.getString(R.string.kattalar_uchun_200_400_mg_har_4_6_soatda_kunlik_maksimal_doza_1200_mg)
                        )
                    ),
                    homeAdvice = listOf(context.getString(R.string.dam_olish),
                        context.getString(R.string.ko_proq_suv_ichish),
                        context.getString(R.string.yengil_ovqat)), image = R.drawable.brain
                ),
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.isitma),
                    description = context.getString(R.string.isitma_tana_haroratining_normal_darajadan_yuqori_ko_tarilishi_odatda_infektsiya_belgisi),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.paracetamol),
                            context.getString(R.string.isitmani_tushiruvchi_va_og_riqni_kamaytiruvchi_dori),
                            context.getString(R.string.kattalar_uchun_500_1000_mg_har_4_6_soatda)
                        ),
                        Pill(
                            context.getString(R.string.ibuprofen),
                            context.getString(R.string.isitma_va_yallig_lanishga_qarshi_dori),
                            context.getString(R.string.kattalar_uchun_200_400_mg_har_6_soatda)
                        )
                    ),
                    homeAdvice = listOf(context.getString(R.string.ko_proq_suyuqlik_ichish), context.getString(R.string.dam_olish),
                        context.getString(
                            R.string.yengil_kiyim_kiying
                        )), image = R.drawable.fever
                ),
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.ich_qotishi),
                    description = context.getString(R.string.ich_qotishi_ichak_harakatlarining_kamayishi_yoki_to_xtashi_og_riq_va_noqulaylik_tug_diradi),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.laktuloza),
                            context.getString(R.string.ich_qotishni_yumshatuvchi_va_ichak_harakatini_oshiruvchi_dori),
                            context.getString(R.string.odatda_10_20_ml_kuniga_bir_necha_marta)
                        ),
                        Pill(
                            context.getString(R.string.magnezium_gidroksid),
                            context.getString(R.string.ichak_harakatini_rag_batlantiruvchi_dori),
                            context.getString(R.string.kattalar_uchun_15_30_ml_kuniga_bir_necha_marta)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.ko_proq_tolali_ovqatlar_iste_mol_qilish),
                        context.getString(R.string.ko_proq_suv_ichish),
                        context.getString(R.string.jismoniy_faollik)
                    ), image = R.drawable.stomach
                ),
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.ishtaha_yo_qolishi),
                    description = context.getString(R.string.ishtaha_yo_qolishi_ovqatga_bo_lgan_qiziqishning_pasayishi),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.multivitaminlar),
                            context.getString(R.string.organizmni_qo_llab_quvvatlash_uchun),
                            context.getString(R.string.kuniga_1_marta)
                        ),
                        Pill(
                            context.getString(R.string.zanjabil_kapsulalari),
                            context.getString(R.string.ovqat_hazm_qilishni_yaxshilaydi),
                            context.getString(R.string.kuniga_1_2_marta)
                        )
                    ),
                    homeAdvice = listOf(context.getString(R.string.yengil_mashqlar),
                        context.getString(R.string.ko_p_suyuqlik_ichish),
                        context.getString(
                            R.string.tanaffuslar_qilish
                        )), image = R.drawable.apatite
                ),
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.qorin_og_rig_i),
                    description = context.getString(R.string.qorin_og_rig_i_turli_sabablarga_ko_ra_yuzaga_keladigan_qorin_sohasidagi_og_riq),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.espumisan),
                            context.getString(R.string.shishishni_kamaytiruvchi_dori),
                            context.getString(R.string.kattalar_uchun_40_mg_kuniga_bir_necha_marta)
                        ),
                        Pill(
                            context.getString(R.string.no_shpa),
                            context.getString(R.string.mushak_gev_etici_va_og_riq_qoldiruvchi),
                            context.getString(R.string.kattalar_uchun_40_80_mg_har_8_soatda)
                        )
                    ),
                    homeAdvice = listOf(context.getString(R.string.dam_olish), context.getString(R.string.yengil_ovqat),
                        context.getString(
                            R.string.issiq_suyuqlik_ichish
                        )), image = R.drawable.stomach
                ),
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.teri_allergiyasi),
                    description = context.getString(R.string.teri_allergiyasi_terining_allergik_reaksiyasi_qizarish_qichishish_va_toshma_bilan_namoyon_bo_ladi),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.loratadin),
                            context.getString(R.string.antiallergik_dori),
                            context.getString(R.string.kattalar_uchun_10_mg_kuniga_bir_marta)
                        ),
                        Pill(
                            context.getString(R.string.setirizin),
                            context.getString(R.string.allergiya_belgilari_uchun_dori),
                            context.getString(R.string.kattalar_uchun_10_mg_kuniga_bir_marta)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.allergenlardan_uzoq_turish),
                        context.getString(R.string.sovuq_siqish),
                        context.getString(R.string.teri_namlantirish)
                    ), image = R.drawable.allergy
                ),
                MainData(
                    category = context.getString(R.string.ommaviy_muammolar),
                    problem = context.getString(R.string.tomoq_og_rig_i),
                    description = context.getString(R.string.tomoq_og_rig_i_tomoqdagi_yallig_lanish_yoki_infektsiya_sababli_yuzaga_keladi),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.ibuprofen),
                            context.getString(R.string.og_riqni_kamaytiruvchi_va_yallig_lanishga_qarshi_dori),
                            context.getString(R.string.kattalar_uchun_200_400_mg_har_6_soatda)                   ),
                        Pill(
                            context.getString(R.string.paratsetamol),
                            context.getString(R.string.og_riq_va_isitmani_kamaytiruvchi_dori),
                            context.getString(R.string.kattalar_uchun_200_400_mg_har_6_soatda)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.iliq_choy_ichish),
                        context.getString(R.string.tomoqni_tuzli_suv_bilan_chayish),
                        context.getString(R.string.dam_olish)
                    ), image = R.drawable.sore
                ),
                MainData(
                    category = context.getString(R.string.ruhiy_sog_liq_muammolari),
                    problem = context.getString(R.string.depressiya),
                    description = context.getString(R.string.depressiya_uzoq_davom_etadigan_qayg_u_va_ruhiy_tushkunlik_holati),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.fluoksetin),
                            context.getString(R.string.antidepressant_dori),
                            context.getString(R.string.kuniga_20_mg_dan_boshlanadi_shifokor_ko_rsatmasi_bo_yicha)
                        ),
                        Pill(
                            context.getString(R.string.sertralin),
                            context.getString(R.string.antidepressant_dori),
                            context.getString(R.string.kuniga_50_mg_dan_boshlanadi_shifokor_ko_rsatmasi_bo_yicha)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.jismoniy_mashqlar_qilish),
                        context.getString(R.string.do_stlar_bilan_muloqot),
                        context.getString(R.string.professional_yordamga_murojaat_qilish)
                    ), image = R.drawable.brain
                ),
                MainData(
                    category = context.getString(R.string.ruhiy_sog_liq_muammolari),
                    problem = context.getString(R.string.stress),
                    description = context.getString(R.string.stress_organizm_va_ongning_kuchli_bosim_va_zo_riqish_holati),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.valeriana_ekstrakti),
                            context.getString(R.string.tinchlantiruvchi_dori),
                            context.getString(R.string.kuniga_1_2_marta_300_mg)
                        ),
                        Pill(
                            context.getString(R.string.magniy_preparatlari),
                            context.getString(R.string.stressni_kamaytiruvchi_minerallar),
                            context.getString(R.string.kuniga_100_300_mg)
                        )
                    ),
                    homeAdvice = listOf(context.getString(R.string.yengil_mashqlar),
                        context.getString(R.string.nafas_olish_mashqlari),
                        context.getString(R.string.yetarli_uxlash)), image = R.drawable.brain
                ),
                MainData(
                    category = context.getString(R.string.ruhiy_sog_liq_muammolari),
                    problem = context.getString(R.string.uyqu_buzilishlari),
                    description = context.getString(R.string.uyqu_buzilishlari_uxlashning_sifat_va_miqdoriga_ta_sir_qiluvchi_holatlar),
                    recommendedPills = listOf(
                        Pill(context.getString(R.string.melatonin),
                            context.getString(R.string.uyquni_tartibga_soluvchi_dori),
                            context.getString(
                                R.string.kechqurun_1_3_mg
                            )),
                        Pill(context.getString(R.string.diphenhidramin),
                            context.getString(R.string.uyqu_dori),
                            context.getString(R.string.kattalar_uchun_25_50_mg_kechqurun))
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.yotishdan_oldin_og_ir_ovqat_yemang),
                        context.getString(R.string.yotish_vaqtini_belgilash),
                        context.getString(R.string.yorug_lik_va_shovqinni_kamaytirish)
                    ), image = R.drawable.sleepless
                ),

                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.gastrit),
                    description = context.getString(R.string.gastrit_oshqozon_shilliq_qavatining_yallig_lanishi),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.omeprazol),
                            context.getString(R.string.oshqozon_kislotasini_kamaytiruvchi_dori),
                            context.getString(R.string.kuniga_20_mg_ovqatdan_oldin)
                        ),
                        Pill(
                            context.getString(R.string.ranitidin),
                            context.getString(R.string.oshqozon_kislotasini_kamaytiruvchi_dori),
                            context.getString(R.string.kuniga_150_mg_kuniga_2_marta)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.achchiq_va_yog_li_ovqatlardan_saqlanish),
                        context.getString(R.string.ko_p_suyuqlik_ichish),
                        context.getString(R.string.chekish_va_spirtli_ichimliklardan_voz_kechish)
                    ), image = R.drawable.stomach
                ),
                MainData(
                    category =context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.hazmsizlik_dispepsiya),
                    description = context.getString(R.string.hazmsizlik_ovqat_hazm_bo_lishida_qiyinchiliklar_va_noqulaylik_hissi),
                    recommendedPills = listOf(
                        Pill(context.getString(R.string.mezim),
                            context.getString(R.string.ovqat_hazm_qilish_fermentlari),
                            context.getString(
                                R.string.ovqatdan_keyin_1_2_tabletka
                            )),
                        Pill(
                            context.getString(R.string.pancreatin),
                            context.getString(R.string.ovqat_hazm_qilish_fermentlari),
                            context.getString(R.string.ovqatdan_keyin_1_2_tabletka)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kichik_porsiyalarda_tez_tez_ovqatlanish),
                        context.getString(R.string.og_ir_va_yog_li_taomlardan_saqlanish),
                        context.getString(R.string.ko_p_suv_ichish)
                    ), image = R.drawable.stomach
                ),
                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.qorin_og_rig_i),
                    description = context.getString(R.string.qorin_og_rig_i_turli_sabablar_bilan_yuzaga_keladigan_noqulaylik),
                    recommendedPills = listOf(
                        Pill(context.getString(R.string.espumizan),
                            context.getString(R.string.gazni_yo_qotuvchi_dori),
                            context.getString(R.string.ovqatdan_keyin_2_3_tabletka)),
                        Pill(context.getString(R.string.no_shpa),
                            context.getString(R.string.spazmolitik_dori),
                            context.getString(R.string.kuniga_2_3_marta_40_mg))
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.yengil_ovqatlanish),
                        context.getString(R.string.oziq_ovqatni_yaxshi_chaynash),
                        context.getString(R.string.stressni_kamaytirish)
                    ), image = R.drawable.stomach
                ),


                MainData(
                    category = context.getString(R.string.allergiya),
                    problem = context.getString(R.string.teridagi_allergiya),
                    description = context.getString(R.string.terida_qizarish_qichishish_shishish_kabi_allergik_reaksiyalar),
                    recommendedPills = listOf(
                        Pill(context.getString(R.string.loratadin),
                            context.getString(R.string.allergiyaga_qarshi_dori),
                            context.getString(R.string.kuniga_10_mg_1_marta)),
                        Pill(context.getString(R.string.cetirizin),
                            context.getString(R.string.allergiyaga_qarshi_dori),
                            context.getString(R.string.kuniga_10_mg_1_marta))
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.allergenlardan_uzoqroq_bo_lish),
                        context.getString(R.string.terini_toza_va_nam_saqlash),
                        context.getString(R.string.sovuq_kompress_qo_yish)
                    ), image = R.drawable.allergy
                ),
                MainData(
                    category = context.getString(R.string.allergiya),
                    problem = context.getString(R.string.burun_allergiyasi_hayfaringit),
                    description = context.getString(R.string.burun_yo_llarining_allergik_yallig_lanishi_burun_bitishi_va_hap_r_k),
                    recommendedPills = listOf(
                        Pill(context.getString(R.string.loratadin),
                            context.getString(R.string.allergiyaga_qarshi_do),
                            context.getString(R.string.kuniga_10_mg_1_mar)),
                        Pill(context.getString(R.string.fexofenadin),
                            context.getString(R.string.allergiyaga_qarshi_d),
                            context.getString(R.string.kuniga_180_mg_1_marta))
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.uy_sharoitida_changni_kamaytirish),
                        context.getString(R.string.allergenlardan_uzoqroq_bo_lis),
                        context.getString(R.string.burunni_nam_saqlash)
                    ), image = R.drawable.allergy
                ),
                MainData(
                    category = context.getString(R.string.allergiya),
                    problem = context.getString(R.string.oshqozon_allergiyasi),
                    description = context.getString(R.string.ovqat_hazm_qilish_tizimining_allergik_reaksiyasi),
                    recommendedPills = listOf(
                        Pill(context.getString(R.string.ranitidi),
                            context.getString(R.string.allergiyaga_qarshi_),
                            context.getString(R.string.kuniga_150_mg_2_marta)),
                        Pill(context.getString(R.string.famotidin),
                            context.getString(R.string.allergiyaga_qarshi),
                            context.getString(R.string.kuniga_20_mg_2_marta))
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.allergen_bo_lgan_ovqatlardan_voz_kechish),
                        context.getString(R.string.kichik_porsiyalarda_ovqatlanish),
                        context.getString(R.string.ko_p_suv_ichis)
                    ), image = R.drawable.stomach
                ),

                MainData(
                    category = context.getString(R.string.qon_bosimi_muammolari),
                    problem = context.getString(R.string.yuqori_qon_bosimi),
                    description = context.getString(R.string.yuqori_qon_bosimi_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.enalapril),
                            context.getString(R.string.enalapril_desc),
                            context.getString(R.string.enalapril_dose)
                        ),
                        Pill(
                            context.getString(R.string.amlodipin),
                            context.getString(R.string.amlodipin_desc),
                            context.getString(R.string.amlodipin_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kamaytir_tuz),
                        context.getString(R.string.jismoniy_faollik),
                        context.getString(R.string.stress_kamaytir),
                        context.getString(R.string.soglom_ovqatlanish)
                    ),
                    image = R.drawable.blood
                ),

                MainData(
                    category = context.getString(R.string.qon_bosimi_muammolari),
                    problem = context.getString(R.string.past_qon_bosimi),
                    description = context.getString(R.string.past_qon_bosimi_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.midodrin),
                            context.getString(R.string.midodrin_desc),
                            context.getString(R.string.midodrin_dose)
                        ),
                        Pill(
                            context.getString(R.string.fludrokortizon),
                            context.getString(R.string.fludrokortizon_desc),
                            context.getString(R.string.fludrokortizon_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kop_suyuqlik),
                        context.getString(R.string.birdan_turmaslik),
                        context.getString(R.string.tuzli_mahsulotlar),
                        context.getString(R.string.elastik_paypoq)
                    ),
                    image = R.drawable.blood
                ),

                MainData(
                    category = context.getString(R.string.nafas_olish_tizimi_muammolari),
                    problem = context.getString(R.string.bronxit),
                    description = context.getString(R.string.bronxit_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.ambroksol),
                            context.getString(R.string.ambroksol_desc),
                            context.getString(R.string.ambroksol_dose)
                        ),
                        Pill(
                            context.getString(R.string.salbutamol),
                            context.getString(R.string.salbutamol_desc),
                            context.getString(R.string.salbutamol_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.issiq_ichimlik),
                        context.getString(R.string.nam_havo),
                        context.getString(R.string.chekishdan_saqlanish),
                        context.getString(R.string.dam_olish)
                    ),
                    image = R.drawable.nose
                ),

                MainData(
                    category = context.getString(R.string.nafas_olish_tizimi_muammolari),
                    problem = context.getString(R.string.bronxial_astma),
                    description = context.getString(R.string.bronxial_astma_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.salbutamol_inhalator),
                            context.getString(R.string.salbutamol_inhalator_desc),
                            context.getString(R.string.salbutamol_inhalator_dose)
                        ),
                        Pill(
                            context.getString(R.string.budesonid),
                            context.getString(R.string.budesonid_desc),
                            context.getString(R.string.budesonid_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.allergen_saqlanish),
                        context.getString(R.string.inhalator_ishlatish),
                        context.getString(R.string.dori_nazorati),
                        context.getString(R.string.soglom_turmush)
                    ), image = R.drawable.nose
                ),

                MainData(
                    category = context.getString(R.string.nafas_olish_tizimi_muammolari),
                    problem = context.getString(R.string.pnevmoniya),
                    description = context.getString(R.string.pnevmoniya_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.amoksitsillin),
                            context.getString(R.string.amoksitsillin_desc),
                            context.getString(R.string.amoksitsillin_dose)
                        ),
                        Pill(
                            context.getString(R.string.azitromitsin),
                            context.getString(R.string.azitromitsin_desc),
                            context.getString(R.string.azitromitsin_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kop_suyuqlik),
                        context.getString(R.string.dam_olish),
                        context.getString(R.string.isitma_tushirish),
                        context.getString(R.string.tibbiy_nazorat)
                    ), image = R.drawable.nose
                ),

                MainData(
                    category = context.getString(R.string.qon_tomir_muammolari),
                    problem = context.getString(R.string.yuqori_qon_bosimi_2),
                    description = context.getString(R.string.yuqori_qon_bosimi_desc_2),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.enalapril),
                            context.getString(R.string.enalapril_desc_2),
                            context.getString(R.string.enalapril_dose_2)
                        ),
                        Pill(
                            context.getString(R.string.amlodipin),
                            context.getString(R.string.amlodipin_desc_2),
                            context.getString(R.string.amlodipin_dose_2)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kamaytir_tuz),
                        context.getString(R.string.sport_shugullanish),
                        context.getString(R.string.stress_kamaytir),
                        context.getString(R.string.vazn_me_yor)
                    ), image = R.drawable.blood
                ),

                MainData(
                    category = context.getString(R.string.qon_tomir_muammolari),
                    problem = context.getString(R.string.past_qon_bosimi_2),
                    description = context.getString(R.string.past_qon_bosimi_desc_2),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.midodrin),
                            context.getString(R.string.midodrin_desc_2),
                            context.getString(R.string.midodrin_dose_2)
                        ),
                        Pill(
                            context.getString(R.string.kofein_prep),
                            context.getString(R.string.kofein_prep_desc),
                            context.getString(R.string.kofein_prep_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.koproq_suyuqlik),
                        context.getString(R.string.tuzli_ovqat),
                        context.getString(R.string.sekin_turish),
                        context.getString(R.string.faollik)
                    ), image = R.drawable.blood
                ),

                MainData(
                    category = context.getString(R.string.qon_tomir_muammolari),
                    problem = context.getString(R.string.varikoz),
                    description = context.getString(R.string.varikoz_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.detraleks),
                            context.getString(R.string.detraleks_desc),
                            context.getString(R.string.detraleks_dose)
                        ),
                        Pill(
                            context.getString(R.string.troxevasin),
                            context.getString(R.string.troxevasin_desc),
                            context.getString(R.string.troxevasin_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.oyoq_dam),
                        context.getString(R.string.siqish_paypoq),
                        context.getString(R.string.uzoq_tik_turish),
                        context.getString(R.string.yurish_harakat)
                    ), image = R.drawable.blood
                ),

                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.gastrit),
                    description = context.getString(R.string.gastrit_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.omeprazol),
                            context.getString(R.string.omeprazol_desc),
                            context.getString(R.string.omeprazol_dose)
                        ),
                        Pill(
                            context.getString(R.string.ranitidin),
                            context.getString(R.string.ranitidin_desc),
                            context.getString(R.string.ranitidin_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kislotali_ovqat),
                        context.getString(R.string.kichik_porsiya),
                        context.getString(R.string.spirtli_ichimlik)
                    ), image = R.drawable.stomach
                ),

                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.oshqozon_yarasi),
                    description = context.getString(R.string.oshqozon_yarasi_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.almagel),
                            context.getString(R.string.almagel_desc),
                            context.getString(R.string.almagel_dose)
                        ),
                        Pill(
                            context.getString(R.string.sucralfate),
                            context.getString(R.string.sucralfate_desc),
                            context.getString(R.string.sucralfate_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.tuz_achchiq_cheklash),
                        context.getString(R.string.chekish_tashlash),
                        context.getString(R.string.stress_kamaytirish)
                    ), image = R.drawable.stomach
                ),

                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.meteorizm),
                    description = context.getString(R.string.meteorizm_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.simetikon),
                            context.getString(R.string.simetikon_desc),
                            context.getString(R.string.simetikon_dose)
                        ),
                        Pill(
                            context.getString(R.string.espumisan),
                            context.getString(R.string.espumisan_desc),
                            context.getString(R.string.espumisan_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.gaz_ovqat_cheklash),
                        context.getString(R.string.yaxshi_chaynalgan),
                        context.getString(R.string.faollikni_oshirish)
                    ), image = R.drawable.stomach
                ),

                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.ich_qotishi),
                    description = context.getString(R.string.ich_qotishi_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.dulkolaks),
                            context.getString(R.string.dulkolaks_desc),
                            context.getString(R.string.dulkolaks_dose)
                        ),
                        Pill(
                            context.getString(R.string.laktuloza),
                            context.getString(R.string.laktuloza_desc),
                            context.getString(R.string.laktuloza_dose)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.kop_suyuqlik),
                        context.getString(R.string.tolaga_boy_ovqat),
                        context.getString(R.string.faollik)
                    ), image = R.drawable.stomach
                ),

                MainData(
                    category = context.getString(R.string.ovqat_hazm_qilish_muammolari),
                    problem = context.getString(R.string.diareya),
                    description = context.getString(R.string.diareya_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.loperamid),
                            context.getString(R.string.loperamid_desc),
                            context.getString(R.string.loperamid_usage)
                        ),
                        Pill(
                            context.getString(R.string.smekta),
                            context.getString(R.string.smekta_desc),
                            context.getString(R.string.smekta_usage)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.diareya_advice1),
                        context.getString(R.string.diareya_advice2),
                        context.getString(R.string.diareya_advice3)
                    ),
                    image = R.drawable.stomach
                ),

                MainData(
                    category = context.getString(R.string.teri_muammolari),
                    problem = context.getString(R.string.akne),
                    description = context.getString(R.string.akne_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.benzoyl),
                            context.getString(R.string.benzoyl_desc),
                            context.getString(R.string.benzoyl_usage)
                        ),
                        Pill(
                            context.getString(R.string.azelain),
                            context.getString(R.string.azelain_desc),
                            context.getString(R.string.azelain_usage)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.akne_advice1),
                        context.getString(R.string.akne_advice2),
                        context.getString(R.string.akne_advice3)
                    ),
                    image = R.drawable.allergy
                ),

                MainData(
                    category = context.getString(R.string.teri_muammolari),
                    problem = context.getString(R.string.dermatit),
                    description = context.getString(R.string.dermatit_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.hidrokortizon),
                            context.getString(R.string.hidrokortizon_desc),
                            context.getString(R.string.hidrokortizon_usage)
                        ),
                        Pill(
                            context.getString(R.string.antihistamin),
                            context.getString(R.string.antihistamin_desc),
                            context.getString(R.string.antihistamin_usage)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.dermatit_advice1),
                        context.getString(R.string.dermatit_advice2),
                        context.getString(R.string.dermatit_advice3)
                    ),
                    image = R.drawable.allergy
                ),

                MainData(
                    category = context.getString(R.string.teri_muammolari),
                    problem = context.getString(R.string.psoriaz),
                    description = context.getString(R.string.psoriaz_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.kalsipotriol),
                            context.getString(R.string.kalsipotriol_desc),
                            context.getString(R.string.kalsipotriol_usage)
                        ),
                        Pill(
                            context.getString(R.string.metotreksat),
                            context.getString(R.string.metotreksat_desc),
                            context.getString(R.string.metotreksat_usage)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.psoriaz_advice1),
                        context.getString(R.string.psoriaz_advice2),
                        context.getString(R.string.psoriaz_advice3)
                    ),
                    image = R.drawable.allergy
                ),

                MainData(
                    category = context.getString(R.string.teri_muammolari),
                    problem = context.getString(R.string.allergik_toshma),
                    description = context.getString(R.string.allergik_toshma_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.antihistaminlar),
                            context.getString(R.string.allergiya_simptom_kamaytiradi),
                            context.getString(R.string.kuniga_1_marta)
                        ),
                        Pill(
                            context.getString(R.string.kortikosteroid_krem),
                            context.getString(R.string.yalliglanishni_kamaytiradi),
                            context.getString(R.string.teri_yuzasiga_surtiladi)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.allergenlardan_uzoq),
                        context.getString(R.string.teringizni_quritmaslik),
                        context.getString(R.string.sovun_kimyoviy_ehtiyot)
                    ),
                    image = R.drawable.allergy
                ),

                MainData(
                    category = context.getString(R.string.teri_muammolari),
                    problem = context.getString(R.string.yiringli_yaralar),
                    description = context.getString(R.string.yiringli_yaralar_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.antibiotiklar),
                            context.getString(R.string.yiringli_infeksiyani_davolaydi),
                            context.getString(R.string.belgilangan_dozada)
                        ),
                        Pill(
                            context.getString(R.string.analjeziklar),
                            context.getString(R.string.ogriqni_kamaytiradi),
                            context.getString(R.string.zaruratga_qarab)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.teri_tozaligi),
                        context.getString(R.string.yaralarni_himoya_qilish),
                        context.getString(R.string.shifokorga_murojaat)
                    ),
                    image = R.drawable.allergy
                ),

                MainData(
                    category = context.getString(R.string.metabolik_muammolar),
                    problem = context.getString(R.string.qandli_diabet),
                    description = context.getString(R.string.qandli_diabet_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.metformin),
                            context.getString(R.string.qon_shakar_pashaytiradi),
                            context.getString(R.string.kuniga_1_2_ovqatdan_keyin)
                        ),
                        Pill(
                            context.getString(R.string.insulin),
                            context.getString(R.string.qondagi_shakarni_tartib),
                            context.getString(R.string.shifokor_nazoratida_dozalanadi)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.soglom_ovqatlanish),
                        context.getString(R.string.jismoniy_faollik),
                        context.getString(R.string.qon_shakar_tekshirish)
                    ),
                    image = R.drawable.metobolib
                ),

                MainData(
                    category = context.getString(R.string.metabolik_muammolar),
                    problem = context.getString(R.string.qandli_diabet_ketoasidozi),
                    description = context.getString(R.string.qandli_diabet_ketoasidozi_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.insulin),
                            context.getString(R.string.qon_shakar_tez_pashaytiradi),
                            context.getString(R.string.tezkor_yordam_talab)
                        ),
                        Pill(
                            context.getString(R.string.elektrolitlar),
                            context.getString(R.string.tana_suyuqlik_balansi),
                            context.getString(R.string.shifokor_nazoratida)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.darhol_shifokorga),
                        context.getString(R.string.suyuqlik_ichishni_kopaytirish),
                        context.getString(R.string.tibbiy_nazoratda_bolish)
                    ),
                    image = R.drawable.metobolib
                )
,MainData(
                    category = context.getString(R.string.metabolik_muammolar),
                    problem = context.getString(R.string.qandli_diabet_asoratlari),
                    description = context.getString(R.string.qandli_diabet_asoratlari_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.antihipertenzivlar),
                            context.getString(R.string.qon_bosimini_tartib),
                            context.getString(R.string.shifokor_korsatmasi)
                        ),
                        Pill(
                            context.getString(R.string.statinlar),
                            context.getString(R.string.xolesterin_kamaytiradi),
                            context.getString(R.string.kuniga_1_marta)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.qon_bosimi_va_xolesterin_nazorat),
                        context.getString(R.string.soglom_turmush_tarzi),
                        context.getString(R.string.doimiy_tibbiy_korik)
                    ),
                    image = R.drawable.metobolib
                ),

                MainData(
                    category = context.getString(R.string.metabolik_muammolar),
                    problem = context.getString(R.string.gipotiroidizm),
                    description = context.getString(R.string.gipotiroidizm_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.levotiroksin),
                            context.getString(R.string.qalqonsimon_bez_almashtiradi),
                            context.getString(R.string.kuniga_bir_marta_bosh_qoringa)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.muntazam_tibbiy_nazorat),
                        context.getString(R.string.soglom_ovqatlanish),
                        context.getString(R.string.jismoniy_faollik_saqlash)
                    ),
                    image = R.drawable.metobolib
                ),

                MainData(
                    category = context.getString(R.string.metabolik_muammolar),
                    problem = context.getString(R.string.giperterioz),
                    description = context.getString(R.string.giperterioz_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.beta_blokerlar),
                            context.getString(R.string.yurak_urishini_sekinlashtiradi),
                            context.getString(R.string.shifokor_tavsiyasi)
                        ),
                        Pill(
                            context.getString(R.string.antitiroyd_dori),
                            context.getString(R.string.qalqonsimon_bez_bostiradi),
                            context.getString(R.string.dozani_shifokor_belgilaydi)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.stressni_kamaytirish),
                        context.getString(R.string.yod_iste_molini_nazorat),
                        context.getString(R.string.shifokor_nazorati)
                    ),
                    image = R.drawable.metobolib
                ),

                MainData(
                    category = context.getString(R.string.suyak_va_mushak_muammolari),
                    problem = context.getString(R.string.osteoporoz),
                    description = context.getString(R.string.osteoporoz_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.kalsiy),
                            context.getString(R.string.suyak_mustahkamlaydi),
                            context.getString(R.string.kuniga_1_2_ovqat_bilan)
                        ),
                        Pill(
                            context.getString(R.string.vitamin_d),
                            context.getString(R.string.kalsiy_sorilishini_oshiradi),
                            context.getString(R.string.kuniga_1_marta)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.soglom_ovqatlanish),
                        context.getString(R.string.jismoniy_faollik_saqlash),
                        context.getString(R.string.quyosh_nurida_bolish)
                    ),
                    image = R.drawable.bone
                ),

                MainData(
                    category = context.getString(R.string.suyak_va_mushak_muammolari),
                    problem = context.getString(R.string.artrit),
                    description = context.getString(R.string.artrit_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.nsaid),
                            context.getString(R.string.yalliglanish_ogriq),
                            context.getString(R.string.shifokor_tavsiyasi)
                        ),
                        Pill(
                            context.getString(R.string.kortikosteroidlar),
                            context.getString(R.string.qattiq_yalliglanish_qollanadi),
                            context.getString(R.string.qisqa_muddat_foydalanish)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.bogim_himoya_qilish),
                        context.getString(R.string.mushak_mustahkamlash),
                        context.getString(R.string.soglom_vazn)
                    ),
                    image = R.drawable.bone
                )
,MainData(
                    category = context.getString(R.string.suyak_va_mushak_muammolari),
                    problem = context.getString(R.string.mushak_spazmi),
                    description = context.getString(R.string.mushak_spazmi_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.mushak_gevsheticiler),
                            context.getString(R.string.mushak_gevsheticiler_desc),
                            context.getString(R.string.shifokor_korsatma)
                        ),
                        Pill(
                            context.getString(R.string.ogriq_qoldiruvchi),
                            context.getString(R.string.ogriq_qoldiruvchi_desc),
                            context.getString(R.string.zaruratga_qarab)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.issiq_kompres),
                        context.getString(R.string.chozish_mashqlari),
                        context.getString(R.string.suyuqlik_ichish)
                    ),
                    image = R.drawable.bone
                ),
                MainData(
                    category = context.getString(R.string.suyak_va_mushak_muammolari),
                    problem = context.getString(R.string.bel_ogrig_i),
                    description = context.getString(R.string.bel_ogrig_i_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.ogriq_qoldiruvchi),
                            context.getString(R.string.ogriq_qoldiruvchi_desc),
                            context.getString(R.string.zaruratga_qarab)
                        ),
                        Pill(
                            context.getString(R.string.yalliglanishga_qarshi),
                            context.getString(R.string.yalliglanishga_qarshi_desc),
                            context.getString(R.string.shifokor_tavsiyasi)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.tana_holati),
                        context.getString(R.string.jismoniy_mashqlar),
                        context.getString(R.string.issiq_vanna)
                    ),
                    image = R.drawable.bone
                ),
                MainData(
                    category = context.getString(R.string.suyak_va_mushak_muammolari),
                    problem = context.getString(R.string.mushak_zaiflik),
                    description = context.getString(R.string.mushak_zaiflik_desc),
                    recommendedPills = listOf(
                        Pill(
                            context.getString(R.string.vitamin_b_kompleks),
                            context.getString(R.string.vitamin_b_kompleks_desc),
                            context.getString(R.string.kuniga_bir_marta)
                        ),
                        Pill(
                            context.getString(R.string.protein_qoshimcha),
                            context.getString(R.string.protein_qoshimcha_desc),
                            context.getString(R.string.ovqat_bilan_birga)
                        )
                    ),
                    homeAdvice = listOf(
                        context.getString(R.string.muntazam_mashqlar),
                        context.getString(R.string.balanslangan_ovqat),
                        context.getString(R.string.yetarli_dam_olish)
                    ),
                    image = R.drawable.bone
                )

            )
        }

        else -> {
            listOf()
        }
    }
}
