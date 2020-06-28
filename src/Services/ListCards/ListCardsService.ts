import { KashierError } from "Model/KashierError";
import { NativeModules, Platform } from "react-native";
import { ListCardsResponse } from "Services/ListCards/ListCardsResponse";
const Kashier = NativeModules?.Kashier;

const listCards = (
  shopperReference: string,
  successCallback: (result: ListCardsResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.listCards(
        shopperReference,
        (result: ListCardsResponse): any => {
          successCallback(result);
        },
        (err: KashierError): any => {
          errorCallback(err);
        }
      );
    })()
  });
};
export { listCards };
