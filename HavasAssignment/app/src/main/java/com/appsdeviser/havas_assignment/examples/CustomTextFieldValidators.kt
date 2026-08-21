package com.appsdeviser.havas_assignment.examples

import com.appsdeviser.component.components.textfield.CustomFieldValidationResult
import com.appsdeviser.component.components.textfield.CustomFieldValidator

val AccountCodeValidator =
    CustomFieldValidator { value ->
        if (Regex("^VALID-\\d{4}$").matches(value)) {
            CustomFieldValidationResult.Valid
        } else {
            CustomFieldValidationResult.Invalid("Use the format such as VALID-1234.")
        }
    }
