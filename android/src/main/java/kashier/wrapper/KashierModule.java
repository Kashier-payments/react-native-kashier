package kashier.wrapper;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import io.kashier.sdk.Core.model.Response.Error.ErrorData;
import io.kashier.sdk.Core.model.Response.TokensList.TokensListResponse;
import io.kashier.sdk.Core.model.Response.UserCallback;
import io.kashier.sdk.Core.network.SDK_MODE;
import io.kashier.sdk.KASHIER_DISPLAY_LANG;
import io.kashier.sdk.Kashier;
import retrofit2.Response;


public class KashierModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public KashierModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Kashier";
    }


    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument + Kashier.getSdkMode().toString());

    }

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
    public void getCurrency(Promise promise) {
        promise.resolve(Kashier.getCurrency());
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
        Kashier.init(this.reactContext, merchantId, apiKey, currency, _sdkMode, _displayLanguage);
        promise.resolve(true);
    }


    @ReactMethod
    public void listCards(
            String shopperReference,
            final Callback successCallback,
            final Callback errorCallback) {

        Kashier.listShopperCards(shopperReference, new UserCallback<TokensListResponse>() {
            @Override
            public void onResponse(Response<TokensListResponse> response) {
                successCallback.invoke("Success");
            }

            @Override
            public void onFailure(ErrorData<TokensListResponse> errorData) {
                WritableMap _errorData = KashierErrorDataParser.parseError(errorData);
                errorCallback.invoke(_errorData);
            }
        });

    }


}
