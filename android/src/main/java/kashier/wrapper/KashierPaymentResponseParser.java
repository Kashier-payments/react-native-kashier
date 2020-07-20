package kashier.wrapper;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import io.kashier.sdk.Core.Helpers.ResponseHelper.ResponseHelper;
import io.kashier.sdk.Core.model.Response.I_GenericResponse.I_GenericResponse;
import io.kashier.sdk.Core.model.Response.I_GenericResponse.I_Messages;
import io.kashier.sdk.Core.model.Response.Payment.Card;
import io.kashier.sdk.Core.model.Response.Payment.CardInfo;
import io.kashier.sdk.Core.model.Response.Payment.DSecure;
import io.kashier.sdk.Core.model.Response.Payment.Merchant;
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


                    if (_responseCard.getResult() != null) {
                        _responseCardMap.putString("result", _responseCard.getResult());
                    }
                    if (_responseCard.getCurrency() != null) {
                        _responseCardMap.putString("currency", _responseCard.getCurrency());
                    }
                    _responseCardMap.putDouble("amount", _responseCard.getAmount());
                    if (_responseCard.getCardInfo() != null) {
                        WritableMap _cardInfoMap = new WritableNativeMap();
                        CardInfo _cardInfo = _responseCard.getCardInfo();
                        if (_cardInfo.getCardDataToken() != null) {
                            _cardInfoMap.putString("cardDataToken", _cardInfo.getCardDataToken());
                        }
                        if (_cardInfo.getCardHash() != null) {
                            _cardInfoMap.putString("cardHash", _cardInfo.getCardHash());
                        }
                        if (_cardInfo.getMaskedCard() != null) {
                            _cardInfoMap.putString("maskedCard", _cardInfo.getMaskedCard());
                        }
                        if (_cardInfo.getCardHolderName() != null) {
                            _cardInfoMap.putString("cardHolderName", _cardInfo.getCardHolderName());
                        }
                        if (_cardInfo.getExpiryMonth() != null) {
                            _cardInfoMap.putString("expiryMonth", _cardInfo.getExpiryMonth());
                        }
                        if (_cardInfo.getExpiryYear() != null) {
                            _cardInfoMap.putString("expiryYear", _cardInfo.getExpiryYear());
                        }
                        if (_cardInfo.getCardBrand() != null) {
                            _cardInfoMap.putString("cardBrand", _cardInfo.getCardBrand());
                        }
                        if (_cardInfo.getCcvToken() != null) {
                            _cardInfoMap.putString("ccvToken", _cardInfo.getCcvToken());
                        }
                        _responseCardMap.putMap("cardInfo", _cardInfoMap);
                    }
                    if (_responseCard.getMerchant() != null) {
                        WritableMap _merchantMap = new WritableNativeMap();
                        if (_responseCard.getMerchant().getMerchantRedirectUrl() != null) {
                            _merchantMap.putString("merchantRedirectUrl", _responseCard.getMerchant().getMerchantRedirectUrl());
                        }
                        _responseCardMap.putMap("merchant", _merchantMap);
                    }
                    if (_responseCard.getDSecure() != null) {
                        WritableMap _3dSecureMap = new WritableNativeMap();
                        DSecure _3DSecure = _responseCard.getDSecure();

                        if (_3DSecure.getAcsUrl() != null) {
                            _3dSecureMap.putString("acsUrl", _3DSecure.getAcsUrl());
                        }
                        if (_3DSecure.getProcessACSRedirectURL() != null) {
                            _3dSecureMap.putString("processACSRedirectURL", _3DSecure.getProcessACSRedirectURL());
                        }
                        if (_3DSecure.getRequestType() != null) {
                            _3dSecureMap.putString("requestType", _3DSecure.getRequestType());
                        }
                        if (_3DSecure.getPaReq() != null) {
                            _3dSecureMap.putString("paReq", _3DSecure.getPaReq());
                        }
                        _responseCardMap.putMap("DSecure", _3dSecureMap);
                    }

                    _payment_response.putMap("card", _responseCardMap);
                }

                _paymentResponseMap.putMap("response", _payment_response);
            }
        }
        return _paymentResponseMap;
    }
}
