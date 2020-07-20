import { Platform, NativeModules } from "react-native";

const { KashierRCT } = NativeModules;

interface KashierInitParams {
  merchantId: string;
  apiKey: string;
  currency: string;
  sdkMode: "DEVELOPMENT" | "PRODUCTION";
  displayLang: "EN" | "AR";
}

const initialize = async (params: KashierInitParams): Promise<boolean> => {
  const {
    merchantId,
    apiKey,
    sdkMode,
    currency = "EGP",
    displayLang = "EN"
  } = params;
  return new Promise(resolve => {
    // @ts-ignore
    Platform.select({
      ios: async () => {
        await KashierRCT.initialize(
          merchantId,
          apiKey,
          sdkMode,
          currency,
          displayLang
        );
        resolve(true);
      },
      android: async () => {
        await KashierRCT.init(
          merchantId,
          apiKey,
          currency,
          sdkMode,
          displayLang
        );
        resolve(true);
      }
    })();
  });
};
const KashierInit = {
  initialize
};
export { KashierInit };
