package kashier.wrapper;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import io.kashier.sdk.Core.model.Response.Payment.PaymentResponse;
import retrofit2.Response;

public class KashierPaymentResponseParser {
    static WritableMap parsePaymentResponse(Response<PaymentResponse> paymentResponse) {
        WritableMap _paymentResponseMap = new WritableNativeMap();
        _paymentResponseMap.putString("DATA_IN_RESPONSE", "data in response");
        return _paymentResponseMap;
    }
}
