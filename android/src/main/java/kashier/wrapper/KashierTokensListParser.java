package kashier.wrapper;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.List;

import io.kashier.sdk.Core.model.Response.TokensList.TokensItem;
import io.kashier.sdk.Core.model.Response.TokensList.TokensListResponse;
import retrofit2.Response;

enum KASHIER_TOKEN_ITEM {
    cardExpiryYear,
    cardExpiryMonth,
    cardNumber,
    creationDate,
    cardToken,
    shopper_reference,
    merchantId
}

class KashierTokensListParser {
    static WritableArray parseTokensList(Response<TokensListResponse> response) {
        WritableArray tokensListMap = new WritableNativeArray();
        if (response.body() != null
                && response.body().getResponse() != null
                && response.body().getResponse().getTokens() != null) {
            List<TokensItem> _tokensFromResponse = response.body().getResponse().getTokens();
            for (TokensItem tokensItem : _tokensFromResponse) {
                WritableMap _tokenItem = new WritableNativeMap();
                String cardExpiryYear = "";
                String cardExpiryMonth = "";
                String cardNumber = "";
                String creationDate = "";
                String cardToken = "";
                String shopper_reference = "";
                String merchantId = "";

                if (tokensItem.getCardExpiryYear() != null) {
                    cardExpiryYear = tokensItem.getCardExpiryYear();
                }

                if (tokensItem.getCardExpiryMonth() != null) {
                    cardExpiryMonth = tokensItem.getCardExpiryMonth();
                }

                if (tokensItem.getCardNumber() != null) {
                    cardNumber = tokensItem.getCardNumber();
                }

                if (tokensItem.getCreationDate() != null) {
                    creationDate = tokensItem.getCreationDate();
                }
                if (tokensItem.getToken() != null) {
                    cardToken = tokensItem.getToken();
                }

                if (tokensItem.getShopperReference() != null) {
                    shopper_reference = tokensItem.getShopperReference();
                }

                if (tokensItem.getMerchantId() != null) {
                    merchantId = tokensItem.getMerchantId();
                }

                _tokenItem.putString(KASHIER_TOKEN_ITEM.cardExpiryYear.toString(), cardExpiryYear);
                _tokenItem.putString(KASHIER_TOKEN_ITEM.cardExpiryMonth.toString(), cardExpiryMonth);
                _tokenItem.putString(KASHIER_TOKEN_ITEM.cardNumber.toString(), cardNumber);
                _tokenItem.putString(KASHIER_TOKEN_ITEM.creationDate.toString(), creationDate);
                _tokenItem.putString(KASHIER_TOKEN_ITEM.cardToken.toString(), cardToken);
                _tokenItem.putString(KASHIER_TOKEN_ITEM.shopper_reference.toString(), shopper_reference);
                _tokenItem.putString(KASHIER_TOKEN_ITEM.merchantId.toString(), merchantId);

                tokensListMap.pushMap(_tokenItem);
            }
        }
        return tokensListMap;
    }
}
