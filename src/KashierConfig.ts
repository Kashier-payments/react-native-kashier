import { NativeModules, Platform } from "react-native";

const Kashier = NativeModules?.Kashier;

const getMerchantId = () => {};
const getApiKey = () => {};
const getCurrency = async (): Promise<string> => {
  return Platform.select({
    android: await Kashier.getCurrency()
  });
};

const getPaymentActivityCustomLayout = () => {};

const setPaymentActivityCustomLayout = () => {};

const getSdkMode = async (): Promise<"DEVELOPMENT" | "PRODUCTION"> => {
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
