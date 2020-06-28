import { NativeModules } from "react-native";
import { Card } from "Model/Card";
import { listCards } from "./Services/ListCards/ListCardsService";

const Kashier = NativeModules?.Kashier;

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
