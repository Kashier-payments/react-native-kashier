//
//  KashierTokenizationResponseParser.h
//  react-native-kashier
//
//  Created by Meena on 7/16/20.
//

#ifndef KashierTokenizationResponseParser_h
#define KashierTokenizationResponseParser_h

#import <Foundation/Foundation.h>
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>
#import "KashierUtils.h"

@interface  KashierTokenizationResponseParser : NSObject

+ (NSDictionary*) parseTokenizationResponse: (TokenizationResponse*) tokensList;

@end

#endif /* KashierTokenizationResponseParser_h */
