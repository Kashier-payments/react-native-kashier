import { NativeModules, Platform } from "react-native";
import { Card } from "./Card";

const Kashier = NativeModules?.Kashier;

const listCards = (
  shopperReference: string,
  successCallback: (result: any) => {},
  errorCallback: (error: any) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.listCards(
        shopperReference,
        (result: any): any => {
          successCallback(result);
        },
        (err: any): any => {
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
