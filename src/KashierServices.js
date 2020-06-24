import { Platform, NativeModules } from "react-native";
import PropTypes from "prop-types";
const Kashier = NativeModules?.Kashier;

const listCards = (shopperReference, successCallback, errorCallback) => {
  return Platform.select({
    android: (() => {
      Kashier.listCards(
        shopperReference,
        succ => {
          successCallback(succ);
        },
        err => {
          errorCallback(err);
        }
      );
    })()
  });
};

const KashierServices = {
  listCards
};
export { KashierServices };
