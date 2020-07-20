interface SaveCardResponse {
  cardToken: string;
  ccvToken: string | null;
  cardHolderName: string;
  maskedCard: string;
  cardExpiryMonth: string;
  cardExpiryYear: string;
}
export { SaveCardResponse };
