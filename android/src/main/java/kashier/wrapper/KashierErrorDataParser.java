package kashier.wrapper;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import io.kashier.sdk.Core.model.Response.Error.ErrorData;
import io.kashier.sdk.Core.model.validation.ValidationResult;

//TODO: Proper Documentation for the available ENUMS
enum KASHIER_ERROR_KEY {
    errorType, //ENUM
    errorMessage,
    networkErrorCode,
    responseStatus, //ENUM
    exceptionErrorMessage,
    validationErrors, //MAP
    validationErrorMessage
}

enum KASHIER_VALIDATION_ERROR {
    validationField, //ENUM
    isValid,
    errorMessage,
    validationErrorCode //ENUM
}

class KashierErrorDataParser {
    static <T> WritableMap parseError(ErrorData<T> errorData) {
        WritableMap errorDataMap = new WritableNativeMap();
        String _errorType = "";
        String _errorMessage = "";
        String _networkErrorCode = "";
        String _responseStatus = "";
        String _exceptionErrorMessage = "";
        String _validationErrorMessage = "";
        WritableArray _validationErrors = new WritableNativeArray();

        if (errorData.getErrorType() != null) {
            _errorType = errorData.getErrorType().toString();
        }
        if (errorData.getErrorMessage() != null) {
            _errorMessage = errorData.getErrorMessage();
        }
        if (errorData.getNetworkErrorCode() != null) {
            _networkErrorCode = errorData.getNetworkErrorCode();
        }
        if (errorData.getResponseStatus() != null) {
            _responseStatus = errorData.getResponseStatus().toString();
        }
        if (errorData.getExceptionErrorMessage() != null) {
            _exceptionErrorMessage = errorData.getExceptionErrorMessage();
        }
        if (errorData.getValidationErrorsMessage() != null) {
            _validationErrorMessage = errorData.getValidationErrorsMessage();
        }

        if (errorData.getValidationErrors().size() > 0) {
            for (ValidationResult validationError : errorData.getValidationErrors()) {
                String _validationField = "";
                String _validationErrorCode = "";
                String _validationErrMessage = "";
                boolean _isValid;

                WritableMap _validationErrorData = new WritableNativeMap();

                if (validationError.getValidationField() != null) {
                    _validationField = validationError.getValidationField().toString();
                }
                if (validationError.getValidationErrorCode() != null) {
                    _validationErrorCode = validationError.getValidationErrorCode().toString();
                }
                if (validationError.getErrorMessage() != null) {
                    _validationErrMessage = validationError.getErrorMessage();
                }

                _isValid = validationError.isValid();

                _validationErrorData.putString(KASHIER_VALIDATION_ERROR.validationField.toString(), _validationField);
                _validationErrorData.putString(KASHIER_VALIDATION_ERROR.validationErrorCode.toString(), _validationErrorCode);
                _validationErrorData.putString(KASHIER_VALIDATION_ERROR.errorMessage.toString(), _validationErrMessage);
                _validationErrorData.putBoolean(KASHIER_VALIDATION_ERROR.isValid.toString(), _isValid);

                _validationErrors.pushMap(_validationErrorData);
            }
        }

        errorDataMap.putString(KASHIER_ERROR_KEY.errorType.toString(), _errorType);
        errorDataMap.putString(KASHIER_ERROR_KEY.errorMessage.toString(), _errorMessage);
        errorDataMap.putString(KASHIER_ERROR_KEY.networkErrorCode.toString(), _networkErrorCode);
        errorDataMap.putString(KASHIER_ERROR_KEY.responseStatus.toString(), _responseStatus);
        errorDataMap.putString(KASHIER_ERROR_KEY.exceptionErrorMessage.toString(), _exceptionErrorMessage);
        errorDataMap.putString(KASHIER_ERROR_KEY.validationErrorMessage.toString(), _validationErrorMessage);

        errorDataMap.putArray(KASHIER_ERROR_KEY.validationErrors.toString(), _validationErrors);

        return errorDataMap;
    }
}
