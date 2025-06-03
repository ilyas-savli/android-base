package com.nyth.app.core.designsystem.errorparser

import com.nyth.app.core.designsystem.R
import com.nyth.app.core.model.local.enums.ErrorMessages
import com.nyth.app.core.model.remote.response.ErrorResponse

class ErrorParser {

	enum class ErrorKey(var value: Int) {
		FIRST_NAME(R.string.general_error_message),
		LAST_NAME(R.string.general_error_message),
		EMAIL(R.string.general_error_message),
		PASSWORD(R.string.general_error_message),
		PASSWORD_CONFIRMATION(R.string.general_error_message);

		companion object {
			fun valueOfOrNull(name: String): ErrorKey? {
				return try {
					valueOf(name.uppercase())
				} catch (e: IllegalArgumentException) {
					null
				}
			}
		}
	}
	companion object {
		fun getKey(res: ErrorResponse?): String? {
			val newErrors = res?.errorList?.map { error ->
				error?.let {
					return@map errorRegexp.replace(error) { matchResult ->
						val key = matchResult.groupValues[1]
						key
					}
				}
			}
			return newErrors?.firstOrNull()
		}

		fun parse(res: ErrorResponse?, getString: (resId: Int) -> String): String? {
			val newErrors = res?.errorList?.map { error ->
				return@map error?.let {
					return@let ErrorMessages.find(it)?.message ?:
					 errorRegexp.replace(error) { matchResult ->
						val key = matchResult.groupValues[1]
						val errorKey = ErrorKey.valueOfOrNull(key)
						errorKey?.value?.let(getString) ?: matchResult.value
					}
				}
			}
			return newErrors?.firstOrNull()
		}
		private val errorRegexp = Regex("'([^'\\s]+)'")
	}
}