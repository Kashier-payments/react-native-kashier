package kashier.wrapper;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

class TokenizationResponseParser {
    static WritableMap parseTokenizationResponse(io.kashier.sdk.Core.model.Response.Tokenization.Response tokenizationResponse) {
        WritableMap _tokenizationResponse = new WritableNativeMap();

        String cardToken = "";
        String ccvToken = "";
        String cardHolderName = "";
        String maskedCard = "";
        String cardExpiryMonth = "";
        String cardExpiryYear = "";


        if (tokenizationResponse.getCardToken() != null) {
            cardToken = tokenizationResponse.getCardToken();
        }
        if (tokenizationResponse.getCardHolderName() != null) {
            cardHolderName = tokenizationResponse.getCardHolderName();
        }
        if (tokenizationResponse.getMaskedCard() != null) {
            maskedCard = tokenizationResponse.getMaskedCard();
        }
        if (tokenizationResponse.getExpiryMonth() != null) {
            cardExpiryMonth = tokenizationResponse.getExpiryMonth();
        }
        if (tokenizationResponse.getExpiryYear() != null) {
            cardExpiryYear = tokenizationResponse.getExpiryYear();
        }
        ccvToken = tokenizationResponse.getCcvToken();

        _tokenizationResponse.putString("cardToken", cardToken);
        if (ccvToken != null) {
            _tokenizationResponse.putString("ccvToken", ccvToken);
        } else {
            _tokenizationResponse.putNull("ccvToken");
        }
        _tokenizationResponse.putString("cardHolderName", cardHolderName);
        _tokenizationResponse.putString("maskedCard", maskedCard);
        _tokenizationResponse.putString("cardExpiryMonth", cardExpiryMonth);
        _tokenizationResponse.putString("cardExpiryYear", cardExpiryYear);

        return _tokenizationResponse;
    }
}
