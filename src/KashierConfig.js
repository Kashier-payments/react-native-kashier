import { Platform, NativeModules } from "react-native";
import PropTypes from "prop-types";
const Kashier = NativeModules?.Kashier;

const getMerchantId = () => {
  return Platform.select({
    android: () => Kashier.getSdkMode()
  });
};
const getApiKey = () => {};
const getCurrency = async () => {
  return Platform.select({
    android: await Kashier.getCurrency()
  });
};

const getPaymentActivityCustomLayout = () => {};

const setPaymentActivityCustomLayout = () => {};

const getSdkMode = async () => {
  return Platform.select({
    android: await Kashier.getSdkMode(),
    ios: (async () => {
      return await Kashier.getSdkMode();
    })()
  });
};

const getDisplayLanguage = () => {};
const setDisplayLanguage = () => {};

const KashierConfig = {
  getMerchantId,
  getApiKey,
  getCurrency,
  getPaymentActivityCustomLayout,
  setPaymentActivityCustomLayout,
  getSdkMode,
  getDisplayLanguage,
  setDisplayLanguage
};
export { KashierConfig };
