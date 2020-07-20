//
//  KashierPaymentResponseParser.m
//  react-native-kashier
//
//  Created by Meena on 7/16/20.
//

#import "KashierPaymentResponseParser.h"

@implementation  KashierPaymentResponseParser

+ (NSDictionary*) getMessagesFromResponse: (GenericResponse*) paymentResponse{
	NSMutableDictionary* parsedResponse = [[NSMutableDictionary alloc]init];
	P_Messages* _messages = NULL;

	if(paymentResponse !=NULL){
		if(paymentResponse.messages!=NULL){
			_messages = paymentResponse.messages;
		}
		if(paymentResponse.responseModel!=NULL){
			if(paymentResponse.responseModel.messages!=NULL){
				_messages = paymentResponse.responseModel.messages;
			}
			if(paymentResponse.responseModel.message!=NULL && paymentResponse.responseModel.message.messages!=NULL){
				_messages = paymentResponse.responseModel.message.messages;
			}
		}

	}
	if(_messages != NULL){
		if(_messages.ar!=NULL){
			[parsedResponse setObject: _messages.ar forKey:@"ar"];
		}
		if(_messages.en!=NULL){
			[parsedResponse setObject: _messages.en forKey:@"en"];
		}
	}
	return parsedResponse;
}

+ (NSString*) getResponseStatusFromString: (NSString*) str{
	NSString* status = @"UNKNOWN";
	if([str isEqualToString:@"SUCCESS"] ||
	   [str isEqualToString:@"200"]){
		status = @"SUCCESS";
	}else if([str isEqualToString:@"FAILURE"] ||
			 [str isEqualToString:@"INVALID_REQUEST"] ||
			 [str isEqualToString:@"PENDING"] ||
			 [str isEqualToString:@"PENDING_ACTION"]){
		status = str;
	}
	return status;
}

+ (NSString*) getResponseStatusFromResponse: (GenericResponse*) paymentResponse{
	NSString* _responseStatus = @"UNKNOWN";
	if(paymentResponse!=NULL){
		if(paymentResponse.status!=NULL && [KashierUtils isNonEmptyString:paymentResponse.status]){
			_responseStatus =  [self getResponseStatusFromString:paymentResponse.status];
		}
		if(paymentResponse.responseModel!=NULL){
			if(paymentResponse.responseModel.status!=NULL &&
			   [KashierUtils isNonEmptyString:paymentResponse.responseModel.status]){
				_responseStatus = [self getResponseStatusFromString:paymentResponse.responseModel.status];
			}
			if(paymentResponse.responseModel.message!=NULL && paymentResponse.responseModel.message.status!=NULL &&
			   [KashierUtils isNonEmptyString:paymentResponse.responseModel.message.status]){
				_responseStatus = [self getResponseStatusFromString:paymentResponse.responseModel.message.status];
			}
			if(paymentResponse.responseModel.cardModel!=NULL && paymentResponse.responseModel.cardModel.result!=NULL){
				_responseStatus = [self getResponseStatusFromString:paymentResponse.responseModel.cardModel.result];
			}
		}
	}
	return _responseStatus;
}

+ (NSDictionary*) parsePaymentResponse: (Payment*) paymentResponse{
	NSMutableDictionary* parsedResponse =[[NSMutableDictionary alloc]init];
	if(paymentResponse!=NULL){
		[parsedResponse setObject: [KashierPaymentResponseParser getMessagesFromResponse:paymentResponse] forKey:@"messages"];
		[parsedResponse setObject:[self getResponseStatusFromResponse:paymentResponse] forKey:@"status"];
		if(paymentResponse.response!=NULL){
			NSMutableDictionary* response =[[NSMutableDictionary alloc]init];
			if(paymentResponse.response.method!=NULL){
				[response setObject:paymentResponse.response.method forKey:@"method"];
			}
			if(paymentResponse.response.orderID!=NULL){
				[response setObject:paymentResponse.response.orderID forKey:@"orderId"];
			}
			if(paymentResponse.response.apiKeyID!=NULL){
				[response setObject:paymentResponse.response.apiKeyID forKey:@"apiKeyId"];
			}
			if(paymentResponse.response.merchantOrderID!=NULL){
				[response setObject:paymentResponse.response.merchantOrderID forKey:@"merchantOrderId"];
			}
			if(paymentResponse.response.creationDate!=NULL){
				[response setObject:paymentResponse.response.creationDate forKey:@"creationDate"];
			}
			if(paymentResponse.response.transactionID!=NULL){
				[response setObject:paymentResponse.response.transactionID forKey:@"transactionId"];
			}
			if(paymentResponse.response.providerType!=NULL){
				[response setObject:paymentResponse.response.providerType forKey:@"providerType"];
			}
			if(paymentResponse.response.merchantID!=NULL){
				[response setObject:paymentResponse.response.merchantID forKey:@"merchantId"];
			}
			if(paymentResponse.response.orderReference!=NULL){
				[response setObject:paymentResponse.response.orderReference forKey:@"orderReference"];
			}
			if(paymentResponse.response.refID!=NULL){
				[response setObject:paymentResponse.response.refID forKey:@"refId"];
			}
			if(paymentResponse.response.status!=NULL){
				[response setObject:paymentResponse.response.status forKey:@"status"];
			}
			if(paymentResponse.response.error!=NULL){
				NSMutableDictionary* _error = [[NSMutableDictionary alloc]init];
				if(paymentResponse.response.error.cause!=NULL &&
				   [KashierUtils isNonEmptyString:paymentResponse.response.error.cause]){
					[_error setObject:paymentResponse.response.error.cause forKey:@"cause"];
				}
				if(paymentResponse.response.error.explanation!=NULL &&
				   [KashierUtils isNonEmptyString:paymentResponse.response.error.explanation]){
					[_error setObject:paymentResponse.response.error.explanation forKey:@"explanation"];
				}
				[response setObject:_error forKey:@"error"];
			}

			if(paymentResponse.response.card!=NULL){
				ResponseCard* _card = paymentResponse.response.card;
				NSMutableDictionary* parsedCard = [[NSMutableDictionary alloc]init];
				if(_card.result!=NULL){
					[parsedCard setObject:_card.result forKey:@"result"];
				}
				if(_card.currency!=NULL){
					[parsedCard setObject:_card.currency forKey:@"currency"];
				}
				[parsedCard setObject:[[NSNumber numberWithDouble:_card.amount] stringValue] forKey:@"amount"];

				if(_card.cardInfo!=NULL){
					NSMutableDictionary* parsedCardInfo = [[NSMutableDictionary alloc]init];
					if(_card.cardInfo.cardDataToken !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.cardDataToken forKey:@"cardDataToken"];
					}
					if(_card.cardInfo.cardHash !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.cardHash forKey:@"cardHash"];
					}
					if(_card.cardInfo.maskedCard !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.maskedCard forKey:@"maskedCard"];
					}
					if(_card.cardInfo.cardHolderName !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.cardHolderName forKey:@"cardHolderName"];
					}
					if(_card.cardInfo.expiryMonth !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.expiryMonth forKey:@"expiryMonth"];
					}
					if(_card.cardInfo.expiryYear !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.expiryYear forKey:@"expiryYear"];
					}
					if(_card.cardInfo.cardBrand !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.cardBrand forKey:@"cardBrand"];
					}
					if(_card.cardInfo.ccvToken !=NULL){
						[parsedCardInfo setObject:_card.cardInfo.ccvToken forKey:@"ccvToken"];
					}

					[parsedCard setObject:parsedCardInfo forKey:@"cardInfo"];
				}

				if(_card.merchant!=NULL && _card.merchant.merchantRedirectURL!=NULL){
					NSMutableDictionary* _merchant = [[NSMutableDictionary alloc]init];
					[_merchant setObject:_card.merchant.merchantRedirectURL forKey:@"merchantRedirectUrl"];
					[parsedCard setObject:_merchant forKey:@"merchant"];
				}

				if(_card.card3DSecure!=NULL){
					NSMutableDictionary* _cardDSecure = [[NSMutableDictionary alloc]init];
					if(_card.card3DSecure.acsURL!=NULL){
						[_cardDSecure setObject:_card.card3DSecure.acsURL forKey:@"acsUrl"];
					}
					if(_card.card3DSecure.processACSRedirectURL!=NULL){
						[_cardDSecure setObject:_card.card3DSecure.processACSRedirectURL forKey:@"processACSRedirectURL"];
					}
					if(_card.card3DSecure.requestType!=NULL){
						[_cardDSecure setObject:_card.card3DSecure.requestType forKey:@"requestType"];
					}
					if(_card.card3DSecure.paReq!=NULL){
						[_cardDSecure setObject:_card.card3DSecure.paReq forKey:@"paReq"];
					}
					[parsedCard setObject:_cardDSecure forKey:@"DSecure"];
				}
				[response setObject:parsedCard forKey:@"card"];
			}
			[parsedResponse setObject:response forKey:@"response"];
		}
	}
	return parsedResponse;
}

@end
