package com.doston.tibbiykomak.data

fun getIllnessList(categoryId:Int):List<MainData> {
    return when (categoryId){
        1-> listOf( MainData(
            category = "Ommaviy muammolar",
            problem = "Bosh og‘rig‘i",
            recommendedPills = listOf(
                Pill(
                    name = "Paratsetamol",
                    description = "Og‘riqni kamaytiruvchi va isitmani tushiruvchi dori.",
                    usage = "Kattalar uchun 500–1000 mg har 4–6 soatda. Kunlik maksimal doza: 4000 mg."
                ),
                Pill(
                    name = "Ibuprofen",
                    description = "Yallig‘lanishga qarshi va og‘riqni kamaytiruvchi dori.",
                    usage = "Kattalar uchun 200–400 mg har 4–6 soatda. Kunlik maksimal doza: 1200 mg."
                )
            ),
            homeAdvice = listOf("Dam olish", "Ko‘proq suv ichish", "Yengil ovqat")
        ), MainData(
            category = "Ruhiy sog‘liq muammolari",
            problem = "Depressiya",
            recommendedPills = listOf(
                Pill(
                    name = "Sertralin",
                    description = "Serotonin darajasini oshiruvchi antidepressant.",
                    usage = "Kuniga bir marta 50 mg, ovqat bilan yoki ovqatsiz qabul qilinadi."
                ),
                Pill(
                    name = "Paroksetin",
                    description = "Serotonin darajasini oshiruvchi antidepressant.",
                    usage = "Kuniga bir marta 20 mg, ovqat bilan yoki ovqatsiz qabul qilinadi."
                )
            ),
            homeAdvice = listOf("Kunlik yurish", "Meditatsiya", "Do‘stlar bilan suhbat")
        ),
            MainData(
            category = "Ruhiy sog‘liq muammolari",
            problem = "Uyqusizlik",
            recommendedPills = listOf(
                Pill(
                    name = "Melatonin",
                    description = "Uyqu siklini tartibga soluvchi tabiiy gormon.",
                    usage = "Yotishdan 1–2 soat oldin 1–3 mg qabul qilinadi."
                ),
                Pill(
                    name = "Donormil (Doksilamin)",
                    description = "Antigistamin xususiyatiga ega uyqu dori.",
                    usage = "Yotishdan 15–30 daqiqa oldin 15 mg qabul qilinadi."
                )
            ),
            homeAdvice = listOf("Ekranlardan uzoqlashish", "Iliq choy ichish", "Tinch xona yaratish")
            ), MainData(
                category = "Ommaviy muammolar",
                problem = "Isitma",
                recommendedPills = listOf(
                    Pill(
                        name = "Paratsetamol",
                        description = "Isitmani tushiruvchi dori.",
                        usage = "500–1000 mg har 4–6 soatda. Maksimal doza: 4000 mg/kun."
                    ),
                    Pill(
                        name = "Ibuprofen",
                        description = "Isitma va yallig‘lanishga qarshi dori.",
                        usage = "200–400 mg har 4–6 soatda. Maksimal doza: 1200 mg/kun."
                    )
                ),
                homeAdvice = listOf("Ko‘proq suv ichish", "Dam olish", "Salqin xona")
    ), MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Diareya",
                recommendedPills = listOf(
                    Pill(
                        name = "Loperamid",
                        description = "Ich ketishni to‘xtatuvchi dori.",
                        usage = "4 mg boshlanishida, so‘ng 2 mg har ich ketishda. Maksimal doza: 16 mg/kun."
                    ),
                    Pill(
                        name = "Smecta",
                        description = "Ichak devorini himoyalovchi tabiiy sorbent.",
                        usage = "Kattalar uchun kuniga 2–3 paket, suvda eritib ichiladi."
                    )
                ),
                homeAdvice = listOf("Ko‘proq suv ichish", "Yengil ovqat", "Yog‘li va shirin ovqatdan saqlanish")
            ))

        else -> listOf()
    }
}