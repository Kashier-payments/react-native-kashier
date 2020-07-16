import { NativeModules, Platform } from "react-native";

const { KashierRCT } = NativeModules;


//Was used mainly in development to test the integration
const getSdkMode = async (): Promise<"DEVELOPMENT" | "PRODUCTION"> => {
  return Platform.select({
    android: await KashierRCT.getSdkMode(),
    ios: await KashierRCT.getSdkMode()
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
