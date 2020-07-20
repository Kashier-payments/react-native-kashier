import { KashierResponseStatus } from "Model/KashierError";

interface Error {
  cause: string;
  explanation: string;
}
interface Messages {
  ar: string;
  en: string;
}

interface CardInfo {
  cardDataToken: string;
  cardHash: string;
  maskedCard: string;
  cardHolderName: string;
  expiryMonth: string;
  expiryYear: string;
  cardBrand: string;
  ccvToken: string;
}
interface Merchant {
  merchantRedirectUrl: string;
}
interface DSecure {
  acsUrl: string;
  processACSRedirectURL: string;
  requestType: string;
  paReq: string;
}
interface card {
  result: string;
  currency: string;
  amount: number;
  cardInfo: CardInfo;
  merchant: Merchant;
  DSecure: DSecure;
}
interface _response {
  method: string;
  orderId: string;
  apiKeyId: string;
  merchantOrderId: string;
  creationDate: string;
  transactionId: string;
  providerType: string;
  merchantId: string;
  orderReference: string;
  refId: string;
  status: string;
  error: string;
  card: card;
}
interface PaymentResponse {
  response: _response;
  messages: Messages;
  status: KashierResponseStatus;
}

export { PaymentResponse };
