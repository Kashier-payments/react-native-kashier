//
//  KashierUtils.h
//  react-native-kashier
//
//  Created by Meena on 9/3/20.
//

#ifndef KashierUtils_h
#define KashierUtils_h

@interface  KashierUtils : NSObject

+(BOOL) isEmptyString : (NSString *)string;
+(BOOL) isNonEmptyString : (NSString *)string;
+(NSString*) NSStringFromBOOL: (BOOL) aBool;

@end
#endif /* KashierUtils_h */
