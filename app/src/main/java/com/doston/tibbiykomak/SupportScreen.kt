package com.doston.tibbiykomak

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doston.tibbiykomak.data.FAQItem
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
fun SupportScreen(navController: NavController,viewModel: ThemeViewModel) {
    val context = LocalContext.current
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val aColor = if (isDarkTheme) AColor else DAColor
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var expandedFAQ by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        containerColor = mainColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { navController.popBackStack() }
                        .clip(shape = CircleShape),
                    tint = textColor
                    
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.support_help),
                    color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold
                )

            }


            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = aColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.faq_title),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                val faqItems = listOf(
                    FAQItem(
                        question = context.getString(R.string.ilovadan_qanday_foydalanish_mumkin),
                        answer = context.getString(R.string.ilova_asosiy_sahifasida_turli_xil_tibbiy_muammolar_bo_yicha_ma_lumotlarni_topishingiz_mumkin_har_bir_bo_limni_tanlash_orqali_batafsil_ma_lumot_olishingiz_mumkin)
                    ),
                    FAQItem(
                        question = context.getString(R.string.dori_eslatmalarini_qanday_sozlash_mumkin),
                        answer = context.getString(R.string.eslatmalar_bo_limiga_o_ting_va_tugmasini_bosing_dori_nomini_vaqtini_va_davomiyligini_kiriting_ilova_avtomatik_ravishda_sizga_eslatma_beradi)
                    ),
                    FAQItem(
                        question = context.getString(R.string.ma_lumotlar_qanchalik_ishonchli),
                        answer = context.getString(R.string.barcha_ma_lumotlar_tibbiy_mutaxassislar_tomonidan_tekshirilgan_lekin_bu_ma_lumotlar_shifokor_maslahatini_almashtirmaydi)
                    ),
                    FAQItem(
                        question = context.getString(R.string.offline_rejimda_ishlay_olasizmi),
                        answer = context.getString(R.string.ha_asosiy_ma_lumotlar_offline_rejimda_mavjud_faqat_yangilanishlar_va_qo_shimcha_ma_lumotlar_uchun_internet_kerak)
                    ),
                    FAQItem(
                        question = context.getString(R.string.qanday_qilib_yordam_so_rash_mumkin),
                        answer = context.getString(R.string.ushbu_sahifadagi_forma_orqali_yoki_telegram_email_orqali_biz_bilan_bog_lanishingiz_mumkin_24_soat_ichida_javob_beramiz)
                    ),
                    FAQItem(
                        question = context.getString(R.string.ilova_bepulmi),
                        answer = context.getString(R.string.ha_ilova_to_liq_bepul_va_hech_qanday_yashirin_to_lovlar_yo_q_barcha_funksiyalar_bepul_foydalanish_uchun)
                    ),
                    FAQItem(
                        question = context.getString(R.string.ma_lumotlarim_xavfsizmi),
                        answer = context.getString(R.string.ha_barcha_shaxsiy_ma_lumotlaringiz_xavfsiz_saqlanadi_va_uchinchi_shaxslarga_berilmaydi)
                    ),
                    FAQItem(
                        question = context.getString(R.string.yangi_funksiyalar_qachon_qo_shiladi),
                        answer = context.getString(R.string.doimiy_ravishda_yangi_funksiyalar_qo_shib_boramiz_yangilanishlar_haqida_bildirishnomalar_orqali_xabar_beramiz)
                    )
                )

                items(faqItems.size) { index ->
                    FAQCard(
                        faq = faqItems[index],
                        isExpanded = expandedFAQ == index,
                        onToggle = { expandedFAQ = if (expandedFAQ == index) null else index },
                    
                        textColor = mainColor,
                        accentColor = mainColor,
                        backgroundColor = textColor
                    )
                }
                item {
                    ContactFormSection(
                        fullName = fullName,
                        onFullNameChange = { fullName = it },
                        email = email,
                        onEmailChange = { email = it },
                        message = message,
                        onMessageChange = { message = it },
                        onSendMessage = {
                            if (fullName.isNotBlank() && message.isNotBlank()) {
                                sendSupportMessage(context, fullName, email, message)
                                fullName=""
                                message=""
                                email=""
                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.fill_all_fields),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        textColor = textColor,
                        mainColor = mainColor,
                        textColor2 = textColor2
                    )
                }

                item {
                    ContactMethodsSection(
                        context = context,
                        textColor = textColor,
                        mainColor = mainColor
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
private fun FAQCard(
    faq: FAQItem,
    isExpanded: Boolean,
    onToggle: () -> Unit,
  
    textColor: Color,
    accentColor: Color,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = faq.question,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = if (isExpanded) "−" else "+",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor
                )
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = textColor.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = faq.answer,
                    fontSize = 14.sp,
                    color = textColor.copy(alpha = 0.8f),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
private fun ContactFormSection(
    fullName: String,
    onFullNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    message: String,
    onMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    textColor: Color,
    mainColor: Color,
    textColor2: Color
) {
    Column {
        Text(
            text = stringResource(R.string.still_need_help),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.send_us_message),
            fontSize = 14.sp,
            color = textColor.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = textColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = stringResource(R.string.contact_form),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = mainColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = fullName,
                    onValueChange = onFullNameChange,
                    label = {
                        Text(
                            stringResource(R.string.full_name),
                            color = mainColor.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = textColor2,
                        unfocusedBorderColor = mainColor.copy(alpha = 0.3f),
                        focusedTextColor = mainColor,
                        unfocusedTextColor = mainColor,
                        cursorColor = mainColor
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = {
                        Text(
                            stringResource(R.string.email_ixtiyoriy),
                            color = mainColor.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = textColor2,
                        unfocusedBorderColor = mainColor.copy(alpha = 0.3f),
                        focusedTextColor = mainColor,
                        unfocusedTextColor = mainColor,
                        cursorColor = mainColor
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = message,
                    onValueChange = onMessageChange,
                    label = {
                        Text(
                            stringResource(R.string.your_message),
                            color = mainColor.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = textColor2,
                        unfocusedBorderColor = mainColor.copy(alpha = 0.3f),
                        focusedTextColor = mainColor,
                        unfocusedTextColor = mainColor,
                        cursorColor = mainColor
                    ),
                    maxLines = 5
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onSendMessage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = mainColor),
                    shape = RoundedCornerShape(12.dp),
                    enabled = fullName.isNotBlank() && message.isNotBlank()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = textColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            stringResource(R.string.send_message),
                            color =textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactMethodsSection(
    context: Context,
    textColor: Color,
    mainColor: Color,
    
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = textColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.other_ways),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = mainColor
            )

            Spacer(modifier = Modifier.height(16.dp))
            ContactMethodItem(
                icon = Icons.Default.Email,
                title = stringResource(R.string.email_support),
                subtitle = "husanovdostonbek1010@gmail.com",
                textColor = mainColor,
                onClick = { sendEmail(context) }, mainColor = mainColor
            )

            Spacer(modifier = Modifier.height(12.dp))
            ContactMethodItem(
                icon = Icons.Default.Phone,
                title = stringResource(R.string.telegram),
                subtitle = "@Dostonbek_Husanov",
                textColor = mainColor,
                onClick = { openTelegram(context) }, mainColor = mainColor
            )
        }
    }
}

@Composable
private fun ContactMethodItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    textColor: Color,
    mainColor: Color,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            tint = mainColor
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = textColor.copy(alpha = 0.7f)
            )
        }
    }
}

private fun sendSupportMessage(context: Context, fullName: String, email: String, message: String) {
    val formattedMessage = buildString {
        appendLine("Name: $fullName")
        if (email.isNotBlank()) {
            appendLine("Email: $email")
        }
        appendLine("Message: $message")
    }

    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Support Message", formattedMessage)
    clipboard.setPrimaryClip(clip)

    sendToTelegram(context, formattedMessage)
}

private fun sendToTelegram(context: Context, message: String) {
    val encodedMessage = Uri.encode(message)

    try {
        val telegramUrl = "https://t.me/Dostonbek_Husanov?text=$encodedMessage"
        val telegramIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
        telegramIntent.setPackage("org.telegram.messenger")
        context.startActivity(telegramIntent)
        Toast.makeText(context, "Opening Telegram with your message...", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        try {
            val telegramUrl = "https://t.me/Dostonbek_Husanov?text=$encodedMessage"
            val telegramXIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
            telegramXIntent.setPackage("org.thunderdog.challegram")
            context.startActivity(telegramXIntent)
            Toast.makeText(context, "Opening Telegram with your message...", Toast.LENGTH_SHORT)
                .show()
        } catch (e2: Exception) {
            try {
                val telegramUrl = "https://t.me/Dostonbek_Husanov?text=$encodedMessage"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
                context.startActivity(browserIntent)
                Toast.makeText(
                    context,
                    "Opening Telegram Web with your message...",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e3: Exception) {
                Toast.makeText(
                    context,
                    "Message copied to clipboard. Please visit t.me/Dostonbek_Husanov and paste it manually.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

private fun openTelegram(context: Context) {
    try {
        val telegramUrl = "https://t.me/Dostonbek_Husanov"
        val telegramIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
        telegramIntent.setPackage("org.telegram.messenger")
        context.startActivity(telegramIntent)
    } catch (e: Exception) {
        try {
            val telegramUrl = "https://t.me/Dostonbek_Husanov"
            val telegramXIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
            telegramXIntent.setPackage("org.thunderdog.challegram")
            context.startActivity(telegramXIntent)
        } catch (e2: Exception) {
            val telegramUrl = "https://t.me/Dostonbek_Husanov"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
            context.startActivity(browserIntent)
        }
    }
}

private fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:husanovdostonbek1010@gmail.com")
        putExtra(Intent.EXTRA_SUBJECT, "Tibbiy Komak - Support Request")
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Email ilovasi topilmadi", Toast.LENGTH_SHORT).show()
    }
}