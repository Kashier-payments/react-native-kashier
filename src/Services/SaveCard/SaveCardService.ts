import { KashierError } from "Model/KashierError";
import { NativeModules, Platform } from "react-native";
import { Card } from "../../Model/Card";
import { SaveCardResponse } from "Services/SaveCard/SaveCardResponse";
const Kashier = NativeModules?.Kashier;
type tokenValidity = "temp" | "perm";

const saveCard = (
  cardData: Card,
  shopperReference: string,
  tokenValidity: tokenValidity,
  successCallback: (result: SaveCardResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.saveCard(
        cardData,
        shopperReference,
        tokenValidity,
        (result: SaveCardResponse): any => {
          successCallback(result);
        },
        (error: KashierError): any => {
          errorCallback(error);
        }
      );
    })()
  });
};
export { saveCard };
