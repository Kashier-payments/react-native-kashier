//
//  KashierCardParser.h
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//

#ifndef KashierCardParser_h
#define KashierCardParser_h
#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>
#import "KashierUtils.h"
#import "KashierCallback.h"
#import "KashierErrorDataParser.h"


@interface  KashierCardParser : NSObject

+ (Card*) parseCardData: (NSDictionary*) cardData  callbackForError:(RCTResponseSenderBlock)callback;

@end

#endif /* KashierCardParser_h */
