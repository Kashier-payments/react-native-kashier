import { Platform, NativeModules } from "react-native";
const Kashier = NativeModules?.Kashier;

interface KashierInitParams {
  merchantId: string;
  apiKey: string;
  currency: string;
  sdkMode: "DEVELOPMENT" | "PRODUCTION";
  displayLang: "EN" | "AR";
}

const initialize = async (params: KashierInitParams): Promise<any> => {
  const { merchantId, apiKey, currency, sdkMode, displayLang } = params;
  return Platform.select({
    android: await Kashier.init(
      merchantId,
      apiKey,
      currency,
      sdkMode,
      displayLang
    ),
    ios: (async () => {
      return await Kashier.getSdkMode();
    })()
  });
};
const KashierInit = {
  initialize
};
export { KashierInit };
