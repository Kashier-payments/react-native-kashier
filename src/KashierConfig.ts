import { NativeModules, Platform } from "react-native";

const Kashier = Platform.select({
  android: NativeModules?.Kashier,
  ios: NativeModules?.KashierRCT
});

//Was used mainly in development to test the integration
const getSdkMode = async (): Promise<"DEVELOPMENT" | "PRODUCTION"> => {
  return Platform.select({
    android: await Kashier.getSdkMode(),
    ios: await Kashier.getSdkMode()
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
