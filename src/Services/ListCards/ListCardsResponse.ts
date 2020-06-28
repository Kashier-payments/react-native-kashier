interface CardModel {
  cardExpiryYear: string;
  cardExpiryMonth: string;
  cardNumber: string;
  creationDate: string;
  cardToken: string;
  shopper_reference: string;
  merchantId: string;
}

type ListCardsResponse = CardModel[];
export type{ ListCardsResponse };
