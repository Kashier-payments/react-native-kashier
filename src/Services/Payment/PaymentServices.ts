import { Card } from "Model/Card";
import { KashierError } from "Model/KashierError";
import { NativeModules, Platform } from "react-native";
import { ConnectedAccount } from "Model/ConnectedAccounts";

const { KashierRCT } = NativeModules;


const setConnectedAccount = (
  connectedAccount: ConnectedAccount
) => {
  KashierRCT.setConnectedAccount(connectedAccount)
};

const payUsingCard = (
  cardData: Card,
  shopperReference: string,
  orderId: string,
  amount: string | number,
  shouldSaveCard: boolean,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  // @ts-ignore
  (Platform.select({
    android: (() => {
      KashierRCT.payUsingCard(
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
    }),
    ios: (() => {
      KashierRCT.payUsingCard(
        cardData,
        String(shopperReference),
        String(orderId),
        String(amount),
        Boolean(shouldSaveCard),
        (error: KashierError, result: PaymentResponse): any => {
          if (error) {
            errorCallback(error);
          } else {
            successCallback(result)
          }
        }
      );
    })
  }))();
};

const payUsingPermToken = (
  shopperReference: string,
  orderId: string,
  amount: string,
  cardToken: string,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  // @ts-ignore
  (Platform.select({
    android: (() => {
      KashierRCT.payUsingPermToken(
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
    }),
    ios: (() => {
      KashierRCT.payUsingPermToken(
        String(shopperReference),
        String(orderId),
        String(amount),
        cardToken,
        (error: KashierError, result: PaymentResponse): any => {
          if (error) {
            errorCallback(error);
          } else {
            successCallback(result)
          }
        }
      );
    })
  }))();
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
  // @ts-ignore
  (Platform.select({
    android: (() => {
      KashierRCT.payUsingTempToken(
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
    }),
    ios: (() => {
      KashierRCT.payUsingTempToken(
        String(shopperReference),
        String(orderId),
        String(amount),
        cardToken,
        cvvToken,
        (error: KashierError, result: PaymentResponse): any => {
          if (error) {
            errorCallback(error);
          } else {
            successCallback(result);
          }
        }
      );
    }),
  }))();
};

const payUsingPaymentForm = (
  shopperReference: string,
  orderId: string,
  amount: string | number,
  successCallback: (result: PaymentResponse) => {},
  errorCallback: (error: KashierError) => {}
) => {
  // @ts-ignore
  (Platform.select({
    android: (() => {
      KashierRCT.payUsingPaymentForm(
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
    }),
    ios: (() => {
      KashierRCT.payUsingPaymentForm(
        String(shopperReference),
        String(orderId),
        String(amount),
        (error: KashierError, result: PaymentResponse): any => {
          if (error) {
            errorCallback(error);
          } else {
            successCallback(result);
          }
        }
      );
    })
  }))();
};

export {
  payUsingCard,
  payUsingPermToken,
  payUsingTempToken,
  payUsingPaymentForm,
  setConnectedAccount
};
