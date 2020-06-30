type Kashier_ErrorType =
  | "VALIDATION"
  | "DATA"
  | "EXCEPTION"
  | "NETWORK_NO_INTERNET"
  | "NETWORK_TIMEOUT"
  | "NETWORK_UNAUTHENTICATED"
  | "NETWORK_CLIENT_ERROR"
  | "NETWORK_SERVER_ERROR"
  | "NETWORK_UNEXPECTED_ERROR"
  | "JSON_PARSING_ERROR";

type KashierResponseStatus =
  | "UNKNOWN"
  | "SUCCESS"
  | "FAILURE"
  | "INVALID_REQUEST"
  | "PENDING"
  | "PENDING_ACTION";

type KashierErrorValidationField =
  | "CARD_HOLDER_NAME"
  | "CARD_NUMBER"
  | "CARD_CVV"
  | "CARD_EXPIRY_DATE"
  | "SHOPPER_REFERENCE"
  | "TOKEN_VALIDITY"
  | "ORDER_ID"
  | "AMOUNT"
  | "CARD_TOKEN"
  | "CVV_TOKEN";
type KashierError_ValidationError =
  | "NO_ERROR"
  | "CARD_HOLDER_NAME_REQUIRED"
  | "CARD_HOLDER_NAME_INVALID"
  | "CVV_REQUIRED"
  | "CVV_INVALID"
  | "EXPIRY_DATE_REQUIRED"
  | "EXPIRY_DATE_INVALID"
  | "CARD_NUMBER_REQUIRED"
  | "CARD_NUMBER_INVALID"
  | "SHOPPER_REFERENCE_REQUIRED"
  | "TOKEN_VALIDITY_REQUIRED"
  | "ORDER_ID_REQUIRED"
  | "AMOUNT_REQUIRED"
  | "AMOUNT_INVALID"
  | "CARD_TOKEN_REQUIRED"
  | "CVV_TOKEN_REQUIRED";

interface KashierValidationResult {
  isValid: boolean;
  errorMessage: string;
  validationField: KashierErrorValidationField;
  validationErrorCode: KashierError_ValidationError;
}

interface KashierError {
  errorType: Kashier_ErrorType;
  validationErrors: KashierValidationResult[];
  validationErrorMessage: string;
  responseStatus: KashierResponseStatus;
  networkErrorCode: string;
  errorMessage: string;
  exceptionErrorMessage: string;
}
export { KashierError ,KashierResponseStatus};
