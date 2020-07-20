//
//  KashierCardParser.m
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//


#import "KashierCardParser.h"

@implementation  KashierCardParser

+ (Card*) parseCardData: (NSDictionary*) cardData  callbackForError:(RCTResponseSenderBlock)callback{
	Card* card;
	NSString* cardHolderName = @"";
	NSString* cardNumber = @"";
	NSString* cardCcv = @"";
	NSString* cardExpiryMonth = @"";
	NSString* cardExpiryYear = @"";
	NSString* cardExpiryDate=@"";
	
	
	if([KashierUtils isNonEmptyString:[cardData objectForKey:@"cardHolderName"]]  ){
		cardHolderName = [cardData objectForKey:@"cardHolderName"];
	}
	if([KashierUtils isNonEmptyString:[cardData objectForKey:@"cardNumber"]]){
		cardNumber = [cardData objectForKey:@"cardNumber"];
	}
	if([KashierUtils isNonEmptyString:[cardData objectForKey:@"cardCcv"]]){
		cardCcv = [cardData objectForKey:@"cardCcv"];
	}
	if([KashierUtils isNonEmptyString:[cardData objectForKey:@"cardExpiryMonth"]]){
		cardExpiryMonth = [cardData objectForKey:@"cardExpiryMonth"];
	}
	if([KashierUtils isNonEmptyString:[cardData objectForKey:@"cardExpiryYear"]]){
		cardExpiryYear = [cardData objectForKey:@"cardExpiryYear"];
	}
	if([KashierUtils isNonEmptyString:(NSString*)[cardData objectForKey:@"cardExpiryDate"]]){
		cardExpiryDate = [cardData objectForKey:@"cardExpiryDate"];
	}
	  
	
	if([KashierUtils isEmptyString:cardExpiryDate] &&
	   [KashierUtils isNonEmptyString:cardExpiryMonth] &&
	   [KashierUtils isNonEmptyString:cardExpiryYear]){
		
		card = [[Card alloc] initWithCardHolderName:cardHolderName
										 cardNumber:cardNumber
											cardCcv:cardCcv
									cardExpiryMonth:cardExpiryMonth
									 cardExpiryYear:cardExpiryYear ];
		
	}else if([KashierUtils isNonEmptyString:cardExpiryDate]){
		card = [[Card alloc] initWithCardHolderName:cardHolderName
										 cardNumber:cardNumber
											cardCcv:cardCcv
									cardExpiryDate:cardExpiryDate ];
	}else{
		ErrorData* cardErrorData = [Card getCardParsingError];
		NSDictionary* errorData = [KashierErrorDataParser parseError:cardErrorData];
		callback([KashierCallback getErrorCallback:errorData]);
	}
	return card;
}

@end
