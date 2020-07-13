import {Platform, NativeModules} from "react-native";

const Kashier = Platform.select({
    android: NativeModules?.Kashier,
    ios: NativeModules?.KashierRCT
});

interface KashierInitParams {
    merchantId: string;
    apiKey: string;
    currency: string;
    sdkMode: "DEVELOPMENT" | "PRODUCTION";
    displayLang: "EN" | "AR";
}

const initialize = async (params: KashierInitParams) => {
    const {merchantId, apiKey, sdkMode, currency = "EGP", displayLang = "EN"} = params;
    return Platform.select({
        ios: (() => {
            Kashier.initialize(merchantId, apiKey, sdkMode, currency, displayLang)
        })(),
        android: (() => {
            Kashier.init(
                merchantId,
                apiKey,
                currency,
                sdkMode,
                displayLang
            )
        })()
    });
};
const KashierInit = {
    initialize
};
export {KashierInit};
