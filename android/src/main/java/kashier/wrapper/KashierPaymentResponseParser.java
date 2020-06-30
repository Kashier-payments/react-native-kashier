package kashier.wrapper;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import io.kashier.sdk.Core.Helpers.ResponseHelper.ResponseHelper;
import io.kashier.sdk.Core.model.Response.I_GenericResponse.I_GenericResponse;
import io.kashier.sdk.Core.model.Response.I_GenericResponse.I_Messages;
import io.kashier.sdk.Core.model.Response.Payment.Card;
import io.kashier.sdk.Core.model.Response.Payment.Messages;
import io.kashier.sdk.Core.model.Response.Payment.PaymentResponse;
import retrofit2.Response;

public class KashierPaymentResponseParser {
    static private WritableMap getMessagesFromResponse(I_GenericResponse paymentResponse) {
        WritableMap _messagesMap = new WritableNativeMap();
        I_Messages _messages = null;
        if (paymentResponse != null) {
            if (paymentResponse.getMessages() != null) {
                _messages = paymentResponse.getMessages();
            }
            if (paymentResponse.getResponse() != null) {
                if (paymentResponse.getResponse().getMessages() != null) {
                    _messages = paymentResponse.getResponse().getMessages();
                }
                if (paymentResponse.getResponse().getMessage() != null
                        && paymentResponse.getResponse().getMessage().getMessages() != null) {
                    _messages = paymentResponse.getResponse().getMessage().getMessages();
                }
            }
        }
        if (_messages != null) {
            if (_messages.getAr() != null) {
                _messagesMap.putString("ar", _messages.getAr());
            }
            if (_messages.getEn() != null) {
                _messagesMap.putString("en", _messages.getEn());
            }
        }
        return _messagesMap;
    }

    static WritableMap parsePaymentResponse(Response<PaymentResponse> paymentResponse) {
        WritableMap _paymentResponseMap = new WritableNativeMap();
        if (paymentResponse.body() != null) {
            if (ResponseHelper.getResponseStatus(paymentResponse.body()) != null) {
                _paymentResponseMap.putString("status", ResponseHelper.getResponseStatus(paymentResponse.body()).toString());
            }
            _paymentResponseMap.putMap("messages", getMessagesFromResponse(paymentResponse.body()));

            if (paymentResponse.body().getResponse() != null) {
                io.kashier.sdk.Core.model.Response.Payment.Response _response = paymentResponse.body().getResponse();
                WritableMap _payment_response = new WritableNativeMap();
                if (_response.getMethod() != null) {
                    _payment_response.putString("method", _response.getMethod());
                }
                if (_response.getOrderId() != null) {
                    _payment_response.putString("orderId", _response.getOrderId());
                }
                if (_response.getApiKeyId() != null) {
                    _payment_response.putString("apiKeyId", _response.getApiKeyId());
                }
                if (_response.getMerchantOrderId() != null) {
                    _payment_response.putString("merchantOrderId", _response.getMerchantOrderId());
                }
                if (_response.getCreationDate() != null) {
                    _payment_response.putString("creationDate", _response.getCreationDate());
                }
                if (_response.getTransactionId() != null) {
                    _payment_response.putString("transactionId", _response.getTransactionId());
                }
                if (_response.getProviderType() != null) {
                    _payment_response.putString("providerType", _response.getProviderType());
                }
                if (_response.getMerchantId() != null) {
                    _payment_response.putString("merchantId", _response.getMerchantId());
                }
                if (_response.getOrderReference() != null) {
                    _payment_response.putString("orderReference", _response.getOrderReference());
                }
                if (_response.getRefId() != null) {
                    _payment_response.putString("refId", _response.getRefId());
                }
                if (_response.getStatus() != null) {
                    _payment_response.putString("status", _response.getStatus());
                }

                if (_response.getError() != null) {
                    WritableMap _error = new WritableNativeMap();
                    if (_response.getError().getCause() != null) {
                        _error.putString("cause", _response.getError().getCause());
                    }
                    if (_response.getError().getExplanation() != null) {
                        _error.putString("explanation", _response.getError().getExplanation());
                    }
                    _payment_response.putMap("error", _error);
                }

                if (_response.getCard() != null) {
                    Card _responseCard = _response.getCard();
                    WritableMap _responseCardMap = new WritableNativeMap();



                    _payment_response.putMap("card", _responseCardMap);

                }

                _paymentResponseMap.putMap("response", _payment_response);
            }


        }
        return _paymentResponseMap;
    }
}
