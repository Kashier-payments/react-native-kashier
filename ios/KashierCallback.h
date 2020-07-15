//
//  KashierCallback.h
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//

#ifndef KashierCallback_h
#define KashierCallback_h
#import <Foundation/Foundation.h>

@interface KashierCallback : NSObject

+ (NSArray*) getSuccessCallback: (NSObject*) str;
+ (NSArray*) getErrorCallback: (NSObject*) err;

@end

#endif /* KashierCallback_h */
