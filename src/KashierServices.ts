import {NativeModules} from "react-native";
import {listCards} from "./Services/ListCards/ListCardsService";
import {saveCard} from "./Services/SaveCard/SaveCardService";
import {
  payUsingCard,
  payUsingPaymentForm,
  payUsingPermToken,
  payUsingTempToken
} from "./Services/Payment/PaymentServices";

const Kashier = NativeModules?.Kashier;

const KashierServices = {
  listCards,
  saveCard,
  payUsingCard,
  payUsingPermToken,
  payUsingTempToken,
  payUsingPaymentForm
};
export { KashierServices };
