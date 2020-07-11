#import "KashierRCT.h"
#import <KashierPaymentSDK/KashierPaymentSDK-Swift.h>

@implementation KashierRCT

RCT_EXPORT_MODULE()


RCT_EXPORT_METHOD(sampleMethod:(NSString *)stringArgument numberParameter:(nonnull NSNumber *)numberArgument callback:(RCTResponseSenderBlock)callback)
{
    // TODO: Implement some actually useful functionality
    	NSString *contentOfURLKashierWithParams = [Kashier getStringWithParamWithStr:@"Hello There" ];

    	printf("Kashier: with params %s\n",[contentOfURLKashierWithParams UTF8String]);
//comment

    callback(@[[NSString stringWithFormat: @"numberArgument: %@ stringArgument: %@", numberArgument, stringArgument]]);
}

@end
