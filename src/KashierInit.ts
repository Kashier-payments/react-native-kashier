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

const initialize = async (params: KashierInitParams): Promise<boolean> => {
    const {merchantId, apiKey, sdkMode, currency = "EGP", displayLang = "EN"} = params;
    return new Promise((resolve) => {
             Platform.select({
                ios: (async () => {
                    await Kashier.initialize(merchantId, apiKey, sdkMode, currency, displayLang)
                    resolve(true)
                })(),
                android: (async () => {
                    if (Kashier?.init && typeof Kashier.init === 'function') {
                        await Kashier.init(
                            merchantId,
                            apiKey,
                            currency,
                            sdkMode,
                            displayLang
                        )
                        resolve(true)
                    }
                })()
            });
        }
    )

};
const KashierInit = {
    initialize
};
export {KashierInit};
