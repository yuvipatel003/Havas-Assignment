package com.appsdeviser.component.components.textfield

fun interface CustomFieldValidator {
    fun validate(value: String): CustomFieldValidationResult
}

sealed interface CustomFieldValidationResult {
    data object Valid : CustomFieldValidationResult

    data class Invalid(
        val message: String,
    ) : CustomFieldValidationResult
}

object DefaultFieldValidators {
    private val emailPattern =
        Regex("^[A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)+$")

    fun email(): CustomFieldValidator =
        CustomFieldValidator { value ->
            if (emailPattern.matches(value.trim())) {
                CustomFieldValidationResult.Valid
            } else {
                CustomFieldValidationResult.Invalid("Enter a valid email address.")
            }
        }

    fun phone() =
        CustomFieldValidator { value ->
            if (value.count(Char::isDigit) == PHONE_NUMBER_DIGITS) {
                CustomFieldValidationResult.Valid
            } else {
                CustomFieldValidationResult.Invalid("Enter a complete 10-digit phone number.")
            }
        }

    fun password() =
        CustomFieldValidator { value ->
            val isValid =
                value.length >= MINIMUM_PASSWORD_LENGTH &&
                    value.any(Char::isUpperCase) &&
                    value.any(Char::isDigit)

            if (isValid) {
                CustomFieldValidationResult.Valid
            } else {
                CustomFieldValidationResult.Invalid("Use 8+ characters with an uppercase letter and a number")
            }
        }

    fun forVariant(variant: CustomTextFieldVariant): CustomFieldValidator? =
        when (variant) {
            CustomTextFieldVariant.Default -> null
            CustomTextFieldVariant.Email -> email()
            CustomTextFieldVariant.PhoneNumber -> phone()
            CustomTextFieldVariant.Password -> password()
        }

    private const val PHONE_NUMBER_DIGITS = 10
    private const val MINIMUM_PASSWORD_LENGTH = 8
}
