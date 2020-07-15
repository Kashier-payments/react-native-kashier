//
//  KashierTokenizationResponseParser.m
//  react-native-kashier
//
//  Created by Meena on 7/16/20.
//

#import "KashierTokenizationResponseParser.h"

@implementation  KashierTokenizationResponseParser

+ (NSDictionary*) parseTokenizationResponse: (TokenizationResponse*) tokenizationResponse{
	NSMutableDictionary* parsedResponse = [[NSMutableDictionary alloc]init];
	
	NSString* cardToken = @"";
	NSString* ccvToken = @"";
	NSString* cardHolderName = @"";
	NSString* maskedCard = @"";
	NSString* cardExpiryMonth = @"";
	NSString* cardExpiryYear = @"";
	
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.cardToken]){
		cardToken = tokenizationResponse.cardData.cardToken;
	}
	
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.cardToken]){
		cardToken = tokenizationResponse.cardData.cardToken;
	}
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.ccvToken]){
		ccvToken = tokenizationResponse.cardData.ccvToken;
	}
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.cardHolderName]){
		cardHolderName = tokenizationResponse.cardData.cardHolderName;
	}
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.maskedCard]){
		maskedCard = tokenizationResponse.cardData.maskedCard;
	}
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.expiryMonth]){
		cardExpiryMonth = tokenizationResponse.cardData.expiryMonth;
	}
	if([KashierUtils isNonEmptyString:tokenizationResponse.cardData.expiryYear]){
		cardExpiryYear = tokenizationResponse.cardData.expiryYear;
	}
	
	[parsedResponse setObject: cardToken forKey:@"cardToken"];
	[parsedResponse setObject: ccvToken forKey:@"ccvToken"];
	[parsedResponse setObject: cardHolderName forKey:@"cardHolderName"];
	[parsedResponse setObject: maskedCard forKey:@"maskedCard"];
	[parsedResponse setObject: cardExpiryMonth forKey:@"cardExpiryMonth"];
	[parsedResponse setObject: cardExpiryYear forKey:@"cardExpiryYear"];
	return parsedResponse;
}

@end
