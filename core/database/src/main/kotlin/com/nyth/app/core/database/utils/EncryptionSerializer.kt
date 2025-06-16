package com.nyth.app.core.database.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random

object EncryptionSerializer {
    private const val AES_KEY_SIZE = 32 // 256 bits
    private const val IV_SIZE = 12 // GCM recommended IV size
    private const val TRANSFORMATION = "AES/GCM/NoPadding"

    // You must store this securely (e.g. encrypted keystore or hardcoded for demo)
    private val secretKey: SecretKey = SecretKeySpec(
        ByteArray(AES_KEY_SIZE) { 0x01 }, // Replace with secure key management!
        "AES"
    )

    fun encrypt(plainText: String?): String {
        val iv = Random.Default.nextBytes(IV_SIZE)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec)
        val encrypted = cipher.doFinal(plainText?.toByteArray(Charsets.UTF_8))

        // Combine IV + encrypted data and base64 encode
        val combined = iv + encrypted
        return Base64.encodeToString(combined, Base64.NO_WRAP)
    }

    fun decrypt(cipherText: String): String {
        val combined = Base64.decode(cipherText, Base64.NO_WRAP)
        val iv = combined.sliceArray(0 until IV_SIZE)
        val encrypted = combined.sliceArray(IV_SIZE until combined.size)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        val decrypted = cipher.doFinal(encrypted)

        return String(decrypted, Charsets.UTF_8)
    }
}