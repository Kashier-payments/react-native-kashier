import {listCards} from "./Services/ListCards/ListCardsService";
import {saveCard} from "./Services/SaveCard/SaveCardService";
import {
  payUsingCard,
  payUsingPaymentForm,
  payUsingPermToken,
  payUsingTempToken,
  setConnectedAccount
} from "./Services/Payment/PaymentServices";

const KashierServices = {
  listCards,
  saveCard,
  payUsingCard,
  payUsingPermToken,
  payUsingTempToken,
  payUsingPaymentForm,
  setConnectedAccount
};
export { KashierServices };
