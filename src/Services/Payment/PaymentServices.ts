import { Card } from "Model/Card";
import { KashierError } from "Model/KashierError";
import { NativeModules, Platform } from "react-native";

const Kashier = Platform.select({
  android: NativeModules?.Kashier,
  ios: NativeModules?.KashierRCT
});

const payUsingCard = (
  cardData: Card,
  shopperReference: string,
  orderId: string,
  amount: string | number,
  shouldSaveCard: boolean,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.payUsingCard(
        cardData,
        String(shopperReference),
        String(orderId),
        String(amount),
        Boolean(shouldSaveCard),
        (result: PaymentResponse): any => {
          successCallback(result);
        },
        (err: KashierError): any => {
          errorCallback(err);
        }
      );
    })()
  });
};

const payUsingPermToken = (
  shopperReference: string,
  orderId: string,
  amount: string,
  cardToken: string,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.payUsingPermToken(
        String(shopperReference),
        String(orderId),
        String(amount),
        cardToken,
        (result: PaymentResponse): any => {
          successCallback(result);
        },
        (err: KashierError): any => {
          errorCallback(err);
        }
      );
    })()
  });
};
const payUsingTempToken = (
  shopperReference: string,
  orderId: string,
  amount: string,
  cardToken: string,
  cvvToken: string,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.payUsingTempToken(
        String(shopperReference),
        String(orderId),
        String(amount),
        cardToken,
        cvvToken,
        (result: PaymentResponse): any => {
          successCallback(result);
        },
        (err: KashierError): any => {
          errorCallback(err);
        }
      );
    })()
  });
};

const payUsingPaymentForm = (
  shopperReference: string,
  orderId: string,
  amount: string | number,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  Platform.select({
    android: (() => {
      Kashier.payUsingPaymentForm(
        String(shopperReference),
        String(orderId),
        String(amount),
        (result: PaymentResponse): any => {
          successCallback(result);
        },
        (err: KashierError): any => {
          errorCallback(err);
        }
      );
    })()
  });
};

export {
  payUsingCard,
  payUsingPermToken,
  payUsingTempToken,
  payUsingPaymentForm
};
