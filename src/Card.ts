class Card {
  private cardHolderName: String;
  private cardNumber: String;
  private cardCcv: String;
  private cardExpiryDate: String | null;
  private cardExpiryMonth: String | null;
  private cardExpiryYear: String | null;
  private constructor(
    cardHolderName: String,
    cardNumber: String,
    cardCcv: String,
    cardExpiryDate: String | null = null,
    cardExpiryMonth: String | null = null,
    cardExpiryYear: String | null = null
  ) {
    this.cardHolderName = cardHolderName;
    this.cardNumber = cardNumber;
    this.cardCcv = cardCcv;
    this.cardExpiryDate = cardExpiryDate;
    this.cardExpiryMonth = cardExpiryMonth;
    this.cardExpiryYear = cardExpiryYear;
  }
  public static FromExpiryDate(
    cardHolderName: String,
    cardNumber: String,
    cardCcv: String,
    cardExpiryDate: String
  ) {
    return new Card(
      cardHolderName,
      cardNumber,
      cardCcv,
      cardExpiryDate,
      null,
      null
    );
  }
  public static FromExpiryMonth_Year(
    cardHolderName: String,
    cardNumber: String,
    cardCcv: String,
    cardExpiryMonth: String,
    cardExpiryYear: String
  ) {
    return new Card(
      cardHolderName,
      cardNumber,
      cardCcv,
      null,
      cardExpiryMonth,
      cardExpiryYear
    );
  }
}
export { Card };
