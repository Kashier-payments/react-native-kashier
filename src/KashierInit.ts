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

const initialize = async (params: KashierInitParams): Promise<any> => {
    const {merchantId, apiKey, currency, sdkMode, displayLang} = params;
    return Platform.select({
        ios: (() => {
            console.log('Inside Kashier.init iOS', Kashier)
            Kashier.sampleMethod("str", 3235, (callback: any) => {
                console.log('Callback', callback)
            })

        })(),
        android: await Kashier.init(
            merchantId,
            apiKey,
            currency,
            sdkMode,
            displayLang
        ),
    });
};
const KashierInit = {
    initialize
};
export {KashierInit};
