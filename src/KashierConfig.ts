import { NativeModules, Platform } from "react-native";

const Kashier = NativeModules?.Kashier;

//Was used mainly in development to test the integration
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
  getSdkMode
  // getDisplayLanguage,
  // setDisplayLanguage
};
export { KashierConfig };
