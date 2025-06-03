package com.nyth.app.core.designsystem.ext

import android.content.Context
import androidx.core.util.PatternsCompat
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.ext.StringExt.isPhoneNumber
import com.nyth.app.core.model.local.enums.InputType
import com.nyth.app.core.model.remote.response.MetadataFormResponse
import com.nyth.app.core.model.utils.AppConstants.Companion.NO_ERROR_VALIDATOR
import java.util.Calendar

object ValidatorExt {
    fun InputType.validate(
        stringToValidate: String,
        isRequired: Boolean? = true,
        validationRules: List<MetadataFormResponse.ValidationRule?>? = null
    ): Int {
        if (stringToValidate.isEmpty()) {
            return if (isRequired == true) {
                R.string.required_field
            } else {
                NO_ERROR_VALIDATOR
            }
        }
        return when (this) {
            InputType.EMAIL -> {
                if (!isValidEmail(stringToValidate)) {
                    R.string.wrong_email_address
                } else {
                    NO_ERROR_VALIDATOR
                }
            }

            InputType.PHONE -> {
                if (!stringToValidate.isPhoneNumber()) {
                    R.string.wrong_phone_number
                } else {
                    NO_ERROR_VALIDATOR
                }
            }

            InputType.NUMBER -> {

                if (stringToValidate.toIntOrNull() == null) {
                    R.string.invalid_number
                }

                val maximumValue = validationRules?.find { it?.type == "Maximum" }?.value
                val minimumValue = validationRules?.find { it?.type == "Minimum" }?.value

                val wrappedValue = maximumValue?.count()?.let {
                    stringToValidate.take(it)
                } ?: stringToValidate

                wrappedValue.toIntOrNull()?.let { intValue ->
                    val maximumValidation =
                        maximumValue?.toIntOrNull()?.let { intValue <= it }.safeGet(true)
                    val minimumValidation =
                        minimumValue?.toIntOrNull()?.let { intValue >= it }.safeGet(true)

                    if (!maximumValidation) {
                        R.string.maximum_value_error
                    } else if (!minimumValidation) {
                        R.string.minimum_value_error
                    } else {
                        NO_ERROR_VALIDATOR
                    }
                } ?: R.string.invalid_number
            }

            else -> NO_ERROR_VALIDATOR
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        var isEmailValid = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

        if (isEmailValid) {
            val parts = email.toString().split("@".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            if (parts.size == 2) {
                val domain = parts[1]

                if (!isValidDomain(domain)) {
                    isEmailValid = false
                }
            } else {
                isEmailValid = false
            }
        }
        return isEmailValid
    }

    private fun isValidDomain(domain: String): Boolean {
        var dotCount = 0
        domain.forEach {
            if (it == '.') {
                dotCount++
            }
        }
        return dotCount == 1
    }

    fun InputType.validateAndShowError(
        context: Context,
        stringToValidate: String,
        isRequired: Boolean? = true,
        validationRules: List<MetadataFormResponse.ValidationRule?>? = null
    ): String? {
        if (stringToValidate.isEmpty()) {
            return if (isRequired == true) {
                context.getString(R.string.required_field)
            } else {
                null
            }
        }
        return when (this) {
            InputType.EMAIL -> {
                if (!isValidEmail(stringToValidate)) {
                    context.getString(R.string.wrong_email_address)
                } else {
                    null
                }
            }

            InputType.LISTING_NAME -> {
                if (stringToValidate.length < 5) {
                    context.getString(R.string.please_check_advert_name)
                } else {
                    null
                }
            }

            InputType.ACTIVITY_NAME -> {
                if (stringToValidate.length < 3) {
                    context.getString(R.string.please_check_advert_name)
                } else {
                    null
                }
            }

            InputType.CARD_NAME -> {
                if (stringToValidate.isEmpty()) {
                    context.getString(R.string.empy_card_name_error)
                } else {
                    null
                }
            }

            InputType.CARD_EXPIRATION_DATE -> {
                if (stringToValidate.length < 4) {
                    context.getString(R.string.payment_date_error)
                } else {
                    val expMonth = stringToValidate.substring(0, 2)
                    val expYear = "20" + stringToValidate.substring(2, 4)
                    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
                    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                    if (expYear.toInt() < currentYear || (expYear.toInt() == currentYear && expMonth.toInt() < currentMonth)) {
                        context.getString(R.string.payment_date_error)
                    } else {
                        null
                    }
                }
            }

            InputType.CARD_CVC -> {
                if (stringToValidate.length < 3) {
                    context.getString(R.string.payment_cvv_error)
                } else {
                    null
                }
            }

            InputType.LISTING_CONTACT_ISSUE_TITLE -> {
                if (stringToValidate.length < 10) {
                    context.getString(R.string.please_check_issue_title)
                } else {
                    null
                }
            }

            InputType.ACCOUNT_FIRST_NAME -> {
                if (stringToValidate.any { it.isDigit() }) {
                    null
                } else if (stringToValidate.length < 2) {
                    context.getString(R.string.please_check_account_firstname)
                } else {
                    null
                }
            }

            InputType.ACCOUNT_LAST_NAME -> {
                if (stringToValidate.any { it.isDigit() }) {
                    null
                } else if (stringToValidate.length < 2) {
                    context.getString(R.string.please_check_account_lastname)
                } else {
                    null
                }
            }

            InputType.ACCOUNT_USER_NAME -> {
                if (stringToValidate.length < 5) {
                    context.getString(R.string.please_check_account_username)
                } else if (!stringToValidate.matches("^[a-zA-Z0-9]*$".toRegex())) {
                    context.getString(R.string.please_check_account_username)
                } else if (stringToValidate.contains(" ")) {
                    context.getString(R.string.please_check_account_username)
                } else {
                    null
                }
            }

            InputType.LISTING_CONTACT_ISSUE_DESCRIPTION -> {
                if (stringToValidate.length < 20) {
                    context.getString(R.string.please_check_issue_desc)
                } else {
                    null
                }
            }

            InputType.LISTING_PRICE -> {
                val value = stringToValidate.toIntOrNull()
                if (value != null) {
                    if (value in 10..10000000) {
                        null
                    } else {
                        context.getString(R.string.please_check_advert_price)
                    }
                } else {
                    context.getString(R.string.please_check_advert_price)
                }
            }

            InputType.PHONE -> {
                if (!stringToValidate.isPhoneNumber()) {
                    context.getString(R.string.wrong_phone_number)
                } else {
                    null
                }
            }

            InputType.NUMBER -> {
                if (stringToValidate.toDoubleOrNull() == null) {
                    context.getString(R.string.invalid_number)
                }

                val maximumValue = validationRules?.find { it?.type == "Maximum" }?.value
                val minimumValue = validationRules?.find { it?.type == "Minimum" }?.value


                stringToValidate.toDoubleOrNull()?.let { doubleValue ->
                    val maximumValidation =
                        maximumValue?.toDoubleOrNull()?.let { doubleValue <= it }.safeGet(true)
                    val minimumValidation =
                        minimumValue?.toDoubleOrNull()?.let { doubleValue >= it }.safeGet(true)

                    if (!maximumValidation) {
                        context.getString(R.string.maximum_value_error, maximumValue)
                    } else if (!minimumValidation) {
                        context.getString(R.string.minimum_value_error, minimumValue)
                    } else {
                        null
                    }
                }
            }

            else -> null
        }
    }
}