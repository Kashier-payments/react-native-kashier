//
//  KashierTokensListParser.h
//  react-native-kashier
//
//  Created by Meena on 9/3/20.
//

#ifndef KashierTokensListParser_h
#define KashierTokensListParser_h
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>
@interface  KashierTokensListParser : NSObject

+ (NSArray*) parseTokensList: (TokensList*) tokensList;

@end


#endif /* KashierTokensListParser_h */
