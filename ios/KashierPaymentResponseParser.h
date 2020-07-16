//
//  KashierPaymentResponseParser.h
//  react-native-kashier
//
//  Created by Meena on 7/16/20.
//

#ifndef KashierPaymentResponseParser_h
#define KashierPaymentResponseParser_h

#import <Foundation/Foundation.h>
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>
#import "KashierUtils.h"

@interface  KashierPaymentResponseParser : NSObject

+ (NSDictionary*) parsePaymentResponse: (Payment*) paymentResponse;

@end
#endif /* KashierPaymentResponseParser_h */
