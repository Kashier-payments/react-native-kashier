import {KashierError} from "Model/KashierError";
import {NativeModules, Platform} from "react-native";
import {Card} from "../../Model/Card";
import {SaveCardResponse} from "Services/SaveCard/SaveCardResponse";

const { KashierRCT } = NativeModules;

type tokenValidity = "temp" | "perm";

const saveCard = (
    cardData: Card,
    shopperReference: string,
    tokenValidity: tokenValidity,
    successCallback: (result: SaveCardResponse) => {},
    errorCallback: (error: KashierError) => {}
) => {
    // @ts-ignore
    (Platform.select({
        android: (() => {
            KashierRCT.saveCard(
                cardData,
                shopperReference,
                tokenValidity,
                (result: SaveCardResponse): any => {
                    successCallback(result);
                },
                (error: KashierError): any => {
                    errorCallback(error);
                }
            );
        }),
        ios: (() => {
            KashierRCT.saveCard(
                cardData,
                shopperReference,
                tokenValidity,
                (error: KashierError, result: SaveCardResponse): any => {
                    if (error) {
                        errorCallback(error);
                    } else {
                        successCallback(result)
                    }
                }
            );
        })
    }))()
};
export {saveCard};
