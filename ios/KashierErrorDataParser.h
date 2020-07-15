//
//  KashierErrorDataParser.h
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//

#ifndef KashierErrorDataParser_h
#define KashierErrorDataParser_h
#import <Foundation/Foundation.h>
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>
#import "KashierUtils.h"

@interface  KashierErrorDataParser : NSObject

+ (NSDictionary*) parseError: (ErrorData*) errorData;

@end

#endif /* KashierErrorDataParser_h */
