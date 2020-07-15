import {KashierError} from "Model/KashierError";
import {NativeModules, Platform} from "react-native";
import {ListCardsResponse} from "Services/ListCards/ListCardsResponse";

const Kashier = Platform.select({
    android: NativeModules?.Kashier,
    ios: NativeModules?.KashierRCT
});

const listCards = (
    shopperReference: string,
    successCallback: (result: ListCardsResponse) => {},
    errorCallback: (error: KashierError) => {}
) => {
    // @ts-ignore
    (Platform.select({
        android: () => {
            Kashier.listCards(
                shopperReference,
                (result: ListCardsResponse): any => {
                    successCallback(result);
                },
                (err: KashierError): any => {
                    errorCallback(err);
                }
            );
        },
        ios: () => {
            Kashier.listCards(
                shopperReference,
                (error:KashierError,success:ListCardsResponse): any => {
                    if(error){
                        errorCallback(error);
                    }else{
                        successCallback(success)
                    }
                },
            );
        }
    }))();
};
export {listCards};
