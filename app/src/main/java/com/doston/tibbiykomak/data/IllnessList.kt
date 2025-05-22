package com.doston.tibbiykomak.data

fun getIllnessList(categoryId: Int): List<MainData> {
    return when (categoryId) {
        1 -> listOf(
            MainData(
                category = "Ommaviy muammolar",
                problem = "Bosh og‘rig‘i",
                description = "Bosh og‘rig‘i – turli sabablarga ko‘ra yuzaga keladigan keng tarqalgan simptom...",
                recommendedPills = listOf(
                    Pill(
                        "Paratsetamol",
                        "Og‘riqni kamaytiruvchi va isitmani tushiruvchi dori.",
                        "Kattalar uchun 500–1000 mg har 4–6 soatda. Kunlik maksimal doza: 4000 mg."
                    ),
                    Pill(
                        "Ibuprofen",
                        "Yallig‘lanishga qarshi va og‘riqni kamaytiruvchi dori.",
                        "Kattalar uchun 200–400 mg har 4–6 soatda. Kunlik maksimal doza: 1200 mg."
                    )
                ),
                homeAdvice = listOf("Dam olish", "Ko‘proq suv ichish", "Yengil ovqat")
            ),
            MainData(
                category = "Ommaviy muammolar",
                problem = "Isitma",
                description = "Isitma – tana haroratining normal darajadan yuqori ko‘tarilishi, odatda infektsiya belgisi.",
                recommendedPills = listOf(
                    Pill(
                        "Paratsetamol",
                        "Isitmani tushiruvchi va og‘riqni kamaytiruvchi dori.",
                        "Kattalar uchun 500–1000 mg har 4–6 soatda."
                    ),
                    Pill(
                        "Ibuprofen",
                        "Isitma va yallig‘lanishga qarshi dori.",
                        "Kattalar uchun 200–400 mg har 6 soatda."
                    )
                ),
                homeAdvice = listOf("Ko‘proq suyuqlik ichish", "Dam olish", "Yengil kiyim kiying")
            ),
            MainData(
                category = "Ommaviy muammolar",
                problem = "Ich qotishi",
                description = "Ich qotishi – ichak harakatlarining kamayishi yoki to‘xtashi, og‘riq va noqulaylik tug‘diradi.",
                recommendedPills = listOf(
                    Pill(
                        "Laktuloza",
                        "Ich qotishni yumshatuvchi va ichak harakatini oshiruvchi dori.",
                        "Odatda 10-20 ml kuniga bir necha marta."
                    ),
                    Pill(
                        "Magnezium gidroksid",
                        "Ichak harakatini rag‘batlantiruvchi dori.",
                        "Kattalar uchun 15-30 ml kuniga bir necha marta."
                    )
                ),
                homeAdvice = listOf(
                    "Ko‘proq tolali ovqatlar iste’mol qilish",
                    "Ko‘proq suv ichish",
                    "Jismoniy faollik"
                )
            ),
            MainData(
                category = "Ommaviy muammolar",
                problem = "Ishtaha yo‘qolishi",
                description = "Ishtaha yo‘qolishi – ovqatga bo‘lgan qiziqishning pasayishi.",
                recommendedPills = listOf(
                    Pill(
                        "Multivitaminlar",
                        "Organizmni qo‘llab-quvvatlash uchun.",
                        "Kuniga 1 marta."
                    ),
                    Pill(
                        "Zanjabil kapsulalari",
                        "Ovqat hazm qilishni yaxshilaydi.",
                        "Kuniga 1-2 marta."
                    )
                ),
                homeAdvice = listOf("Yengil mashqlar", "Ko‘p suyuqlik ichish", "Tanaffuslar qilish")
            ),
            MainData(
                category = "Ommaviy muammolar",
                problem = "Qorin og‘rig‘i",
                description = "Qorin og‘rig‘i – turli sabablarga ko‘ra yuzaga keladigan qorin sohasidagi og‘riq.",
                recommendedPills = listOf(
                    Pill(
                        "Espumisan",
                        "Shishishni kamaytiruvchi dori.",
                        "Kattalar uchun 40 mg kuniga bir necha marta."
                    ),
                    Pill(
                        "No-shpa",
                        "Mushak gevşetici va og‘riq qoldiruvchi.",
                        "Kattalar uchun 40-80 mg har 8 soatda."
                    )
                ),
                homeAdvice = listOf("Dam olish", "Yengil ovqat", "Issiq suyuqlik ichish")
            ),
            MainData(
                category = "Ommaviy muammolar",
                problem = "Teri allergiyasi",
                description = "Teri allergiyasi – terining allergik reaksiyasi, qizarish, qichishish va toshma bilan namoyon bo‘ladi.",
                recommendedPills = listOf(
                    Pill(
                        "Loratadin",
                        "Antiallergik dori.",
                        "Kattalar uchun 10 mg kuniga bir marta."
                    ),
                    Pill(
                        "Setirizin",
                        "Allergiya belgilari uchun dori.",
                        "Kattalar uchun 10 mg kuniga bir marta."
                    )
                ),
                homeAdvice = listOf(
                    "Allergenlardan uzoq turish",
                    "Sovuq siqish",
                    "Teri namlantirish"
                )
            ),
            MainData(
                category = "Ommaviy muammolar",
                problem = "Tomoq og‘rig‘i",
                description = "Tomoq og‘rig‘i – tomoqdagi yallig‘lanish yoki infektsiya sababli yuzaga keladi.",
                recommendedPills = listOf(
                    Pill(
                        "Ibuprofen",
                        "Og‘riqni kamaytiruvchi va yallig‘lanishga qarshi dori.",
                        "Kattalar uchun 200–400 mg har 6 soatda."
                    ),
                    Pill(
                        "Paratsetamol",
                        "Og‘riq va isitmani kamaytiruvchi dori.",
                        "Kattalar uchun 500–1000 mg har 4–6 soatda."
                    )
                ),
                homeAdvice = listOf(
                    "Iliq choy ichish",
                    "Tomoqni tuzli suv bilan chayish",
                    "Dam olish"
                )
            ),
            MainData(
                category = "Ruhiy sog‘liq muammolari",
                problem = "Depressiya",
                description = "Depressiya — uzoq davom etadigan qayg‘u va ruhiy tushkunlik holati.",
                recommendedPills = listOf(
                    Pill(
                        "Fluoksetin",
                        "Antidepressant dori.",
                        "Kuniga 20 mg dan boshlanadi, shifokor ko‘rsatmasi bo‘yicha."
                    ),
                    Pill(
                        "Sertralin",
                        "Antidepressant dori.",
                        "Kuniga 50 mg dan boshlanadi, shifokor ko‘rsatmasi bo‘yicha."
                    )
                ),
                homeAdvice = listOf(
                    "Jismoniy mashqlar qilish",
                    "Do‘stlar bilan muloqot",
                    "Professional yordamga murojaat qilish"
                )
            ),
            MainData(
                category = "Ruhiy sog‘liq muammolari",
                problem = "Stress",
                description = "Stress — organizm va ongning kuchli bosim va zo‘riqish holati.",
                recommendedPills = listOf(
                    Pill(
                        "Valeriana ekstrakti",
                        "Tinchlantiruvchi dori.",
                        "Kuniga 1-2 marta 300 mg."
                    ),
                    Pill(
                        "Magniy preparatlari",
                        "Stressni kamaytiruvchi minerallar.",
                        "Kuniga 100-300 mg."
                    )
                ),
                homeAdvice = listOf("Yengil mashqlar", "Nafas olish mashqlari", "Yetarli uxlash")
            ),
            MainData(
                category = "Ruhiy sog‘liq muammolari",
                problem = "Uyqu buzilishlari",
                description = "Uyqu buzilishlari — uxlashning sifat va miqdoriga ta’sir qiluvchi holatlar.",
                recommendedPills = listOf(
                    Pill("Melatonin", "Uyquni tartibga soluvchi dori.", "Kechqurun 1-3 mg."),
                    Pill("Diphenhidramin", "Uyqu dori.", "Kattalar uchun 25-50 mg kechqurun.")
                ),
                homeAdvice = listOf(
                    "Yotishdan oldin og‘ir ovqat yemang",
                    "Yotish vaqtini belgilash",
                    "Yorug‘lik va shovqinni kamaytirish"
                )
            ),

            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Gastrit",
                description = "Gastrit — oshqozon shilliq qavatining yallig‘lanishi.",
                recommendedPills = listOf(
                    Pill(
                        "Omeprazol",
                        "Oshqozon kislotasini kamaytiruvchi dori.",
                        "Kuniga 20 mg, ovqatdan oldin."
                    ),
                    Pill(
                        "Ranitidin",
                        "Oshqozon kislotasini kamaytiruvchi dori.",
                        "Kuniga 150 mg, kuniga 2 marta."
                    )
                ),
                homeAdvice = listOf(
                    "Achchiq va yog‘li ovqatlardan saqlanish",
                    "Ko‘p suyuqlik ichish",
                    "Chekish va spirtli ichimliklardan voz kechish"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Hazmsizlik (Dispepsiya)",
                description = "Hazmsizlik — ovqat hazm bo‘lishida qiyinchiliklar va noqulaylik hissi.",
                recommendedPills = listOf(
                    Pill("Mezim", "Ovqat hazm qilish fermentlari.", "Ovqatdan keyin 1-2 tabletka."),
                    Pill(
                        "Pancreatin",
                        "Ovqat hazm qilish fermentlari.",
                        "Ovqatdan keyin 1-2 tabletka."
                    )
                ),
                homeAdvice = listOf(
                    "Kichik porsiyalarda tez-tez ovqatlanish",
                    "Og‘ir va yog‘li taomlardan saqlanish",
                    "Ko‘p suv ichish"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Qorin og‘rig‘i",
                description = "Qorin og‘rig‘i — turli sabablar bilan yuzaga keladigan noqulaylik.",
                recommendedPills = listOf(
                    Pill("Espumizan", "Gazni yo‘qotuvchi dori.", "Ovqatdan keyin 2-3 tabletka."),
                    Pill("No-shpa", "Spazmolitik dori.", "Kuniga 2-3 marta 40 mg.")
                ),
                homeAdvice = listOf(
                    "Yengil ovqatlanish",
                    "Oziq-ovqatni yaxshi chaynash",
                    "Stressni kamaytirish"
                )
            ),


            MainData(
                category = "Allergiya",
                problem = "Teridagi allergiya",
                description = "Terida qizarish, qichishish, shishish kabi allergik reaksiyalar.",
                recommendedPills = listOf(
                    Pill("Loratadin", "Allergiyaga qarshi dori.", "Kuniga 10 mg, 1 marta."),
                    Pill("Cetirizin", "Allergiyaga qarshi dori.", "Kuniga 10 mg, 1 marta.")
                ),
                homeAdvice = listOf(
                    "Allergenlardan uzoqroq bo‘lish",
                    "Terini toza va nam saqlash",
                    "Sovuq kompress qo‘yish"
                )
            ),
            MainData(
                category = "Allergiya",
                problem = "Burun allergiyasi (Hayfaringit)",
                description = "Burun yo‘llarining allergik yallig‘lanishi, burun bitishi va hapşırık.",
                recommendedPills = listOf(
                    Pill("Loratadin", "Allergiyaga qarshi dori.", "Kuniga 10 mg, 1 marta."),
                    Pill("Fexofenadin", "Allergiyaga qarshi dori.", "Kuniga 180 mg, 1 marta.")
                ),
                homeAdvice = listOf(
                    "Uy sharoitida changni kamaytirish",
                    "Allergenlardan uzoqroq bo‘lish",
                    "Burunni nam saqlash"
                )
            ),
            MainData(
                category = "Allergiya",
                problem = "Oshqozon allergiyasi",
                description = "Ovqat hazm qilish tizimining allergik reaksiyasi.",
                recommendedPills = listOf(
                    Pill("Ranitidin", "Allergiyaga qarshi dori.", "Kuniga 150 mg, 2 marta."),
                    Pill("Famotidin", "Allergiyaga qarshi dori.", "Kuniga 20 mg, 2 marta.")
                ),
                homeAdvice = listOf(
                    "Allergen bo‘lgan ovqatlardan voz kechish",
                    "Kichik porsiyalarda ovqatlanish",
                    "Ko‘p suv ichish"
                )
            ),

            MainData(
                category = "Qon bosimi muammolari",
                problem = "Yuqori qon bosimi (Gipertoniya)",
                description = "Qon bosimi me’yordan yuqori bo‘lib, bosh og‘rig‘i, bosh aylanishi va yurak urishining tezlashishi bilan kechadi.",
                recommendedPills = listOf(
                    Pill(
                        "Enalapril",
                        "ACE inhibitörü, qon bosimini tushiradi.",
                        "Kuniga 5–20 mg, 1–2 marta."
                    ),
                    Pill(
                        "Amlodipin",
                        "Kalsiy kanali blokatori, qon bosimini pasaytiradi.",
                        "Kuniga 5–10 mg, 1 marta."
                    )
                ),
                homeAdvice = listOf(
                    "Tuz iste'molini kamaytirish",
                    "Doimiy jismoniy faollik",
                    "Stressni kamaytirish",
                    "Sog‘lom ovqatlanish"
                )
            ),
            MainData(
                category = "Qon bosimi muammolari",
                problem = "Past qon bosimi (Gipotoniya)",
                description = "Qon bosimi me’yordan past bo‘lib, holsizlik, bosh aylanishi va hushdan ketish holatlari kuzatiladi.",
                recommendedPills = listOf(
                    Pill("Midodrin", "Qon bosimini ko‘taradi.", "Kuniga 2.5–10 mg, 2–3 marta."),
                    Pill(
                        "Fludrokortizon",
                        "Suyuqlik tutib qolish orqali qon bosimini oshiradi.",
                        "Kuniga 0.1 mg, kerak bo‘lsa dozani oshirish mumkin."
                    )
                ),
                homeAdvice = listOf(
                    "Ko‘p suyuqlik ichish",
                    "Birdan turib ketmaslik",
                    "Tuzli mahsulotlarni iste’mol qilish",
                    "Elastik paypoq kiyish"
                )
            ),
            MainData(
                category = "Nafas olish tizimi muammolari",
                problem = "Bronxit",
                description = "Bronxial naylarning yallig‘lanishi, yo‘tal, balg‘am ajralishi va nafas olishda qiynalish bilan kechadi.",
                recommendedPills = listOf(
                    Pill(
                        "Ambroksol",
                        "Balg‘amni yupatadi va chiqarishni osonlashtiradi.",
                        "Kuniga 30 mg, 2–3 marta."
                    ),
                    Pill(
                        "Salbutamol",
                        "Bronxlarni kengaytiradi, nafasni yengillashtiradi.",
                        "Zaruratga qarab, kuniga 2–4 marta."
                    )
                ),
                homeAdvice = listOf(
                    "Issiq ichimliklar ichish",
                    "Nam havo ta’minlash",
                    "Chekishdan saqlanish",
                    "Dam olish"
                )
            ),
            MainData(
                category = "Nafas olish tizimi muammolari",
                problem = "Bronxial astma",
                description = "Bronxlarning surunkali yallig‘lanishi, hansirash, nafas siqilishi va yo‘tal bilan namoyon bo‘ladi.",
                recommendedPills = listOf(
                    Pill(
                        "Salbutamol (inhalator)",
                        "Bronxlarni kengaytiradi, tez yordam sifatida ishlatiladi.",
                        "Zarurat tug‘ilganda 1–2 marta bosiladi."
                    ),
                    Pill(
                        "Budesonid",
                        "Yallig‘lanishga qarshi inhalatsion preparat.",
                        "Kuniga 1–2 marta, doza shifokor tomonidan belgilanadi."
                    )
                ),
                homeAdvice = listOf(
                    "Allergenlardan saqlanish",
                    "Inhalatorni to‘g‘ri ishlatish",
                    "Doimiy dori nazorati",
                    "Sog‘lom turmush tarzi"
                )
            ),
            MainData(
                category = "Nafas olish tizimi muammolari",
                problem = "Pnevmoniya",
                description = "O‘pkalarning yallig‘lanishi, isitma, yo‘tal, ko‘krak og‘rig‘i va umumiy holsizlik bilan kechadi.",
                recommendedPills = listOf(
                    Pill(
                        "Amoksitsillin",
                        "Bakterial infeksiyalarga qarshi antibiotik.",
                        "Kuniga 500 mg, 3 marta."
                    ),
                    Pill(
                        "Azitromitsin",
                        "Keng ta’sir doirasiga ega antibiotik.",
                        "Kuniga 500 mg, 1 marta, 3–5 kun davomida."
                    )
                ),
                homeAdvice = listOf(
                    "Ko‘p suyuqlik ichish",
                    "Dam olish",
                    "Isitmani tushirish",
                    "Tibbiy nazorat ostida bo‘lish"
                )
            ),

            // Cardiovascular problems
            MainData(
                category = "Qon-tomir muammolari",
                problem = "Yuqori qon bosimi (gipertoniya)",
                description = "Qon bosimining me’yoridan baland bo‘lishi, bosh og‘rig‘i, bosh aylanishi, yurak urishining tezlashuvi bilan namoyon bo‘ladi.",
                recommendedPills = listOf(
                    Pill(
                        "Enalapril",
                        "Qon bosimini pasaytiradi.",
                        "Kuniga 5–20 mg, shifokor nazoratida."
                    ),
                    Pill(
                        "Amlodipin",
                        "Qon tomirlarni kengaytiradi, yurakka yukni kamaytiradi.",
                        "Kuniga 5–10 mg."
                    )
                ),
                homeAdvice = listOf(
                    "Tuz iste’molini kamaytirish",
                    "Sport bilan shug‘ullanish",
                    "Stressni kamaytirish",
                    "Vaznni me’yorda saqlash"
                )
            ),
            MainData(
                category = "Qon-tomir muammolari",
                problem = "Past qon bosimi (gipotoniya)",
                description = "Qon bosimining me’yoridan past bo‘lishi, holsizlik, bosh aylanishi, hushdan ketishga moyillik bilan kechadi.",
                recommendedPills = listOf(
                    Pill(
                        "Midodrin",
                        "Qon bosimini oshiradi.",
                        "Shifokor ko‘rsatmasiga ko‘ra kuniga bir necha marta."
                    ),
                    Pill(
                        "Kofeinli preparatlar",
                        "Markaziy asab tizimini stimulyatsiya qiladi.",
                        "Zaruratga qarab."
                    )
                ),
                homeAdvice = listOf(
                    "Ko‘proq suyuqlik ichish",
                    "Tuzli ovqatlar yeyish",
                    "Sekin o‘rnashish",
                    "Doimiy jismoniy faollik"
                )
            ),
            MainData(
                category = "Qon-tomir muammolari",
                problem = "Varikoz (qon tomirlar kengayishi)",
                description = "Oyoqlarda tomirlarning kengayishi, shish, og‘riq, og‘irlik hissi bilan namoyon bo‘ladi.",
                recommendedPills = listOf(
                    Pill(
                        "Detraleks",
                        "Qon aylanishini yaxshilaydi, tomirlarni mustahkamlaydi.",
                        "Kuniga 1–2 tabletka."
                    ),
                    Pill("Troxevasin", "Tomir devorini mustahkamlaydi.", "Kuniga 2 kapsula.")
                ),
                homeAdvice = listOf(
                    "Oyoqlarni baland joyda dam oldirish",
                    "Siqish paypoqlaridan foydalanish",
                    "Uzoq tik turgan holatda qolmaslik",
                    "Yurish va harakatni oshirish"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Gastrit",
                description = "Me’da shilliq qavatining yallig‘lanishi, oshqozon og‘rig‘i, ko‘ngil aynishi, qusish bilan kechadi.",
                recommendedPills = listOf(
                    Pill(
                        "Omeprazol",
                        "Oshqozon kislotasini kamaytiradi.",
                        "Kuniga 1 tabletka, ovqatdan oldin."
                    ),
                    Pill(
                        "Ranitidin",
                        "Oshqozon kislotasini bostiradi.",
                        "Kuniga 150 mg, kechqurun."
                    )
                ),
                homeAdvice = listOf(
                    "Kislotali va achchiq ovqatlardan voz kechish",
                    "Ko‘p va kichik porsiyalar bilan ovqatlanish",
                    "Spirtli ichimliklardan tiyilish"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Oshqozon yarasi",
                description = "Oshqozon devorida yaralar paydo bo‘lishi, qattiq og‘riq va noqulaylik bilan namoyon bo‘ladi.",
                recommendedPills = listOf(
                    Pill(
                        "Almagel",
                        "Oshqozon kislotasini neytrallashtiradi.",
                        "Har ovqatdan keyin."
                    ),
                    Pill("Sucralfate", "Yarani qoplash va himoya qilish uchun.", "Kuniga 4 marta.")
                ),
                homeAdvice = listOf(
                    "Tuz va achchiq ovqatlardan cheklanish",
                    "Chekishni tashlash",
                    "Stressni kamaytirish"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Qorin bo‘shlig‘i shishishi (meteorizm)",
                description = "Qorin hududida gaz to‘planishi va shishish, noqulaylik va og‘riq bilan kechadi.",
                recommendedPills = listOf(
                    Pill(
                        "Simetikon",
                        "Gazni kamaytiradi va shishishni oldini oladi.",
                        "Ovqatdan keyin."
                    ),
                    Pill("Espumisan", "Gazni chiqarishga yordam beradi.", "Kuniga 2-3 marta.")
                ),
                homeAdvice = listOf(
                    "Gaz hosil qiluvchi ovqatlardan saqlanish",
                    "Yaxshi chaynalgan ovqat yeyish",
                    "Jismoniy faollikni oshirish"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Ich qotishi (kabızlık)",
                description = "Ichaklarning sekin harakatlanishi natijasida qattiq va kam siydik chiqarish bilan bog‘liq muammo.",
                recommendedPills = listOf(
                    Pill("Dulkolaks", "Ichaklarni rag‘batlantiradi.", "Kuniga 5-10 mg."),
                    Pill("Laktuloza", "Ichak harakatini yaxshilaydi.", "Kuniga 15-30 ml.")
                ),
                homeAdvice = listOf(
                    "Ko‘p suyuqlik ichish",
                    "To‘yimli ovqatlanish va tolaga boy mahsulotlar yeyish",
                    "Jismoniy faollik"
                )
            ),
            MainData(
                category = "Ovqat hazm qilish muammolari",
                problem = "Diareya",
                description = "Ich ketishi, suyuq najas tez-tez chiqarilishi bilan namoyon bo‘ladi.",
                recommendedPills = listOf(
                    Pill("Loperamid", "Ich ketishni kamaytiradi.", "Shifokor tavsiyasiga ko‘ra."),
                    Pill("Smekta", "Ichakni tozalashga yordam beradi.", "Kuniga 3 marta.")
                ),
                homeAdvice = listOf(
                    "Ko‘p miqdorda suyuqlik ichish",
                    "Yengil va kam yog‘li ovqatlar yeyish",
                    "Antibiotiklarni faqat shifokor nazorati ostida qabul qilish"
                )
            ),

            // Skin problems
            MainData(
                category = "Teri muammolari",
                problem = "Akne (chandiq)",
                description = "Yuzda yoki tananing boshqa joylarida paydo bo‘ladigan yiringli yoki qora nuqtalar bilan ifodalanadigan teri kasalligi.",
                recommendedPills = listOf(
                    Pill(
                        "Benzoyl peroksid",
                        "Bakteriyalarni yo‘q qiladi va yallig‘lanishni kamaytiradi.",
                        "Kuniga 1-2 marta, muammoli joylarga surtiladi."
                    ),
                    Pill(
                        "Azelain kislotasi",
                        "Yallig‘lanishni pasaytiradi va pigmentatsiyani kamaytiradi.",
                        "Kuniga 2 marta."
                    )
                ),
                homeAdvice = listOf(
                    "Teringizni muntazam tozalang",
                    "Yog‘li ovqatlardan cheklaning",
                    "Yuzga qo‘l bilan tegmaslik"
                )
            ),
            MainData(
                category = "Teri muammolari",
                problem = "Dermatit",
                description = "Teri qizarishi, qichishish va yallig‘lanish bilan kechuvchi teri kasalligi.",
                recommendedPills = listOf(
                    Pill(
                        "Gidrokortizon kremi",
                        "Yallig‘lanishni kamaytiradi.",
                        "Kuniga 1-2 marta, teriga surtiladi."
                    ),
                    Pill(
                        "Antihistaminlar",
                        "Qichishishni kamaytiradi.",
                        "Shifokor tavsiyasiga ko‘ra."
                    )
                ),
                homeAdvice = listOf(
                    "Allergenlardan saqlanish",
                    "Teringizni namlab turish",
                    "Issiq suv bilan yuvinishdan tiyilish"
                )
            ),
            MainData(
                category = "Teri muammolari",
                problem = "Psoriaz",
                description = "Teri hujayralarining tez ko‘payishi natijasida yuzaga keladigan qizarish va qalinlashgan teri joylari.",
                recommendedPills = listOf(
                    Pill(
                        "Kalsipotriol kremi",
                        "Teri hujayralarining ko‘payishini kamaytiradi.",
                        "Kuniga 1-2 marta."
                    ),
                    Pill(
                        "Metotreksat",
                        "Yallig‘lanishni kamaytiruvchi dori.",
                        "Shifokor nazoratida qabul qilinadi."
                    )
                ),
                homeAdvice = listOf(
                    "Stressni kamaytirish",
                    "Namlikni saqlash",
                    "Qo‘pol va qichishuvchi kiyimlardan voz kechish"
                )
            ),
            MainData(
                category = "Teri muammolari",
                problem = "Allergik toshma",
                description = "Teri yuzasida qizarish, qichishish va shishish bilan kechuvchi allergik reaksiya.",
                recommendedPills = listOf(
                    Pill(
                        "Antihistaminlar",
                        "Allergiya simptomlarini kamaytiradi.",
                        "Kuniga 1 marta."
                    ),
                    Pill(
                        "Kortikosteroid krem",
                        "Yallig‘lanishni kamaytiradi.",
                        "Teri yuzasiga surtiladi."
                    )
                ),
                homeAdvice = listOf(
                    "Allergenlardan uzoq turish",
                    "Teringizni quritmaslik",
                    "Sovun va kimyoviy moddalardan ehtiyot bo‘lish"
                )
            ),
            MainData(
                category = "Teri muammolari",
                problem = "Yiringli yaralar",
                description = "Teri ostida yiring to‘planishi bilan yuzaga keladigan yallig‘lanishli yaralar.",
                recommendedPills = listOf(
                    Pill(
                        "Antibiotiklar (shifokor tavsiyasiga ko‘ra)",
                        "Yiringli infeksiyani davolaydi.",
                        "Belgilangan dozada."
                    ),
                    Pill("Analjeziklar", "Og‘riqni kamaytiradi.", "Zaruratga qarab.")
                ),
                homeAdvice = listOf(
                    "Teri tozaligiga e’tibor berish",
                    "Yaralarni ifloslanishdan himoya qilish",
                    "Shifokorga murojaat qilish"
                )
            ),

            // Metabolic problems
            MainData(
                category = "Metabolik muammolar",
                problem = "Qandli diabet",
                description = "Qondagi shakar miqdorining yuqoriligi bilan tavsiflanadigan surunkali kasallik.",
                recommendedPills = listOf(
                    Pill(
                        "Metformin",
                        "Qon shakarini pasaytiradi.",
                        "Kuniga 1-2 marta ovqatdan keyin."
                    ),
                    Pill(
                        "Insulin",
                        "Qondagi shakarni tartibga soladi.",
                        "Shifokor nazoratida dozalanadi."
                    )
                ),
                homeAdvice = listOf(
                    "Sog‘lom ovqatlanish",
                    "Jismoniy faollikni oshirish",
                    "Qon shakarini muntazam tekshirish"
                )
            ),
            MainData(
                category = "Metabolik muammolar",
                problem = "Qandli diabet ketoasidozi",
                description = "Qondagi qand darajasining juda yuqori bo‘lib, organizmda kislotali moddalarning ko‘payishi.",
                recommendedPills = listOf(
                    Pill(
                        "Insulin",
                        "Qon shakarini tez pasaytiradi.",
                        "Tezkor tibbiy yordam talab etiladi."
                    ),
                    Pill(
                        "Elektrolitlar",
                        "Tana suyuqliklarini muvozanatda saqlaydi.",
                        "Shifokor nazoratida."
                    )
                ),
                homeAdvice = listOf(
                    "Darhol shifokorga murojaat qilish",
                    "Suyuqlik ichishni ko‘paytirish",
                    "Tibbiy nazoratda bo‘lish"
                )
            ),
            MainData(
                category = "Metabolik muammolar",
                problem = "Qandli diabetning asoratlari",
                description = "Diabet tufayli yuzaga keladigan yurak-qon tomir kasalliklari, buyrak yetishmovchiligi va boshqalar.",
                recommendedPills = listOf(
                    Pill(
                        "Antihipertenzivlar",
                        "Qon bosimini tartibga soladi.",
                        "Shifokor ko‘rsatmasi bo‘yicha."
                    ),
                    Pill("Statinlar", "Xolesterin darajasini kamaytiradi.", "Kuniga 1 marta.")
                ),
                homeAdvice = listOf(
                    "Qon bosimini va xolesterin darajasini nazorat qilish",
                    "Sog‘lom turmush tarzini olib borish",
                    "Doimiy tibbiy ko‘rikdan o‘tish"
                )
            ),
            MainData(
                category = "Metabolik muammolar",
                problem = "Gipotiroidizm",
                description = "Qalqonsimon bezning yetarlicha gormon ishlab chiqarmasligi natijasida metabolizm sekinlashishi.",
                recommendedPills = listOf(
                    Pill(
                        "Levotiroksin",
                        "Qalqonsimon bez gormonlarini almashtiradi.",
                        "Kuniga bir marta bo‘sh qoring‘a qabul qilinadi."
                    )
                ),
                homeAdvice = listOf(
                    "Muntazam tibbiy nazorat",
                    "Sog‘lom ovqatlanish",
                    "Jismoniy faollikni saqlash"
                )
            ),
            MainData(
                category = "Metabolik muammolar",
                problem = "Giperterioz",
                description = "Qalqonsimon bezning gormonlarini haddan tashqari ko‘p ishlab chiqarishi natijasida metabolizm tezlashishi.",
                recommendedPills = listOf(
                    Pill(
                        "Beta-blokerlar",
                        "Yurak urishini sekinlashtiradi.",
                        "Shifokor tavsiyasiga ko‘ra."
                    ),
                    Pill(
                        "Antitiroyd dori preparatlari",
                        "Qalqonsimon bez faoliyatini bostiradi.",
                        "Dozani shifokor belgilaydi."
                    )
                ),
                homeAdvice = listOf(
                    "Stressni kamaytirish",
                    "Yod iste’molini nazorat qilish",
                    "Doimiy shifokor nazorati"
                )
            ),
            MainData(
                category = "Suyak va mushak muammolari",
                problem = "Osteoporoz",
                description = "Suyaklarning zichligi pasayib, ularning sindirish xavfi oshadi.",
                recommendedPills = listOf(
                    Pill("Kalsiy", "Suyaklarni mustahkamlaydi.", "Kuniga 1-2 marta ovqat bilan."),
                    Pill("Vitamin D", "Kalsiyning so‘rilishini oshiradi.", "Kuniga bir marta.")
                ),
                homeAdvice = listOf(
                    "Sog‘lom ovqatlanish",
                    "Jismoniy faollikni saqlash",
                    "Quyosh nurida ko‘proq bo‘lish"
                )
            ),
            MainData(
                category = "Suyak va mushak muammolari",
                problem = "Artrit",
                description = "Suyaklarning va bo‘g‘imlarning yallig‘lanishi, og‘riq va shish paydo bo‘lishi.",
                recommendedPills = listOf(
                    Pill(
                        "NSAID",
                        "Yallig‘lanishni kamaytiradi va og‘riqni yengillashtiradi.",
                        "Shifokor tavsiyasiga ko‘ra."
                    ),
                    Pill(
                        "Kortikosteroidlar",
                        "Qattiq yallig‘lanish holatida qo‘llanadi.",
                        "Qisqa muddatli foydalanish."
                    )
                ),
                homeAdvice = listOf(
                    "Bo‘g‘imlarni himoya qilish",
                    "Mushaklarni mustahkamlash uchun mashqlar",
                    "Sog‘lom vaznni saqlash"
                )
            ),
            MainData(
                category = "Suyak va mushak muammolari",
                problem = "Mushaklarning spazmi",
                description = "Mushaklarning birdan va noxush qisqarishi, og‘riq bilan birga.",
                recommendedPills = listOf(
                    Pill(
                        "Mushak gevşeticiler",
                        "Mushaklarni bo‘shashtiradi.",
                        "Shifokor ko‘rsatmasiga binoan."
                    ),
                    Pill("Og‘riq qoldiruvchi dorilar", "Og‘riqni kamaytiradi.", "Zaruratga qarab.")
                ),
                homeAdvice = listOf(
                    "Issiq kompreslar qo‘llash",
                    "Yumshoq cho‘zish mashqlari",
                    "Yetarli suyuqlik ichish"
                )
            ),
            MainData(
                category = "Suyak va mushak muammolari",
                problem = "Bel og‘rig‘i",
                description = "Orqa sohadagi og‘riq, ko‘pincha mushak yoki suyak muammolari bilan bog‘liq.",
                recommendedPills = listOf(
                    Pill("Og‘riq qoldiruvchi dorilar", "Og‘riqni kamaytiradi.", "Zaruratga qarab."),
                    Pill(
                        "Yallig‘lanishga qarshi dorilar",
                        "Yallig‘lanishni kamaytiradi.",
                        "Shifokor tavsiyasiga ko‘ra."
                    )
                ),
                homeAdvice = listOf(
                    "Tana holatini to‘g‘ri saqlash",
                    "Muntazam jismoniy mashqlar",
                    "Issiq vannalar qabul qilish"
                )
            ),
            MainData(
                category = "Suyak va mushak muammolari",
                problem = "Mushaklarning zaifligi",
                description = "Mushaklarning kuchsizlanishi, harakatda qiyinchiliklar.",
                recommendedPills = listOf(
                    Pill(
                        "Vitamin B kompleks",
                        "Mushaklarning funksiyasini yaxshilaydi.",
                        "Kuniga bir marta."
                    ),
                    Pill(
                        "Protein qo‘shimchalari",
                        "Mushaklarni mustahkamlaydi.",
                        "Ovqat bilan birga."
                    )
                ),
                homeAdvice = listOf(
                    "Muntazam mashqlar bajarish",
                    "Sog‘lom va balanslangan ovqatlanish",
                    "Yetarli dam olish"
                )
            )
        )

        else -> listOf()
    }
}
