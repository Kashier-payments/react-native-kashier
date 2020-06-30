interface Error {
  cause: string;
  explanation: string;
}
interface Messages {
  ar: string;
  en: string;
}
interface PaymentResponse {
  Response: any;
  Messages: Messages;
  error: Error;
}

export { PaymentResponse };
