package kashier.wrapper;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import io.kashier.sdk.Core.model.Card.Card;
import io.kashier.sdk.Core.model.Response.Error.ERROR_TYPE;
import io.kashier.sdk.Core.model.Response.Error.ErrorData;
import io.kashier.sdk.Core.model.Response.Tokenization.TokenizationResponse;

class KashierCardParser {
    static Card parseCardData(ReadableMap cardData) throws Exception {
        Card _cardData;

        String cardHolderName = "";
        String cardNumber = "";
        String cardCcv = "";
        String cardExpiryDate = "";
        String cardExpiryMonth = "";
        String cardExpiryYear = "";

        if (cardData.hasKey("cardHolderName") && cardData.getString("cardHolderName") != null) {
            cardHolderName = cardData.getString("cardHolderName");
        }
        if (cardData.hasKey("cardNumber") && cardData.getString("cardNumber") != null) {
            cardNumber = cardData.getString("cardNumber");
        }
        if (cardData.hasKey("cardCcv") && cardData.getString("cardCcv") != null) {
            cardCcv = cardData.getString("cardCcv");
        }
        if (cardData.hasKey("cardExpiryDate") && cardData.getString("cardExpiryDate") != null) {
            cardExpiryDate = cardData.getString("cardExpiryDate");
        }
        if (cardData.hasKey("cardExpiryMonth") && cardData.getString("cardExpiryMonth") != null) {
            cardExpiryMonth = cardData.getString("cardExpiryMonth");
        }
        if (cardData.hasKey("cardExpiryYear") && cardData.getString("cardExpiryYear") != null) {
            cardExpiryYear = cardData.getString("cardExpiryYear");
        }

        if (cardExpiryDate != null && !cardExpiryDate.equals("")) {
            _cardData = new Card(cardHolderName, cardNumber, cardCcv, cardExpiryDate);
        } else if (cardExpiryMonth != null && !cardExpiryMonth.equals("")
                && cardExpiryYear != null && !cardExpiryYear.equals("")) {
            _cardData = new Card(cardHolderName, cardNumber, cardCcv, cardExpiryMonth, cardExpiryYear);
        } else {
            throw new Exception("Invalid or missing Card Date!!");
        }

        return _cardData;
    }

    static WritableMap handleParsingError(ReadableMap cardData) {
        ErrorData cardParsingError = new ErrorData()
                .setErrorType(ERROR_TYPE.EXCEPTION)
                .setErrorMessage("Failed to parse card data, Make sure card expiry date or Month/Year are correct")
                .setExceptionErrorMessage("Current Card Data: ", cardData.toString());
        return KashierErrorDataParser.parseError(cardParsingError);
    }
}
