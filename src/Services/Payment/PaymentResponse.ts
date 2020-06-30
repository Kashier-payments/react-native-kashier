import { KashierResponseStatus } from "Model/KashierError";

interface Error {
  cause: string;
  explanation: string;
}
interface Messages {
  ar: string;
  en: string;
}
interface _response{

}
interface PaymentResponse {
  response: _response;
  messages: Messages;
  status: KashierResponseStatus;
}

export { PaymentResponse };
