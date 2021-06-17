#import "KashierRCT.h"


PaymentCallback* CommonPaymentCallback( RCTResponseSenderBlock callback){
	return  [[PaymentCallback alloc] initOnResponse:^(Payment * paymentResponse) {

		NSDictionary* parsedPaymentResponse = [KashierPaymentResponseParser parsePaymentResponse:paymentResponse];
		callback([KashierCallback getSuccessCallback:parsedPaymentResponse]);

	} onFailure:^(ErrorData * _errorData) {
		NSDictionary* errorData = [KashierErrorDataParser parseError:_errorData];
		callback([KashierCallback getErrorCallback:errorData]);
	}];

}

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
 [Kashier initializeObjCWithMerchantId:merchantId apiKey:apiKey sdkMode:_sdkMode];
//	[Kashier initializeObjCWithMerchantId:merchantId apiKey:apiKey sdkMode:_sdkMode currency:currency displayLang:_displayLang];
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

RCT_EXPORT_METHOD(saveCard:(NSDictionary*)cardData
				  _:(NSString*)shopperReference
				  _:(NSString*)tokenValidity
				  _:(RCTResponseSenderBlock)callback)
{
	enum KASHIER_TOKEN_VALIDITY _tokenValidity;
	if([tokenValidity isEqualToString:@"perm"]){
		_tokenValidity = KASHIER_TOKEN_VALIDITYPERMANENT;
	}else{
		_tokenValidity = KASHIER_TOKEN_VALIDITYTEMPORARY;
	}

	Card* card = [KashierCardParser parseCardData:cardData callbackForError:callback];
	if(card != NULL){
		[Kashier saveShopperCardWithCardData:card
							shopperReference:shopperReference
							   tokenValidity:_tokenValidity
						tokenizationCallback:[[TokenizationCallback alloc]
											  initOnResponse:^(TokenizationResponse * tokenizationResponse) {


			NSDictionary* parsedTokenizationResponse = [KashierTokenizationResponseParser parseTokenizationResponse:tokenizationResponse];
			callback([KashierCallback getSuccessCallback:parsedTokenizationResponse]);


		} onFailure:^(ErrorData * _errorData) {
			NSDictionary* errorData = [KashierErrorDataParser parseError:_errorData];
			callback([KashierCallback getErrorCallback:errorData]);
		}]];
	}
}

RCT_EXPORT_METHOD(setConnectedAccount:(NSDictionary*)connectedAccount)
{
    Connected_Accounts* connectedAccountData = [KashierConnectedAccountParser parseConnectedAccountData:connectedAccount];
    if(connectedAccountData != NULL){
        [Kashier setConnectedAccount:connectedAccountData];
    }
    
}


RCT_EXPORT_METHOD(payUsingCard:(NSDictionary*)cardData
				  _:(NSString*)shopperReference
				  _:(NSString*)orderId
				   _:(NSString*)amount
				  _:(BOOL)shouldSaveCard
				  _:(RCTResponseSenderBlock)callback)
{

	Card* card = [KashierCardParser parseCardData:cardData callbackForError:callback];
	if(card != NULL){

		[Kashier payWithCardWithCardData:card
								 orderId:orderId
								  amount:amount
						shopperReference:shopperReference
						  shouldSaveCard:shouldSaveCard
						 paymentCallback:CommonPaymentCallback(callback)];
	}
}


RCT_EXPORT_METHOD(payUsingPermToken:(NSString*)shopperReference
				  _:(NSString*)orderId
				  _:(NSString*)amount
				 _:(NSString*)cardToken
				  _:(RCTResponseSenderBlock)callback)
{
	[Kashier payWithPermTokenWithShopperReference:shopperReference
										  orderId:orderId
										   amount:amount
										cardToken:cardToken
								  paymentCallback:CommonPaymentCallback(callback)];
}

RCT_EXPORT_METHOD(payUsingTempToken:(NSString*)shopperReference
				  _:(NSString*)orderId
				  _:(NSString*)amount
				  _:(NSString*)cardToken
				  _:(NSString*)cvvToken
				  _:(RCTResponseSenderBlock)callback)
{

	[Kashier payWithTempTokenWithShopperReference:shopperReference
										  orderId:orderId
										   amount:amount
										cardToken:cardToken
										 cvvToken:cvvToken
								  paymentCallback:CommonPaymentCallback(callback)];
}


RCT_EXPORT_METHOD(payUsingPaymentForm:(NSString*)shopperReference
				  _:(NSString*)orderId
				  _:(NSString*)amount
				  _:(RCTResponseSenderBlock)callback)
{
	dispatch_sync(dispatch_get_main_queue(), ^{
		[Kashier payUsingPaymentFormWithOrderId:orderId
										 amount:amount
							   shopperReference:shopperReference
								paymentCallback:CommonPaymentCallback(callback)
								  customXibFile:NULL];
	});
}

@end
