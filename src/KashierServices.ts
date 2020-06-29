import {NativeModules} from "react-native";
import {listCards} from "./Services/ListCards/ListCardsService";
import {saveCard} from "./Services/SaveCard/SaveCardService";

const Kashier = NativeModules?.Kashier;

const KashierServices = {
  listCards,
  saveCard
};
export { KashierServices };
