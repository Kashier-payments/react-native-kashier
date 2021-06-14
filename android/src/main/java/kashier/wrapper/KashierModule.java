package kashier.wrapper;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import io.kashier.sdk.Core.model.Card.Card;
import io.kashier.sdk.Core.model.Request.Payment.Connected_Accounts;
import io.kashier.sdk.Core.model.Request.Tokenization.TOKEN_VALIDITY;
import io.kashier.sdk.Core.model.Response.Error.ERROR_TYPE;
import io.kashier.sdk.Core.model.Response.Error.ErrorData;
import io.kashier.sdk.Core.model.Response.Payment.PaymentResponse;
import io.kashier.sdk.Core.model.Response.Tokenization.TokenizationResponse;
import io.kashier.sdk.Core.model.Response.TokensList.TokensListResponse;
import io.kashier.sdk.Core.model.Response.UserCallback;
import io.kashier.sdk.Core.network.SDK_MODE;
import io.kashier.sdk.KASHIER_DISPLAY_LANG;
import io.kashier.sdk.Kashier;
import retrofit2.Response;


public class KashierModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    static Connected_Accounts connected_accounts = null;


    public KashierModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "KashierRCT";
    }

//
//    @ReactMethod
//    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
//        // TODO: Implement some actually useful functionality
//        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument + Kashier.getSdkMode().toString());
//
//    }

    @ReactMethod
    public void getSdkMode(Promise promise) {
        SDK_MODE _sdkMode = Kashier.getSdkMode();
        if (_sdkMode != null) {
            promise.resolve(_sdkMode.toString());
        } else {
            promise.reject("-1", "Couldn't get SDK Mode");
        }
    }


    @ReactMethod
    public void init(String merchantId,
                     String apiKey,
                     String currency,
                     String sdkMode,
                     @Nullable String displayLanguage, Promise promise) {
        SDK_MODE _sdkMode = null;
        KASHIER_DISPLAY_LANG _displayLanguage = null;

        if (sdkMode.equals("DEVELOPMENT")) {
            _sdkMode = SDK_MODE.DEVELOPMENT;
        }
        if (sdkMode.equals("PRODUCTION")) {
            _sdkMode = SDK_MODE.PRODUCTION;
        }
        if (displayLanguage != null) {
            if (displayLanguage.equals("AR")) {
                _displayLanguage = KASHIER_DISPLAY_LANG.AR;
            }
            if (displayLanguage.equals("EN")) {
                _displayLanguage = KASHIER_DISPLAY_LANG.EN;
            }
        }
        Kashier.init(this.reactContext, merchantId, apiKey, currency, _sdkMode, String.valueOf(_displayLanguage));
        promise.resolve(true);
    }

    @ReactMethod
     public void setConnectedAccount(ReadableMap connectedAccount) {

        Connected_Accounts _connectedAccountData = new Connected_Accounts("");
        try {
            _connectedAccountData = KashierConnectedAccountParser.connectedAccountParser(connectedAccount);
        } catch (Exception ex) {
//           invoke(KashierCardParser.handleParsingError(connectedAccount));
            return;
        }
        Kashier.setConnectedAccount(_connectedAccountData);
    }

    @ReactMethod
    public void listCards(
            String shopperReference,
            final Callback successCallback,
            final Callback errorCallback) {
        Kashier.listShopperCards(shopperReference, new UserCallback<TokensListResponse>() {
            @Override
            public void onResponse(Response<TokensListResponse> response) {
                WritableArray tokensList = KashierTokensListParser.parseTokensList(response);
                successCallback.invoke(tokensList);
            }

            @Override
            public void onFailure(ErrorData<TokensListResponse> errorData) {
                WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                errorCallback.invoke(_errorData);
            }
        });

    }

    @ReactMethod
    public void saveCard(
            ReadableMap cardData,
            String shopperReference,
            String tokenValidity,
            final Callback successCallback,
            final Callback errorCallback) throws Exception {
        Card _cardData = new Card("", "", "", "", "");
        try {
            _cardData = KashierCardParser.parseCardData(cardData);
        } catch (Exception ex) {
            errorCallback.invoke(KashierCardParser.handleParsingError(cardData));
            return;
        }
        TOKEN_VALIDITY _tokenValidity;

        if (tokenValidity.equals(TOKEN_VALIDITY.PERMANENT.value())) {
            _tokenValidity = TOKEN_VALIDITY.PERMANENT;
        } else {
            _tokenValidity = TOKEN_VALIDITY.TEMPORARY;
        }
        Kashier.saveShopperCard(_cardData, shopperReference, _tokenValidity, new UserCallback<TokenizationResponse>() {
            @Override
            public void onResponse(Response<TokenizationResponse> tokenizationResponse) {
                if (tokenizationResponse != null
                        && tokenizationResponse.body() != null
                        && tokenizationResponse.body().getBody() != null
                        && tokenizationResponse.body().getBody().getResponse() != null) {
                    io.kashier.sdk.Core.model.Response.Tokenization.Response response = tokenizationResponse.body().getBody().getResponse();
                    WritableMap _tokenizationResponseMap = TokenizationResponseParser.parseTokenizationResponse(response);
                    successCallback.invoke(_tokenizationResponseMap);
                } else {
                    onFailure(new ErrorData<TokenizationResponse>().setErrorType(ERROR_TYPE.DATA).setErrorMessage("Missing data in response"));
                }
            }

            @Override
            public void onFailure(ErrorData<TokenizationResponse> errorData) {
                WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                errorCallback.invoke(_errorData);
            }
        });
    }

    @ReactMethod
    public void payUsingCard(
            ReadableMap cardData,
            String shopperReference,
            String orderId,
            String amount,
            Boolean shouldSaveCard,
            final Callback successCallback,
            final Callback errorCallback) {
        Card _cardData = new Card("", "", "", "", "");
        try {
            _cardData = KashierCardParser.parseCardData(cardData);
        } catch (Exception ex) {
            errorCallback.invoke(KashierCardParser.handleParsingError(cardData));
            return;
        }
        Kashier.callPaymentAPI(_cardData, orderId, amount, shopperReference, shouldSaveCard, new UserCallback<PaymentResponse>() {
            @Override
            public void onResponse(Response<PaymentResponse> response) {
                WritableMap paymentResponse = KashierPaymentResponseParser.parsePaymentResponse(response);
                successCallback.invoke(paymentResponse);
            }

            @Override
            public void onFailure(ErrorData<PaymentResponse> errorData) {
                WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                errorCallback.invoke(_errorData);
            }
        });
    }

    @ReactMethod
    public void payUsingPaymentForm(
            String shopperReference,
            String orderId,
            String amount,
            final Callback successCallback,
            final Callback errorCallback) {
        AppCompatActivity activity = (AppCompatActivity) getCurrentActivity();
        if (activity != null) {
            Kashier.startPaymentActivity(activity, shopperReference, orderId, amount, new UserCallback<PaymentResponse>() {
                @Override
                public void onResponse(Response<PaymentResponse> response) {
                    WritableMap paymentResponse = KashierPaymentResponseParser.parsePaymentResponse(response);
                    successCallback.invoke(paymentResponse);
                }

                @Override
                public void onFailure(ErrorData<PaymentResponse> errorData) {
                    WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                    errorCallback.invoke(_errorData);
                }
            });
        } else {
            Log.d("Kashier", "payUsingPaymentForm: Can't find activity");
        }
    }

    @ReactMethod
    public void payUsingTempToken(
            String shopperReference,
            String orderId,
            String amount,
            String cardToken,
            String cvvToken,
            final Callback successCallback,
            final Callback errorCallback) {
        Kashier.payWithTempToken(shopperReference, orderId, amount, cardToken, cvvToken, new UserCallback<PaymentResponse>() {
            @Override
            public void onResponse(Response<PaymentResponse> response) {
                WritableMap paymentResponse = KashierPaymentResponseParser.parsePaymentResponse(response);
                successCallback.invoke(paymentResponse);
            }

            @Override
            public void onFailure(ErrorData<PaymentResponse> errorData) {
                WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                errorCallback.invoke(_errorData);
            }
        });
    }

    @ReactMethod
    public void payUsingPermToken(
            String shopperReference,
            String orderId,
            String amount,
            String cardToken,
            final Callback successCallback,
            final Callback errorCallback) {
        Kashier.payWithPermanentToken(shopperReference, orderId, amount, cardToken, new UserCallback<PaymentResponse>() {
            @Override
            public void onResponse(Response<PaymentResponse> response) {
                WritableMap paymentResponse = KashierPaymentResponseParser.parsePaymentResponse(response);
                successCallback.invoke(paymentResponse);
            }

            @Override
            public void onFailure(ErrorData<PaymentResponse> errorData) {
                WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                errorCallback.invoke(_errorData);
            }
        });
    }
}
