//
//  KashierTokensListParser.m
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//

#import "KashierTokensListParser.h"

@implementation KashierTokensListParser

+ (NSArray*) parseTokensList: (TokensList*) tokensList{
	
	NSArray<Token*>* tokens = tokensList.response.tokens;
	NSMutableArray* tokensArray=[[NSMutableArray alloc] init];

	for(Token* token in tokens){
        
		NSMutableDictionary *mutableDict = [[NSMutableDictionary alloc]init];
		[mutableDict setObject:token.cardExpiryYear forKey:@"cardExpiryYear"];
		[mutableDict setObject:token.cardExpiryMonth forKey:@"cardExpiryMonth"];
		[mutableDict setObject:token.cardNumber forKey:@"cardNumber"];
		[mutableDict setObject:token.creationDate forKey:@"creationDate"];
		[mutableDict setObject:token.token forKey:@"cardToken"];
		[mutableDict setObject:token.shopperReference forKey:@"shopper_reference"];
		[mutableDict setObject:token.merchantID forKey:@"merchantId"];
		[tokensArray addObject:mutableDict];
	}
	return tokensArray;
}

@end
