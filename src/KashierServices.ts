import { NativeModules, Platform } from "react-native";
import { Card } from "Card";
import { KashierError } from "KashierError";

const Kashier = NativeModules?.Kashier;

const listCards = (
  shopperReference: string,
  successCallback: (result: any) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.listCards(
        shopperReference,
        (result: any): any => {
          successCallback(result);
        },
        (err: KashierError): any => {
          errorCallback(err);
        }
      );
    })()
  });
};

const saveCard = (
  cardData: Card,
  shopperReference: string,
  tokenValidity: string,
  successCallback: (result: any) => {},
  errorCallback: (result: any) => {}
) => {
  console.log("Card Data", cardData);
};

const KashierServices = {
  listCards
};
export { KashierServices };
