package com.appsdeviser.component.components.textfield

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CustomTextFieldValidatorsTest {
    @Test
    fun email_acceptsValidAndRejectsInvalidFormats() {
        assertEquals(CustomFieldValidationResult.Valid, DefaultFieldValidators.email().validate("dev@example.com"))
        assertTrue(DefaultFieldValidators.email().validate("dev@invalid") is CustomFieldValidationResult.Invalid)
        assertTrue(DefaultFieldValidators.email().validate("dev example.com") is CustomFieldValidationResult.Invalid)
    }

    @Test
    fun phone_requiresExactlyTenDigitsButAllowsFormatting() {
        assertEquals(CustomFieldValidationResult.Valid, DefaultFieldValidators.phone().validate("(416) 555-0199"))
        assertTrue(DefaultFieldValidators.phone().validate("416-555") is CustomFieldValidationResult.Invalid)
        assertTrue(DefaultFieldValidators.phone().validate("1-416-555-0199") is CustomFieldValidationResult.Invalid)
    }

    @Test
    fun passwordRequiresLengthUppercaseAndNumber() {
        val validator = DefaultFieldValidators.password()
        assertEquals(CustomFieldValidationResult.Valid, validator.validate("Compose9"))
        assertTrue(validator.validate("password9") is CustomFieldValidationResult.Invalid)
        assertTrue(validator.validate("PasswordOnly") is CustomFieldValidationResult.Invalid)
        assertTrue(validator.validate("Short1") is CustomFieldValidationResult.Invalid)
    }

    @Test
    fun validatorUsesCallerProvidedLocalizedMessage() {
        assertEquals(
            CustomFieldValidationResult.Invalid("Enter a valid email address."),
            DefaultFieldValidators.email().validate("invalid"),
        )
    }

    @Test
    fun defaultVariantHasNoBuiltInValidator() {
        assertNull(DefaultFieldValidators.forVariant(CustomTextFieldVariant.Default))
    }

    @Test
    fun externalValidatorCanImplementAppSpecificPolicy() {
        val accountCodeValidator =
            CustomFieldValidator { value ->
                if (Regex("^VALID-\\d{4}$").matches(value)) {
                    CustomFieldValidationResult.Valid
                } else {
                    CustomFieldValidationResult.Invalid("Use the format VALID-1234.")
                }
            }

        assertEquals(
            CustomFieldValidationResult.Valid,
            accountCodeValidator.validate("VALID-1234"),
        )
        assertEquals(
            CustomFieldValidationResult.Invalid("Use the format VALID-1234."),
            accountCodeValidator.validate("ABC"),
        )
    }
}
