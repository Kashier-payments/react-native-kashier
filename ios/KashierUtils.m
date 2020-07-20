//
//  KashierUtils.m
//  react-native-kashier
//
//  Created by Meena on 7/15/20.
//

#import "KashierUtils.h"

@implementation KashierUtils

+(BOOL) isEmptyString : (NSString *)string
{
	if(
	   [string isKindOfClass:[NSNull class]] ||
	   string == nil ||
	   [string isEqualToString:@""] ||
	   [string length] == 0	)
	{
		return YES;         //IF String Is An Empty String
	}
	return NO;
}
+(BOOL) isNonEmptyString : (NSString *)string
{
	return ![KashierUtils isEmptyString:string];
}

+(NSString*) NSStringFromBOOL: (BOOL) aBool
{
	return aBool ? @"YES" : @"NO";
}

@end
