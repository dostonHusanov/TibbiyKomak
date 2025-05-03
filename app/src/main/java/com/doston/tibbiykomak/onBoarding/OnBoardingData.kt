package com.doston.tibbiykomak.onBoarding

import com.doston.tibbiykomak.R

class OnBoardingData(val image:Int,val title:String,val desc:String)
val listData= listOf(OnBoardingData(
    image = R.drawable.people,
    title = "Xush kelibsiz!",
    desc = "Tibbiy Ko'mak ilovasi orqali sog‘lig‘ingiz haqida tez va foydali maslahatlar oling."
),OnBoardingData(
    image = R.drawable.people,
    title = "Barcha muammolar tartiblangan",
    desc = "Ruhiy, surunkali, bolalar, ayollar va boshqa sog‘liq muammolari qulay turkumlarda."
),OnBoardingData(
    image =R.drawable.people,
    title = "Belgilar orqali qidiring",
    desc = "Faqatgina muammoni yozing, sizga to‘g‘ri keladigan maslahatni topamiz."
),OnBoardingData(
    image = R.drawable.people,
    title = "Ishonchli maslahatlar",
    desc = "Shifokor maslahatiga asoslangan dorilar va uy sharoitidagi tavsiyalar."
),)