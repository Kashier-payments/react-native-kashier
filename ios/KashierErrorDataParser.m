//
//  KashierErrorDataParser.m
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//


#import "KashierErrorDataParser.h"

@implementation KashierErrorDataParser
+ (NSDictionary*) parseError: (ErrorData*) errorData{
	NSMutableDictionary *parsedErrorData = [[NSMutableDictionary alloc]init];
	
	NSString* errorType = @"";
	NSString* errorMessage = @"";
	NSString* networkErrorCode = @"";
	NSString* responseStatus = @"";
	NSString* exceptionErrorMessage = @"";
	NSString* validationErrorMessage = @"";
	
	NSMutableArray* validationErrors = [[NSMutableArray alloc] init];
	
	if(errorData.errorType!= ERROR_TYPENO_VALUE){
		switch (errorData.errorType) {
			case ERROR_TYPEINITIALIZATION:
				errorType = @"INITIALIZATION";
				break;
			case ERROR_TYPEVALIDATION:
				errorType = @"VALIDATION";
				break;
			case ERROR_TYPEDATA:
				errorType = @"DATA";
				break;
			case ERROR_TYPEEXCEPTION:
				errorType = @"EXCEPTION";
				break;
			case ERROR_TYPENETWORK_ERROR:
				errorType = @"NETWORK_ERROR";
				break;
			case ERROR_TYPENETWORK_NO_INTERNET:
				errorType = @"NETWORK_NO_INTERNET";
				break;
			case ERROR_TYPENETWORK_TIMEOUT:
				errorType = @"NETWORK_TIMEOUT";
				break;
			case ERROR_TYPENETWORK_UNAUTHENTICATED:
				errorType = @"NETWORK_UNAUTHENTICATED";
				break;
			case ERROR_TYPENETWORK_CLIENT_ERROR:
				errorType = @"NETWORK_CLIENT_ERROR";
				break;
			case ERROR_TYPENETWORK_SERVER_ERROR:
				errorType = @"NETWORK_SERVER_ERROR";
				break;
			case ERROR_TYPENETWORK_UNEXPECTED_ERROR:
				errorType = @"NETWORK_UNEXPECTED_ERROR";
				break;
			case ERROR_TYPEJSON_PARSING_ERROR:
				errorType = @"JSON_PARSING_ERROR";
				break;
			default:
				break;
		}
	}
	
	if([KashierUtils isNonEmptyString:errorData.errorMessage]){
		errorMessage = errorData.errorMessage;
	}
	
	if([KashierUtils isNonEmptyString:errorData.networkErrorCode]){
		networkErrorCode = errorData.networkErrorCode;
	}
	
	if(errorData.responseStatus!=KASHIER_RESPONSE_STATUSNO_VALUE){
		switch (errorData.responseStatus) {
			case KASHIER_RESPONSE_STATUSUNKNOWN:
				responseStatus = @"UNKNOWN";
				break;
			case KASHIER_RESPONSE_STATUSSUCCESS:
				responseStatus = @"SUCCESS";
				break;
			case KASHIER_RESPONSE_STATUSFAILURE:
				responseStatus = @"FAILURE";
				break;
			case KASHIER_RESPONSE_STATUSINVALID_REQUEST:
				responseStatus = @"INVALID_REQUEST";
				break;
			case KASHIER_RESPONSE_STATUSPENDING:
				responseStatus = @"PENDING";
				break;
			case KASHIER_RESPONSE_STATUSPENDING_ACTION:
				responseStatus = @"PENDING_ACTION";
				break;
				
			default:
				break;
		}
		
	}
	
	if([KashierUtils isNonEmptyString:errorData.exceptionErrorMessage]){
		exceptionErrorMessage = errorData.exceptionErrorMessage;
	}
	
	if([KashierUtils isNonEmptyString:[errorData getValidationErrorsMessage]]){
		validationErrorMessage = [errorData getValidationErrorsMessage];
	}
	
	NSArray<ValidationResult*>* validationResult = errorData.validationErrors;
	
	if([validationResult count]){
	
		for(ValidationResult* _validationResultItem in validationResult){
			NSMutableDictionary *validationItem = [[NSMutableDictionary alloc]init];
			
			BOOL isValid = _validationResultItem.isValid;
			NSString* validationErrorMessage = @"";
			NSString* validationField = @"";
			NSString* validationErrorCode=@"";
			
			if([KashierUtils isNonEmptyString:_validationResultItem.errorMessage]){
				validationErrorMessage = _validationResultItem.errorMessage;
			}
	
			switch (_validationResultItem.validationField) {
				case VALIDATION_FIELDCARD_HOLDER_NAME:
					validationField = @"CARD_HOLDER_NAME";
					break;
				case VALIDATION_FIELDCARD_NUMBER:
					validationField = @"CARD_NUMBER";
					break;
				case VALIDATION_FIELDCARD_CVV:
					validationField = @"CARD_CVV";
					break;
				case VALIDATION_FIELDCARD_EXPIRY_DATE:
					validationField = @"CARD_EXPIRY_DATE";
					break;
				case VALIDATION_FIELDSHOPPER_REFERENCE:
					validationField = @"SHOPPER_REFERENCE";
					break;
				case VALIDATION_FIELDTOKEN_VALIDITY:
					validationField = @"TOKEN_VALIDITY";
					break;
				case VALIDATION_FIELDORDER_ID:
					validationField = @"ORDER_ID";
					break;
				case VALIDATION_FIELDAMOUNT:
					validationField = @"AMOUNT";
					break;
				case VALIDATION_FIELDCARD_TOKEN:
					validationField = @"CARD_TOKEN";
					break;
				case VALIDATION_FIELDCVV_TOKEN:
					validationField = @"CVV_TOKEN";
					break;
				default:
					break;
			}
			
			switch (_validationResultItem.validationErrorCode) {
				case VALIDATION_ERRORNO_ERROR:
					validationErrorCode = @"NO_ERROR";
					break;
				case VALIDATION_ERRORCARD_HOLDER_NAME_REQUIRED:
					validationErrorCode = @"CARD_HOLDER_NAME_REQUIRED";
					break;
				case VALIDATION_ERRORCARD_HOLDER_NAME_INVALID:
					validationErrorCode = @"CARD_HOLDER_NAME_INVALID";
					break;
				case VALIDATION_ERRORCVV_REQUIRED:
					validationErrorCode = @"CVV_REQUIRED";
					break;
				case VALIDATION_ERRORCVV_INVALID:
					validationErrorCode = @"CVV_INVALID";
					break;
				case VALIDATION_ERROREXPIRY_DATE_REQUIRED:
					validationErrorCode = @"EXPIRY_DATE_REQUIRED";
					break;
				case VALIDATION_ERROREXPIRY_DATE_INVALID:
					validationErrorCode = @"EXPIRY_DATE_INVALID";
					break;
				case VALIDATION_ERRORCARD_NUMBER_REQUIRED:
					validationErrorCode = @"CARD_NUMBER_REQUIRED";
					break;
				case VALIDATION_ERRORCARD_NUMBER_INVALID:
					validationErrorCode = @"CARD_NUMBER_INVALID";
					break;
				case VALIDATION_ERRORSHOPPER_REFERENCE_REQUIRED:
					validationErrorCode = @"SHOPPER_REFERENCE_REQUIRED";
					break;
				case VALIDATION_ERRORTOKEN_VALIDITY_REQUIRED:
					validationErrorCode = @"TOKEN_VALIDITY_REQUIRED";
					break;
				case VALIDATION_ERRORORDER_ID_REQUIRED:
					validationErrorCode = @"ORDER_ID_REQUIRED";
					break;
				case VALIDATION_ERRORAMOUNT_REQUIRED:
					validationErrorCode = @"AMOUNT_REQUIRED";
					break;
				case VALIDATION_ERRORAMOUNT_INVALID:
					validationErrorCode = @"AMOUNT_INVALID";
					break;
				case VALIDATION_ERRORCARD_TOKEN_REQUIRED:
					validationErrorCode = @"CARD_TOKEN_REQUIRED";
					break;
				case VALIDATION_ERRORCVV_TOKEN_REQUIRED:
					validationErrorCode = @"CVV_TOKEN_REQUIRED";
					break;
					
				default:
					break;
			}
		
			[validationItem setObject:[KashierUtils NSStringFromBOOL:isValid] forKey:@"isValid"];
			[validationItem setObject:validationErrorCode forKey:@"validationErrorCode"];
			[validationItem setObject:validationField forKey:@"validationField"];
			[validationItem setObject:validationErrorMessage forKey:@"errorMessage"];
			
			[validationErrors addObject:validationItem];
		}
	}
	
	[parsedErrorData setObject:errorType forKey:@"errorType"];
	[parsedErrorData setObject:errorMessage forKey:@"errorMessage"];
	[parsedErrorData setObject:networkErrorCode forKey:@"networkErrorCode"];
	[parsedErrorData setObject:responseStatus forKey:@"responseStatus"];
	[parsedErrorData setObject:exceptionErrorMessage forKey:@"exceptionErrorMessage"];
	[parsedErrorData setObject:validationErrors forKey:@"validationErrors"];
	[parsedErrorData setObject:validationErrorMessage forKey:@"validationErrorMessage"];
	
	return parsedErrorData;
}

@end
