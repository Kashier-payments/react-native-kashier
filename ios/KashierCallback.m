//
//  KashierCallback.m
//  Pods
//
//  Created by Meena on 7/15/20.
//

#import <Foundation/Foundation.h>
#import "KashierCallback.h"

@implementation KashierCallback

+ (NSArray*) getSuccessCallback: (NSObject*) success{
	return @[ [NSNull null],success];
}

+ (NSArray*) getErrorCallback: (NSObject*) err{
	return @[err, [NSNull null] ];
}



@end
