#import "KashierRCT.h"


@implementation KashierRCT

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initialize:(NSString *)merchantId
				  apiKey:(NSString *)apiKey
				  sdkMode:(NSString *)sdkMode
				  currency:(NSString *)currency
				  displayLang:(NSString *)displayLang
				  _:(RCTPromiseResolveBlock)resolve
				  _:(RCTPromiseRejectBlock)reject)
{
	enum KASHIER_SDK_MODE _sdkMode;
	enum KASHIER_DISPLAY_LANG _displayLang;

	
	if([sdkMode isEqualToString:@"DEVELOPMENT"]){
		_sdkMode = KASHIER_SDK_MODEDEVELOPMENT;
	}else{
		_sdkMode = KASHIER_SDK_MODEPRODUCTION;
	}
	if([displayLang isEqualToString:@"AR"]){
		_displayLang = KASHIER_DISPLAY_LANGAR;
	}else{
		_displayLang = KASHIER_DISPLAY_LANGEN;
	}
	[Kashier initializeObjCWithMerchantId:merchantId apiKey:apiKey sdkMode:_sdkMode currency:currency displayLang:_displayLang];
	resolve(NULL);
}

RCT_EXPORT_METHOD(getSdkMode:(RCTPromiseResolveBlock)resolve
				 _:(RCTPromiseRejectBlock)reject)
{
	enum KASHIER_SDK_MODE _sdkMode = [Kashier sdkMode];
	NSString *sdkModeStr = NULL;
	if(_sdkMode == KASHIER_SDK_MODEPRODUCTION){
		sdkModeStr = @"PRODUCTION";
	}else{
		sdkModeStr = @"DEVELOPMENT";
	}
	resolve(sdkModeStr);
}




RCT_EXPORT_METHOD(listCards:(NSString*)shopperReference
				  _:(RCTResponseSenderBlock)callback)
{
	[Kashier listShopperCardsWithShopperReference:shopperReference userCallBack:[[TokensListCallback alloc] initOnResponse:^(TokensList * tokensList) {
		
		NSArray<NSDictionary*>* tokensArray = [KashierTokensListParser parseTokensList:tokensList];
		callback([KashierCallback getSuccessCallback:tokensArray]);
		
	} onFailure:^(ErrorData * _errorData) {
		NSDictionary* errorData = [KashierErrorDataParser parseError:_errorData];
		callback([KashierCallback getErrorCallback:errorData]);
	}]];
}
@end
