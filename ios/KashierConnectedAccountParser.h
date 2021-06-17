//
//  KashierConnectedAccountParser.h
//  
//
//  Created by MacBook on 15/06/2021.
//


#ifndef KashierConnectedAccountParser_h
#define KashierConnectedAccountParser_h

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>
#import "KashierUtils.h"

@interface  KashierConnectedAccountParser : NSObject

+ (Connected_Accounts*) parseConnectedAccountData: (NSDictionary*) connectedAccountData;

@end
#endif /* KashierConnectedAccountParser_h */
